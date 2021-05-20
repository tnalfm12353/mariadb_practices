package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest01 {

	public static void main(String[] args) {
		PetVo vo = new PetVo();
		vo.setName("peach");
		vo.setSpecies("dog");
		Boolean result = update(vo);
		System.out.println(result ? "성공" : "실패");
	}

	public static Boolean update(PetVo vo) {
		Connection conn = null;
		Statement stmt = null;
		Boolean result = false;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. maria db 연결
			String url = "jdbc:mysql://192.168.80.105:3307/webdb";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			
			// 3. Statement 생성
			stmt = conn.createStatement();
			String temp = "f";
			// 4. SQL문을 실행
			String sql = "update pet "+
						 "set species = '"+ vo.getSpecies() + "'" +
						 "where name = '"+vo.getName()+"'";
						 
			int count = stmt.executeUpdate(sql);
			result = count == 1;
			
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
