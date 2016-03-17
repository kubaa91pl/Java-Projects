#Jakub Nizynski - zestaw zadan - cwiczenia 2
#------------------------------
#10.
print "Zadanie 10"
line = "\t GvR Lorem ipsum dolor sit amet, consectetur adipiscing elit, \n " \
       "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n " \
       "\t Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris \n" \
       "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in\n " \
       "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla z \n" \
       "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui \n" \
       "officia deserunt mollit anim id est laborum."

ilosc = len(line.split())
print ilosc
#------------------------------
#11.
print "Zadanie 11"
word = "\t Lorem ipsum dolor sit amet, consectetur adipiscing elit, \n " \
       "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n " \
       "\t Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris \n" \
       "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in\n " \
       "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla z \n" \
       "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui \n" \
       "officia deserunt mollit anim id est laborum."
List = list()
x2= ""
List= word.split()
for x in List:
    if(x == len(List)-1):
        x2= x2+ x
    else:
        x2= x2+ x+"_"
print x2
#------------------------------
#12.
print "Zadanie 12"
napis = "";
for i in line.upper().split():
    napis += i[0]
print napis
#------------------------------
#13.
print "Zadanie 13"
count = 0
for x in List:
    count = count + len(x)
print count
#------------------------------
#14.
print "Zadanie 14"
najdl ="";
for x in List:
    if(len(x)>= len(najdl)):
        najdl = x
dlugosc = len(najdl)

print "Najdluzszy wyraz to: "+ najdl
print dlugosc
#------------------------------
#15.
print "Zadanie 15"
L = [5,6, 35, 566, 244, 25, 14]
Lnapis = ""
for x in L:

    Lnapis = Lnapis + str(x)
print Lnapis
#------------------------------
#16.
print "Zadanie 16"
import re
pattern = "GvR"
replaced = re.sub(pattern, 'Guido van Rossum', line)
print replaced
#------------------------------
#17.
print "Zadanie 17"
List.sort
print List
#------------------------------
#18.
print "Zadanie 18"
Liczba = 2309482049280940909029000002492394238
Liczban = str(Liczba)
print(str(Liczban).count('0'))
#------------------------------
#19.
print "Zadanie 19"
for x in L:
    print(str(x).zfill(3))
#------------------------------