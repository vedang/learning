from utils import sendto


def fib(n):
    """ Generate n Fibonacci numbers.
    This can easily be extended to be an infinite sequence."""
    fib0 = 0
    fib1 = 1
    count = 2
    yield fib0
    yield fib1
    while count < n:
        i = fib0 + fib1
        yield i
        fib0 = fib1
        fib1 = i
        count += 1
    return


if __name__ == '__main__':
    fibs = fib(10)
    sendto(fibs, ("0.0.0.0", 4000))
