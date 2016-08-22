//
// Created by Admin on 2016-08-04.
//

#ifndef BATTLESHIPS_BATTLEBOARD_H
#define BATTLESHIPS_BATTLEBOARD_H

#include <regex>
#include <string>


static int boardSize = 11;
static char ship = '*';
static char hit = 'X';
static char miss = '-';
static char sea = '@';


class BattleBoard {
private:

public:
    char ** board;
    BattleBoard();
    virtual ~BattleBoard(){
        for(int i=0; i<boardSize ; i++){
            delete board[i];
        }
        delete[] board;
    };
    void fillBoard();

    //void showBaord() const;

    void showEnemyBoard() const;

    bool shoot();

    bool checkValue(std::string value);
};


#endif //BATTLESHIPS_BATTLEBOARD_H
