# Makefile for usaco's C programs
# fill in the source and executable fields when you want to use the file.


OPTIMIZATION?=-O2
CFLAGS?= -std=c99 -pedantic $(OPTIMIZATION) -Wall
CC=gcc
LDFLAGS=
SOURCES=
OBJECTS=$(SOURCES:.c=.o)
EXECUTABLE=

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

clean:
	rm -rf *.o
	rm -rf *.out

check-syntax:
	$(CC) -o nul $(CFLAGS) -S ${CHK_SOURCES}
