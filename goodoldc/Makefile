# Makefile
# fill in the source and executable fields when you want to use the file.


OPTIMIZATION?=-O2
CFLAGS?= -std=gnu99 -pedantic $(OPTIMIZATION) -Wall
CC=gcc
LDFLAGS=
SOURCES=epoll.c \
        util_funcs.c
OBJECTS=$(SOURCES:.c=.o)
EXECUTABLE=epoll.out

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

clean:
	rm -rf *.o
	rm -rf *.out

check-syntax:
	$(CC) -o nul $(CFLAGS) -S ${CHK_SOURCES}
