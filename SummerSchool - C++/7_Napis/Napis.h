//
// Created by Admin on 2016-08-03.
//

#ifndef NAPIS_NAPIS_H
#define NAPIS_NAPIS_H

#include<string>


class Napis {

public:


    Napis(const char *text);
    ~Napis();

    void test();

    void test(int length);

    int getTextLength(const char* text);

    void getComments();
private:
    const char* napis;
    int length;
};


#endif //NAPIS_NAPIS_H
