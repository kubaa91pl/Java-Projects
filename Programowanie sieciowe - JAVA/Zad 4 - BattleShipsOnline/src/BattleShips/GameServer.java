package BattleShips;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Admin on 2016-06-24.
 */
public class GameServer{
    private static int port = 1234;

    MyBoard myBoard;
    BattleBoard enemyBoard;
    String[] splitedMsg;



    public GameServer() throws InterruptedException {
        GameHelper.serversTurn = true;
        ServerSocket serverSocket;
        Socket socket;
        String sendMessage = "";
        Scanner scanner;
        myBoard = new MyBoard("Me");
        enemyBoard = new BattleBoard("Enemy");

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for a client - port: " + port);
            socket = serverSocket.accept();
            System.out.println("Client has been connected");

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Receiver receiver = new Receiver(socket,output, myBoard, enemyBoard);
            GameHelper.ChatOn = false;
            scanner = new Scanner(System.in);

            myBoard.placeShips();
            showBoards();
            GameHelper.showCommands();
            GameHelper.myTurn= true;
            while(!GameHelper.endGame()){
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
                    if(GameHelper.myTurn){
                        if (myBoard.checkCommand(splitedMsg, 1)) {
                            output.writeUTF(GameHelper.shootCode + " " + sendMessage);
                            output.flush();
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