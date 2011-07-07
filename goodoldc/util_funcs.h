/* Some utility functions which are necessary to run programs
   but have nothing to do with the core logic I'm trying to learn. */

#include <stdio.h>
#include <termios.h>


int ttySetCbreak(int fd, struct termios *prevTermios);

int err_exit(const char *message);
