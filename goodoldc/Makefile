# Makefile

OPTIMIZATION?=-O2
CFLAGS?= -std=c99 -pedantic $(OPTIMIZATION) -Wall

clean:
	rm -rf *.o

check-syntax:
	gcc -o nul $(CFLAGS) -S ${CHK_SOURCES}
