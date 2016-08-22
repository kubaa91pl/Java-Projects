import java.io.File;
import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * Created by Admin on 2016-05-29.
 */
public class Sender {
    private final int size = 99999;
    private int fileSize;
    private boolean consist = false;
    private File folder;
    private String fileList[];
    private StringBuilder stringBuilder;
    private static final String port2 = "6666";
    private String filename;

    byte[] sendData;
    String sendMessage;
    DatagramPacket datagramSendPacket;

    private String[] createFileList(){
        stringBuilder = new StringBuilder();
        folder = new File("files/");
        return folder.list();

    }

    public DatagramPacket sendFileList(InetAddress IP, int port){
        fileList = createFileList();
        stringBuilder.append("List: ");
        for(String s : fileList){
            stringBuilder.append(s+" ");
        }
        sendMessage = stringBuilder.toString();

        System.out.println(sendMessage);

        sendData = sendMessage.getBytes();
        datagramSendPacket = new DatagramPacket(sendData, sendData.length, IP, port);
        return datagramSendPacket;
    }

    public DatagramPacket sendFile(String fileName, InetAddress IP, int port){
        this.filename = fileName;
        consist = false;
        String[] filelist = createFileList();

        for(String s: filelist){
            if(s.equals(fileName)){
                consist = true;
                break;
            }
        }

        if(consist == false){
            sendMessage = "File doesnt exit.";

            sendData = sendMessage.getBytes();
            datagramSendPacket = new DatagramPacket(sendData, sendData.length, IP, port);
            return datagramSendPacket;
        }
        else{
            sendMessage = "File has been found.";
            sendData = sendMessage.getBytes();
            datagramSendPacket = new DatagramPacket(sendData, sendData.length, IP, port);
            return datagramSendPacket;

        }

    }

    public DatagramPacket sendPortFilenameSize(int portDataTransfer, InetAddress IP, int port) {
        File file = new File("files/"+filename);
        fileSize = (int)file.getTotalSpace();
        sendMessage = String.valueOf(portDataTransfer)+"@"+filename;
        sendData = sendMessage.getBytes();
        datagramSendPacket = new DatagramPacket(sendData, sendData.length, IP, port);
        return datagramSendPacket;

    }
}
