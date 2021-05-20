package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeletTest01 {

	public static void main(String[] args) {
		Boolean result = delete("pp");
		System.out.println(result ? "성공" : "실패");
	}
	
	private static boolean delete (String name) {
		Connection conn = null;
		Statement stmt = null;
		boolean result = false;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. maria db 연결
			String url = "jdbc:mysql://192.168.80.105:3307/webdb";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			
			// 3. Statement 생성
			stmt = conn.createStatement();
			
			// 4. SQL문을 실행
			String sql = "delete "+
							"from pet " +
							"where name = '"+ name +"'";
			int count = stmt.executeUpdate(sql);
			result = count ==1;
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(stmt != null) { stmt.close();}
				if(conn != null) { conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
