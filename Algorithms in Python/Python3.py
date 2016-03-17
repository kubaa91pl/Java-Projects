# Jakub Nizynski - zestaw zadan - cwiczenia 3
# ------------------------------
# 3.1
print "Zadanie 3.1"
# Pierwszy kod jest dobry

# Drugi kod poprawiony
for i in "qwerty":
    if ord(i) < 1000: print i

# Trzeci kod dziala
for i in "axby": print ord(i) if ord(i) < 100 else i
# ------------------------------
# 3.2
# L = L.sort()
# L nie byla wczesniej zdefiniowana
# Oprocz tego przypisanie jest niepotrzebne poniewaz metoda sort modyfikuje L

# x, y = 1, 2, 3
# o 1 wartosc za duzo przypisana

# X = 1, 2, 3 ; X[1] = 4
# krotka jest immutable

# X = [1, 2, 3] ; X[3] = 4
# na liscie nie ma takiego elementu

# X = "abc" ; X.append("d")
# String nie posiada metody append

# map(pow, range(8))
# kod jest poprawny
# ------------------------------
# 3.3
print("Zadanie 3.3")
liczby = range(0, 30)
for x in liczby:
    if not (x % 3) == 0:
        print(x)
# ------------------------------
# 3.4
print("Zadanie 3.4")
while 1:
    print("Podaj liczbe:... lub gdy chcesz zakonczyc 'stop'")
    x = input()
    if x =="stop":
        break
    else:
        try:
            y = float(x)
            print("%d, %d" % (y, y * y * y))
        except ValueError:
            print("Blad")
# ------------------------------
# 3.5
print("Zadanie 3.5")
x = input("Dlugosc miarki: ")
length = int(x)
print("|" + ("....|" * length))
for i in range(0, length + 1):
    j = (' ' * (5 - len(str(i))))
    print(i,j)
# ------------------------------
# 3.6
print("Zadanie 3.6")
height = int(input("Wysokosc: "))
length = int(input("Dlugosc: "))

for i in range(height):
    print("+---+" + ("---+" * (length - 1)))
    print("| |" + (" |" * (length - 1)))
# ------------------------------
# 3.8
print("Zadanie 3.8")
Sekwencja1 = [0, 1, 5, 11, 22, 30]
Sekwencja2 = [1, 2, 4, 6, 10, 18, 19, 100]
print("Elementy wystepujace bez powtorzen:")
print(list(set(Sekwencja1) & set(Sekwencja2)))
print("Lista wszystkich elementow")
print(list(set(Sekwencja1) | set(Sekwencja2)))
# ------------------------------
# 3.9
print("Zadanie 3.9")
L = [[],[4],(1,2),[3,4],(5,6,7)]
print(list(map(sum, L)))
# ------------------------------
# 3.10
print("Zadanie 3.10")
def roman2int(romanNumber):
    slownik = {'I':1, 'V':5, 'X':10, 'L':50, 'C':100, 'D':500,'M':1000}
    #przechowuje cyfry wraz z arabskimi odpowiednikami
    liczba=0
    Lc=[]
    for x in list(romanNumber):
        Lc.append(slownik[x]) #wczytanie liczby do zmiennej
    r = len(list(romanNumber))-1
    for x in range(r): #przegladanie po kolei cyfr liczby
        if Lc[x+1] > Lc[x]: #w zaleznosci czy cyfra sklada sie z jednego czy dwoch znakow jest dodawana lub odejmowana
            liczba = liczba -  Lc[x]
        else:
            liczba = liczba + Lc[x]
    return liczba

print(roman2int("LVI"))
#opis innego sposobu:
#funkcja moglaby iterowac po kolei po cyfrach i podmieniac je na arabskie odpowiedniki i
# dodajac  w odpowiednie miejsce w zaleznosci od dlugosci cyfry

#inny sposob:
#mozna sprobowac uzyc funkcji eval() by dodawac do siebie stringi oraz inty
# i w ten sposob konwertowac liczby rzymskie na arabskie

# ------------------------------