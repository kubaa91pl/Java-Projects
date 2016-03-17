def mediana_sort(L, left, right):
    if (left>right or left> len(L) or right > len(L)):
        raise ValueError
    L.sort
    mid = (left + right) /2
    return L[mid]