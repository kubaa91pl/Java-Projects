/**
 * Created by Admin on 02.04.2016.
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class TimeUDPClient {
    private static final int PORT_TIME = 37;
    private static final String QUERY = "Ktora godzina?";
    private static final int LINELEN = 5;
    private static final long UNIXEPOCH = 2208988800L;

    public static void main(String[] args) {
        try {
            byte[] buffer = new byte[LINELEN];
            DatagramSocket sock = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(QUERY.getBytes(), 0, QUERY.length(), InetAddress.getByName(args[0]), PORT_TIME);

            sock.send(dp);
            dp = new DatagramPacket(buffer, LINELEN);
            sock.receive(dp);
            long time = 0;
            int i;
            for (i=0; i<4; i++){
                time *= 256;
                time += (buffer[i] & 255);
            }
            time -= UNIXEPOCH;
            time *= 1000;
            Date d = new Date(time);
            System.out.println(d);
            sock.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

}
