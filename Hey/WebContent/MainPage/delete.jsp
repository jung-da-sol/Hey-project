<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
BoardDAO dao = new BoardDAO();
int seq= Integer.parseInt(request.getParameter("seq"));
%>

<%=dao.deleteBoard(seq)%>