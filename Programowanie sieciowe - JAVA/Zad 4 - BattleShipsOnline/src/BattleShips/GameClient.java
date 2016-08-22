package BattleShips;

import BattleShips.BattleBoard;
import BattleShips.Launcher;
import BattleShips.MyBoard;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class GameClient{
    private final int port = 1234;
    private final String address = "localhost";
    private final static String flush = "\n";
    private String[] splitedMsg;
    protected String sendMessage;
    MyBoard myBoard;
    BattleBoard enemyBoard;
    Scanner scanner;

    public GameClient() throws InterruptedException {
        enemyBoard = new BattleBoard("Enemy");
        myBoard = new MyBoard("Me");
        try {
            Socket socket = new Socket(address, port);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Receiver receiver = new Receiver(socket, output, myBoard, enemyBoard);
            scanner = new Scanner(System.in);

            myBoard.placeShips();
            showBoards();
            GameHelper.showCommands();
            GameHelper.myTurn = false;
            while(!GameHelper.endGame()){
                while(!GameHelper.endGame()){
                    //showBoards();
                    //if(GameHelper.p2ShootOn){
                    if(GameHelper.myTurn){
                        System.out.println("Twoja kolej na strzal");
                    }
                    else{
                        System.out.println("Strzela przeciwnik");
                    }
                    sendMessage = scanner.nextLine();
                    splitedMsg = sendMessage.split(" ");
                    if(splitedMsg[0].trim().toLowerCase().equals("c") && !GameHelper.ChatOn){
                        GameHelper.ChatOn = true;
                        System.out.println("Napisz wiadomosc");
                    }
                    else if(GameHelper.ChatOn){
                        output.writeUTF(sendMessage);
                        output.flush();
                        GameHelper.ChatOn = false;
                    }
                    else if(!GameHelper.ChatOn && splitedMsg[0].trim().toLowerCase().equals("s")){
                        //if(GameHelper.p2ShootOn){
                        if(GameHelper.myTurn){
                            if (myBoard.checkCommand(splitedMsg, 1)) {
                                output.writeUTF(GameHelper.shootCode + " " + sendMessage);
                                output.flush();
//                                GameHelper.p1ShootOn = true;
//                                GameHelper.p2ShootOn = false;
                                GameHelper.myTurn = false;
                                Thread.sleep(1000);
                                showBoards();
                            }
                        }
                        else{
                            System.out.println("Poczekaj na swoja kolej.");
                        }
                    }
                    else if(!GameHelper.ChatOn && splitedMsg[0].trim().toLowerCase().equals("show")){
                        showBoards();
                    }
                    else if(!GameHelper.ChatOn && splitedMsg[0].trim().toLowerCase().equals("help")){
                        GameHelper.showCommands();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showBoards() {
        GameHelper.showScore();
        enemyBoard.showBoard();
        myBoard.showBoard();
    }
}
