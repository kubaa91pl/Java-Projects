//
// Created by Admin on 2016-08-04.
//

#include <iostream>
#include "Game.h"

#include "BattleBoard.h"

int Game:: ammo =18;
int Game:: score = 0;

Game::Game(BattleBoard& bb)
        :enemyBoard(bb)
{
    std::cout << "Witaj w grze w statki!" <<std::endl;
    std::cout << "----------------------" <<std::endl;
    std::cout << "Musisz zatopic przeciwnika majac ograniczona amunicje" <<std::endl;
    std::cout << "Wspolrzedne punktu strzalu sa liczbami od 1 do "<<boardSize<<std::endl;
    std::cout << "----------------------" <<std::endl<<std::endl;

}

void Game::showScoreAndAmmo() {
    std::cout << "Amunicja: "<<ammo<<std::endl;
    std::cout << "Trafienia: " << score << std::endl;
    std::cout << std::endl;
}

bool Game::endGame() {
    if(score==14){
        std::cout << "Brawo! Wygrales!!!!"<<std::endl;
        return true;
    }
    else if(ammo==0){
        std::cout <<"Koniec aminicji!!!"<<std::endl;
        std::cout <<"Wrog przetrwal :("<<std::endl;
        return true;
    }
    else{
        return false;
    }

}





