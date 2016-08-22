import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Admin on 2016-06-05.
 */
public class DataSender {

    public DataSender(int port, String filename) throws IOException {
        ServerSocket servsock = null;
        try {
            servsock = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File myFile = new File("files/"+filename);
                Socket sock = servsock.accept();
                byte[] mybytearray = new byte[(int) myFile.length()];
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
                bis.read(mybytearray, 0, mybytearray.length);
                OutputStream os = sock.getOutputStream();
                os.write(mybytearray, 0, mybytearray.length);
                os.flush();
                sock.close();

    }
}
