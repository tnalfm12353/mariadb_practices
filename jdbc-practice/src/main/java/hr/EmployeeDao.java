package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.80.105:3307/employees";
			conn = DriverManager.getConnection(url,"hr","hr");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
	
		
		return conn;
	}
	
	
	public List<EmployeeVo> findByName(String name){
		List <EmployeeVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select emp_no, first_name, last_name, date_format(hire_date, '%Y-%m-%d') "+
					 	"from employees "+
					 	"where first_name like ? "+
					 	"and last_name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+ name +"%");
			pstmt.setString(2, "%"+ name +"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long empNo = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setEmpNo(empNo);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setHireDate(hireDate);
				result.add(vo);
			}
		
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(rs != null) { rs.close();}
				if(pstmt != null) { pstmt.close();}
				if(conn != null) { conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public List<EmployeeVo> findBySalary(int minSalary,int maxSalary){
		List <EmployeeVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			conn = getConnection();
			
			String sql = "select e.emp_no, e.first_name, e.last_name, s.salary "+
					 		"from employees e, salaries s "+
					 		"where e.emp_no = s.emp_no "+
					 		"and s.to_date = '9999-01-01' "+
					 		"and s.salary between ? and ? "+
					 		"order by s.salary asc";
					 	
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, minSalary);
			pstmt.setInt(2, maxSalary);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long empNo = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				int salary = rs.getInt(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setEmpNo(empNo);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setSalary(salary);
				result.add(vo);
			}
		
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(rs != null) { rs.close();}
				if(pstmt != null) { pstmt.close();}
				if(conn != null) { conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
