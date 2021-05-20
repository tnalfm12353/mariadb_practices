package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletTest02 {

	public static void main(String[] args) {
		Boolean result = delete("pp");
		System.out.println(result ? "성공" : "실패");
	}
	
	private static boolean delete (String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. maria db 연결
			String url = "jdbc:mysql://192.168.80.105:3307/webdb";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			
			// 3. Statement 생성
			String sql = "delete "+
					"from pet " +
					"where name = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			// 4. SQL문을 실행
			int count = pstmt.executeUpdate();
			result = count == 1;
		
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
