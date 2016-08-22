import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.*;

/**
 * Created by Admin on 2016-04-17.
 */
public class PortsScanner {
    private static boolean isOpenTCP(String ip, int port, int timeout) {
        try {
            new Socket().connect(new InetSocketAddress(ip, port), timeout);
            new Socket().close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static boolean isOpenUDP(InetAddress IP, int port) {
        try {
            byte[] bytes = new byte[128];
            DatagramSocket datagramSocket = new DatagramSocket();
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, IP, port);
            datagramSocket.setSoTimeout(1000);
            datagramSocket.send(datagramPacket);
            datagramPacket = new DatagramPacket(bytes, bytes.length);
            datagramSocket.receive(datagramPacket);
            datagramSocket.close();
        } catch (InterruptedIOException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        InetAddress adr = null;
        try {
            adr = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Integer start = Integer.parseInt(args[2]);
        while (start <= Integer.parseInt(args[3])) {
            if (args[1].equals("TCP")) {
                boolean openTCP = isOpenTCP(args[0], start, 1000);
                if (openTCP) System.out.println("Port " + start + " jest otwarty");
                else {
                    System.out.println("Port" + start + " jest zamkniety");
                }
                start++;

            }
            else if(args[1].equals("UDP")){
                boolean openUDP = isOpenUDP(adr, start);
                if (openUDP) System.out.println("Port " + start + " jest otwarty");
                else {
                    System.out.println("Port" + start + " jest zamkniety");
            }
                start++;
            }
        }
    }
}

