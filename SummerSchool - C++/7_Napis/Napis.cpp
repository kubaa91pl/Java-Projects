//
// Created by Admin on 2016-08-03.
//

#include <iostream>
#include "Napis.h"

Napis::Napis(const char *text) {
    this->length = this->getTextLength(text);
    this-> napis = new char[length];
    this-> napis= text;

};

Napis::~Napis(){
    delete napis;
};


void Napis::test(){
    Napis::test(Napis::getTextLength(this->napis));
};

void Napis::test(int length) {

    std::cout << "This is the original text: " << std::endl << std::endl;
    for(int i=0; i<length; i++){
        std::cout<< napis[i];
        }
};


int Napis::getTextLength(const char* text){
    int length = 0;
    while(text[length]!='\0'){
        length++;
    }
    return length;
};

void Napis::getComments(){
    int i=0;
    std::cout << "Comments: " << std::endl << std::endl;
    while(napis[i+1]!='\0'){
        if(napis[i]=='/'&& napis[i+1]=='/'){
            std::cout<<std::endl;
            i=i+2;
                while(napis[i]!='\n'){
                    std::cout<<napis[i];
                    i++;
                }
                std::cout << std::endl;

        }

        if(napis[i]=='/'&& napis[i+1]=='*'){
                std::cout<<std::endl;
                i=i+2;
                while(  (!(napis[i]=='*'&&napis[i+1]=='/')&& i !=length)  ){
                      if(napis[i]=='\n'){
                        std::cout<<std::endl;
                      }
                    std::cout<<napis[i];
                    i++;
                }
                std::cout << std::endl;

        }

        i++;
    }
};



