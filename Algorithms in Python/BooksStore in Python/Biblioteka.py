from difflib import SequenceMatcher as SM
import os.path

class Library:
    Books = []
    def __init__(self):
        self.Books =['Harry Potter i Komnata Tajemnic; 2000; J K Rowling',
                'Piotrus Pan; 1977; J.M. Barrie',
                'Thinking in Java; 2008; Bruce Eckel',
                'Harry Potter i Wiezien Azkabanu; 1998; J K Rowling']

    def Save(self, thelist):
        try:
            with open("Books.txt", 'w') as f:
                for s in thelist:
                    f.write(s + '\n')
        except IOError:
            print "\nOperacja Save sie nie powiodla!\n"


    def Load(self, thelist):
        if(os.path.isfile("Books.txt")):
            try:
                with open("Books.txt", "r") as ins:
                    while(len(thelist) != 0):
                        thelist.pop()
                    for line in ins:
                        thelist.append(line)
                print "\nNowy spis ksiazek z pliku Books.txt zostal zaladowany.\n"
            except IOError:
                print "\nOperacja Load sie nie powiodla\n"
        else:
            print"\nPlik Books.txt nie istnieje.\n"

    def DisplayBooks(self,thelist):
        index = 0
        print"-----------------------------"
        print("Spis ksiazek:")
        for element in thelist:
            index = index+1
            print (str(index)+". " +element)
        print "\n"


    def AddBook(self, thelist):
        print"-----------------------------"
        print "Dodawnia ksiazki..."
        title = raw_input("Podaj tytul ksiazki: ")
        year = raw_input("Podaj rok wydania: ")
        author = raw_input("Podaja autora ksiazki: ")
        rekord = title+"; "+year+"; "+author
        thelist. append(rekord)
        print("\nKsiazka zostala dodana.")

    def RemoveBook(self,thelist):
        print"-----------------------------"
        print "Usuwane ksiazki..."
        if(not len(thelist)==0):
            self.DisplayBooks(thelist)
            indexstr = raw_input("Podaj index ksiazki ktora chcesz usunac z listy: ")
            index = int(indexstr)
            if(index<1 or index>len(thelist)):
                print("Nie ma takiego indexu w spisie.")
                print("Sprobuj jeszcze raz.")
                return self.RemoveBook(thelist)
            thelist.pop(index-1)
            print "Ksiazka zostala usunieta."
        else:
            print("Spis ksiazek jest pusty.")



    def Search(self, thelist):
        print"-----------------------------"
        print "Przeszukiwanie katalogu..."
        expression = raw_input("Podaj: nazwe ksiazki lub / i nazwe autora: ")
        set_ratio = 0.3

        if(len(expression)<7):
            set_ratio=0.1

        results=[]
        result_ratios=[]
        i = 0

        for element in thelist:
            book = thelist[i]
            ratio = SM(None, expression, book).ratio()
            if(ratio >set_ratio):
                results.append(str(i)+". "+book)
                result_ratios.append(ratio)
            i=i+1
        keydict = dict(zip(results, result_ratios))
        results.sort(key=keydict.get, reverse=True)
        if not len(results)==0:
            i=0
            for elements in results:
                print results[i]
                i = i+1

        else:
            print "Nie znaleziono ksiazki."

    def Run(self):
        condition = True
        print "Witaj, w katalogu ksiazek!"
        print"-----------------------------"
        commands = "Wcisnij numer operacji ktora chcesz wykonac i nacisnij ENTER:\n" \
                   "1. Pokaz wszystkie ksiazki.\n" \
                   "2. Dodaj ksiazke.\n" \
                   "3. Usun ksiazke.\n" \
                   "4. Szukaj ksiazki.\n" \
                   "5. Save.\n" \
                   "6. Load.\n" \
                   "7. Wyswietl ponownie liste komend.\n" \
                   "8. Zakoncz prorgam.\n"

        print commands
        while(condition):
            print "Numer operacji: "
            decisionstr = raw_input()
            decision = int(decisionstr)
            if(not isinstance(decision, int)):
                "Wpisz jedna cyfre bez kropki."
            else:
                if(decision<1 or decision >8):
                    print "Nie ma takiej komendy!!!"
                else:
                    if(decision==1):
                        self.DisplayBooks(self.Books)
                    elif(decision==2):
                        self.AddBook(self.Books)
                    elif(decision==3):
                        self.RemoveBook(self.Books)
                    elif(decision==4):
                        self.Search(self.Books)
                    elif(decision==5):
                        self.Save(self.Books)
                        print "\nSpis ksiazek zapisany w pliku Books.txt.\n"
                    elif(decision==6):
                        self.Load(self.Books)
                    elif(decision==7):
                        print commands
                    elif(decision==8):
                        print "\nDo widzenia! :)\n"
                        condition = False;
                    print "Wpisz nr komendy i nacisnij ENTER. \n" \
                          "By wyswietlic liste komend ponownie wpisz 7."

def main():
    Biblioteka = Library()
    Biblioteka.Run()


if  __name__ =='__main__':main()