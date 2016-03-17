package messenger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.UUID;

public class Server {
    public Server() {
        this.port = 9090;
    }
    
    
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void start() throws IOException {
        System.out.println("Starting the socket server at port:" + port);
        serverSocket = new ServerSocket(port);
        
        //Listen for clients. Block till one connects
        System.out.println("Waiting for clients...");
        
        while (true) {
            Socket client = serverSocket.accept();
            
            InetAddress address = client.getLocalAddress();
            
            System.out.println("Client connected: " + address.getHostAddress()+ ":" + client.getPort());
            
            String request = readRequest(client);
            if(request != null && request.isEmpty() == false){
                String[] command = request.split(";");
                if(command.length==0){
                    System.out.println("Bad command");
                }
                else{
                    if(command[0].equals("login")){
                        if(command.length<5){
                            System.out.println("Bad command");
                            sendMessage(client, "-1");
                        }
                        else{
                            int userId = DatabaseHelper.getUserId(command[1], command[2]);
                            
                            String ipAddress = command[3];
                            int port = Integer.parseInt(command[4]);
                            
                            boolean isSessionCreated = false;
                            if(userId!=-1){
                                isSessionCreated = DatabaseHelper.addSession(UUID.randomUUID(), userId, ipAddress, port);
                            }
                            
                            if(isSessionCreated){
                                sendMessage(client, Integer.toString(userId));
                            }
                            else{
                                sendMessage(client, "-1");
                            }
                        }
                    }
                    else if(command[0].equals("logout")){
                        if(command.length<2){
                            System.out.println("Bad command");
                            sendMessage(client, "false");
                        }
                        else{
                            int userId = Integer.parseInt(command[1]);
                            boolean isSessionRemove = DatabaseHelper.removeSession(userId);
                            
                            if(isSessionRemove){
                                sendMessage(client, "true");
                            }
                            else{
                                sendMessage(client, "false");
                            }
                        }
                    }
                    else if(command[0].equals("message")){
                        if(command.length<5){
                            System.out.println("Bad command");
                            sendMessage(client, "false");
                        }
                        else{
                            int from = Integer.parseInt(command[1]);
                            int to = Integer.parseInt(command[2]);
                            Timestamp date = Timestamp.valueOf(command[3]);
                            String message = command[4];

                            DatabaseHelper.addMessage(from, to, date, message);

                            int port = DatabaseHelper.getPort(to);
                            
                            if(port!=-1){
                                Client refreshClient = new Client("localhost", port);
                                refreshClient.connect();
                                refreshClient.sendRefresh(from);
                            }
                            
                            sendMessage(client, "true");
                        }
                    }
                }
            }
            else{
                System.out.println("Request is empty");
            }
            
        }
    }
    
    
    
    private void sendMessage(Socket client, String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        writer.write(message);
        writer.flush();
        writer.close();
    }
    
    private String readRequest(Socket client) throws IOException{
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));

        return stdIn.readLine();
    }
    

    private final int port;
    
    private ServerSocket serverSocket;
}