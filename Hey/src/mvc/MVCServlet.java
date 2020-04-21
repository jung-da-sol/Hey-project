package mvc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dao.MappingDAO;
import dao.MemberDAO;
import dao.SuccessDAO;
import vo.BoardVO;
import vo.MappingVO;
import vo.MemberVO;
import vo.SuccessVO;

@WebServlet("*.mvc")
public class MVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		String[] uri_parse = uri.split("[/.]");
		for (int i = 0; i < uri_parse.length; i++) {
			System.out.println(uri_parse[i]);
		}
		String process = uri_parse[uri_parse.length - 2];

		if (process.contentEquals("login")) {// 로그인
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			if(id==null) {process = "login.jsp";}
			else {
			MemberDAO dao = new MemberDAO();
			int result = dao.loginMember(id, pw);
//			request.setAttribute("loginstatus", result);
			if (result == 1) {
				session.setAttribute("sessionid", id);
			}
			process = "help.jsp";
			}
		} else if (process.contentEquals("help")) {
			process = "help.jsp";
		}
		else if (process.contentEquals("join")) {// 회원가입
			process = "join.jsp";
		} else if (process.contentEquals("joinprocess")) {
			MemberDAO dao = new MemberDAO();
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("mail1") + "@" + request.getParameter("mail2");
			String gender = request.getParameter("gender");
			MemberVO vo = new MemberVO(id, pw, name, phone, email, gender);
			int result = dao.joinMember(vo);
			request.setAttribute("joinstatus", result);
			process = "login.jsp";
		} else if (process.contentEquals("helpme")) {
			process = "helpme.jsp";
		} else if (process.contentEquals("helpmewrite")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = (String) session.getAttribute("sessionid");
			String pw = request.getParameter("pw");
			String sido = request.getParameter("sido");
			String gugun = request.getParameter("gugun");
			String gender = request.getParameter("gender");
			String state = "true";

			BoardDAO dao = new BoardDAO();
			BoardVO vo = new BoardVO(title, content, writer, pw, sido, gugun, gender, state);

			int result = dao.insertBoard(vo);

			if (result == 1) {
				response.sendRedirect("helplist.mvc");
				return;
			} else {
				process = "helpme.jsp";
			}
		} else if (process.contentEquals("helplist")) {
			BoardDAO dao = new BoardDAO();
			ArrayList<BoardVO> list = dao.getBoardList();
			request.setAttribute("list", list);
			process = "helplist.jsp";
		} else if (process.contentEquals("search")) {
			String sido = request.getParameter("sido");
			String gugun = request.getParameter("gugun");
			String gender = request.getParameter("gender");
			String condition = request.getParameter("condition");

			if (sido.equals("시/도 선택"))
				sido = "%";
			if (gugun.equals(""))
				gugun = "%";
			if (gender.equals("무관"))
				gender = "%";
			if (condition.equals(""))
				condition = "%";
			else
				condition = "%" + condition + "%";

			System.out.println(sido + ", " + gugun + ", " + gender + ", " + condition);
			BoardDAO dao = new BoardDAO();
			ArrayList<BoardVO> list = dao.getSearchList(gender, sido, gugun, condition);
			request.setAttribute("list", list);
			System.out.println(list.size());
			process = "helplist.jsp";
		} else if (process.contentEquals("helpmeedit")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = (String) session.getAttribute("sessionid");
			String pw = request.getParameter("pw");
			String sido = request.getParameter("sido");
			String gugun = request.getParameter("gugun");
			String gender = request.getParameter("gender");
			int seq = Integer.parseInt(request.getParameter("seq"));
			String state = "true";

			BoardDAO dao = new BoardDAO();
			BoardVO vo = new BoardVO(title, content, writer, pw, sido, gugun, gender, state);
			vo.setSeq(seq);
			int result = dao.updateBoard(vo);

			response.sendRedirect("./helpedit.jsp?seq=" + seq + "&end=" + result);
			return;
		} else if (process.contentEquals("reqmapping")) {
			String helperid = request.getParameter("helper_id");
			int seq = Integer.parseInt(request.getParameter("seq"));

			MappingDAO mdao = new MappingDAO();
			MappingVO vo = new MappingVO();
			vo.setBoard_seq(seq);
			vo.setHelper_id(helperid);

			request.setAttribute("result", mdao.insertApply(vo));
			process = "mapping.jsp";
		} else if (process.contentEquals("getcount")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String id = (String) session.getAttribute("sessionid");
			MappingDAO mdao = new MappingDAO();
			ArrayList<MappingVO> mvolist = mdao.getBoard_seq(id);

			int result = 0;
			for (int i = 0; i < mvolist.size(); i++) {
				if (mvolist.get(i).getBoard_seq() == seq) {
					result = 1;
					break;
				}
			}
			request.setAttribute("result", result);
			process = "mapping.jsp";
		} else if (process.contentEquals("mypage")) {
			// 완료건수, 만족건수
			String id = (String) session.getAttribute("sessionid");
			SuccessDAO sdao = new SuccessDAO();
			int fin = sdao.getFinCount(id);
			int sat = sdao.getSatCount(id);
			System.out.println(fin + ", " + sat);
			request.setAttribute("fin", fin);
			request.setAttribute("sat", sat);

			// 도움받은거
			BoardDAO bdao = new BoardDAO();
			ArrayList<BoardVO> getHelpMelist = bdao.getMyBoardList(id);
			ArrayList<BoardVO> gtlistB = bdao.getHelpBoardB(id);
			ArrayList<BoardVO> gtlistA = bdao.getHelpBoardA(id);
			request.setAttribute("gtlistB", gtlistB);
			request.setAttribute("gtlistA", gtlistA);
			request.setAttribute("getHelpMelist", getHelpMelist);

			// 도움준거
			ArrayList<BoardVO> gvlist = bdao.giveHelpBoard(id);
			ArrayList<BoardVO> applylist = bdao.applyHelpBoard(id);
			request.setAttribute("gvlist", gvlist);
			request.setAttribute("applylist", applylist);

			process += ".jsp";

		} else if (process.contentEquals("myedit")) {
			// 수정전 기존 정보 띄우기
			MemberDAO dao = new MemberDAO();
			MemberVO pre_inf = dao.getMemberList((String) session.getAttribute("sessionid"));
			request.setAttribute("pre_inf", pre_inf);

			process += ".jsp";
		} else if (process.contentEquals("editprocess")) {

			MemberDAO dao = new MemberDAO();
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("mail1") + "@" + request.getParameter("mail2");
			String gender = request.getParameter("gender");
			MemberVO vo = new MemberVO(id, pw, name, phone, email, gender);
			int result = dao.updateMember(vo);
			request.setAttribute("editstatus", result);
			process = "mypage.mvc";

		} else if (process.contentEquals("editprocess")) {

			MemberDAO dao = new MemberDAO();
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("mail1") + "@" + request.getParameter("mail2");
			String gender = request.getParameter("gender");
			MemberVO vo = new MemberVO(id, pw, name, phone, email, gender);
			int result = dao.updateMember(vo);
			request.setAttribute("editstatus", result);
			process = "myedit.jsp";

		} else if (process.contentEquals("setLevel")) {
			String id = request.getParameter("id");
			int seq = Integer.parseInt(request.getParameter("seq"));
			int level = Integer.parseInt(request.getParameter("level"));

			SuccessDAO sdao = new SuccessDAO();
			SuccessVO vo = new SuccessVO();
			vo.setWriter_id(id);
			vo.setBoard_seq(seq);
			vo.setS_level(level);
			sdao.updateLevel(vo);
		} else {
			process = "none.jsp";
			System.out.println("잘못된 요청입니다.");
		}
		// 3. 응답할 jsp 파일 선정
		RequestDispatcher dispatcher = request.getRequestDispatcher("/MainPage/" + process);
		dispatcher.forward(request, response);

	}

}