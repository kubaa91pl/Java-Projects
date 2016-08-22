import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

/**
 * Created by Admin on 2016-06-04.
 */
public class FileReceiver {
    //private byte[] received;
    private static final int size = 99999;
//    private DatagramPacket datagramReceiveFilePacket;
//    private DatagramSocket datagramClientSocket = null;
//    private FileOutputStream file = null;


    public FileReceiver(int port, String filename) throws IOException {
        Socket sock = new Socket("localhost", port);
        byte[] mybytearray = new byte[size];
        InputStream is = sock.getInputStream();
        FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int bytesRead = is.read(mybytearray, 0, mybytearray.length);
        bos.write(mybytearray, 0, bytesRead);
        bos.close();
        sock.close();
    }


//    public DatagramPacket receivePort() {
//        received = new byte[size];
//        return new DatagramPacket(received, received.length);
////        datagramReceiveFilePacket = new DatagramPacket(received, received.length);
////        try {
////            datagramClientSocket.receive(datagramReceiveFilePacket);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        String sPort = (new String(datagramReceiveFilePacket.getData()));
////        System.out.println("Port for dataTransfer: " + sPort);
////
////        return Integer.valueOf(sPort);
//    }
}

