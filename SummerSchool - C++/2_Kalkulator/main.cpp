#include <iostream>
#include <regex>
#include "kalkulator.hpp"

using namespace std;

bool calculatorON = true;
string choice;
float number1;
float number2;


int main()
{
    cout << "Witaj w progamie kalkulator!" << endl;

    while(calculatorON){

            showMenu();

            switch(atoi(choice.c_str())){

                case 1:
                    getArguments();
                    add(number1, number2);
                    break;

                case 2:
                    getArguments();
                    subtract(number1, number2);
                    break;

                case 3:
                    getArguments();
                    multiply(number1, number2);
                    break;

                case 4:
                    getArguments();
                    divide(number1, number2);
                    break;

                case 5:
                    cout << "Do widzenia." << endl;
                    return 0;
            }

            cout << "Jesli chcesz liczyc dalej nacisnij 'y'" << endl << "w przeciwnym razie dowolny inny klawisz.";
            cin >> choice;
            cout << endl;

            if(! (choice == "y" || choice == "Y") ){
                    calculatorON = false;
            }

    }


    return 0;
}


//------------------------------------------

void showMenu(){
    std::regex regex_pattern("[1-5]{1}", std::regex_constants::extended);

    do{
            cout << "----------------------------" << endl;
            cout << "Wpisz cyfre okreslajaca dzialanie jakie chcia³bys wykonac: " << endl;
            cout << "1) Dodawanie." << endl;
            cout << "2) Odejmowanie." << endl;
            cout << "3) Mnozenie." << endl;
            cout << "4) Dzielenie." << endl;
            cout << "5) Zamknij kalkulator." << endl;
            cout << endl;
            cout << "Wybor: ";
            cin >> choice;
    }while(!(std::regex_match(choice,regex_pattern)));
}

bool getArguments(){

    std::regex regex_pattern("[1-9][0-9]*\.?[0-9]*([Ee][+-]?[0-9]+)?", std::regex_constants::extended);

    do{
        cout << "Wprowadz liczbe 1: ";
        cin >> number1;
    }while(!(std::regex_match(choice,regex_pattern)));

    do{
        cout << "Wprowadz liczbe 2: ";
        cin >> number2;
    }while(!(std::regex_match(choice,regex_pattern)));

    cout << endl;
    return true;
}

void add(float num1, float num2){
    cout << num1 << " + "<< num2 << " = " << num1 + num2 << endl;
}

void subtract(float num1, float num2){
    cout << num1 << " - "<< num2 << " = " << num1 - num2 << endl;
}

void multiply(float num1, float num2){
    cout << num1 << " * "<< num2 << " = " << num1 * num2 << endl;
}

void divide(float num1, float num2){
    if(num2 != 0){
        cout << num1 << " / "<< num2 << " = " << num1 / num2 << endl;
    }
    else{
        cout << "Nie dzielimy przez zero" << endl;
        cout << "Sprobuj jeszcze raz" << endl;

        if(getArguments()){
            divide(number1, number2);
        }
    }
}

