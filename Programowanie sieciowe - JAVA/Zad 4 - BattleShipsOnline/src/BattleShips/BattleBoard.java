package BattleShips;

import java.util.Scanner;

/**
 * Created by Admin on 2016-06-24.
 */

/*
Plansza z polami od 0 do 11, o wymiarach 12x12.
0 - pole puste []
1 - pole ze statkiem [x]
2 - trafione puste pole [-]
3 - trafione pole ze statkiem [@]

p - rozmieszczenie statku h poziomo v pionowo
c - czat
s - strzal
 */
public class BattleBoard{
    private final int messageLength = 50;

    protected String player;
    protected int[][] Board;
    protected final int size_x = 12, size_y = 12;
    protected int x, y;
    protected int shipType;
    protected Scanner scanner;
    protected String sCommand[];
    protected String expectedCommand;

    public BattleBoard(String player) {
        this.player = player;
        Board = new int[size_x][size_y];
        scanner = new Scanner(System.in);
        sCommand = new String[messageLength];
    }


    public void showBoard() {
        System.out.println(player+": ");
        for (int i = 0; i < size_y; i++) {
            System.out.print("  " + (i));
        }
        System.out.println();
        for (int i = 0; i < size_x; i++) {
            System.out.print(i);
            for (int j = 0; j < size_y; j++) {
                if (Board[j][i] == 0) {
                    System.out.print("[ ]");
                } else if (Board[j][i] == 1) {
                    System.out.print("[x]");
                } else if (Board[j][i] == 2) {
                    System.out.print("[-]");
                } else if (Board[j][i] == 3) {
                    System.out.print("[#]");
                }
            }
            System.out.println();
        }
    }

    public boolean shoot(String[] sCommand){
        showBoard();
        System.out.println("Strzal");
        //sCommand = scanner.nextLine().split(" ");
        x = Integer.parseInt(sCommand[1].trim());
        y = Integer.parseInt(sCommand[2].trim());

        if(checkCommand(sCommand, 1)){
            if(Board[x][y] == 1){
                Board[x][y] = 3;
                return true;
            }
            else if(Board[x][y]==3 || Board[x][y]==2){
                System.out.println("Juz tu strzelales. Sprobuj jeszcze raz");
                return shoot(this.sCommand);
            }
            else{
                Board[x][y]=2;
                return false;
            }
        }
        else {
            return shoot(this.sCommand);
        }
    }

    protected boolean checkCommand(String sCommand[],  int shipType) {
        if (sCommand.length >= 3) {
            int x = Integer.parseInt(sCommand[1]);
            int y = Integer.parseInt(sCommand[2]);
            if (sCommand.length == 3 && sCommand[0].equals("s")) {
                if (x>= 0 && x <= size_x - 1 && y >= 0 && y<=size_y-1) {
                    return true;
                }
                else {
                    System.out.println("Strzeliles poza pole. Sprobuj jeszcze raz!");
                    return false;
                }
            }
            if(sCommand.length == 4 && sCommand[0].equals("p")){
                if(sCommand[3].equals("h")){
                    if(x >= 0 && x <= size_x -1-(shipType-1) && y >=0 && y<= size_y-1-shipType-1){
                        return true;
                    }
                    else{
                        System.out.println("Weszles poza pole");
                        return false;
                    }
                }
                else if(x >= 0 && x <= size_x-1 && y >=0 && y<= size_y -1-(shipType-1)){
                    return true;
                }
                else{
                    System.out.println("Weszles poza pole");
                    return false;
                }
            }
            else if(sCommand.length == 3 && sCommand[0].equals("p")){
                if(x >= 0 && x <= size_x && y >=0 && y<= size_y -1 -(shipType-1)){
                    return true;
                }
                else{
                    System.out.println("Weszles poza pole");
                    return false;
                }
            }
        }
        else {
            System.out.println("Blad komendy");
            GameHelper.showCommands();
            return false;
        }
        if(!sCommand.equals("s") && !sCommand.equals("p") && !sCommand.equals("c")) {
            return true;
        }
        else {
            return false;
        }
    }

    protected boolean checkIfFieldFree(int x, int y, int shipType, String orientation){
        if(orientation.equals("h")) {
            for (int i = 0; i < shipType; i++) {
                if (Board[x][y + i] == 0){}
                else {
                    System.out.println("Pole zajete");
                    return false;
                }
            }
        }
        if(orientation.equals("v")){
            for (int i = 0; i < shipType; i++) {
                if (Board[x+i][y] == 0){}//
                else {
                    System.out.println("Pole zajete");
                    return false;
                }
            }
        }
        return true;
    }
}
