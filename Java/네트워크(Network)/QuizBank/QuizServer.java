QuizServer.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizServer {
	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(9191);

		}catch (IOException e) {
			System.exit(1);
		}
		Socket clientSockect = null;
		try {
			clientSockect = serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// client로 데이터를 보내기 위한 스트림 형성
		PrintWriter out = new PrintWriter(clientSockect.getOutputStream(),true);
		
		// client로부터 데이터를 받기 위한 스트림 형성
		BufferedReader in = new BufferedReader(
								new InputStreamReader(clientSockect.getInputStream()));
		
		String outputLine,inputLine;
		QuizBank bank = new QuizBank();
		outputLine = bank.process(null); // 퀴즈시작
		out.println(outputLine);
		
		while( (inputLine = in.readLine()) != null) {
			outputLine = bank.process(inputLine);
			out.println(outputLine);
			if(outputLine.equals("quit"))
				break;
		}
		
		out.close();
		in.close();
		clientSockect.close();
		serverSocket.close();
	}
}
