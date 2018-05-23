import javax.servlet.*;
import java.rmi.*;
import java.util.*;
import javax.servlet.http.*;
import java.io.*;

public class Ch1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
	    java.util.Date today = new java.util.Date();
	    out.println("<html>" + 
		    "<body>" + 
		    "<h1 align=center>HF\'s Chapter1 Servlet</h1>" + 
		    "<br>" +
		    today + 
		    "</body>" + 
		    "</html>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String question = request.getParameter("txt");
		try {
//        try {
//            int RMIPort;
//            String hostName;
//            InputStreamReader is = new InputStreamReader(System.in);
//            BufferedReader br = new BufferedReader(is);
//            hostName = "localhost";
//            RMIPort = 10990;
//            String registryURL = "rmi://" + hostName + ":" + RMIPort + "/hello";
//            ChatbotInterface h = (ChatbotInterface) Naming.lookup(registryURL);
//            String message = h.answer("DoperJ");
//	    System.out.println(message);
//        }
//        catch(Exception ex) {
//            System.out.println("Exception in ChatbotClient" + ex);
//        }
		Process pr = Runtime.getRuntime().exec("python /root/apache-tomcat-8.5.30/webapps/chatbot/python/predict.py " + question);
		BufferedReader err_br = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
		String line;
		String answer = "";
		while ((line = err_br.readLine()) != null) {
		    //answer += line;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		while ((line = br.readLine()) != null) {
		    answer += line;
		}
		int ex = pr.waitFor();
		    PrintWriter out = response.getWriter();
		    //out.println(answer + " what?");
		    //out.println(ex == 0 ? "succeed" : "fail");
		    out.println(answer);
		    br.close();
		}
		catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
}

