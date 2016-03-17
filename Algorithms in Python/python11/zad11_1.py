import random

def randomlist(size):
    if size < 1:
        raise IndexError
    list = []
    licznik =0
    while (licznik != size):
        element = random.randint(0,size-1)
        if(not list.__contains__(element)):
            list.append(element)
            licznik+=1
    return list

def almostsorted(size):
    if size < 1:
        raise IndexError
    if size == 1 or size == 2 or size == 3:
        list = [5] #zeby chociaz 1 element na pewno byl nie w kolejosci pozniej
        licznik = 1
        while licznik != size:
            ele = random.randint(1,4)
            if(not list.__contains__(ele)):
                    list.append(ele)
                    licznik+=1
        return list

    pozycja1=1
    pozycja2=2
    list = [2] #zeby chociaz 1 element na pewno byl nie w kolejosci pozniej
    licznik = 1
    while True:
        while(True):
            ele1 = random.randint(pozycja1,pozycja2)
            if(not list.__contains__(ele1)):
                list.append(ele1)
                break
        while(True):
            ele2 = random.randint(pozycja1+2,pozycja2+2)
            if(not list.__contains__(ele2)):
                list.append(ele2)
                break
        if (pozycja2==size): return list
        if (pozycja2+1==size):
            while(True):
                ele = random.randint(pozycja1+2,pozycja2+2)
                if(not list.__contains__(ele)):
                    list.append(ele)
                    break
            return list
        pozycja1+=2
        pozycja2+=2

def almostsortedrev(size):
    if size<1:
        raise IndexError
    list = almostsorted(size)
    list.reverse()
    return list

def gausslist(size):
    if size < 1:
        raise IndexError
    list = []
    licznik =0
    while (licznik != size):
        element = random.gauss(0, 1)
        if(not list.__contains__(element)):
            list.append(element)
            licznik+=1
    return list


def fixedsize_and_maxlist(size, max):
    list=[]
    if size < 0 or max < 1 or size<=max:
        raise ValueError
    if size < max-1:
        raise ValueError
    for x in range(size):
        list.append(random.randint(0, max - 1))
    return list

print "Random"
print randomlist(11)
print "Almost sorted"
print almostsorted(11)
print "Almost sorted and reversed"
print almostsortedrev(11)
print "Gauss"
print gausslist(11)
print "k<N list"
print fixedsize_and_maxlist(11,4)