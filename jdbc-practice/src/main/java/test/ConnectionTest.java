package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. maria db 연결
			String url = "jdbc:mysql://192.168.80.105:3307/webdb";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			
			// 3. Statement 생성
			stmt = conn.createStatement();
			// 4. SQL문을 실행
			String sql = "select name,owner,birth "+
						 "from pet "+
						 "where gender = 'f'";
			rs = stmt.executeQuery(sql);
			
			// 5. 결과 가져오기 
			while(rs.next()) {
				String name = rs.getString(1);
				String owner = rs.getString(2);
				String birth = rs.getString(3);
				
				System.out.println("name : "+ name +"\t owner : " + owner +"\t birth : " + birth);
			}
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(rs != null) { rs.close();}
				if(stmt != null) { stmt.close();}
				if(conn != null) { conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
