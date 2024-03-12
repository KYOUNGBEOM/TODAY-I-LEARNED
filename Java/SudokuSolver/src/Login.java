import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.lang.model.element.ModuleElement.UsesDirective;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Login extends JFrame {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String sysId = "sys as sysdba";
	private String sysPw = "1234";

	private Container contentPane;

	private JTextField idText;
	private JPasswordField pwText;

	private JButton loginButton;
	private JButton joinMembership;
	private JButton withdrawalMembership;
	private JButton changePw;

	private Connection conn;
	private PreparedStatement userNames;
	private PreparedStatement authorization;
	private PreparedStatement checkUserStatement;
	private ResultSet userResult;
	private Statement statement;

	private SudokuMain sm;
	private ArrayList users = new ArrayList();


	Login(String title) {
		super(title);

		compose();

		setSize(600,600);
		setVisible(true);
	} 

	public void compose() {
		contentPane  = getContentPane();
		contentPane.setLayout(null);
		Border border = BorderFactory.createLineBorder(Color.black, 1);

		JTable table = new JTable();
		table.setLayout(null);
		table.setBorder(border);
		table.setBounds(40,80,500,400);

		JLabel title = new JLabel("스도쿠 퍼즐");
		Font f = new Font("Dialog", Font.BOLD, 24);
		title.setFont(f);
		title.setBounds(180, 10, 500, 70);

		JPanel loginPage = new JPanel();
		loginPage.setLayout(null);
		loginPage.setBorder(border);
		loginPage.setBounds(100, 80, 300, 200);

		loginButton = new JButton("로그인");
		loginButton.setBounds(98, 150, 100, 30);

		loginPage.add(loginButton);

		JLabel idLabel = new JLabel("아이디");
		JLabel pwLabel = new JLabel("비밀번호");
		idLabel.setBounds(10, 50, 50, 50);
		pwLabel.setBounds(10, 90, 60, 60);

		loginPage.add(idLabel);
		loginPage.add(pwLabel);

		idText = new JTextField();
		pwText = new JPasswordField();

		idText.setText("");
		pwText.setEchoChar('*');
		idText.setBounds(70, 66, 200, 20);
		pwText.setBounds(70, 111, 200, 20);
		idText.setBorder(border);
		pwText.setBorder(border);

		loginPage.add(idText);
		loginPage.add(pwText);

		JPanel membership = new JPanel();
		membership.setLayout(new FlowLayout());
		membership.setBorder(border);
		membership.setBounds(100, 290, 300, 70);

		joinMembership = new JButton("회원 가입");
		withdrawalMembership = new JButton("회원 탈퇴");
		changePw = new JButton("비밀번호 변경");

		membership.add(joinMembership);
		membership.add(withdrawalMembership);
		membership.add(changePw);

		table.add(title);
		table.add(membership);
		table.add(loginPage);
		contentPane.add(table);

		setEvent();
	}

	public void setEvent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginButton.addMouseListener(new MouseHandler());
		joinMembership.addMouseListener(new MouseHandler());
		withdrawalMembership.addMouseListener(new MouseHandler());
		changePw.addMouseListener(new MouseHandler());
	}

	public void connect(String id, String pw) {
		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공");
		} catch (SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}
	}

	public void loginAdmin() {	
		try {
			conn = DriverManager.getConnection(url, sysId, sysPw);
			System.out.println("관리자 계정 접속 성공");
		} catch (SQLException e) {
			System.out.println("관리자 계정 접속 실패");
			e.printStackTrace();
		}
	}

	public void getUserData() {
		loginAdmin();
		try {
			String usersData = "SELECT * FROM All_USERS";
			userNames = conn.prepareStatement(usersData);
			userResult = userNames.executeQuery();

			while(userResult.next()) 
				users.add(userResult.getString("USERNAME"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(userResult != null)
					userResult.close();
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}

	}

	public void makeId(String id, String pw) {
		loginAdmin();

		try {
			String alterSession = "alter session set \"_oracle_script\"=true";
			String createUserSQL = "CREATE USER " + id.toUpperCase() + " IDENTIFIED BY " + pw + " ACCOUNT UNLOCK";
			String grantPermissionSQL = "GRANT CREATE SESSION, RESOURCE, CONNECT, CREATE TABLE, CREATE SEQUENCE TO " + id.toUpperCase();
			String alterPermissionSQL = "ALTER USER " + id.toUpperCase() + " DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS";
			String checkUserExistence = "SELECT COUNT(*) FROM All_USERS WHERE USERNAME = '" + id.toUpperCase() + "'";

			authorization = conn.prepareStatement(alterSession);
			authorization.execute();

			checkUserStatement = conn.prepareStatement(checkUserExistence);

			userResult = checkUserStatement.executeQuery();
			userResult.next();

			// 이미 존재하는 계정 처리
			if (userResult.getInt(1) > 0) {
				System.out.println("이미 존재하는 계정입니다.");
				JOptionPane.showMessageDialog(contentPane, "이미 존재하는 아이디입니다.", "계정 생성 오류", JOptionPane.ERROR_MESSAGE);
			}  else {    
				statement = conn.createStatement();
				statement.executeUpdate(createUserSQL);
				statement.executeUpdate(grantPermissionSQL);
				statement.executeUpdate(alterPermissionSQL);

				System.out.println("계정 생성에 성공하였습니다.");
				JOptionPane.showMessageDialog(contentPane, "아이디 생성에 성공하였습니다.", "계정 생성 성공", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(authorization != null)
					authorization.close();
				if(checkUserStatement != null) 
					checkUserStatement.close();	
				if(statement != null) 
					statement.close();
				if(conn != null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == joinMembership) {
				String id = idText.getText();
				char[] tmpPw = pwText.getPassword();
				String pw = new String(tmpPw);

				if(id.equals("") && pw.equals("")) 
					JOptionPane.showMessageDialog(contentPane, "아이디와 비밀번호를 입력해주세요", "아이디, 비밀번호 오류", JOptionPane.ERROR_MESSAGE);
				else if(id.equals("")) 
					JOptionPane.showMessageDialog(contentPane, "아이디를 입력해주세요", "아이디 오류", JOptionPane.ERROR_MESSAGE);
				else if(pw.equals(""))
					JOptionPane.showMessageDialog(contentPane, "비밀번호를 입력해주세요", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
				else
					makeId(id, pw);
			} else if(e.getSource() == withdrawalMembership) {
				String dropUserId = JOptionPane.showInputDialog(contentPane, "탈퇴할 아이디를 입력하세요", "회원 탈퇴", JOptionPane.QUESTION_MESSAGE);
				if (dropUserId != null && !dropUserId.trim().isEmpty()) {
					withdrawMembership(dropUserId);
				} else {
					JOptionPane.showMessageDialog(contentPane, "올바른 아이디를 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
				}
			} else if(e.getSource() == changePw) {
				String alterId = JOptionPane.showInputDialog(contentPane, "비밀번호를 변경할 아이디를 입력하세요", "비밀번호 변경", JOptionPane.QUESTION_MESSAGE);
				JPasswordField passwordField = new JPasswordField();
				String alterPw = "";
				Object[] message = {"변경할 비밀번호를 입력하세요.", passwordField};
				int option = JOptionPane.showConfirmDialog(contentPane, message, "Password", JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION) {
					char[] passwordChars = passwordField.getPassword();
					alterPw = new String(passwordChars);
				}

				if (alterId!= null && !alterId.trim().isEmpty() && alterPw!= null && !alterPw.trim().isEmpty()) 
					alterPassword(alterId, alterPw);
			} else if(e.getSource() == loginButton) {
				String id = idText.getText();
				char[] tmpPw = pwText.getPassword();
				String pw = new String(tmpPw);

				getUserData();

				if(id.equals("") && pw.equals("")) 
					JOptionPane.showMessageDialog(contentPane, "아이디와 비밀번호를 입력해주세요", "아이디, 비밀번호 오류", JOptionPane.ERROR_MESSAGE);
				else if(id.equals("")) 
					JOptionPane.showMessageDialog(contentPane, "아이디를 입력해주세요", "아이디 오류", JOptionPane.ERROR_MESSAGE);
				else if(pw.equals(""))
					JOptionPane.showMessageDialog(contentPane, "비밀번호를 입력해주세요", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);		
				else {
					boolean isId = true;
					for(int i = 0; i < users.size(); i++) {
						if(users.get(i).equals(id.toUpperCase())) {
							sm = new SudokuMain("스도쿠 마스터", id, pw);
							isId = false;
						} 
					}
					if(isId)
						JOptionPane.showMessageDialog(contentPane, "존재하지 않는 아이디입니다.", "아이디 오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void alterPassword(String alterId, String alterPw) {
		loginAdmin();

		String alterSession = "alter session set \"_oracle_script\"=true";
		String alterPwSql = "ALTER USER " + alterId.toUpperCase() + " IDENTIFIED BY " + alterPw;

		try {
			statement = conn.createStatement();
			statement.execute(alterSession);
			statement.executeUpdate(alterPwSql);	
			JOptionPane.showMessageDialog(contentPane, "pw를 변경하였습니다.", "변경 성공", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(contentPane, "id를 다시 확인해주세요", "id 오류", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			try {
				if(statement != null)
					statement.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void withdrawMembership(String dropUserId) {
		loginAdmin();

		String alterSession = "alter session set \"_oracle_script\"=true";
		String dropUserSql = "DROP USER " + dropUserId.toUpperCase() + " CASCADE";

		try {
			statement = conn.createStatement();
			statement.execute(alterSession);
			statement.execute(dropUserSql);	
			JOptionPane.showMessageDialog(contentPane, "id를 삭제하였습니다.", "삭제 성공", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(contentPane, "id를 다시 확인해주세요", "id 오류", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			try {
				if(statement != null)
					statement.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		Login login = new Login("로그인");
	}
}
