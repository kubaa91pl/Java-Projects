//
// Created by Admin on 2016-08-04.
//

#include <iostream>
#include "BattleBoard.h"
#include "Game.h"

void BattleBoard::fillBoard() {

    this->board[0][0] = ship;
    this->board[1][0] = ship;
    this->board[2][0] = ship;
    this->board[3][0] = ship;

    this->board[5][2] = ship;
    this->board[5][3] = ship;
    this->board[5][4] = ship;

    this->board[7][4] = ship;
    this->board[7][5] = ship;
    this->board[7][6] = ship;

    this->board[8][4] = ship;
    this->board[8][5] = ship;

    this->board[1][3] = ship;
    this->board[2][3] = ship;
}

BattleBoard::BattleBoard() {
    board = new char *[boardSize];
    for (int i = 0; i < boardSize; ++i) {
        board[i] = new char[boardSize];
    }

    for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardSize; j++) {
            board[i][j] = sea;
        }
    }

    BattleBoard::fillBoard();
};

/*
void BattleBoard::showBaord() const {
    std::cout<<" "<<std::endl;
    for (int i = 1; i < boardSize; i++) {
        std::cout << i << ' ';

    }
    for (int i = 0; i < boardSize; ++i) {
        for (int j = 0; j < boardSize; ++j) {
            if (j == 0) {
                std::cout << i + 1 << ' ';
            }
            std::cout << board[i][j] << ' ';
        }
        std::cout << std::endl;
    }
};
 */


void BattleBoard::showEnemyBoard() const {
    std::cout<<" "<<std::endl;

    for (int i = 1; i < boardSize; i++) {
        std::cout << i << ' ';
    }
    std::cout << std::endl;

    for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardSize; j++) {
            if (j == 0) {
                std::cout << i + 1 << ' ';
            }
            else if (board[i][j] == ship) {
                std::cout << sea << ' ';
            }
            else{
                std::cout << board[i][j] << ' ';
            }
        }
        std::cout << std::endl;
    }
};

bool BattleBoard::shoot() {
    std::cout << "Strzal: " << std::endl;

    std::string value;
    int x, y;

    int temp = 0;

    do {
        if (temp == 0) {
            std::cout << "Podaj wspolrzedna x: " << std::endl;
        }
        else {
            std::cout << "Podaj liczbe z przedzialu: <1 , " << boardSize << ">" << std::endl;
        }
        std::cin >> value;
        temp++;
    } while (!checkValue(value));

    temp = 0;
    x = std::stoi(value);

    do {
        if (temp == 0) {
            std::cout << "Podaj wspolrzedna y: " << std::endl;
        }
        else {
            std::cout << "Podaj liczbe z przedzialu: <1 , " << boardSize << ">" << std::endl;
        }
        std::cin >> value;
        temp++;
    } while (!checkValue(value));

    y = std::stoi(value);

    if (board[x - 1][y - 1] == ship) {
        board[x - 1][y - 1] = hit;
        return true;

    }
    else if (board[x - 1][y - 1] == sea) {
        board[x - 1][y - 1] = miss;
        return false;
    }
    else if (board[x - 1][y - 1] == ship) {
        board[x - 1][y - 1] = ship;
        std::cout << "Strzelales juz w to pole" << std::endl;
        std::cout << "Sprobuj jeszcze raz!" << std::endl;
        return shoot();

    }
    else if (board[x - 1][y - 1] == miss) {
        board[x - 1][y - 1] = miss;
        std::cout << "Strzelales juz w to pole" << std::endl;
        std::cout << "Sprobuj jeszcze raz!" << std::endl;
        return shoot();
    }

    return false;

}

bool BattleBoard::checkValue(std::string value) {
    std::regex regex_pattern("[0-9]", std::regex_constants::extended);
    std::regex regex_pattern2("[0-9]{2}", std::regex_constants::extended);
    if (std::regex_match(value, regex_pattern) || std::regex_match(value, regex_pattern2)) {
        int iValue = std::stoi(value);
        if (iValue <= boardSize && iValue >= 1) {
            return true;
        }
    }

    return false;
};

