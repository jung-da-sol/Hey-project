<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int result = (Integer)request.getAttribute("result");
	if(result == 1 ){
		out.println("{\"count\": \"1\"}");
	}else{
		out.println("{\"count\": \"0\"}");
	}

%>