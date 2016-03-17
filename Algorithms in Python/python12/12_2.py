def binarne_rek(L, left, right, y):
    if (left>right or left> len(L) or right > len(L)):
        raise ValueError
    lewo = left
    prawo = right
    szukana = y
    """Wyszukiwanie binarne w wersji rekurencyjnej."""
    if lewo <= prawo:
        k = (lewo + prawo)/2
        if y == L[k]:
            return k
        if y > L[k]:
            lewo = k+1
            binarne_rek(L, lewo, prawo, szukana)
        else:
            prawo = k-1
            binarne_rek(L, lewo, prawo, szukana)
    print "Nie znaleziono elementu"
    return None

L = [2,6,7,8,9,10,12,16]
print binarne_rek(L, 2, 6, 9)
