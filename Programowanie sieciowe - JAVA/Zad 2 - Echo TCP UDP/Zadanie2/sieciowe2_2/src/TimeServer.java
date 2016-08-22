import java.net.*;
import java.util.Date;

/**
 * Created by Admin on 02.04.2016.
 */
public class TimeServer {
    private static final int PORT_TIME = 37;
    private static final int PORT_DAYTIME = 13;
    private static final int LINELEN = 50;
    private static final long differenceBetweenEpochs = 2208988800L;

    public static void main(String [] args) throws Exception {
        if(args.length != 2){
            System.out.println("The program requires 2 arguments");
        }
        else if(!args[0].equals("TIME") && (!args[0].equals("DAYTIME")) ){
            System.out.println("Choose between TIME and DAYTIME to be the first argument");
        }
        else if(args[0].equals("TIME")) {
            System.out.println("TIME >>>");
            if(args[1].equals("UDP")) {
                System.out.println("UDP>>>");
                DatagramSocket datagramSocket = new DatagramSocket(PORT_TIME);
                byte[] incommingData;

                while (true) {
                    System.out.println("Waiting for a client...");
                    incommingData = new byte[LINELEN];

                    DatagramPacket datagramPacket = new DatagramPacket(incommingData, incommingData.length);
                    datagramSocket.receive(datagramPacket);

                    InetAddress ip = datagramPacket.getAddress();
                    int port = datagramPacket.getPort();

                    Date date = new Date();

                    long msSince1970 = date.getTime();
                    long sSince1970 = msSince1970/1000;
                    long sSince1900 = sSince1970 + differenceBetweenEpochs;

                    byte[] time = new byte[4];
                    time[0] = (byte) ((sSince1900 & 0x00000000FF000000L) >> 24);
                    time[1] = (byte) ((sSince1900 & 0x0000000000FF0000L) >> 16);
                    time[2] = (byte) ((sSince1900 & 0x000000000000FF00L) >> 8);
                    time[3] = (byte) (sSince1900 & 0x00000000000000FFL);

                    datagramPacket = new DatagramPacket(time, time.length, ip, port);
                    datagramSocket.send(datagramPacket);
                }
            }
        } else { //args[0] = DAYTIME
            System.out.println("DAYTIME>>>");
            if (args[1].equals("UDP")) {
                System.out.println("UDP>>>");
                DatagramSocket datagramSocket = new DatagramSocket(PORT_DAYTIME);
                byte[] message = null;

                while (true) {
                    System.out.println("Waiting for a client...");

                    message = new byte[LINELEN];

                    DatagramPacket datagramPacket = new DatagramPacket(message, message.length);
                    datagramSocket.receive(datagramPacket);

                    InetAddress IP = datagramPacket.getAddress();
                    int port = datagramPacket.getPort();

                    String presentDate = (new Date()).toString();
                    byte[] feedback = presentDate.getBytes();

                    datagramPacket = new DatagramPacket(feedback, feedback.length, IP, port);
                    datagramSocket.send(datagramPacket);
                }
            }
        }
    }
}
