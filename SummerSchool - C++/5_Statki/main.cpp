#include <iostream>
#include "Game.h"
#include "BattleBoard.h"

using namespace std;

int main() {

    BattleBoard enemyBoard;
    Game game(enemyBoard);


    while(!game.endGame())
    {
        game.showScoreAndAmmo();
        enemyBoard.showEnemyBoard();
        if(enemyBoard.shoot()){
            Game::score++;
        }
        Game::ammo--;
    }

    enemyBoard.showEnemyBoard();

    return 0;
}