public class QuizBank {

	final int WAITING = 0;
	final int PROBLEM = 1;
	final int ANSWER = 2;
	int state = WAITING;
	int currentProblem = 0;
	int count = 0;
	
	String[] problems = {"네트워크 처리 패키지의 이름은?",
			     "서버 소켓의 클래스 이름은??",
			     "인터넷에서 컴퓨터를 식별하는 주소는?"
			    };
	
	String[] answers = {"java.net","ServerSocket","IP주소"};
	
	public String process(String input) {
		String output = null;
		
		if(state == WAITING) {
			output = "퀴즈를 시작합니다.(y/n)";
			state = PROBLEM;
		}else if(state == PROBLEM) {
			if(input.equals("y")) {
				output = problems[currentProblem];
				state = ANSWER;
			}else if(input.equals("n")){
				state = WAITING;
				output = "총 맞추신 개수는" + count + "입니다.";
				
			}else {
				output = "값을 잘못입력하셨습니다.(y/n만으로 입력해주세요)";
			}
		}else if(state == ANSWER) {
			if(input.equals(answers[currentProblem])) {
				output = "정답입니다. 계속하시겠습니까? (y/n)";
				state = PROBLEM;
				count++;
			}else {
				output = "오답입니다. 계속하시겠습니까? (y/n)";
				state = PROBLEM;
			}
			currentProblem = ( currentProblem + 1 ) % 3; // 012012012012
		}

		return output;
	}
}
