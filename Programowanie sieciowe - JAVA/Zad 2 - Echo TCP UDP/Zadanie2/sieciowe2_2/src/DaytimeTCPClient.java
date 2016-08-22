import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Admin on 02.04.2016.
 */
public class DaytimeTCPClient {
    private static final int PORT_DAYTIME = 13;

    public static void main(String [] args) {
        String host = args[0];
        Socket socket = null;
        BufferedReader bufferedReader = null;

        try {
            socket = new Socket(host, PORT_DAYTIME);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String time = bufferedReader.readLine();
            System.out.println("Host: " + host + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
