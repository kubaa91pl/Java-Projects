import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * Created by Admin on 01.04.2016.
 */
public class EchoServer {
    private static final int PORT_ECHO = 7;
    private static final int LINELEN = 100;

    public static void main(String[] args) {
        if (args[0].equals("TCP") || args[0].equals("UDP")) {
            if (args[0].equals("TCP")) {
                byte[] buffer = new byte[LINELEN];
                ServerSocket serverSocket = null;
                Socket clientSocket = null;
                OutputStream outputStream = null;
                InputStream inputStream = null;
                int numberOfBytes;
                try {
                    serverSocket = new ServerSocket(PORT_ECHO);

                    while (true) {
                        System.out.println("Waiting for a client...");
                        clientSocket = serverSocket.accept();
                        System.out.println("Client has been connected");

                        outputStream = clientSocket.getOutputStream();
                        inputStream = clientSocket.getInputStream();
                        String message = "";

                        while (true) {
                            try {
                                numberOfBytes = inputStream.read(buffer);
                                message = new String(buffer, 0, numberOfBytes);
                                System.out.print(message + "\n");
                                outputStream.write(buffer);
                            } catch (StringIndexOutOfBoundsException e) {
                                outputStream.close();
                                inputStream.close();
                                clientSocket.close();
                                break;
                            }
                        }
                    }

                } catch (IOException e) {
                }
            } else {
                if (args[0].equals("UDP")) {
                    DatagramSocket datagramSocket;
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[LINELEN], LINELEN);

                    try {
                        datagramSocket = new DatagramSocket(PORT_ECHO);
                    } catch (SocketException e) {
                        System.out.println(e);
                        return;
                    }

                    while (true) {
                        System.out.println("Wiating for packets...");
                        try {
                            datagramSocket.receive(datagramPacket);
                            byte[] receivedData = datagramPacket.getData();
                            int dataLength = datagramPacket.getLength();
                            String message = new String(receivedData, 0, dataLength);
                            System.out.println("Received from client: " + message);

                            System.out.println("Sending feedback... ");
                            datagramSocket.send(datagramPacket);
                        } catch (IOException ioe) {
                            System.out.println(ioe);
                        }
                    }
                }
            }
        } else {
            System.out.println("Choose TCP or UDP, as an argument");
        }
    }
}
