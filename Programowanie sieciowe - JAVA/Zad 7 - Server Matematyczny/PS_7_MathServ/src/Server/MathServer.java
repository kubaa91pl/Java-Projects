package Server;

public class MathServer {

    public static class ServerUDP implements Runnable{
        int clientID = 0;
        int port;

        public ServerUDP(int port) {
            this.port = port;

            new Thread(this).start();
        }

        @Override
        public void run(){
            new ClientService(port, clientID, "UDP");
            clientID++;
        }
    }

    public static class ServerTCP implements Runnable{
        int clientID = 0;
        int port;

        public ServerTCP(int port) {
            this.port = port;

            new Thread(this).start();
        }

        @Override
        public void run() {
            new ClientService(port, clientID, "TCP");
            clientID++;
        }
    }

    public static void main(String[] args) {
        new ServerTCP(6456);
        new ServerUDP(3334);
    }
}



