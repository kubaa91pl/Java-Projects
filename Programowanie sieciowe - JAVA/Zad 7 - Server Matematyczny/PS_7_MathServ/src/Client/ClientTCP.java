package Client;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Admin on 2016-06-12.
 */
public class ClientTCP {
    private static boolean exit;
    private static int port = 6456;

    public static void main(String args[]) throws IOException {

        String received;
        Socket socket = new Socket("localhost", port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        Scanner scanner = new Scanner(System.in);
        String equasion;
        String result;
        exit = false;
        System.out.println("Write equasion: ");
        equasion = scanner.nextLine();
        out.write(equasion+"\n");
        out.flush();
        System.out.println("sent: "+equasion);
        result = in.readLine();
        System.out.println(result);


        out.close();
        in.close();
        socket.close();
    }
}