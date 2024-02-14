import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(6666);	

		
		System.out.println("Waiting...");
		Socket clientSocket = serverSocket.accept();
		System.out.println("connect!!!");
		
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 


		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

		String outputLine, inputLine;
		
		QuizBank bank = new QuizBank();
		outputLine = bank.process(null);      
		out.println(outputLine);

		while(true) {
			inputLine = in.readLine();
			outputLine = bank.process(inputLine);
			out.println(outputLine);
			if(outputLine.contains("총 맞추신 개수는"))
				break;
		}

		if(out != null) out.close(); 
		in.close();
		clientSocket.close();
		serverSocket.close();

	}
}
