package messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Program {
    public static void main(String[] args) {
        try {
            ProperitiesHelper properities = new ProperitiesHelper();
            String port = properities.getPort();

            Login window = new Login();
            window.setVisible(true);
            
            SessionContext.setPort(Integer.parseInt(port));
            
            InetAddress address =InetAddress.getLocalHost();
            SessionContext.setIpAddress(address.getHostAddress());
            
            ServerSocket serverSocket = new ServerSocket(SessionContext.getPort());
            
            while (true) {
                Socket client = serverSocket.accept();

                String request = readRequest(client);
                if(request != null && request.isEmpty() == false){
                    String[] command = request.split(";");

                    if(command[0].equals("refresh")){
                        int contactId = Integer.parseInt(command[1]);
                        if(contactId == SessionContext.getSelectedContactId()){
                            MainWindow widnow = MainWindow.getInstance();
                            widnow.refreshMessage();
                        }
                    }
                }
                else{
                    System.out.println("Request is empty");
                }         
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    
    
    
    private static String readRequest(Socket client) throws IOException{
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));

        return stdIn.readLine();
    }
}