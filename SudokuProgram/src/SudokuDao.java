import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.LinkedList;

public class SudokuDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "";
	String pw = "";
	Object[][] sudoku = new Object[9][9];
	Object[][] baseData = new Object[9][9];
	LinkedList<SudokuBean> sudokull = new LinkedList<>();
	LinkedList<SudokuBean> baseDatall = new LinkedList<>(); 

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Statement statement;

	SudokuDao() {}

	SudokuDao(String id, String pw) throws SQLSyntaxErrorException, SQLException {
		try {
			this.id = id;
			this.pw = pw;
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");

			connect();
			makeTable();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		} 
	}

	public void connect() throws SQLException {
		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공");
		} catch (SQLException e) {
			System.out.println("접속 실패");
			throw e;
		}
	}

	public void makeTable() throws SQLSyntaxErrorException {
		try {
			statement = conn.createStatement();

			String makeEasyTable = "create table easy ("
					+ " col number primary key,"
					+ "	row1 number,"
					+ "	row2 number,"
					+ "	row3 number,"
					+ "	row4 number,"
					+ "	row5 number,"
					+ "	row6 number,"
					+ "	row7 number,"
					+ "	row8 number,"
					+ "	row9 number)";
			statement.executeUpdate(makeEasyTable);

			String makeNormalTable = "create table normal ("
					+ " col number primary key,"
					+ "	row1 number,"
					+ "	row2 number,"
					+ "	row3 number,"
					+ "	row4 number,"
					+ "	row5 number,"
					+ "	row6 number,"
					+ "	row7 number,"
					+ "	row8 number,"
					+ "	row9 number)";
			statement.executeUpdate(makeNormalTable);

			String makeHardTable = "create table hard ("
					+ " col number primary key,"
					+ "	row1 number,"
					+ "	row2 number,"
					+ "	row3 number,"
					+ "	row4 number,"
					+ "	row5 number,"
					+ "	row6 number,"
					+ "	row7 number,"
					+ "	row8 number,"
					+ "	row9 number)";
			statement.executeUpdate(makeHardTable);

			String makeExpertTable = "create table expert ("
					+ " col number primary key,"
					+ "	row1 number,"
					+ "	row2 number,"
					+ "	row3 number,"
					+ "	row4 number,"
					+ "	row5 number,"
					+ "	row6 number,"
					+ "	row7 number,"
					+ "	row8 number,"
					+ "	row9 number)";
			statement.executeUpdate(makeExpertTable);

			String makeMasterTable = "create table master ("
					+ " col number primary key,"
					+ "	row1 number,"
					+ "	row2 number,"
					+ "	row3 number,"
					+ "	row4 number,"
					+ "	row5 number,"
					+ "	row6 number,"
					+ "	row7 number,"
					+ "	row8 number,"
					+ "	row9 number)";
			statement.executeUpdate(makeMasterTable);

			insertDefaultData();
		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(statement != null)
					statement.close();
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
				if(conn != null) {
					conn.close();
					System.out.println("접속 종료");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insert(String sql, String tableName) {
		Connection SqlidConn = null;
		PreparedStatement  SqlidPs = null;
		ResultSet SqlidRs = null;
		try {
			SqlidConn = DriverManager.getConnection(url, "sqlid", "sqlpw");
			SqlidPs = SqlidConn.prepareStatement(sql);
			SqlidRs = SqlidPs.executeQuery();

			while(SqlidRs.next()) {
				String insertDataSql = "insert into " + tableName + " values(" + SqlidRs.getInt("COL") + "," + SqlidRs.getInt("ROW1") + "," + SqlidRs.getInt("ROW2") + "," + SqlidRs.getInt("ROW3") + "," + SqlidRs.getInt("ROW4") + "," + SqlidRs.getInt("ROW5") + "," + SqlidRs.getInt("ROW6") + "," + SqlidRs.getInt("ROW7") + "," + SqlidRs.getInt("ROW8") + "," + SqlidRs.getInt("ROW9") + ")";
				ps = conn.prepareStatement(insertDataSql);			
				int cnt = ps.executeUpdate();
				System.out.println(cnt + "건 업데이트 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(SqlidRs != null)
					SqlidRs.close();
				if(SqlidPs != null)
					SqlidPs.close();
				if(SqlidConn != null)
					SqlidConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	public void insertDefaultData() {	
		String Easysql = "select * from EASY";
		insert(Easysql, "EASY");

		String Normalsql = "select * from NORMAL";
		insert(Normalsql, "NORMAL");

		String Hardsql = "select * from HARD";
		insert(Hardsql, "HARD");

		String Expertsql = "select * from EXPERT";
		insert(Expertsql, "EXPERT");

		String Mastersql = "select * from MASTER";
		insert(Mastersql, "MASTER");
	}

	public void save(int r, Object[][] sudokuData, String difficulty) throws SQLException {
		try {		
			String sql = "update " + difficulty.toUpperCase() + " set ROW1 = ?, ROW2 = ?, ROW3 = ?, ROW4 = ?, ROW5 = ?, ROW6 = ?, ROW7 = ?, ROW8 = ?, ROW9 = ? where COL = ?";
			sudoku = sudokuData;
			for(int k = 0; k < 9; k++)
				for(int l = 0; l < 9; l++) 
					if(sudoku[k][l].toString().equals("")) sudoku[k][l] = "0";

			connect();

			if(r == 0) { 
				ps = conn.prepareStatement(sql);
				for(int i = 0; i < 9; i++) {
					for(int j = 0; j < 9; j++) {
						ps.setInt(j+1,Integer.parseInt(sudoku[i][j].toString()));					
					}
					ps.setInt(10, i+1);
					ps.executeUpdate();
				}
			} else if(r==1) {
				ps = conn.prepareStatement(sql);
				for(int i = 0; i < 9; i++) {
					for(int j = 0; j < 9; j++) {
						ps.setInt(j+1, Integer.parseInt(sudoku[i][j].toString()));		
					}	
					ps.setInt(10, i+10);
					ps.executeUpdate();
				}
			} else if(r==2) {
				ps = conn.prepareStatement(sql);
				for(int i = 0; i < 9; i++) {
					for(int j = 0; j < 9; j++) {
						ps.setInt(j+1, Integer.parseInt(sudoku[i][j].toString()));								
					}	
					ps.setInt(10, i+19);
					ps.executeUpdate();	
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(ps != null)
					ps.close();
				if(conn != null) {
					conn.close();
					System.out.println("접속 종료");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public LinkedList<SudokuBean> setBaseData(String difficulty) {
		baseDatall.clear();
		Connection sqlConn = null;
		PreparedStatement sqlPs = null;
		ResultSet sqlRs = null;
		try {
			int i = 0;
			SudokuBean sb = new SudokuBean();
			sqlConn = DriverManager.getConnection(url, "sqlid", "sqlpw");
			String sql = "select * from " + difficulty;

			sqlPs = sqlConn.prepareStatement(sql);
			sqlRs = sqlPs.executeQuery();

			while(sqlRs.next()) {
				for(int j = 0; j < 9; j++) {
					baseData[i][j] = sqlRs.getInt(("ROW" + (j+1)));
				}
				i++;
				if(i % 9== 0) {
					sb.setSudoku_puzzle(baseData);
					baseDatall.add(sb);
					baseData = new Object[9][9];
					sb = new SudokuBean();
					i = 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(sqlRs!=null)
					sqlRs.close();
				if(sqlPs!=null)
					sqlPs.close();
				if(sqlConn!=null) {
					sqlConn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return baseDatall;
	}

	public  LinkedList<SudokuBean> selectByDifficulty(String difficulty, String id, String pw) {
		sudokull.clear();
		int i = 0;
		this.id = id;
		this.pw = pw;

		try {
			connect();

			SudokuBean sb = new SudokuBean();
			String sql = "select * from " + difficulty;		

			ps = conn.prepareStatement(sql);	
			rs = ps.executeQuery();	

			while(rs.next()) {	
				for(int j = 0; j < 9; j++) {
					sudoku[i][j] = rs.getInt(("ROW" + (j+1)));
				}
				i++;
				if(i % 9== 0) {
					sb.setSudoku_puzzle(sudoku);
					sudokull.add(sb);
					sudoku = new Object[9][9];
					sb = new SudokuBean();
					i = 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(conn!=null) {
					conn.close();
					System.out.println("접속 종료");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return sudokull;
	}
}