import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

public class SudokuMain extends JFrame {

	private LinkedList<SudokuBean> sudokuList;
	private LinkedList<SudokuBean> baseDataList;
	private Object[][] sudokuData; // 스도쿠 문제를 담을 배열
	private Object[][] sudokuAnswer = new Object[9][9]; // 스도쿠 해답을 담을 배열
	private JTable table;
	private JTextField[][] tf = new JTextField[9][9];
	private boolean[][] check_memoOnOff = new boolean[9][9];
	private JPanel[][] panel_memo = new JPanel[9][9];
	private JTextField tf_memo;
	private JButton[] north_btn = new JButton[5];
	private Container contentPane;
	private JLabel timer = new JLabel("00:00:00");
	private JPanel pCenter;
	private JButton resetTimer;
	private JButton startTimer;
	private JButton pauseTimer;
	private SudokuSolver ss;
	private SudokuDao sd;
	private TimerMaker t;
	private String formattedTime;
	private int r = 0;
	private int check = 0; // 중복된 스도쿠 문제가 나왔는지 확인하기 위한 변수
	private JButton save;
	private JButton memo; 
	private JButton hint; 
	private JButton reset;
	private JButton answer;
	private JButton logout;
	private int clickRow; // 마지막으로 클릭한 텍스트필드의 위치 저장
	private int clickCol; // 마지막으로 클릭한 텍스트필드의 위치 저장
	private String id;
	private String pw;
	private static String difficulty;

	private Font f = new Font("Dialog", Font.BOLD, 24); // 글자체, 글자크기 설정
	Border border = BorderFactory.createLineBorder(Color.black, 1); // 스도쿠 구분 테두리 설정

	// 생성자
	SudokuMain(String title, String id, String pw) {
		super(title);

		try {
			this.id = id;
			this.pw = pw;

			ss = new SudokuSolver(); // 스도쿠 해답을 찾는 머세드들을 가진 클래스
			sd = new SudokuDao(this.id, this.pw); // 데이터베이스와 연동하기 위한 메서드들을 가진 클래스

			difficulty = "easy";
			sudokuList = sd.selectByDifficulty(difficulty, id, pw); // 스도쿠 처음 실행시 default 값
			baseDataList = sd.setBaseData(difficulty);

			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					tf[i][j] = new JTextField();
				}
			}

			fillData(sudokuList);	// 데이터를 할당
			findAnswer(r);

			compose(); // 화면을 구성

			setSize(720,670);
			setVisible(true);
		} catch(SQLSyntaxErrorException e) {		
			sd = new SudokuDao();

			difficulty = "easy";
			sudokuList = sd.selectByDifficulty(difficulty, id, pw); // 스도쿠 처음 실행시 default 값
			baseDataList = sd.setBaseData(difficulty);

			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					tf[i][j] = new JTextField();
				}
			}

			fillData(sudokuList);	// 데이터를 할당


			compose(); // 화면을 구성

			setSize(720,670);
			setVisible(true);
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(contentPane, "틀린 비밀번호 입니다.", "pw 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 데이터 베이스로부터 데이터를 받아와 스도쿠 문제를 배열에 저장하는 메서드
	public void fillData(LinkedList<SudokuBean> sudokuList) {
		r = 0;

		do {
			r = (int)(Math.random() * 3); // 랜덤으로 스도쿠 문제를 생성하는 변수
		} while(check == r);

		check = r; // 이전과 중복된 스도쿠 문제가 나왔는지 확인하기 위한 변수

		findAnswer(r);

		sudokuData = sudokuList.get(r).getSudoku_puzzle();

		for(int i = 0; i < 9; i++) 
			for(int j = 0; j < 9; j++) {
				if(Integer.parseInt(sudokuData[i][j].toString()) == 0) {
					tf[i][j].setText("");
					tf[i][j].setBorder(border);
					tf[i][j].setLayout(new BorderLayout());
					panel_memo[i][j] = new JPanel();

					addMemoFunction(i, j);
					tf[i][j].add(panel_memo[i][j]);
				} else { 
					if(sudokuAnswer[i][j] == sudokuData[i][j]) {
						tf[i][j].setText(sudokuData[i][j].toString());
						tf[i][j].setEnabled(false);
						tf[i][j].setBorder(border);					
					} else {
						tf[i][j].setText(sudokuData[i][j].toString());
						tf[i][j].setBorder(border);
						tf[i][j].setLayout(new BorderLayout());
						panel_memo[i][j] = new JPanel();

						addMemoFunction(i, j);
						tf[i][j].add(panel_memo[i][j]);
					}
				}
			}	
	}


	public void findAnswer(int r) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sudokuAnswer[i][j] = baseDataList.get(r).getSudoku_puzzle()[i][j];
			}
		}
		ss.sudokuSolve(sudokuAnswer);
	}

	public void addMemoFunction(int i, int j) {
		panel_memo[i][j] = new JPanel();
		panel_memo[i][j].setLayout(new GridLayout(3,3));
		panel_memo[i][j].addMouseListener(new MouseHandler());
		for(int k = 0; k < 3; k++) {
			for(int l = 0; l < 3; l++) {
				tf_memo = new JTextField();
				tf_memo.setHorizontalAlignment(JTextField.CENTER);
				tf_memo.setBorder(border);
				panel_memo[i][j].add(tf_memo);
			}
		}
		panel_memo[i][j].setVisible(false);
	}

	// 데이터 값이 올바르게 들어갔는지 확인하는 메서드
	public void checkData(int col, int row) {
		try {
			if(tf[col][row].isEnabled()) {
				if(tf[col][row].getText().isEmpty()){}
				else if(!(1 <= Integer.parseInt(tf[col][row].getText()) && Integer.parseInt(tf[col][row].getText()) <= 9)) {
					JOptionPane.showMessageDialog(contentPane, "1에서 9까지의 숫자만 입력해주세요", "값 범위 오류", JOptionPane.ERROR_MESSAGE);
					tf[col][row].setText("");
					tf[col][row].requestFocus();						
				} 
			}
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPane, "숫자만 입력해주세요", "값 형식 오류", JOptionPane.ERROR_MESSAGE);
			tf[col][row].setText("");
			tf[col][row].requestFocus();
		}
	}

	// 화면 구성을 설정하는 메서드
	public void compose() {		
		// 테이블, 패널등을 담을 컨테이너 생성 
		contentPane = getContentPane();
		contentPane.setLayout(null);
		
		// 주의 문구 설정
		JLabel caution = new JLabel("저장을 생활화 합시다!");
		caution.setBounds(10,30, 300, 67);
		caution.setFont(f);
		contentPane.add(caution);

		// 테이블 설정	
		table = new JTable();
		table.setBounds(225, 150, 453, 453);
		table.setBorder(border);
		contentPane.add(table);

		// 텍스트 필드를 담을 패널 설정
		for (int i = 0; i < 9; i++) {
			pCenter = new JPanel();
			pCenter.setBorder(border);
			pCenter.setLayout(new GridLayout(3, 3));

			for (int j = (i / 3) * 3; j < (i / 3 + 1) * 3; j++) {
				for (int k = (i % 3) * 3; k < (i % 3 + 1) * 3; k++) {
					pCenter.add(tf[j][k]);
				}
			}

			pCenter.setBounds(150 * (i % 3) + 2, 150 * (i / 3) + 2, 149, 149);
			table.add(pCenter);
		}

		// 텍스트 필드 설정
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				tf[i][j].setFont(f); // 글자체, 글자크기 설정
				tf[i][j].setDisabledTextColor(Color.BLACK); // 값을 변경할 수 없는 텍스트 필드 값 검은색 설정
				tf[i][j].setHorizontalAlignment(JTextField.CENTER); // 모튼 텍스트 필드의 값을 중간 정렬
			}
		}

		// 버튼을 담을 패널 설정
		GridBagConstraints gbc = new GridBagConstraints();	
		JPanel pNorth = new JPanel();
		pNorth.setLayout(new GridBagLayout()); // 패널 그리드 레이아웃으로 설정
		pNorth.setBounds(275, 25, 350, 75); // 패널 위치 설정		
		pNorth.setBorder(border);

		f = new Font("SansSerif", Font.BOLD, 18);
		JLabel titleNorth = new JLabel("난이도");
		titleNorth.setAlignmentX(JLabel.CENTER);
		titleNorth.setFont(f);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 10;
		gbc.insets = new Insets(0,0,5,0);
		pNorth.add(titleNorth, gbc);

		String[] north_btnName = {"쉬움", "보통", "어려움", "전문가", "마스터"}; // 버튼의 이름

		for(int i = 0; i < north_btnName.length; i++) {
			north_btn[i] = new JButton(north_btnName[i]);
			gbc.gridx = i;
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			pNorth.add(north_btn[i], gbc);
		}

		contentPane.add(pNorth); // 패널을 컨테이너에 담음

		// 타이머를 담을 레이블 설정
		JPanel timerPanel = new JPanel();
		timerPanel.setName("타이머");
		timerPanel.setLayout(new GridBagLayout()); // 패널 그리드 레이아웃 설정

		JLabel titleTimer = new JLabel("타이머");
		titleTimer.setFont(f);

		resetTimer = new JButton("초기화");
		startTimer = new JButton("시작");
		pauseTimer = new JButton("중지");

		resetTimer.addMouseListener(new MouseHandler());
		startTimer.addMouseListener(new MouseHandler());
		pauseTimer.addMouseListener(new MouseHandler());

		t = new TimerMaker();

		t.timerStart();	

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(0,2,5,0);
		timerPanel.add(titleTimer, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;	
		timerPanel.add(resetTimer, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		timerPanel.add(startTimer, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		timerPanel.add(pauseTimer, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		timerPanel.add(timer, gbc);
		timer.setFont(f);
		timerPanel.setBounds(10, 150, 200, 100);
		timerPanel.setBorder(border);

		contentPane.add(timerPanel);

		// 힌트, 정답확인등을 담을 패널 설정
		JPanel setting = new JPanel();
		setting.setLayout(new GridLayout(6, 1, 2, 2));
		setting.setBounds(10, 260, 200, 340);
		setting.setBorder(border);

		save = new JButton("저장");
		memo = new JButton("메모");
		hint = new JButton("힌트");
		reset = new JButton("초기화");
		answer = new JButton("정답확인");
		logout = new JButton("로그아웃");

		setting.add(save);
		setting.add(memo);
		setting.add(hint);
		setting.add(reset);
		setting.add(answer);
		setting.add(logout);

		contentPane.add(setting);
		// 이벤트 설정
		setEvent(); 
	}

	// 스도쿠 난이도 변경 메서드
	private void changeDifficulty(String difficulty) {
		sudokuList = sd.selectByDifficulty(difficulty, id, pw);
		baseDataList = sd.setBaseData(difficulty);

		border = BorderFactory.createLineBorder(Color.black, 1);
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				tf[i][j].removeAll();
				tf[i][j].setText("");
				tf[i][j].setEnabled(true);
				tf[i][j].setBorder(border);
				if(panel_memo[i][j] != null)
					panel_memo[i][j].removeAll();
			}
		}		    
		fillData(sudokuList);
	}


	// 이벤트를 설정하는 메서드
	public void setEvent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 텍스트 필드에 키리스너 추가
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(tf[i][j].isEnabled())
					tf[i][j].addKeyListener(new KeyHandler());
			}
		}

		// 버튼에 마우스리스너 추가
		for(int i = 0; i < north_btn.length; i++) {
			north_btn[i].addMouseListener(new MouseHandler()); 
		}

		save.addMouseListener(new MouseHandler());
		memo.addMouseListener(new MouseHandler());
		hint.addMouseListener(new MouseHandler());
		reset.addMouseListener(new MouseHandler());
		answer.addMouseListener(new MouseHandler());
		logout.addMouseListener(new MouseHandler());

		// 텍스트 필드에 마우스리스너 추가
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				tf[i][j].addMouseListener(new MouseHandler());
			}
		}
	}

	public void resetSudoku(LinkedList<SudokuBean> baseDataList) {
		sudokuData = baseDataList.get(r).getSudoku_puzzle();

		for(int i = 0; i < 9; i++) 
			for(int j = 0; j < 9; j++) {
				if(Integer.parseInt(sudokuData[i][j].toString()) == 0) {
					tf[i][j].setText("");
					tf[i][j].setBorder(border);
					tf[i][j].setLayout(new BorderLayout());
					panel_memo[i][j] = new JPanel();

					addMemoFunction(i, j);
					tf[i][j].add(panel_memo[i][j]);
				} else { 
					if(sudokuAnswer[i][j] == sudokuData[i][j]) {
						tf[i][j].setText(sudokuData[i][j].toString());
						tf[i][j].setEnabled(false);
						tf[i][j].setBorder(border);					
					} else {
						tf[i][j].setText(sudokuData[i][j].toString());
						tf[i][j].setBorder(border);
						tf[i][j].setLayout(new BorderLayout());
						panel_memo[i][j] = new JPanel();

						addMemoFunction(i, j);
						tf[i][j].add(panel_memo[i][j]);
					}
				}
			}	
	}

	public void checkAnswer() {
		boolean hasUncorrect = false;				
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sudokuData[i][j] = tf[i][j].getText(); // sudokuData에 사용자가 현재까지 입력한 데이터 새로 저장
				border = BorderFactory.createLineBorder(Color.black, 1);
				tf[i][j].setBorder(border);

				if(!(sudokuAnswer[i][j].toString().equals(sudokuData[i][j].toString()))) {
					border = BorderFactory.createLineBorder(Color.red, 1);
					tf[i][j].setBorder(border);
					hasUncorrect = true;
				} else {
					if(tf[i][j].isEnabled()) {
						border = BorderFactory.createLineBorder(Color.BLUE, 1);
						tf[i][j].setEnabled(false);
						tf[i][j].setBorder(border);
					}
				}
			}
		}
		if(hasUncorrect)
			JOptionPane.showMessageDialog(contentPane, "오답입니다!", "오답", JOptionPane.ERROR_MESSAGE);
		else 
			JOptionPane.showMessageDialog(contentPane, "정답입니다!", "정답", JOptionPane.INFORMATION_MESSAGE);
	}

	// 키이벤트를 처리하는 내부클래스
	public class KeyHandler extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					if(e.getSource() ==  tf[i][j]) {
						int col = i;
						int row = j;

						checkData(col, row);
					}
				}
			}
		}
	}

	// 마우스이벤트를 처리하는 내부클래스
	public class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == north_btn[0]) {
				difficulty = "easy";
				changeDifficulty(difficulty);
				t.timerReset();
			} else if (e.getSource() == north_btn[1]) {
				difficulty = "normal";
				changeDifficulty(difficulty);
				t.timerReset();
			} else if (e.getSource() == north_btn[2]) {
				difficulty = "hard";
				changeDifficulty(difficulty);
				t.timerReset();
			} else if (e.getSource() == north_btn[3]) {
				difficulty = "expert";
				changeDifficulty(difficulty);
				t.timerReset();
			} else if (e.getSource() == north_btn[4]) {
				difficulty = "master";
				changeDifficulty(difficulty);
				t.timerReset();
			}

			if(e.getSource() == startTimer) {
				t.timerStart();
			} else if(e.getSource() == pauseTimer) {
				t.timerStop();
			} else if(e.getSource() == resetTimer) {
				t.timerReset();
			}

			if(e.getSource() == save) {
				for(int i = 0; i < 9; i++) 
					for(int j = 0; j < 9; j++) 
						sudokuData[i][j] = tf[i][j].getText();	
				try {
					sd.save(r, sudokuData, difficulty);
					JOptionPane.showMessageDialog(contentPane, "저장하였습니다!", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
				}catch(SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "저장에 실패하였습니다!", "저장 실패", JOptionPane.ERROR_MESSAGE);
				}
			} else if(e.getSource() == memo) {
				if(!check_memoOnOff[clickRow][clickCol]) {
					check_memoOnOff[clickRow][clickCol] = true;
					panel_memo[clickRow][clickCol].setVisible(true);
				} else {
					check_memoOnOff[clickRow][clickCol] = false;
					panel_memo[clickRow][clickCol].setVisible(false);
				}
			} else if(e.getSource() == hint) {
				tf[clickRow][clickCol].setText(sudokuAnswer[clickRow][clickCol].toString());
				tf[clickRow][clickCol].repaint();
			} else if(e.getSource() == reset) {
				resetSudoku(baseDataList);
			} else if(e.getSource() == answer) {
				checkAnswer();
			} else if(e.getSource() == logout) {
				dispose();
				Login login = new Login("로그인");
			}

			// 마지막으로 클릭한 스도쿠 위치 저장
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					if(e.getSource() == tf[i][j]) {
						clickRow = i;
						clickCol = j;
					}
				}
			}
		}
	}

	// 타이머 클래스
	public class TimerMaker {
		int count = 0;
		Timer timeset;

		TimerMaker() {
			timeset = new Timer(1000, new ActionListener() {		
				public void actionPerformed(ActionEvent e) {
					count++;
					changeTime();
				}
			});	
		}

		public void timerStart() {
			timeset.start();
		}

		public void timerStop() {
			timeset.stop();
		}

		public void timerReset() {
			count = 0;
			changeTime();
		}

		private void changeTime() {
			int hours = count / 3600;
			int minutes = (count % 3600) / 60;
			int seconds = count % 60;

			formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			timer.setText(formattedTime);
		}
	}
} // SudokuMain 끝


