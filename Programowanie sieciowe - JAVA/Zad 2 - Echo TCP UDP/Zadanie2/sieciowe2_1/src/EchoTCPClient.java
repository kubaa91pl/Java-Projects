/**
 * Created by Admin on 01.04.2016.
 */
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoTCPClient {
    private static final int PORT_ECHO = 7;
    private static final int LINELEN = 100;

    public static void main(String[] args) {
        byte[] buffer = new byte[LINELEN];
        if (args.length<2){
            System.out.println("wywolanie java EchoTCPClient serwer argument1 argument2 ...");
            return;
        }
        try {
            Socket sock = new Socket(args[0], PORT_ECHO);
            OutputStream os = sock.getOutputStream();
            InputStream in = sock.getInputStream();
            int i, k, j;
            for (i=1; i<args.length; i++){
                os.write(args[i].getBytes());
                for(j=0; j<args[i].length(); j+=k){
                    k = in.read(buffer);
                    System.out.print(new String(buffer, 0, k));
                }
                System.out.println();
            }
            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}