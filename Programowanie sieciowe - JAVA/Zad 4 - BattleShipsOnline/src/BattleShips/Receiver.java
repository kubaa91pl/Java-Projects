package BattleShips;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Admin on 2016-06-24.
 */
public class Receiver extends Thread {
    private final DataOutputStream output;

    Socket socket;
    DataInputStream dataInputStream;
    String sReceived;
    private boolean chatOn;
    private MyBoard myBoard;
    private BattleBoard enemyBoard;
    String[] store;

    public Receiver(Socket socket, DataOutputStream output, MyBoard myBoard, BattleBoard enemyBoard) {
        chatOn = false;
        this.socket = socket;
        this.output = output;
        this.myBoard = myBoard;
        this.enemyBoard = enemyBoard;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                sReceived = dataInputStream.readUTF();
                store = sReceived.split(" ");
                //if(store[0].equals(GameHelper.READY))
                if(store[0].equals(GameHelper.shootCode)){
                    String[] sCommand = {store[1], store[2], store[3]};
                    if(myBoard.shoot(sCommand)){

                        System.out.println("boli!!!!!");
                        int x = Integer.valueOf(sCommand[1]);
                        int y = Integer.valueOf(sCommand[2]);
                        myBoard.Board[x][y]=3;
                        output.writeUTF(GameHelper.scored+" "+x+" "+y);
                        GameHelper.myTurn = true;
                        GameHelper.score2++;
                    }
                    else{
                        System.out.println("uff...");
                        int x = Integer.valueOf(sCommand[1]);
                        int y = Integer.valueOf(sCommand[2]);
                        myBoard.Board[x][y]=2;
                        GameHelper.myTurn = true;
                        output.writeUTF(GameHelper.missed+" "+x+" "+y);
                    }
                }
                else if(store[0].equals(GameHelper.scored)){
                    int x = Integer.valueOf(store[1]);
                    int y = Integer.valueOf(store[2]);
                    enemyBoard.Board[x][y] = 3;
                    GameHelper.score1++;
                }
                else if(store[0].equals(GameHelper.missed)){
                    int x = Integer.valueOf(store[1]);
                    int y = Integer.valueOf(store[2]);
                    enemyBoard.Board[x][y] = 2;
                }
                else{
                    System.out.println(sReceived);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
