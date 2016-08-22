package BattleShips;

import java.util.Random;

/**
 * Created by Admin on 2016-06-24.
 */
public class MyBoard extends BattleBoard {
    public MyBoard(String player) {
        super(player);
    }

    public void placeShips() {
        expectedCommand = "p";
        String manually;
        System.out.println("Czas rozmiescic statki:");
        System.out.println("Do rozmieszczenia beda kolejno: jeden xxxx, dwa xxx, trzy xx, cztery x");
        System.out.println("Czy chcesz sam rozmiescic statki? Jesli tak, wpisz y, jesli nie dowolny inny string");
        manually = scanner.nextLine().trim().toLowerCase();
        if(manually.trim().toLowerCase().equals("y")){
            System.out.println("Wpisz: p <x1> <y1> <dir>, gdzie x1 i y1 to cyfry od 0 do 11 a dir to kierunek (h-poziomo lub kazdy inny znak pionowo) ");
            addShipsManually(Board, 4);
            addShipsManually(Board, 3);
            addShipsManually(Board, 2);
            addShipsManually(Board, 1);
        }
        else{
            addShipsAuto(Board);
        }
        showBoard();
        System.out.println("Koniec rozmieszczania statkow");
        System.out.println("--------------------------------");
    }

    private int howManyShips(int shipType){
        if(shipType==1) return 4;
        else if(shipType==2) return 3;
        else if(shipType==3) return 2;
        else return 1;
    }

    private void addShipsAuto(int Board[][]){
        String sCommand[];
        Random random = new Random();
        String orientation;
        for(int shipKind=4; shipKind>=1;shipKind--) {
            shipType = shipKind;
            int quantity = howManyShips(shipKind);
            int length = shipKind;
            while (quantity > 0) {
                x = random.nextInt(size_x);
                y = random.nextInt(size_y);
                int z = random.nextInt(size_x);
                if (z > size_x / 2) {
                    orientation = "v";
                } else {
                    orientation = "h";
                }

                if (orientation.equals("h")) {
                    sCommand = new String("p "+x+" "+y+" "+"h").split(" ");
                    if(checkCommand(sCommand, shipKind)){
                        if (checkIfFieldFree(x, y, shipKind, "h")) {
                            for (int i = 0; i < shipType; i++) {
                                Board[x + i][y] = 1;
                            }
                            quantity--;
                        }
                    }
                } else {
                    sCommand = new String("p "+x+" "+y+" "+"h").split(" ");
                    if(checkCommand(sCommand, shipKind)) {
                        if (checkIfFieldFree(x, y, shipKind, "v")) {
                            for (int i = 0; i < shipKind; i++) {
                                Board[x][y + i] = 1;
                            }
                            quantity--;
                        }
                    }

                }

            }
        }
    }


    private void addShipsManually(int Board[][], int shipType) {

        int quantity = howManyShips(shipType);

        while (quantity > 0) {
            sCommand = scanner.nextLine().split(" ");
            if (checkCommand(sCommand, shipType))
            {
                x = Integer.parseInt(sCommand[1]);
                y = Integer.parseInt(sCommand[2]);
                if(sCommand.length== 4 && sCommand[3].equals("h")){
                    if (checkIfFieldFree(x, y, shipType, "h")){
                        for(int i=0; i<shipType;i++){
                            Board[x+i][y] = 1;
                        }
                        quantity--;
                        showBoard();
                    }
                }
                else{
                    if (checkIfFieldFree(x, y, shipType, "v")){
                        for(int i=0; i<shipType;i++){
                            Board[x][y+i] = 1;
                        }
                        quantity--;
                        showBoard();
                    }

                }
            }

        }
    }
}
