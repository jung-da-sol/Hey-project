<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){

});
</script>
</head>
<body>
<%
String id = request.getParameter("id");
MemberDAO dao = new MemberDAO();
int result = dao.getMember(id);
if(result == 1){
	out.println("사용중인 아이디입니다.");
}
else{
	out.println("사용할수 있는 아이디입니다.");
}
%>

</body>
</html>