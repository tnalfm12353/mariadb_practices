package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest02 {

	public static void main(String[] args) {
		insertStatement("pp");
	}
	private static boolean insertStatement (String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. maria db 연결
			String url = "jdbc:mysql://192.168.80.105:3307/webdb";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			
			// 3. SQL문 준비
			String sql = "insert "+
					"into pet " +
					"values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩(binding)
			pstmt.setString(1, name);
			pstmt.setString(2, "hong");
			pstmt.setString(3, "dog");
			pstmt.setString(4, "m");
			pstmt.setString(5, "2021-01-01");
			pstmt.setString(6, "2021-01-01");
			
			// 5. SQL문을 실행
			int count = pstmt.executeUpdate();
			result = count ==1;
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(pstmt != null) { pstmt.close();}
				if(conn != null) { conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
