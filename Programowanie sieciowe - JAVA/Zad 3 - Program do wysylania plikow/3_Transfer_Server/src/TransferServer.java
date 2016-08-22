import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Admin on 2016-05-28.
 */
public class TransferServer {

    private static final int port = 9664;
    private static final int size = 99999;
    private static int portDataTransfer= 6666;
    private static final String path ="files/";
    static DatagramSocket datagramServerSocket;
    static DatagramPacket datagramPacket;
    private static boolean connection = true;


    public static void main(String[] args) {
        try {

            Sender helper = new Sender();
            datagramServerSocket = new DatagramSocket(port);
            byte[] send;
            byte[] receiveData;
            String receivedMessage;
            String[] commandsAndWords;
            String command;
            InetAddress IP;
            int port;
            System.out.println("Waiting for a client");
            receiveData = new byte[size];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramServerSocket.receive(receivePacket);
            IP = receivePacket.getAddress();
            port = receivePacket.getPort();
            System.out.println("A client with " + IP + " " + port + " has been connected");
            send = "Welcome".getBytes();
            datagramPacket = new DatagramPacket(send, send.length, IP, port);
            datagramServerSocket.send(datagramPacket);


            while (true) {
                receiveData = new byte[size];
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                datagramServerSocket.receive(receivePacket);
                receivedMessage = new String (receivePacket.getData());
                receivedMessage = receivedMessage.trim();
                System.out.println(receivedMessage);
                commandsAndWords = receivedMessage.split(" ");
                command = commandsAndWords[0].toLowerCase();

                if(command.equals("ls") || command.equals("list")){

                    DatagramPacket ListPacket = helper.sendFileList(datagramPacket.getAddress(), datagramPacket.getPort());
                    datagramServerSocket.send(ListPacket);
                    System.out.println("list has been sent");
                }
                else {
                    if (command.equals("get")) {
                        StringBuilder fileNameBuilder = new StringBuilder();
                        for (int i = 1; i < commandsAndWords.length; i++) {
                            if (commandsAndWords.length != 1 && i != commandsAndWords.length - 1) {
                                fileNameBuilder.append(commandsAndWords[i] + " ");
                            } else {
                                fileNameBuilder.append(commandsAndWords[i]);
                            }
                        }
                        String fileName = new String(fileNameBuilder.toString());
                        System.out.println("file: " + fileName);

                        DatagramPacket ListPacket = helper.sendFile(fileName, datagramPacket.getAddress(), datagramPacket.getPort());
                        datagramServerSocket.send(ListPacket);
                        if ((new String(ListPacket.getData()).equals("File has been found."))) {
                            DatagramPacket PortPacket = helper.sendPortFilenameSize(portDataTransfer, datagramPacket.getAddress(), datagramPacket.getPort());
                            datagramServerSocket.send(PortPacket);
                            //new DataTransfer(IP, portDataTransfer, (fileName));
                            new DataSender(portDataTransfer, fileName);
                            System.out.println("DataSender");
                           //DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            //datagramServerSocket.receive(receivePacket2);
                            portDataTransfer++;

                        } else {
                            System.out.println("get operation with wrong arguments");

                        }
                    } else {
                        System.out.println(IP + "port: " + port + " " + receivedMessage);
                        String sCommands = "ls or list for file list or get filename for download";
                        send = sCommands.getBytes();
                        datagramPacket = new DatagramPacket(send, send.length, datagramPacket.getAddress(), datagramPacket.getPort());
                        datagramServerSocket.send(datagramPacket);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
