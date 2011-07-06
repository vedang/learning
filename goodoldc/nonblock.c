/* A tiny program to play with non-blocking file descriptors and FD sets. */
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/time.h>
#include <sys/select.h>


int main()
{
        int fd = open("/home/vedang/masai.log", O_RDONLY);
        int flags = fcntl(fd, F_GETFL);
        fd_set readfds, writefds;

        printf("Intial flags on the file: %d\n", flags);

        flags |= O_NONBLOCK;
        fcntl(fd, F_SETFL, flags);
        flags = fcntl(fd, F_GETFL);
        printf("\n New flags, non-blocking ones: %d\n", flags);

        FD_ZERO(&readfds);
        FD_ZERO(&writefds);

        FD_SET(fd, &readfds);
        printf("\n FD_ISSET on the read set = %d\n", FD_ISSET(fd, &readfds));
        printf("\n FD_ISSET on the write set = %d\n", FD_ISSET(fd, &writefds));

        close(fd);
        return 0;
}
