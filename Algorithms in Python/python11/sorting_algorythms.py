# -*- coding: utf-8 -*-
def quicksort(L, left, right):
    if left >= right:
        return
    swap(L, left, int((left + right) / 2))  # element podziału
    last = left  # przesuń do L[left]
    for i in range(left + 1, right + 1):  # podział
        if L[i] < L[left]:
            last = last + 1
            swap(L, last, i)
    swap(L, left, last)  # odtwórz element podziału
    quicksort(L, left, last - 1)
    quicksort(L, last + 1, right)

def swap(L, i, k):
    L[i], L[k] = L[k], L[i]

def bubblesort(L, left, right):
    for i in range(left, right):
        for j in range(left, right):
            if L[j] > L[j+1]:
                swap(L, j+1, j)

def shellsort(L, left, right):
    # Ustalenie największego kroku z sekwencji:
    # 1, 4, 13, 40, 121, 364, 1093, 3280, 9841, ...
    h = 1
    while h <= (right-left) / 9:
        h = 3*h+1
    while h > 0:
        for i in range(left+h, right+1):
            j = i
            # Zamiast swap() mamy przesuniecia.
            item = L[i]
            while j >= left+h and item < L[j-h]:
                L[j] = L[j-h]
                j = j-h
            L[j] = item
        h = h / 3