#include <iostream>
#include <c++/fstream>
#include <sstream>
#include "Napis.h"
#define ENOUGH 10000

using namespace std;

int main() {
    char buffer[ENOUGH];

    string plik = "ksiazki.txt";

    ifstream myFile(plik.c_str());

    if(myFile.fail()){
        cout<<"Nie udalo sie wczytac pliku tekstowego... :(";
        return 0;
    }
    else{
        int length =0;

        while(!myFile.eof()){
            myFile.get(buffer[length]);
            length++;
        }

        const char* testedFile = buffer;

//-------------------------------------

        Napis napis(testedFile);
        napis.test();
        napis.getComments();

    }
    return 0;
}
