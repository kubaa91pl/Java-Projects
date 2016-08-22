//
// Created by Admin on 2016-08-04.
//

#ifndef BATTLESHIPS_GAME_H
#define BATTLESHIPS_GAME_H


#include "BattleBoard.h"



class Game {
public:
    static int ammo;
    static int score;

    BattleBoard enemyBoard;
    Game(BattleBoard& bb);
    void showScoreAndAmmo();
    bool endGame();
    virtual ~Game(){};



};


#endif //BATTLESHIPS_GAME_H
