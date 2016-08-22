package Server;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.net.*;

/**
 * Created by Admin on 2016-06-10.
 */

public class ClientService implements Runnable {
    private final int clientID;
    private String protocol;
    private int port;
    private int size = 1024;

    private boolean error;

    public ClientService(int port, int clientID, String protocol) {
        this.port = port;
        this.clientID = clientID;
        this.protocol = protocol;
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (this.protocol.equals("UDP")) {
            InetAddress ip;
            String message;
            DatagramSocket datagramSocket;
            Object result = 1;
            String sResult;
            try {
                datagramSocket = new DatagramSocket(port);
                byte[] buffer = new byte[size];
                String fromClient;
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(receivePacket);
                fromClient = new String(receivePacket.getData());
                System.out.println("Client has been conneceted: " + fromClient);
                ip = receivePacket.getAddress();
                buffer = "Welcome".getBytes();
                datagramSocket.send(new DatagramPacket(buffer, buffer.length, receivePacket.getAddress(), receivePacket.getPort()));
                System.out.println("Sending: "+ buffer);

                while (true) {
                    buffer = new byte[size];
                    receivePacket = new DatagramPacket(buffer, buffer.length);
                    datagramSocket.receive(receivePacket);
                    String equasion = new String(receivePacket.getData()).trim();
                    System.out.println("equasion: "+equasion);
                    try {
                        error = false;
                        ScriptEngineManager manager = new ScriptEngineManager();
                        ScriptEngine engine = manager.getEngineByName("js");
                        result = engine.eval(equasion);
                    }
                    catch(Exception e){
                        System.out.println("Error");
                        error = true;
                    }
                    if(error){
                        sResult = "Wrong expression";
                    }
                    else {
                        System.out.println("Result: " + result);
                        sResult = String.valueOf(result);
                    }
                    buffer = sResult.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, receivePacket.getAddress(), receivePacket.getPort());
                    System.out.println("sending....");
                    datagramSocket.send(sendPacket);
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(6456);
                Socket clientSocket = serverSocket.accept();
                System.out.println("accepted");
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String fromClient;
                Object result =1;
                String sResult;
                fromClient = reader.readLine();
                System.out.println("from client: "+fromClient);


                if(fromClient.toLowerCase().trim().equals("exit")) {
                    reader.close();
                    writer.close();
                }
                else{
                    try {
                        error = false;
                        ScriptEngineManager manager = new ScriptEngineManager();
                        ScriptEngine engine = manager.getEngineByName("js");
                        result = engine.eval(fromClient);
                    }
                    catch(Exception e){
                        System.out.println("Error");
                        error = true;
                    }
                    if(error){
                        sResult = "Wrong expression";
                    }
                    else {
                        System.out.println("Result: " + result);
                        sResult = String.valueOf(result);
                        writer.write("Result: "+ sResult+"\n");
                        writer.flush();
                    }
                    clientSocket.close();
                    System.out.println("Client has disconnected");
                    }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
