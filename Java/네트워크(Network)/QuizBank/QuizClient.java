QuizClient.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class QuizClient {
	public static void main(String[] args) {

		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		BufferedReader kb = null;
		
		try {
			clientSocket = new Socket("localhost",9191);
			
			// server로 데이터를 보내기 위한 스트림 형성
			out = new PrintWriter(clientSocket.getOutputStream(),true);
			
			// server로부터 데이터를 받기 위한 스트림 형성
			in = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
			
			kb = new BufferedReader(
					new InputStreamReader(System.in));
			
			String fromServer, fromClient;
			while( (fromServer = in.readLine()) != null) {
				System.out.println("서버:" + fromServer);
				if(fromServer.equals("quit"))
					break;
				
				fromClient = kb.readLine();
				if(fromClient != null) {
					System.out.println("클라이언트:" + fromClient);
					out.println(fromClient);
				}
			}
		}catch (UnknownHostException e) {
			System.exit(1);
		}catch (IOException e) {
			System.exit(1);
		} finally {
			try {
				if(out != null)
					out.close();
				in.close();
				kb.close();
				clientSocket.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
