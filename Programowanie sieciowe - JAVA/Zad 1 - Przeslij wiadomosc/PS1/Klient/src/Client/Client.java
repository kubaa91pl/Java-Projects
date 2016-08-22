package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Admin on 2016-03-07.
 */
public class Client {

    private static String message;
    private static int port;
    private static Socket socket;
    private static String fromServer;
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        port = Integer.parseInt(args[0]);
        Scanner scanner = new Scanner(System.in);
        System.out.println("What message would u like to send");
        message = scanner.nextLine();

        InetAddress host = InetAddress.getLocalHost().getLocalHost();
        socket = new Socket(host.getHostName(), port);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending request to server");
        objectOutputStream.writeObject(message);

        objectInputStream = new ObjectInputStream((socket.getInputStream()));
        do{
            fromServer = null;
            try{
                fromServer= (String)objectInputStream.readObject();
                if(fromServer!=null) System.out.println(fromServer);
            }
            catch(Exception e){
                System.out.println("No more messages");
            }

        }while(fromServer!=null);

        objectInputStream.close();
        objectOutputStream.close();
    }
}