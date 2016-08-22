//
// Created by Admin on 2016-08-04.
//

#include <iostream>

using namespace std;

//------------------------------------------

class Figure {
public:
    char id;
    int x, y;
    bool white;
    static int start;
    static int size;
//    static int last_idx = size - 1;

    virtual bool check_bond(int x, int y) {
        return x < size && y < size && x >= 0 && y >= 0;
    }

    virtual bool check_move(int x, int y)=0;

    virtual bool check_all(int x, int y) {
        return check_bond(x, y) && check_move(x, y);
    }

    virtual bool move(int x, int y, Figure *board[8][8]) {
        bool checked = check_all(x, y);
        if (checked) {
            board[x][y] = this;
            board[this->x][this->y] = 0;
            this->x = x;
            this->y = y;
        }
        return checked;
    }

    Figure(int init_x, int init_y, bool init_white) : x(init_x), y(init_y), white(init_white) {};
};

int Figure::start = 0;
int Figure::size = 8;


std::ostream &operator<<(std::ostream &strm, const Figure &a) {
    return strm << a.id;
}

//------------------------------------------

class Pawn : public Figure {
public:

    Pawn(int init_x, int init_y, bool init_white) : Figure(init_x, init_y, init_white) { this->id = 'p'; }

    bool check_move(int x, int y) {
        bool cond1 = this->x == x && ((y - this->y) == 1);
        bool cond2 = this->x == x && this->y == 1 && ((y - this->y) == 2);
        return cond1 || cond2;
    }

    bool check_move_others(int x, int y, Figure **board) {

    }
};

//------------------------------------------

const int size = 8;

Figure *board[size][size];

void showBoard();

void fillBoard();

Figure *p0 = new Pawn(0, 1, true);


//------------------------------------------

int main() {
    cout << "Witaj w grze w szachy!" << endl << endl;

    fillBoard();
    showBoard();


    p0->move(0, 3, board);
    cout << "Ruszam sie pionkiem na 0 3"<<endl;

    showBoard();

    return 0;
}

//------------------------------------------

void showBoard() {
    cout << "  ";
    for (int i = 0; i < size; i++) {
        cout << i << ' ';
    }
    cout << endl;

    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (j == 0) cout << i << " ";
            if (board[i][j] == 0) {
                cout << 0;
            } else {
                cout << board[i][j]->id;
            }
            cout << ' ';
        }
        cout << endl;
    }
    cout << endl;
};

void fillBoard() {
    board[0][1] = p0;
//    board[6][1] = p_2;
//    board[6][2] = p_3;
//    board[6][3] = p_4;
//    board[6][4] = p_5;
//    board[6][5] = p_6;
//    board[6][6] = p_7;
//    board[6][7] = p_8;
//
//    board[7][0] = w_1;
//    board[7][1] = s_1;
//    board[7][2] = g_1;
//    board[7][3] = h_1;
//    board[7][4] = k_1;
//    board[7][5] = g_2;
//    board[7][6] = s_2;
//    board[7][7] = w_2;
};
