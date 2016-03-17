package messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public Client(){
        this.hostname = "localhost";
        this.port = 9090;
    }
    
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
    
    
    
    public int sendLogin(String userName, String password) throws IOException {
        PrintWriter writer = new PrintWriter( clientSocket.getOutputStream(), true);
        writer.println("login;" + userName+ ";" + password);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String request = reader.readLine();
        
        int userId = -1;
        
        try{
            userId = Integer.parseInt(request);
        }
        catch(NumberFormatException exception){
            
        }
        
        
        return userId;
    }
    
    public int sendLogin(String userName, String password, String ipAddress, int port) throws IOException {
        PrintWriter writer = new PrintWriter( clientSocket.getOutputStream(), true);
        writer.println("login;" + userName + ";" + password + ";" + ipAddress  + ";" +  port);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String request = reader.readLine();
        
        int userId = -1;
        
        try{
            userId = Integer.parseInt(request);
        }
        catch(NumberFormatException exception){
            
        }
        
        
        return userId;
    }
    
    boolean sendLogout(int currentUserId) throws IOException  {
        PrintWriter writer = new PrintWriter( clientSocket.getOutputStream(), true);
        writer.println("logout;" + currentUserId);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String request = reader.readLine();
        
        boolean result = false;
        
        try{
            result = Boolean.parseBoolean(request);
        }
        catch(NumberFormatException exception){
            
        }
        
        return result;
    }
    
    public boolean sendMessage(Message message) throws IOException {
        PrintWriter writer = new PrintWriter( clientSocket.getOutputStream(), true);
        writer.println("message;" + message.getFrom()+ ";" + message.getTo() + ";" + message.getDate() + ";" + message.getMessage());
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String request = reader.readLine();
        
        boolean isMessageCreated = false;
        
        try{
            isMessageCreated = Boolean.parseBoolean(request);
        }
        catch(Exception exception){
            
        }
        
        return isMessageCreated;
    }
    
    public void sendRefresh(int contactId) throws IOException {
        PrintWriter writer = new PrintWriter( clientSocket.getOutputStream(), true);
        writer.println("refresh;" + contactId);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String request = reader.readLine();
        
        boolean isMessageCreated = false;
        
        try{
            isMessageCreated = Boolean.parseBoolean(request);
        }
        catch(Exception exception){
            
        }
    }
    
    
    
    private String hostname;
    private int port;
    private static Socket clientSocket;
}