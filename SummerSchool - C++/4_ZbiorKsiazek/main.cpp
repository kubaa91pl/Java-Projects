#include <iostream>
#include <regex>
#include <fstream>
#include <vector>
#include <locale>
#include <stack>
#include <sstream>

using namespace std;


void showCommands();
int getCommand(int numberOfCommands);
bool checkCommand(string choice);
void executeCommand(int number);
void printBookInfo();
void printBookList();
vector<string> SearchBook(bool moreInfo);
void printBookSeries();

int number;
const string plik = "ksiazki.txt";

vector<string> arr;
vector<string> allBooks;
string line;
int howManyBooks = 0;
int descriptors = 5;
int numberOfCommands = 5;

//----------------------------

int main()
{
    ifstream myFile(plik.c_str());
    if(myFile.fail()){
       cout<<"Nie udalo sie wczytac zbioru ksiazek... :(";
       return 0;
    }

    while(!myFile.eof()){
            getline(myFile, line, '\n');
            arr.push_back(line);
            howManyBooks++;
        }


    for(int i=0; i<howManyBooks; i++){
            line = arr.at(i);
            stringstream ss(line);

                while(getline(ss, line, '\t')){
                    allBooks.push_back(line);
                }

    }
    cout << "Witaj w zbiorze ksiazek!" << endl;

    while(true){
        cout<<endl<<"--------------------------"<<endl;
        showCommands();
        number = getCommand(numberOfCommands);
        executeCommand(number);
    }


    return 0;
}


//----------------------------

void showCommands(){
    cout << endl;
    cout << "1. Wypisz informacje o danej ksiazce"<<endl;
    cout << "2. Wypisz zbior ksiazek"<<endl;
    cout << "3. Wyszukaj ksiazke po autorze lub tytule"<<endl;
    cout << "4. Wypisz serie do jakiej nalezy ksiazka"<<endl;
    cout << "5. Zakoncz program"<<endl;
    cout << endl<< "Wpisz nr komendy: ";
};

bool checkCommand(string choice){
    std::regex regex_pattern("[0-9]", std::regex_constants::extended);

    if(std::regex_match(choice,regex_pattern)){
      return true;
    }
    else{
        return false;
    }
};

int getCommand(int numberOfCommands){

    string choice;
    cin.clear();
    fflush(stdin);
    cin >> choice;

    if( checkCommand(choice) ) {
            int number = atoi( choice.c_str() );
            if( number >=1 && number <=numberOfCommands ){
                    return number;
            }
            else{
                cout << "Nie ma takiej komendy";
                return getCommand(numberOfCommands);
            }
    }
    else {
        cout << "Nie ma takiej komendy"<<endl;
        return getCommand(numberOfCommands);
    }

};

void executeCommand(int number){
    switch(number){
        case 1: printBookInfo();
        break;

        case 2: printBookList();
        break;

        case 3: SearchBook(false);
        break;

        case 4: printBookSeries();
        break;

        case 5:
            {
            cout <<"Do widzenia!";
            exit(0);
            }

    }
};

void printBookInfo(){
    vector <string> books = SearchBook(true);

    cout<< endl << "Podaj nr ksiazki dla ktorej wyswietlic informacje: ";

    int choice = getCommand(books.size());

    cout << endl;
    cout << "- Index: " << books[choice-1 + (5*(choice-1) )] <<endl;
    cout << "- Autor: " << books[choice + (5*(choice-1) )] <<endl;
    cout << "- Tytul: " << books[choice+1 + (5*(choice-1) )] <<endl;
    cout << "- Index nastepnej czesci: " << books[choice+2 + (5*(choice-1) )] <<endl;
    cout << "- Index poprzedniej czesci: " << books[choice+3 + (5*(choice-1) )] <<endl;
    cout << endl;

};
void printBookList(){
    cout <<endl<<"BookList"<<endl<<"----------"<<endl;

    int counter = 0;
    int bookNumber = 1;

    for(int i = 0; i<allBooks.size(); i++){

        if(counter==1){
            cout<< bookNumber<<". "<<allBooks.at(i)<<": ";
        }
        else if(counter ==2){
            cout<< allBooks.at(i)<<endl;
            bookNumber++;
            }
        ++counter;
        if(counter == descriptors){
            counter =0;
        }
    }

};

vector<string> SearchBook(bool moreInfo){
    string phrase;
    string line;
    cout << "Wpisz autora lub tytul:";
    cin >> phrase;
    cin.clear();
    cout << endl;

    vector<string> results;

    int counter = 0;
    int bookNumber = 1;

    for(int i = 0; i<allBooks.size(); i++){

        if(counter==1){
            if(allBooks.at(i).find(phrase)!=string::npos){

                cout<< bookNumber<< " "<< allBooks[i] <<" "<< allBooks[i+1] << endl;
                bookNumber++;

                if(moreInfo==true){
                        results.push_back(allBooks.at(i-1));
                        results.push_back(allBooks.at(i));
                        results.push_back(allBooks.at(i+1));
                        results.push_back(allBooks.at(i+2));
                        results.push_back(allBooks.at(i+3));
                }
            }
        }
        else if(counter ==2){

            if(allBooks.at(i).find(phrase)!=string::npos){
                cout<< bookNumber<< " " << allBooks[i-1] <<" "<< allBooks[i] <<endl;
                bookNumber++;

                if(moreInfo==true){
                    results.push_back(allBooks.at(i-2));
                    results.push_back(allBooks.at(i-1));
                    results.push_back(allBooks.at(i));
                    results.push_back(allBooks.at(i+1));
                    results.push_back(allBooks.at(i+2));
                }
            }
        }
        ++counter;
        if(counter == descriptors){
            counter =0;
        }
    }
    return results;
};
//CUDAAA!!!!!!
void printBookSeries(){


    vector <string> books = SearchBook(true);

    cout << endl<<"@@@ Nie wszystkie przypadki zaimplementowane" << endl<< "- Moze dzialac niepoprawnie:(";
    cout<< endl << "Podaj nr ksiazki dla ktorej sprawdzi serie: ";

    int choice = getCommand(books.size());
    cout << endl;
    if(books[choice+2 + (5*(choice-1) )] == "0" &&
       books[choice+3 + (5*(choice-1) )] == "0" ){
           cout << "Tylko jedna ksiazka w tej serii" << endl;
           }

    cout << endl;
    if(books[choice+2 + (5*(choice-1) )]!="0" &&
            books[choice+3 + (5*(choice-1) )] == "0"){

             int nr = 1;
             cout << nr <<". "<<books[choice + (5*(choice-1) )] << " " << books[choice+1 + (5*(choice-1) )] <<  endl;

            string nextBook = books[choice+2 + (5*(choice-1) )].c_str();

            //cout << "next book: "<< nextBook << endl;


            int counter=-1;

            for(int i=0; i<allBooks.size(); i=i+5){
                if(allBooks[i]==nextBook){
                    nr++;
                    //cout << allBooks[i]<<endl;
                    cout<< nr <<". "<<allBooks[i+1]<<" "<<allBooks[i+2]<<endl;
                    nextBook = allBooks[i+3];
                }
                if(i==allBooks.size()-1){
                    if(allBooks[i+3]!="0"){
                        i = 0;
                    }
                }
            }
        }
    /*cout << "OKKK   qqqq";
    cout << endl;
    if(books[choice+2 + (5*(choice-1) )]=="0" &&
            books[choice+3 + (5*(choice-1) )] != "0"){
                //stack<string> sStack;

                //sStack.push(books[choice + (5*(choice-1) )]);
                //sStack.push(books[choice+1 + (5*(choice-1) )]);
*/
                //string nextBook = books[choice+3 + (5*(choice-1) )].c_str();
                /*
                for(int i=0; i<allBooks.size(); i=i+5){
                        if(allBooks[i]==nextBook){
                            nextBook = allBooks[i+4];
                            sStack.push(allBooks[i+2]);
                            sStack.push(allBooks[i+1]);
                    }
                    if(i==allBooks.size()-1){
                            if(allBooks[i+4]!="0"){
                                i = 0;
                                }
                        }
                }
                for(unsigned int i=0; i< sStack.size(); i++){
                    cout << sStack.top();
                    sStack.pop();
                    cout <<" "<<sStack.top()<<endl;
                    sStack.pop();
                }

                */
               // cout<<"ok"<<endl;
        //}

    else{
        //ksiazka jest w srodku serii.
        //cofnij sie do poczatku zapisujac na stos, potem zdejmuj.
        //potem idz znowu do danej ksiazki i wypisuj jej nastepniki
        cout << "srodek"<<endl;
    }

    //cout << "- Index: " << books[choice-1 + (5*(choice-1) )] <<endl;
    //cout << "- Autor: " << books[choice + (5*(choice-1) )] <<endl;
    //cout << "- Tytul: " << books[choice+1 + (5*(choice-1) )] <<endl;
    //cout << "- Index natepnej czesci: " << books[choice+2 + (5*(choice-1) )] << endl;
    //cout << "- Index poprzedniej czesci: " << books[choice+3 + (5*(choice-1) )] <<endl;
    cout << endl;

};
