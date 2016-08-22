import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class ClientService extends Thread{
    Socket clientSocket;
    DataInputStream dataIN;
    DataOutputStream dataOUT;
    int clientNumber;
    DatagramSocket datagramSocket;
    DatagramPacket dataPacket;
    byte[] dataBuffer = new byte[100];
    String protocol;

    ClientService(Socket socket, int clientNumber, String protocol) throws IOException  {
        this.clientSocket = socket;
        this.clientNumber = clientNumber;
        this.protocol = protocol;
        dataIN = new DataInputStream(socket.getInputStream());
        dataOUT = new DataOutputStream(socket.getOutputStream());
        start();
    }

    ClientService(int clientNumber, String protocol, int port){
        this.clientNumber = clientNumber;
        this.protocol = protocol;
        try {
            datagramSocket = new DatagramSocket(port);
            dataPacket = new DatagramPacket(dataBuffer, dataBuffer.length);

        }catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    public double calc(String operation){

        String factor1 = "";
        String operator = "";
        String factor2 = "";
        boolean negative = true;
        for(int i = 0; i < operation.length(); i++){
            if(operation.charAt(i) == 20){

            }
            else if(operation.charAt(i) > 47 && operation.charAt(i) < 58 || operation.charAt(i) == 46 || operation.charAt(i) == 45){
                if(negative == true && operation.charAt(i) == 45)
                    factor1 += operation.substring(i, i+1);
                else if(negative == false && operation.charAt(i) == 45)
                    continue;
                else
                    factor1 += operation.substring(i, i+1);
                negative = false;
            }
            else if(operation.charAt(i) == 37 || operation.charAt(i) == 43 || operation.charAt(i) == 45 || operation.charAt(i) == 47 || operation.charAt(i) == 42){
                operator = operation.substring(i, i+1); i++;
                negative = true;
                for(int j = 0; j<operation.length()-i; j++){
                    if(operation.charAt(j+i) == 20){}
                    else if(operation.charAt(j+i) > 47 && operation.charAt(j+i) < 58 || operation.charAt(j+i) == 46 || operation.charAt(i) == 45){

                        if(negative == true && operation.charAt(j+i) == 45) {
                            factor2 += operation.substring(j + i, j + i + 1);
                        }else
                            factor2 += operation.substring(j+i, j+i+1);

                    }
                    negative = false;
                }
                break;

            }
        }
        double result = 0;
        try {
            double doubleFactor1 = Double.parseDouble(factor1);
            double doubleFactor2 = Double.parseDouble(factor2);

            if (operator.equals("+"))
                result = doubleFactor1 + doubleFactor2;
            else if (operator.equals("/"))
                result = doubleFactor1 / doubleFactor2;
            else if (operator.equals("*"))
                result = doubleFactor1 * doubleFactor2;
            else if (operator.equals("%"))
                result = doubleFactor1 % doubleFactor2;
            System.out.println();
        }catch (NumberFormatException e){
            result = Double.MIN_VALUE;
        }
        return result;

    }


    public void run(){
        if(protocol.equals("TCP")) {
            while (true) {
                try {
                    String resultString;
                    String operation = dataIN.readUTF();
                    double result = calc(operation);
                    if (result == Double.MIN_VALUE)
                        resultString = "B³êdne wyra¿nie";
                    else
                        resultString = "" + result;
                    dataOUT.writeUTF(resultString);

                } catch (IOException e) {
                    System.out.println("Client nr " + clientNumber + " " + protocol +" siê od³¹czy³");
                    try {
                        clientSocket.close();
                        break;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        }else{
            while (true) {
                try {
                    String resultString;
                    datagramSocket.receive(dataPacket);
                    String operation = new String(dataPacket.getData(), 0, dataPacket.getLength());
                    double result = calc(operation);
                    if (result == Double.MIN_VALUE)
                        resultString = "Bledne wyrazenie";
                    else
                        resultString = "" + result;
                    dataPacket = new DatagramPacket(resultString.getBytes(), 0, resultString.length(), dataPacket.getAddress(), dataPacket.getPort());
                    datagramSocket.send(dataPacket);

                } catch (IOException e) {
                    System.out.println("Client nr " + clientNumber + protocol + " siê od³¹czy³");
                    datagramSocket.close();
                    break;
                }

            }

        }
    }



}
