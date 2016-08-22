package messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {   
    public Client(String host, int port){
        this.hostname = host;
        this.port = port;
    }
    
    
    
    public void connect(){
        try {
            System.out.println("Attempting to connect to "+hostname+":"+port);
            clientSocket = new Socket(hostname,port);
            System.out.println("Connection Established");
        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
        }
    }
    
    
    
    public void sendRefresh(int contactId) throws IOException {
        PrintWriter writer = new PrintWriter( clientSocket.getOutputStream(), true);
        writer.println("refresh;" + contactId);
    }
    
    
    
    private String hostname;
    private int port;
    private static Socket clientSocket;
}