# Interesting Pythonism: If you aren't already listening on the socket,
# connection to the socket is refused.
# Moral of the story: run the consumer first.

from utils import receivefrom

if __name__ == '__main__':
    for r in receivefrom(("0.0.0.0", 4000)):
        print r
