from timeit import Timer
from random import randint


def switch_if():
    value = randint(1, 10)
    if value == 1:
        return '1'
    elif value == 2:
        return '2'
    elif value == 3:
        return '3'
    elif value == 4:
        return '4'
    elif value == 5:
        return '5'
    elif value == 6:
        return '6'
    elif value == 7:
        return '7'
    elif value == 8:
        return '8'
    elif value == 9:
        return '9'
    else:
        return '10'


def switch_map():
    value = randint(1, 10)
    smap = {1: '1',
            2: '2',
            3: '3',
            4: '4',
            5: '5',
            6: '6',
            7: '7',
            8: '8',
            9: '9',
            10: '10'}
    return smap[value]


t = Timer(setup='from __main__ import switch_if', stmt='switch_if()')
print t.timeit()

t = Timer(setup='from __main__ import switch_map', stmt='switch_map()')
print t.timeit()
