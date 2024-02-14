public class QuizBank {

	final int WAITING = 0;
	final int PROBLEM = 1;
	final int ANSWER = 2;
	int state = WAITING;
	int currentProblem = 0;
	
	String[] problems = {"네트워크 처리 패키지는?",
						"서버쪽 소켓 클래스는?",
						"인터넷에서 컴퓨터를 식별하는 주소는?"
						};
	
	String[] answers = {"java.net","ServerSocket","IP주소"};
	
	public String process(String input) { // input:null
		String output = null;
		
		//state:WAITING=>PROBLEM
		
		if(state == WAITING) {
			output = "퀴즈를 시작합니다.(y/n)";
			state = PROBLEM;
		}else if(state == PROBLEM) {
			if(input.equals("y")) {
				output = problems[currentProblem];
				state = ANSWER;
			}else {
				state = WAITING;
				output = "quit";
			}
		}else if(state == ANSWER) {
			if(input.equals(answers[currentProblem])) {
				output = "정답입니다. 계속하시겠습니까? (y/n)";
				state = PROBLEM;
			}else {
				output = "오답입니다. 계속하시겠습니까? (y/n)";
				state = PROBLEM;
			}
			currentProblem = ( currentProblem + 1 ) % 3; // 012012012012
		}
		
		return output; // 퀴즈시작
	}
}
