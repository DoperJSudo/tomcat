import java.rmi.*;
import java.io.*;

public interface ChatbotInterface extends Remote{
    public String answer(String question) throws java.rmi.RemoteException, java.io.IOException;
}
