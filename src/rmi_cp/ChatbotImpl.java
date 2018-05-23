import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.io.*;

public class ChatbotImpl extends UnicastRemoteObject implements ChatbotInterface{
    public ChatbotImpl() throws RemoteException {
        super();
    }
    public String answer(String question) throws RemoteException, IOException {
	Process pr = Runtime.getRuntime().exec("python /root/apache-tomcat-8.5.30/webapps/chatbot/python/predict.py " + question);
	BufferedReader err_br = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
	String line;
	String answer = "";
	while ((line = err_br.readLine()) != null) {
	    answer += line;
	}
	BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	while ((line = br.readLine()) != null) {
	    answer += line;
	}
	return answer;
    }
}


