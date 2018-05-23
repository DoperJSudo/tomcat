import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;

public class ChatbotServer {
//    static {
//	try {
//	    LocateRegistry.createRegistry(2099);
//	}
//	catch (RemoteException e) {
//	    e.printStackTrace();
//	}
//    }
    public static void main(String args[]) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String portNum, registryURL;
        try{
            System.out.println("Here is new Server. Enter the RMIregistry port number: ");
            //portNum = (br.readLine()).trim();
            portNum = "10990";
            int RMIPortNum = Integer.parseInt(portNum);
            startRegistry(RMIPortNum);
            ChatbotImpl exportedObj = new ChatbotImpl();
            registryURL = "rmi://172.18.189.72:" + portNum + "/hello";
            Naming.rebind(registryURL, exportedObj);
            System.out.println("Server registed. Registry currently contains: ");
            listRegistry(registryURL);
            System.out.println("Chatbot Server ready.");
        }
        catch (Exception re) {
            System.out.println("Exception in ChatbotServer.main " + re);
        }
    }
    private static void startRegistry(int RMIPortNum) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list();
        }
        catch(RemoteException r) {
            System.out.println("RMI registry cannot be located at port " + RMIPortNum);
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
            System.out.println("RMI registry created at port " + RMIPortNum);
        }
    }
    private static void listRegistry(String registryURL) throws RemoteException, MalformedURLException {
        System.out.println("Registry " + registryURL + " contains: ");
        String[] names = Naming.list(registryURL);
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }
}
