package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.BoardVO;

public class BoardDAO {

	// 전체 리스트 조회 메소드
	public ArrayList<BoardVO> getBoardList() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from board order by seq desc";
			PreparedStatement pt = con.prepareStatement(sql);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setPw(rs.getString("pw"));
				vo.setIndate(rs.getString("indate"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setGender(rs.getString("gender"));
				vo.setState(rs.getString("state"));
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

	// 상세 게시물 조회 메소드
	public BoardVO getBoardDetail(int seq) {
		BoardVO vo = new BoardVO();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "select * from board where seq = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1, seq);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setPw(rs.getString("pw"));
				vo.setIndate(rs.getString("indate"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setGender(rs.getString("gender"));
				vo.setState(rs.getString("state"));
			}
			rs.close();
			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	// 게시물 검색결과 리스트
	public ArrayList<BoardVO> getSearchList(String g, String sd, String gg, String c) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from board where"
					+ " gender like ? and sido like ? and gugun like ? and (title like ? or content like ?)";

			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, g);
			pt.setString(2, sd);
			pt.setString(3, gg);
			pt.setString(4, c);
			pt.setString(5, c);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setPw(rs.getString("pw"));
				vo.setIndate(rs.getString("indate"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setGender(rs.getString("gender"));
				vo.setState(rs.getString("state"));
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
		System.out.println("갯수 ====> " + list.size());
		return list;
	}

	// 내 게시물 리스트 메소드
	public ArrayList<BoardVO> getMyBoardList(String id) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from board where writer = ? order by seq desc";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setPw(rs.getString("pw"));
				vo.setIndate(rs.getString("indate"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setGender(rs.getString("gender"));
				vo.setState(rs.getString("state"));
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

	// board 게시물 추가 메소드
	public int insertBoard(BoardVO vo) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "insert into board values((select max(seq) from board) + 1, ?, ?, ?, ?, sysdate, ?, ?, ?, ?)";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, vo.getTitle());
			pt.setString(2, vo.getContent());
			pt.setString(3, vo.getWriter());
			pt.setString(4, vo.getPw());
			pt.setString(5, vo.getSido());
			pt.setString(6, vo.getGugun());
			pt.setString(7, vo.getGender());
			pt.setString(8, vo.getState());

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

	// board 게시물 수정 메소드
	public int updateBoard(BoardVO vo) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "update board set title = ?, content = ?, writer = ?, indate = sysdate, "
					+ "sido = ?, gugun = ?, gender = ? where seq = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, vo.getTitle());
			pt.setString(2, vo.getContent());
			pt.setString(3, vo.getWriter());
			pt.setString(4, vo.getSido());
			pt.setString(5, vo.getGugun());
			pt.setString(6, vo.getGender());
			pt.setInt(7, vo.getSeq());

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

	// board 상태 수정 메소드
	public int updateBoardState(BoardVO vo) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "update board set state = ? where seq = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, vo.getState());
			pt.setInt(2, vo.getSeq());

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

	// board 게시물 삭제 메소드
	public int deleteBoard(int seq) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "delete from board where seq = ?";
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
	
	

	// 내가 도움받은 board게시물 조회 평가전
	public ArrayList<BoardVO> getHelpBoardB(String id) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from board where seq in (select board_seq from success where writer_id = ? and s_level = ?)";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			pt.setInt(2, 0);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setPw(rs.getString("pw"));
				vo.setIndate(rs.getString("indate"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setGender(rs.getString("gender"));
				vo.setState(rs.getString("state"));
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
	
	// 내가 도움받은 board게시물 조회 평가후
	public ArrayList<BoardVO> getHelpBoardA(String id) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from board where seq in (select board_seq from success where writer_id =? and (s_level=1 or s_level=-1))";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setPw(rs.getString("pw"));
				vo.setIndate(rs.getString("indate"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setGender(rs.getString("gender"));
				vo.setState(rs.getString("state"));
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
	
	// 내가 도움주기 신청한 board게시물 조회
	public ArrayList<BoardVO> applyHelpBoard(String id) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from board where seq in (select board_seq from mapping where helper_id =?) and state = 'true'";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setPw(rs.getString("pw"));
				vo.setIndate(rs.getString("indate"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setGender(rs.getString("gender"));
				vo.setState(rs.getString("state"));
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

	// 내가 도움을 준 board게시물 조회
	public ArrayList<BoardVO> giveHelpBoard(String id) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from board where seq in (select board_seq from success where helper_id =?)";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setPw(rs.getString("pw"));
				vo.setIndate(rs.getString("indate"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setGender(rs.getString("gender"));
				vo.setState(rs.getString("state"));
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
	
	// state false로 바꾸는 함수
	public int updateBoardState(int seq) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");

			String sql = "update board set state = 'false' where seq = ?";
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
}
