import java.rmi.*;
import java.io.*;
import java.util.*;

public class ChatbotClient {
    public static void main(String args[]) {
        try {
            int RMIPort;
            String hostName;
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            hostName = "localhost";
            RMIPort = 10990;
            String registryURL = "rmi://" + hostName + ":" + RMIPort + "/hello";
            ChatbotInterface h = (ChatbotInterface) Naming.lookup(registryURL);
            String message = h.answer("?");
	    System.out.println(message);
        }
        catch(Exception ex) {
            System.out.println("Exception in ChatbotClient" + ex);
        }

    }
}
