/* A tiny program to play around with signals */
#include <stdio.h>
#include <sys/types.h>
#include <signal.h>
#include <unistd.h>
#include <fcntl.h>
#include <termios.h>
#include "util_funcs.h"

static volatile sig_atomic_t gotSigio = 0;


static void sigio_handler(int sig)
{
        gotSigio = 1;
}


int main()
{
        int fd = STDIN_FILENO;
        int flags = fcntl(fd, F_GETFL);
        char ch;
        int done, cnt, j;
        struct sigaction sa;
        struct termios orig_termios;

        /* handler for i/o possible signal */
        sigemptyset(&sa.sa_mask);
        sa.sa_flags = SA_RESTART;
        sa.sa_handler = sigio_handler;
        if (sigaction(SIGIO, &sa, NULL) == -1) {
                return err_exit("sigaction");
        }

        /* set owner, enable SIGIO signalling */
        if (fcntl(fd, F_SETOWN, getpid()) == -1) {
                return err_exit("fcntl(F_SETOWN)");
        }
        if (fcntl(fd, F_SETFL, flags | O_ASYNC | O_NONBLOCK) == -1) {
                return err_exit("fcntl(F_SETFL)");
        }

        /* Do processing over the terminal until we ask it to exit */
        /* Place terminal in cbreak mode */
        if (ttySetCbreak(fd, &orig_termios) == -1) {
                return err_exit("ttySetCbreak");
        }

        for (done = 0, cnt = 0; !done ; cnt++) {
                /* Slow main loop down a little */
                for (j = 0; j < 100000000; j++)
                        continue;
                if (gotSigio) {
                        /* Is input available? */
                        /* Read all available input until error
                           (probably EAGAIN)
                           or EOF (not actually possible in cbreak mode)
                           or a hash (#) character is read */
                        while (read(fd, &ch, 1) > 0 && !done) {
                                printf("cnt=%d; read %c\n", cnt, ch);
                                done = (ch == '#');
                        }
                        gotSigio = 0;
                }
        }
        /* Restore original terminal settings */
        if (tcsetattr(fd, TCSAFLUSH, &orig_termios) == -1)
                return err_exit("tcsetattr");
        return 0;
}
