package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MappingVO;

public class MappingDAO {

	// 특정 게시글에 신청한 helper 조회 메소드 
	public ArrayList<MappingVO> getHelper_id(int board_seq) {
		ArrayList<MappingVO> list = new ArrayList<MappingVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from mapping where board_seq = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, board_seq);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				MappingVO vo = new MappingVO();
				vo.setBoard_seq(rs.getInt("board_seq"));
				vo.setHelper_id(rs.getString("helper_id"));

				list.add(vo);
			}
			rs.close();
			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// helper별 신청한 게시글 목록 메소드
	public ArrayList<MappingVO> getBoard_seq(String helper_id) {
		ArrayList<MappingVO> list = new ArrayList<MappingVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from mapping where helper_id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, helper_id);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				MappingVO vo = new MappingVO();
				vo.setBoard_seq(rs.getInt("board_seq"));
				vo.setHelper_id(rs.getString("helper_id"));

				list.add(vo);
			}
			rs.close();
			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// helper 신청 메소드
	public int insertApply(MappingVO vo) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "insert into mapping values(?, ?)";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, vo.getBoard_seq());
			pt.setString(2, vo.getHelper_id());

			result = pt.executeUpdate();

			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// helper 신청 삭제 메소드
	public int deleteMyApply(int seq, String id) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "delete from mapping where board_seq = ? and helper_id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, seq);
			pt.setString(2, id);
			
			result = pt.executeUpdate();

			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 게시글의 helper 목록 삭제 메소드
	public int deleteAllApply(int seq) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "delete from mapping where board_seq = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, seq);
			
			result = pt.executeUpdate();

			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		MappingVO vo = new MappingVO();
		vo.setBoard_seq(3);
		vo.setHelper_id("js");
		
		MappingDAO dao = new MappingDAO();
		System.out.println(dao.insertApply(vo));
		
	}
}
