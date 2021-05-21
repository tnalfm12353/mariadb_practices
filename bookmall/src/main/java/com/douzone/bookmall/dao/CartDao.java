package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.CartVo;

public class CartDao {
	
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

	public Boolean insert(CartVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Boolean result = false;
		try {
			conn = getConnection();

			String sql = "insert into cart value(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCount());
			pstmt.setLong(2, vo.getMemberId());
			pstmt.setLong(3, vo.getBookId());
			
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

	public List<CartVo> findAll() {
		List<CartVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "select m.name, b.title, c.count, b.price"
							+ "	from cart c, book b, member m"
						+ "    where c.book_id = b.id"
						+ "    and c.member_id = m.id;";
			pstmt = conn.prepareStatement(sql);


			rs = pstmt.executeQuery();
			while(rs.next()) {
				CartVo vo = new CartVo();
				vo.setMemberName(rs.getString(1));
				vo.setBookTitle(rs.getString(2));
				vo.setCount(rs.getInt(3));
				vo.setBookPrice(rs.getInt(4));
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
			String sql = "delete from cart";
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
}
