import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

public class ServerThread extends Thread {
    private String recievedMessage;
    private BufferedReader incommingMessage;
    private DataOutputStream outgoingMessage;

    private ArrayList<int[]> cordinates = new ArrayList<>();


    //Constructors ---------------------------------------------------------------------------------------------------

    public ServerThread(Socket socket) throws IOException {
        incommingMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outgoingMessage = new DataOutputStream(socket.getOutputStream());
        int[] cord1 = {9, 4};
        int[] cord2 = {14, 15};
        int[] cord3 = {4, 5};
        cordinates.add(cord1);
        cordinates.add(cord2);
        cordinates.add(cord3);


    }


    //Methods - Get, Set & Add ---------------------------------------------------------------------------------------


    //Methods - Other ------------------------------------------------------------------------------------------------
    @Override
    public void run() {
        while (true) {
            try {
                recievedMessage = incommingMessage.readLine();
                Server.notifyThreads(recievedMessage);
            } catch (IOException e) {

            }
        }
    }

    public void sentMessage(String message) {
        try {
            outgoingMessage.writeBytes(message + '\n');
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void assignPlayer(Set<String> keySet, int playerNumber) {
        try {
            outgoingMessage.writeBytes("Assign " + keySet.toArray()[playerNumber] + " " + cordinates.get(playerNumber - 1)[0] + " " + cordinates.get(playerNumber - 1)[1] + '\n');
            String players = "Place";
            for (String s : keySet) {
                players += s + " ";
            }

            Server.notifyThreads(players);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
