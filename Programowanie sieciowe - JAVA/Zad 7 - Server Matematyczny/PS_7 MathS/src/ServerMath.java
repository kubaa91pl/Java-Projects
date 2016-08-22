import java.io.IOException;
import java.net.ServerSocket;

public class ServerMath {


    public static class ServerUDP extends Thread{

        int clientNumber = 0;
        int port;


        ServerUDP(int port) {
            this.port = port;
            start();
        }

        public void run(){


            new ClientService(clientNumber, "UDP", port);
            clientNumber++;


        }
    }

    public static class ServerTCP extends Thread{
        ServerSocket serverSocket;
        int port;
        int clientNumber = 0;


        ServerTCP(int port){
            this.port = port;
            start();
        }


        public void run(){
            try {
                serverSocket = new ServerSocket(port);

                while(true){
                    new ClientService(serverSocket.accept(), clientNumber, "TCP");
                    clientNumber++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void  main(String[] args){

        new ServerTCP(345);
        new ServerUDP(112);

    }

}

