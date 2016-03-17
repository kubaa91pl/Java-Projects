import time
from sorting_algorythms import *
from zad11_1 import randomlist

print
print "Sortujemy:"
for size in (10 ** 2, 10 ** 3, 10 ** 4, 10 ** 5, 10 ** 6):
    lista = randomlist(size)
    print "Elementow w liscie: "+ str(size)


    start = time.clock()
    bubblesort(lista, 0, size - 1)
    stop = time.clock()
    result = stop -start
    print "Czas dla bubblesort: "+ str(result)

    start = time.clock()
    quicksort(lista, 0, size - 1)
    stop = time.clock()
    result = stop - start
    print "Czas dla quicksort: "+ str(result)

    start = time.clock()
    shellsort(lista, 0, size - 1)
    stop = time.clock()
    result = stop-start
    print "Czas dla shellsort: "+ str(result)