package Server;

import java.io.*;
import java.net.*;

/**
 * Created by Admin on 2016-03-07.
 */
public class Server {
    private static ServerSocket serverSocket;
    private static int port;
    private static int count;
    private static String feedback;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        port = Integer.parseInt(args[0]);
        feedback = args[1];
        count = Integer.parseInt(args[2]);
        serverSocket = new ServerSocket(port);

        while(true){
            System.out.println("Server is waiting for connection");
            Socket socket = serverSocket.accept();
            System.out.println("Client: "+socket.getInetAddress());
            System.out.println("Port: "+socket.getLocalPort());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String message = (String) objectInputStream.readObject();
            System.out.println("Message received: " + message);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject(feedback);
            if(message!=null) objectOutputStream.writeObject("message received");
            for(int i=0; i<count; i++) {
                objectOutputStream.writeObject(message);
            }

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
            if(message=="end") break;
        }

        System.out.println("Server socket is shutting down");
        serverSocket.close();
    }
}
