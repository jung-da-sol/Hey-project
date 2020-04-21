package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.SuccessVO;

public class SuccessDAO {
	// 매칭 리스트 조회 메소드
	public ArrayList<SuccessVO> getMatchingList() {
		ArrayList<SuccessVO> list = new ArrayList<SuccessVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from success order by helper_id";
			PreparedStatement pt = con.prepareStatement(sql);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				SuccessVO vo = new SuccessVO();
				vo.setHelper_id(rs.getString("helper_id"));
				vo.setWriter_id(rs.getString("writer_id"));
				vo.setBoard_seq(rs.getInt("board_seq"));
				vo.setS_level(rs.getInt("s_level"));
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

	// 개인 매칭 리스트 조회 메소드 helper 기준
	public ArrayList<SuccessVO> getMyMatching_H(String id) {
		ArrayList<SuccessVO> list = new ArrayList<SuccessVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from success where helper_id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				SuccessVO vo = new SuccessVO();
				vo.setHelper_id(rs.getString("helper_id"));
				vo.setWriter_id(rs.getString("writer_id"));
				vo.setBoard_seq(rs.getInt("board_seq"));
				vo.setS_level(rs.getInt("s_level"));
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

	public ArrayList<SuccessVO> getMyMatching_W(String id) {
		ArrayList<SuccessVO> list = new ArrayList<SuccessVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from success where writer_id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				SuccessVO vo = new SuccessVO();
				vo.setHelper_id(rs.getString("helper_id"));
				vo.setWriter_id(rs.getString("writer_id"));
				vo.setBoard_seq(rs.getInt("board_seq"));
				vo.setS_level(rs.getInt("s_level"));
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

	// 게시글의 매칭여부 확인 매소드
	public int getMatchingBoard(String id, int seq) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from success where helper_id = ? and board_seq = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			pt.setInt(2, seq);

			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				result = 1;
			}
			rs.close();
			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 매칭 성공시 DB저장 메소드
	public int insertMatching(SuccessVO vo) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "insert into success values(?, ?, ?, 0)";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, vo.getHelper_id());
			pt.setString(2, vo.getWriter_id());
			pt.setInt(3, vo.getBoard_seq());
			System.out.println(vo.getHelper_id());
			result = pt.executeUpdate();

//			if(result == 1) {
//				MappingDAO dao = new MappingDAO();
//				dao.deleteMyApply(vo.getBoard_seq(), vo.getHelper_id());
//				dao.deleteAllApply(vo.getBoard_seq());
//			}

			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// level 업데이트 메소드
	public int updateLevel(SuccessVO vo) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "update success set s_level = ? where writer_id = ? and board_seq = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, vo.getS_level());
			pt.setString(2, vo.getWriter_id());
			pt.setInt(3, vo.getBoard_seq());

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

	// level 조회 메소드
	public int getLevel(SuccessVO vo) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "select sum(s_level) from success where helper_id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, vo.getHelper_id());

			ResultSet rs = pt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("sum(s_level)");
			}

			rs.close();
			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// mypage에 조회할 완료된 개수
	public int getFinCount(String id) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select count(*) from success where helper_id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			if (rs.next())
				result = rs.getInt(1);

			rs.close();
			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	// 만족 개수
	public int getSatCount(String id) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select sum(s_level) from success where helper_id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);

			ResultSet rs = pt.executeQuery();

			if (rs.next())
				result = rs.getInt(1);

			rs.close();
			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
