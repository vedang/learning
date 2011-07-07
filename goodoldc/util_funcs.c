#include "util_funcs.h"


int ttySetCbreak(int fd, struct termios *prevTermios)
{
        struct termios t;

        if (tcgetattr(fd, &t) == -1)
                return -1;

        if (prevTermios != NULL)
                *prevTermios = t;

        t.c_lflag &= ~(ICANON | ECHO);
        t.c_lflag |= ISIG;

        t.c_iflag &= ~ICRNL;

        t.c_cc[VMIN] = 1;                   /* Character-at-a-time input */
        t.c_cc[VTIME] = 0;                  /* with blocking */

        if (tcsetattr(fd, TCSAFLUSH, &t) == -1)
                return -1;

        return 0;
}


int err_exit(const char *message)
{
        printf("\n %s", message);
        return -1;
}
