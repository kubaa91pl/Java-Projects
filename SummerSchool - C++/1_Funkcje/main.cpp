#include <iostream>
#include "functions.hpp"
#include <float.h>

using namespace std;

float a = 30;
float b = 2111;

float div_total;
float div_remainder;

int main()
{
    find_min(20, 50, 2);
    find_max(20, 24423, 3);
    do_swap(a , b);
    cout << "a: " << a << endl;
    cout << "b: " << b << endl;
    do_sort(3 , 65, 75 ,8);
    do_sort(33, 52, 5);
    do_sort( 3, 5);

    cout << "Wynik dzielenia bez reszty: " << divide(129, 40, div_total, div_remainder) << endl;
    cout << "Czesc calkowita: " << div_total << endl
    << "Reszta: " << div_remainder;

    return 0;
}

//-----------------------------------------

float find_min(float number1, float number2, float number3){
    cout << endl << "--- Minimum ---" << endl;
    cout << "Podane liczby: " << number1 << ", " << number2 << ", " << number3 << endl;

    float min_val = number1;

    if(number2 < min_val){
        min_val = number2;
    }

    if(number3 < min_val){
        min_val = number3;
    }

    cout << "@ Wartosc minimalna: " << min_val << endl << endl;
    return min_val;
}

float find_max(float number1, float number2, float number3){
    cout << endl << "--- Maksimum ---" << endl;
    cout << "Podane liczby: " << number1 << ", " << number2 << ", " << number3 << endl;

    float max_val = number1;

    if(number2 > max_val){
        max_val = number2;
    }

    if(number3 > max_val){
        max_val = number3;
    }

    cout << "@ Wartosc maksymalna: " << max_val << endl << endl;
    return max_val;
}

void do_swap(float& number1, float& number2){
    cout << endl << "--- Zamiana ---" << endl;
    cout << "Przed zamiana: " << endl;
    cout << "liczba1: " << number1 << endl;
    cout << "liczba2: " << number2 << endl;

    float temp;
    temp = number1;
    number1 = number2;
    number2 = temp;

    cout << "@ Po zamianie: " << endl;
    cout << "@ liczba1: " << number1 << endl;
    cout << "@ liczba2: " << number2 << endl << endl;
}

void do_sort(float number1, float number2, float number3, float number4){
    cout << endl << "--- Sortowanie ---" << endl;
    cout << "Nieposortowane: " << number1 << ", " << number2 << ", " << number3 << ", " << number4 << endl;

    for(int i =0; i< 3; i++){
        if(number2 > number1){
            swap(number2 , number1);
        }
        if(number3 > number2){
            swap(number3, number2);
        }
        if(number4 > number3){
            swap(number4, number3);
        }
    }

    cout << "@ Posortowane: " << number1 << ", " << number2;
    if(number3 != FLT_MIN){
        cout << ", " << number3;
    }
    if(number4 != FLT_MIN){
        cout << ", " << number4;
    }
    cout << endl << endl;
}

float divide(float a, float b, float &div_total, float &div_remainder){
    cout << endl << "--- Dzielenie ---" << endl;

    float result = a/b;
    int iTotal = result;
    div_total = iTotal;
    div_remainder = result - div_total;

    return result;
}
