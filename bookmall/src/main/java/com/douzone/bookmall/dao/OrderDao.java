package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.OrderVo;

public class OrderDao {
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.80.105:3307/bookmall?charset=utf-8";
			conn = DriverManager.getConnection(url,"bookmall","bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
		
		return conn;
	}
	
	public Boolean insert(OrderVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Boolean result = false;
		try {
			conn = getConnection();
			
			String sql = "insert into bookmall.order values(null,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getOrderNum());
			pstmt.setInt(2, vo.getTotalPrice());
			pstmt.setString(3, vo.getAddress());
			pstmt.setLong(4, vo.getMemberId());
			int count = pstmt.executeUpdate();
			result = count == 1;
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

	public List<OrderVo> findAll() {
		List<OrderVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select o.id, o.order_num, m.name, m.email, o.total_price, o.address "
					+ "		from bookmall.order o, member m"
					+ "		where o.member_id = m.id";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setId(rs.getLong(1));
				vo.setOrderNum(rs.getInt(2));
				vo.setMemberName(rs.getString(3));
				vo.setMemberEmail(rs.getString(4));
				vo.setTotalPrice(rs.getInt(5));
				vo.setAddress(rs.getString(6));
				result.add(vo);
			}
		
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
	
	public void delete () {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "delete from bookmall.order";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
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
	}

	public void updateOrder(OrderVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "update bookmall.order"
					   + "	set total_price = ?"
					   + "	where id = ?"
					   + "	and order_num = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getTotalPrice());
			pstmt.setLong(2, vo.getId());
			pstmt.setInt(3, vo.getOrderNum());
			
			pstmt.executeUpdate();
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
	}
}
