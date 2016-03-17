# -*- coding: utf-8 -*-
# Problem ośmiu hetmanów.
# Wiersze i kolumny mają zakres od 0 do N-1.

def rysuj():
    for w in range(N):
        wiersz = ""
        for k in range(N):
            if x[k] == w:
                wiersz += "H  "
            else:
                wiersz += ".  "
        print(wiersz)


def dopuszczalny(w, k):
    global historia
    wynik = a[w] and b[w + k] and c[w - k]
    if wynik == False:
        return False
    else:
        pom = list(x)
        pom[k] = w
        if None in pom:
            return True
        else:  # sprawdzenie czy juz nie bylo takiego rozwiazanie
            if pom in historia:
                return False
            else:
                historia.append(pom)
                return True


def zapisz(w, k):
    x[k] = w
    a[w] = False
    b[w + k] = False
    c[w - k] = False


def wymaz(w, k):
    a[w] = True
    b[w + k] = True
    c[w - k] = True


def probuj(k):
    udany = False
    w = 0  # numery od 0 do N-1
    while (not udany) and (w < N):
        if dopuszczalny(w, k):
            zapisz(w, k)
            if k < (N - 1):
                udany = probuj(k + 1)
                if not udany:
                    wymaz(w, k)
            else:
                udany = True
        w = w + 1
    return udany

def Hetman(rozmiar):
    if rozmiar < 1:
        raise ValueError
    global N
    global a
    global b
    global c
    global x
    global historia

    N = rozmiar  # bok szachownicy i jednocześnie liczba hetmanów

    # x[i] to pozycja hetmana w kolumnie i
    x = N * [None]

    # a[j]==True to brak hetmana w wierszu j
    a = N * [True]

    # b[k]==True to brak hetmana na przekątnej k [/].
    # Suma wiersz+kolumna od 0 do (2N-2).
    b = (2 * N - 1) * [True]

    # c[k]==True to brak hetmana na przekątnej k [\].
    # Różnica wiersz-kolumna od (-N+1) do (N-1).
    c = (2 * N - 1) * [True]

    historia = []

    rozw = 0

    if rozmiar ==1:
        print "Rozmiar planszy: 1"
        print"Rozwiazanie 1"
        print "H"
        print
    if rozmiar ==2:
        print "Rozmiar planszy: 2"
        print"Roziwazanie 1"
        print ". H"
        print "H ."
        print
        print "Rozwiazanie 2"
        print "H ."
        print ". H"
        print
    if rozmiar ==3:
        print "Rozmiar 3:"
        print "Rozwiazanie 1"
        print "H . ."
        print ". H ."
        print ". . H"
        print
        print "Rozwiazanie 2"
        print ". . H"
        print ". H ."
        print "H . ."
        print
    else:
        while(True):
            a = N * [True]
            x = N * [None]
            b = (2 * N - 1) * [True]
            c = (2 * N - 1) * [True]
            if(probuj(0)):
                rozw +=1
                print
                print "Rozmiar planszy: " + str(N)
                print"Rozwiązanie " + str(rozw)
                rysuj()
            else: break

        if rozw ==0:
            print "Rozmiar planszy: " + str(N)
            print "Brak rozwiazan"
            print


print "Problem Hetmana"
for i in range(2,8):
    Hetman(i)