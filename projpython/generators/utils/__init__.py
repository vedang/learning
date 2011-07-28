import pickle
import socket


def gen_pickle(source, protocol=pickle.HIGHEST_PROTOCOL):
    for item in source:
        yield pickle.dumps(item, protocol)
    return


def gen_unpickle(infile):
    while True:
        try:
            item = pickle.load(infile)
            yield item
        except EOFError:
            return


def sendto(source, addr):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(addr)
    for pitem in gen_pickle(source):
        s.sendall(pitem)
    s.close()
    return


def receivefrom(addr):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    s.bind(addr)
    s.listen(5)
    c, a = s.accept()
    for item in gen_unpickle(c.makefile()):
        yield item
    c.close()
    return
