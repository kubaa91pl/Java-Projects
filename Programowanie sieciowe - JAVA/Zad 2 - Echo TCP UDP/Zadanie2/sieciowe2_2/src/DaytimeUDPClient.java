import java.io.IOException;
import java.net.*;

/**
 * Created by Created by Admin on 02.04.2016.
 */
public class DaytimeUDPClient {
    private static final int PORT_DAYTIME = 13;
    private static final int LINELEN = 50;

    public static void main(String [] args){
        try {
            DatagramSocket sock = new DatagramSocket();
            DatagramPacket pak;
            byte msg[] = new byte[LINELEN];
            InetAddress daytimeHost = InetAddress.getByName(args[0]);

            pak = new DatagramPacket( msg, LINELEN, daytimeHost, PORT_DAYTIME );

            sock.send(pak);
            sock.setSoTimeout(30000);
            sock.receive(pak);

            String received = new String( pak.getData() );
            received = received.trim();

            System.out.println( "Time: " + received );

            sock.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
