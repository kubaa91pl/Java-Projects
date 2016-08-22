package BattleShips;

import java.util.Scanner;

/**
 * Created by Admin on 2016-06-18.
 */
public class Launcher {

    private static Scanner scanner;
    private static String choice;

    public static boolean choose() {
        System.out.println("Kim chcesz byc? jeœli serwerem naciœnj 1, jeœli klientem 2");
        scanner = new Scanner(System.in);
        choice = scanner.nextLine();
        if (choice.trim().equals("1")) return true;
        else if (choice.trim().equals("2")) return false;
        else {
            System.out.println("Wpisz 1 dla serwera, 2 dla klienta");
            return choose();
        }
    }

    public static void main(String[] args) {
        System.out.println("Witaj w grze w statki");
        if (choose()) {
            try {
                GameServer gameServer = new GameServer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                GameClient gameClient = new GameClient();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
