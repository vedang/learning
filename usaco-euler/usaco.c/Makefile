# Makefile for usaco's C programs

OPTIMIZATION?=-O2
CFLAGS?= -std=c99 -pedantic $(OPTIMIZATION) -Wall

all: ride test

test:
	$(CC) -o test.o $(CFLAGS) test.c

ride:
	$(CC) -o ride.o $(CFLAGS) ride.c

clean:
	rm -rf *.o

check-syntax:
	gcc -o nul $(CFLAGS) -S ${CHK_SOURCES}
