#include <iostream>
#include <string>
#include <math.h>
#include <stdlib.h>
#include <regex>
#include <cstdio>

using namespace std;

const int size = 9; //mozliwe 4, 9, 16 itd.
const string emptySpace = "[]";
bool gameOn = true;

const int countInterspaceBtwBoards = sqrt(size);

string **sudokuBoard;
string** createEmptyBoard(const int size, const string emptySpace);
void fillWithSomeNumbers(string** board, const int size, const string emptySpace);
void showBoard(string** board);


int getValue();
string getValue(bool needString);
bool checkValueRange(const int size, string choice);
void putNextNumber(string** board);
bool sudokuFinished(const int size, string** board);


//------------------------------------


int main()
{
    sudokuBoard = createEmptyBoard(size, emptySpace);
    fillWithSomeNumbers(sudokuBoard, size, emptySpace);


    cout <<"Witaj w grze SUDOKU"<<endl<<endl;

    while(gameOn){
            showBoard(sudokuBoard);
            putNextNumber(sudokuBoard);

            if(sudokuFinished(size, sudokuBoard)){
                gameOn = false;
            }
    }

    return 0;
}


//------------------------------------


bool sudokuFinished(const int size, string** board){
    int iNumber;
    int condition_table[size];

    for(int i=0; i<size; i++){
        for(int j=0; j<size ; j++){
            condition_table[iNumber]++;
            iNumber = atoi(board[i][j].c_str());
        }
        for(int i=0; i<size; i++){
                if(condition_table[i]!=1){
                    return false;
                    }
        }
        fill(condition_table, condition_table+size, 0);
    }

    for(int i=0; i<size; i++){
        for(int j=0; j<size ; j++){
            condition_table[iNumber]++;
            iNumber = atoi(board[i][j].c_str());
        }
        for(int i=0; i<size; i++){
                if(condition_table[i]!=1){
                    return false;
                    }
        }
        fill(condition_table, condition_table+size, 0);
    }

    return true;

};

bool checkValueRange(const int size, string choice){

    ostringstream oss;
    oss << "[1-"<<size<<"]";
    string range = oss.str();
    std::regex regex_pattern(range, std::regex_constants::extended);

    if(std::regex_match(choice,regex_pattern)){
            if(atoi(choice.c_str()) >size || atoi(choice.c_str())<1){
                return false;
                }
            else{
                return true;
                }
    }
    else{
        return false;
    }
}

void putNextNumber(string** board){
    cout<<"Wprowadzaj kolejno wspolrzedne x, y o wartosciach od 0 do "<<size-1<<"."<<endl;
    cout<<"Wartosci wpisywane w plansze to liczby od 1 do "<<size<<"."<<endl;

    cout<<endl<<"Podaj x: ";
    int x = getValue();

    cout<<endl<<"Podaj y: ";
    int y = getValue();

    cout<<endl<<"Podaj liczbe: ";
    string value = getValue(true);

    board[y][x] = value+"*";
    cout<<endl<<"Podana liczba zostala zapisana na planszy"<<endl<<endl;

}

int getValue(){
    string value;
    cin >> value;

    if(checkValueRange(size-1, value)){
        return atoi( value.c_str() );
    }
    else{
        cout<<"Blad - Wpisz liczbe z zakresu od "<< "0" <<" do "<<size-1<<": ";
        return getValue();
    }
}

string getValue(bool needString){
    string value;
    cin >> value;
    if(checkValueRange(size, value)){
        return value;
    }
    else{
        cout<<"Blad - Wpisz liczbe z zakresu od "<< "1" <<" do "<<size<<": ";
        return getValue(true);
    }

}


string** createEmptyBoard(const int size, const string emptySpace){
    string** sudokuArray = new string*[size];

    for(int i=0; i< size; i++){
        sudokuArray[i] = new string[size];

        for(int j=0; j<size; j++){
            sudokuArray[i][j] = emptySpace;
        }
    }

    return sudokuArray;
};

void fillWithSomeNumbers(string** board, const int size, const string emptySpace){
    int counter = size;
    int x, y;
    string number;

    ostringstream temp;
    temp<<counter;
    number=temp.str();

    while(counter>0){
        x = ( rand() % size );
        y = ( rand() % size );
        if(board[x][y]== emptySpace){
            board[x][y]=number+"*";
            temp.clear();
            temp.str("");
            counter--;
            temp<<counter;
            number=temp.str();
        }
    }
}

void showBoard(string** board){
    cout << "---Tablica---"<<endl<<endl;

    int howManyBoardsInaRow = countInterspaceBtwBoards;
    int howManyBoardsInaColumn = countInterspaceBtwBoards;

    cout <<"  ";
    for (int i =0; i< size; i++){
        cout<<i<<" ";
        howManyBoardsInaRow--;
        if(howManyBoardsInaRow==0){
                    cout <<" ";
                    howManyBoardsInaRow = countInterspaceBtwBoards;
                }
    }
    cout << endl<<endl;
    for(int i=0; i< size; i++){
            cout<< i<< " ";
            for(int j=0; j<size; j++){
                cout<<board[i][j];
                howManyBoardsInaRow--;

                if(howManyBoardsInaRow==0){
                    cout <<" ";
                    howManyBoardsInaRow = countInterspaceBtwBoards;
                }
            }
            howManyBoardsInaColumn--;
            if(howManyBoardsInaColumn==0){
                cout<<endl;
                howManyBoardsInaColumn = countInterspaceBtwBoards;
            }
            cout << endl;
    }
};
