import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class QuizClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket clientSocket = new Socket("localhost", 6666);

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);    
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));   
		
		

		String fromServer, fromClient;                                            
		while(true){
			fromServer = in.readLine();
			System.out.println("서버 :" + fromServer);
			if(fromServer.contains("총 맞추신 개수는")) {
				break;
			}
			
			fromClient = kb.readLine();
			if(!(fromClient.equals("y") || fromClient.equals("n"))) {
				fromClient = null;
				out.println(fromClient);
			}
			
			if(fromClient != null) {
				out.println(fromClient);                   
			}
		}
		if(out != null) out.close(); 
		in.close();
		kb.close();
		clientSocket.close();
	}
}
