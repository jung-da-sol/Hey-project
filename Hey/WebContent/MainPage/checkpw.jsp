<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BoardDAO dao = new BoardDAO();
	int seq = Integer.parseInt(request.getParameter("seq"));
	String pw = request.getParameter("pw");
	BoardVO vo = dao.getBoardDetail(seq);
	boolean result = false;
	if (pw.equals(vo.getPw())) {
		result = true;
	}
	response.getWriter().println(result);
%>