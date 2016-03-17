from random import random


def calc_pi(n=100):
    """Obliczanie liczby pi metoda Monte Carlo.
    n oznacza liczbe losowanych punktow."""


#random generuje liczby od 0.0 do 1.0
# wiec mozemy dla ulatwienia przyjac ze tyle wynosi bok kwadratu

    a = 1.0 #bok kwadratu
    r = a/2.0 #promien kola
    liczba_trafien = 0

    for i in range(0, n):
        x = random() #losujemy punkt w ktory trafiamy
        y = random()
        print("Trafilismy w punkt: ("+str(x)+" , "+str(y)+")")
        if((x*x) + (y*y)<=(r*r)):
            liczba_trafien+=1.0
    pi=(16*liczba_trafien)/n
    print("Liczba pi wynosi: "+str(pi))
    return pi


calc_pi(10000)