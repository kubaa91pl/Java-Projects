package Client;

import java.io.*;


import java.net.*;

import static java.net.InetAddress.*;

/**
 * Created by Admin on 2016-06-12.
 */
public class ClientUDP {
    private static int port = 3334;
    private static int size = 1024;

    public static void main(String[] args) throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = getByName("localhost");
        String sentence="";
        byte[] sendData;
        byte[] receiveData;
        System.out.println();
        sentence = "Hi. its the client";
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        clientSocket.send(sendPacket);
        System.out.println("Sending: " + sentence);
        receiveData = new byte[size];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);


        while(!sentence.trim().toLowerCase().equals("exit")) {
            System.out.println("Type equasion: ");
            sendData = new byte[size];
            sentence = inFromUser.readLine();
            sendData = sentence.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            clientSocket.send(sendPacket);

            receiveData = new byte[size];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
        }
        clientSocket.close();
    }
}


