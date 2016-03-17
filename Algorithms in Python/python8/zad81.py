def solve1(a, b, c):
    """Rozwiazywanie rownania liniowego a x + b y + c = 0."""
    if(a==0 & b ==0):
        if(c==0):
            print("Rwonanie zawsze prawdziwe")
        else:
            print("Rownanie sprzeczne")
    elif(a==0):
        if(c==0):
            print("Rownanie jest prawdziwe dla kazdego x i dla y = 0")
        else:
            print("Rownanie ma postac y=-c/b")
            wynik = (-c)/b
            print("Rozwiazaniami rownania sa liczby dowolne x oraz y="+wynik)
    elif(b==0):
        if(c==0):
            print("Rownanie jest prawdziwe dla kazdego y i dla x = 0")
        else:
            print("Rownanie ma postac x=-c/b")
            wynik = (-c)/b
            print("Rozwiazaniami rownania sa liczby dowolne y oraz x="+wynik)
    else: # kiedy a b oraz c sa rozne od zera
        print("rozwiazaniami rownania sa: x=-(by+c)/a, oraz y = -(ax+c)/b. Rownanie ma nieskonczenie wiele rozwiazan")


solve1(1,2,4)
solve1(0,2,1)
solve1(4,2,6)
solve1(0,0,0)

#Sprawdzam po kolei od najskrajniejszych przyoadow:
#1)Gdy wszystkie a i b, oraz c beda rowne zero
#2)Gdy a lub beda rowe zero
#3)Gdy a i b sa roze od era







