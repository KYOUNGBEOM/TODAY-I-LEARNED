import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "sqlid";
	private String pw = "sqlpw";
	private Connection conn;
	ArrayList<PersonBean> al = new ArrayList<>();
	PreparedStatement ps = null;
	ResultSet rs = null;

	PersonDao() {
		try {
			Class.forName(driver); // 1. 드라이버 로드
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	} // 생성자

	public void connect() {
		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공");
		} catch (SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}
	} // 2. 계정 접속

	public ArrayList<PersonBean> select() {
		try {
			// 2. 계정 접속
			connect();

			// 3. sql 분석
			String sql = "select * from person";
			ps = conn.prepareStatement(sql);

			// 4. sql 실행
			rs = ps.executeQuery();

			while(rs.next()) {
				PersonBean pb = new PersonBean();
				pb.setNum(rs.getInt("num"));
				pb.setName(rs.getString("name"));
				pb.setAge(rs.getInt("age"));
				pb.setGender(rs.getString("gender"));
				pb.setBirth(String.valueOf(rs.getDate("birth")));

				al.add(pb);
			}
		} catch (SQLException e) {
		} finally {
			try {
				if(ps != null) ps.close();
				if(rs != null) rs.close();
				if(conn != null) {
					conn.close();
					System.out.println("접속 종료");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;			
	} // getAllPerson

	public  ArrayList<PersonBean> getGender(String gender) {
		try {
			// 2. 계정 접속
			connect();

			String sql = "select * from person where gender = ?";
			al.clear();

			ps = conn.prepareStatement(sql);
			ps.setString(1, gender);

			rs = ps.executeQuery();

			while(rs.next()) {
				PersonBean pb = new PersonBean();
				pb.setNum(rs.getInt("num"));
				pb.setName(rs.getString("name"));
				pb.setAge(rs.getInt("age"));
				pb.setGender(rs.getString("gender"));
				pb.setBirth(String.valueOf(rs.getDate("birth")));

				al.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(rs != null) rs.close();
				if(conn != null) {
					conn.close();
					System.out.println("접속 종료");
				}
			} catch(SQLException e) { 
				e.printStackTrace();
			}
		}
		return al;
	} // getGender

	public void insert(String name, int age, String gender, String birth) {
		try {
			// 2. 계정 접속
			connect();
			int num = 5;

			String sql = "insert into person values(?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, num);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, gender);
			ps.setString(5, birth);

			num++;

			int cnt = ps.executeUpdate();		
			if(cnt == -1) {
				System.out.println("수정실패");
			} else if(cnt == 0) {
				System.out.println("조건에 맞는 레코드가 없습니다.");
			} else {
				System.out.println(cnt +"건 추가 성공하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(rs != null) rs.close();
				if(conn != null) {
					conn.close();
					System.out.println("접속 종료");
				}
			} catch(SQLException e) { 
				e.printStackTrace();
			}
		}
	} // insert

	public void update(int num, String name, int age, String gender, String birth) {
		try {
			// 2. 계정 접속
			connect();

			String sql = "update person set person.name = ?, person.age = ?, person.gender = ?, person.birth = ? where person.num = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, gender);
			ps.setString(4, birth);
			ps.setInt(5, num);

			int cnt = ps.executeUpdate();
			if(cnt == -1) {
				System.out.println("수정실패");
			} else if(cnt == 0) {
				System.out.println("조건에 맞는 레코드가 없습니다.");
			} else {
				System.out.println(cnt +"건 수정하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(rs != null) rs.close();
				if(conn != null) {
					conn.close();
					System.out.println("접속 종료");
				}
			} catch(SQLException e) { 
				e.printStackTrace();
			}
		}
	} // update
	
	public void delete(int num) {
		try {
			// 2. 계정 접속
			connect();
			String sql = "delete person where person.num = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			int cnt = ps.executeUpdate();
			if(cnt == -1) {
				System.out.println("수정실패");
			} else if(cnt == 0) {
				System.out.println("조건에 맞는 레코드가 없습니다.");
			} else {
				System.out.println(cnt + "건 삭제하였습니다.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(rs != null) rs.close();
				if(conn != null) {
					conn.close();
					System.out.println("접속 종료");
				}
			} catch(SQLException e) { 
				e.printStackTrace();
			}
		}
	} // delete
}
