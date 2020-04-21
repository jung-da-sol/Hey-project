package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.MemberVO;

public class MemberDAO {

	// 로그인 메소드
	public int loginMember(String id, String pw) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from	member where id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			if (rs.next()) {
				String db_pw = rs.getString("pw");
				if (pw.equals(db_pw)) {
					result = 1;
				} else {
					result = 2;
				}
			} else {
				result = 3;
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

	// 아이디 중복확인
	public int getMember(String id) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from	member where id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			if (rs.next()) {
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

	// 회원가입 메소드
	public int joinMember(MemberVO vo) {
		int recodecnt = 0;
		try {
			// 1. jdbc driver 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. oracle 127.0.0.1 1521 xe, hr, hr 연결
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "insert into member values(?, ?, ?, ?, ?, ?)";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, vo.getId());
			pt.setString(2, vo.getPw());
			pt.setString(3, vo.getName());
			pt.setString(4, vo.getPhone());
			pt.setString(5, vo.getEmail());
			pt.setString(6, vo.getGender());

			recodecnt = pt.executeUpdate();

			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recodecnt;
	}

	// 회원정보 수정 메소드
	public int updateMember(MemberVO vo) {
		int recodecnt = 0;
		try {
			// 1. jdbc driver 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. oracle 127.0.0.1 1521 xe, hr, hr 연결
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "update member set pw = ?, name = ?, phone = ?, email = ?, gender = ? where id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, vo.getPw());
			pt.setString(2, vo.getName());
			pt.setString(3, vo.getPhone());
			pt.setString(4, vo.getEmail());
			pt.setString(5, vo.getGender());
			pt.setString(6, vo.getId());

			recodecnt = pt.executeUpdate();

			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 설치 오류");
		} catch (SQLException e) {
			System.out.println("연결 정보 오류");
		}
		return recodecnt;
	}

	// 회원정보조회
	public MemberVO getMemberList(String id) {
		MemberVO inf = new MemberVO();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select * from member where id= ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				inf.setId(rs.getString("id"));
				inf.setPw(rs.getString("pw"));
				inf.setName(rs.getString("name"));
				inf.setPhone(rs.getString("phone"));
				inf.setEmail(rs.getString("email").split("@")[0]);
				inf.setGender(rs.getString("gender"));

			}
			System.out.println(id);
			System.out.println(inf);
			rs.close();
			pt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inf;
	}

	// 메일주소만 가져오기
	public String getMemberEmail(String id) {
		String result = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.15.119:1521:xe", "pro", "pro");
			String sql = "select email from	member where id = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			if (rs.next()) {
				result = rs.getString(1);
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

}
