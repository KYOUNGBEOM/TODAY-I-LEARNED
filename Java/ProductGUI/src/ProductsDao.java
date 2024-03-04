
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductsDao { 

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "sqlid";
	private String pw = "sqlpw";
	Connection conn = null;

	public ProductsDao(){
		try {
			//1
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("jar 파일 누락");
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			//2
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공");
		} catch (SQLException e) {
			System.out.println("접속 오류");
			e.printStackTrace();
		}
	}//connect

	public ArrayList<ProductsBean> getAllProducts(){
		System.out.println("여기1");
		connect(); // 2. 계정에 접속

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductsBean> lists = new ArrayList<ProductsBean>();
		System.out.println("여기2");
		try {
			//3.
			String sql = "select * from products order by id asc";
			ps = conn.prepareStatement(sql);

			//4.
			rs = ps.executeQuery();
			/*
			 ID NAME              STOCK      PRICE CATEGORY     INPUTDAT
			 ---- ------------ ---------- ---------- ------------ --------
			    1 mp3                  20        300 IT           24/02/27
			    2 갤럭시S6             30        200 IT           24/02/27
			    3 iPhone               40        500 IT           24/02/27
			    4 세탁기               20        300 KJ           24/02/27
			    5 냉장고               30        200 KJ           24/02/27
			    6 TV                   40        500 KJ           24/02/27
			    7 Computer             20        300 IT           24/02/27
			    8 iMac                 30        200 IT           24/02/27
			 */			  
			System.out.println("여기3");
			while(rs.next()) {
				int id = rs.getInt("id"); // id칼럼에 있는 int값 가져와라
				String name = rs.getString("name");
				int stock = rs.getInt("stock");
				int price = rs.getInt("price");
				String category = rs.getString("category");
				//String inputdate = rs.getString("inputdate");
				Date d = rs.getDate("inputdate");
				String inputdate = String.valueOf(d);

				ProductsBean pb = new ProductsBean();
				pb.setId(id);
				pb.setName(name);
				pb.setStock(stock);
				pb.setPrice(price);
				pb.setCategory(category);
				pb.setInputdate(inputdate);

				lists.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lists;
	}

	public ArrayList<ProductsBean> getProductsById(int id){
		connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductsBean> lists = new ArrayList<ProductsBean>();
		try {
			String sql = "select * from products where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while(rs.next()) {
				int id2 = rs.getInt("id"); // id칼럼에 있는 int값 가져와라
				String name = rs.getString("name");
				int stock = rs.getInt("stock");
				int price = rs.getInt("price");
				String category = rs.getString("category");
				//String inputdate = rs.getString("inputdate");
				Date d = rs.getDate("inputdate");
				String inputdate = String.valueOf(d);

				ProductsBean pb = new ProductsBean();
				pb.setId(id2);
				pb.setName(name);
				pb.setStock(stock);
				pb.setPrice(price);
				pb.setCategory(category);
				pb.setInputdate(inputdate);

				lists.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}

	public ProductsBean getProductsById2(int id){
		connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductsBean pb = null ;
		try {
			String sql = "select * from products where id=?"; // 3
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if(rs.next()) { // 찾았으면(있으면) 
				int id2 = rs.getInt("id"); // id칼럼에 있는 int값 가져와라
				String name = rs.getString("name");
				int stock = rs.getInt("stock");
				int price = rs.getInt("price");
				String category = rs.getString("category");
				//String inputdate = rs.getString("inputdate");
				Date d = rs.getDate("inputdate");
				String inputdate = String.valueOf(d);

				pb = new ProductsBean();
				pb.setId(id2);
				pb.setName(name);
				pb.setStock(stock);
				pb.setPrice(price);
				pb.setCategory(category);
				pb.setInputdate(inputdate);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pb; // 

	}//getProductsById2

	public ArrayList<ProductsBean> getProductByCategory(String category){ // it

		connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductsBean> lists = new ArrayList<ProductsBean>();
		try {
			//String sql = "select * from pRODucts whEre cateGory=upper(?)"; // it  
			String sql = "select * from pRODucts whEre upper(cateGory)=?"; // it
			ps = conn.prepareStatement(sql);
			//ps.setString(1, category);
			ps.setString(1, category.toUpperCase()); // it=>IT

			rs = ps.executeQuery();
			while(rs.next()) {
				int id2 = rs.getInt("id"); // id칼럼에 있는 int값 가져와라
				String name = rs.getString("NAME");
				int stock = rs.getInt("stock");
				int price = rs.getInt("pRIce");
				String category2 = rs.getString("category");
				//String inputdate = rs.getString("inputdate");
				Date d = rs.getDate("inputdate");
				String inputdate = String.valueOf(d);

				ProductsBean pb = new ProductsBean();
				pb.setId(id2);
				pb.setName(name);
				pb.setStock(stock);
				pb.setPrice(price);
				pb.setCategory(category2);
				pb.setInputdate(inputdate);

				lists.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("lists.size():"+lists.size());
		return lists;
	}//getProductByCategory
	
	//public void insertProducts(String name,int stock,int price,String category,String inputdate){
	public int insertProducts(ProductsBean pb){
		// pb에는 있다.
		// 테스트 확인
		System.out.println(pb.getName());
		System.out.println(pb.getCategory());
		System.out.println(pb.getInputdate());
		int cnt = -1;
		connect();
		PreparedStatement ps = null;
		try {
			String sql = "insert into products(id,name,stock,price,category,inputdate) values(prdseq.nextval, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			//ps.setString(1,name);
			ps.setString(1,pb.getName());
			ps.setInt(2,pb.getStock());
			ps.setInt(3, pb.getPrice());
			ps.setString(4, pb.getCategory());
			ps.setString(5, pb.getInputdate());
			
			//4.실행
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null)
					ps.close();
				if(conn != null)
					conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	//public void updateProducts(int id, String name,int stock,int price, String category, String inputdate) {
	public int updateProducts(ProductsBean pb) {
		System.out.println(pb.getId());
		
		connect();
		PreparedStatement ps = null;
		int cnt = -1;
		try {
			String sql = "update products set name=?, stock=?, price=?, category=?, inputdate=? where id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pb.getName());
			ps.setInt(2, pb.getStock());
			ps.setInt(3, pb.getPrice());
			ps.setString(4, pb.getCategory());
			ps.setString(5, pb.getInputdate());
			ps.setInt(6, pb.getId());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if(conn != null)
					conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
		
	}//updateProducts

	public int deleteProducts(int id) {
		
		connect();
		PreparedStatement ps = null;
		int cnt = -1;
		try {
			String sql = "delete from products where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if(conn != null)
					conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}
}








