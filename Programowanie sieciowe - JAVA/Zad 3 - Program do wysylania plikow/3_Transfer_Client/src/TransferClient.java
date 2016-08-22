import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

import java.util.Scanner;

/**
 * Created by Admin on 2016-05-28.
 */
public class TransferClient {
    private static final int port = 9664;
    private static final int size = 99999;
    private static DatagramPacket datagramReceiveFilePacket;
    private static DatagramPacket datagramSendPacket;
    private static DatagramPacket datagramReceivePacket;
    private static int portFileReceiver;
    private static boolean connection = true;
    String port2;
    static String[] FileList = null;

    public static void main(String[] args) throws UnknownHostException, SocketException {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket datagramClientSocket = new DatagramSocket();
        InetAddress IPAdress = InetAddress.getByName("localhost");
        while (true) {
            try {

                //FileReceiver receiver = new FileReceiver();

                byte[] sendData;
                byte[] receiveData;
                String receivedMessage;
                if(connection) sendData = "Hi".getBytes();
                else sendData ="ls".getBytes();
                datagramSendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, port);datagramClientSocket.send(datagramSendPacket);
                receiveData = new byte[size];
                datagramReceivePacket = new DatagramPacket(receiveData, receiveData.length);
                datagramClientSocket.receive(datagramReceivePacket);

                    System.out.println(new String(datagramReceivePacket.getData()));



                connection = true;
                String message;
                while (connection) {
                    System.out.println("Write a command");
                    sendData = new byte[size];
                    message = userInput.readLine();
                    sendData = message.getBytes();
                    datagramSendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, port);
                    datagramClientSocket.send(datagramSendPacket);
                    receiveData = new byte[1024];
                    datagramReceivePacket = new DatagramPacket(receiveData, receiveData.length);
                    datagramClientSocket.receive(datagramReceivePacket);
                    receivedMessage = new String(datagramReceivePacket.getData());
                    System.out.println(receivedMessage);
                    Thread.sleep(1000);

                    if (receivedMessage.startsWith("File has been found.")) {
                        byte[] received = new byte[size];
                        datagramReceiveFilePacket = new DatagramPacket(received, received.length);
                        datagramClientSocket.receive(datagramReceiveFilePacket);
                        String PortAndName[] = (new String(datagramReceiveFilePacket.getData())).split("@");

                        System.out.println("Port for dataTransfer: " + PortAndName[0]);
                        portFileReceiver = Integer.valueOf(PortAndName[0].trim());
                        //new Receiver(PortAndName[1].trim(), portFileReceiver,212385);
                        new FileReceiver(Integer.valueOf(PortAndName[0].trim()), PortAndName[1].trim());
                        System.out.println("FileReceier");
                        Thread.sleep(1000);
                        System.out.println("Would u like to disconnet? y/n");
                        Scanner scanner = new Scanner(System.in);
                        if (scanner.nextLine().equals("y")) {
                            datagramClientSocket.close();
                            System.exit(0);
                        } else {
                            //datagramClientSocket.close();

                            connection = false;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
