/* a tiny program to play with epoll */
#include <stdio.h>
#include <sys/epoll.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>
#include "util_funcs.h"

#define MAX_BUF 1000
#define MAX_EVENTS 5


int main(int argc, char *argv[])
{
        int epfd = epoll_create(argc - 1);
        int fd, ready;
        int num_open_fds = argc - 1;
        struct epoll_event ev, evlist[MAX_EVENTS];
        char buf[MAX_BUF];

        if (epfd == -1) {
                return err_exit("epoll_create");
        }

        for (int i = 1; i < argc; i++) {
                printf("\ninside open for");
                if ((fd = open(argv[i], O_RDONLY)) == -1) {
                        return err_exit("open");
                }
                printf("\n Opened %s on fd %d\n", argv[i], fd);
                ev.data.fd = fd;
                ev.events = EPOLLIN;

                if (epoll_ctl(epfd, EPOLL_CTL_ADD, fd, &ev) == -1) {
                        return err_exit("epoll_ctl");
                }
        }

        while (num_open_fds > 0) {
                printf("\nAbout to epoll_wait()\n");
                ready = epoll_wait(epfd, evlist, MAX_EVENTS, -1);
                if (ready == -1) {
                        if (errno == EINTR) {
                                continue;
                        } else {
                                return err_exit("epoll_wait");
                        }
                }
                printf("\nReady: %d\n", ready);
                for (int i = 0; i < ready; i++) {
                        printf("\n fd = %d, events = %s%s%s\n",
                               evlist[i].data.fd,
                               (evlist[i].events & EPOLLIN) ? "EPOLLIN" : "",
                               (evlist[i].events & EPOLLERR) ? "EPOLLERR" : "",
                               (evlist[i].events & EPOLLHUP) ? "EPOLLHUP" : "");
                        if (evlist[i].events & EPOLLIN) {
                                int j = read(evlist[i].data.fd, buf, MAX_BUF);
                                if (j == -1) {
                                    return err_exit("read");
                                }
                                printf("\nRead %d bytes: %s\n", j, buf);
                        } else if (evlist[i].events & (EPOLLHUP | EPOLLERR)) {
                                printf("\n closing fd %d\n", evlist[i].data.fd);
                                close(evlist[i].data.fd);
                                num_open_fds--;
                        }
                }
        }
        printf("\n All fds closed, bbye");
        close(epfd);
        return 0;
}
