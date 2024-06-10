package obinox.com;

import java.io.*;
import java.net.*;

public class Client {

    private static final String SERVER_IP = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        MahjongFrame mf = new MahjongFrame();
        try (Socket socket = new Socket(SERVER_IP, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            mf.out = out;
            mf.in = in;

            new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        if (message.equals("===start")){
                            mf.mode = 2;
                        } else if (message.startsWith("===hand:")){
                            mf.tileString = message.substring(8);
                            mf.handLen = mf.tileString.length()/2;
                        } else if (message.startsWith("===tsumo:")){
                            mf.tsumoString = message.substring(9);
                        } else if (message.startsWith("===input:")){
                            mf.canInput = message.substring(9).equals("0");
                        } else if (message.startsWith("===kawa0:")){
                            mf.myKawaString = message.substring(9);
                        } else if (message.startsWith("===kawa1:")){
                            mf.shimoKawaString = message.substring(9);
                        } else if (message.startsWith("===kawa2:")){
                            mf.toiKawaString = message.substring(9);
                        } else if (message.startsWith("===kawa3:")){
                            mf.kamiKawaString = message.substring(9);
                        } else if (message.startsWith("===dora:")){
                            mf.doraString = message.substring(8);
                        }
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String message;

            while ((message = console.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}