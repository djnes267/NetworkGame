import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static Map<String, ServerThread> threads = new HashMap<>();

    public static void main(String[] args) throws Exception {
        //start server
        ServerSocket serverSocket = new ServerSocket(8877);
        while (true) {
            Socket connectionSocket = serverSocket.accept();

            System.out.println("Forbindelse oprettet");

            ServerThread serverThread = new ServerThread(connectionSocket);

            threads.put("player" + threads.size(), serverThread);

            System.out.println(threads.size());

            serverThread.assignPlayer(threads.keySet(), threads.size());

            serverThread.start();

        }
    }
    public static void notifyThreads(String message){
        for (ServerThread t: threads.values()){
            t.sentMessage(message);
        }
    }

}
