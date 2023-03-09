import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReciever  extends Thread{
    //Fields ---------------------------------------------------------------------------------------------------------
    private String recievedMessage;
    private BufferedReader incommingMessage;

    //Constructors ---------------------------------------------------------------------------------------------------

    public MessageReciever(Socket socket) throws IOException {
        incommingMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }


    //Methods - Get, Set & Add ---------------------------------------------------------------------------------------


    //Methods - Other ------------------------------------------------------------------------------------------------
    @Override
    public void run () {
        while (true) {
            try {
                recievedMessage = incommingMessage.readLine();

             /*   if (server == true) {
                    //send kopi til alle
                }*/

                //Reager
                System.out.println(recievedMessage);

            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

}
