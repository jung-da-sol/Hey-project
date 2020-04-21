<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% session.invalidate(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap&subset=korean"
	rel="stylesheet"> -->
<link rel= "stylesheet" type="text/css" href="helpform.css">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
$(document).ready(function(){
	$("#join").on('click',function(){
		location.href = "join.jsp";
	});	
});

</script>

</head>
<body>
<div class="hey">
	<img src="hey.jpg" width=500px>
	<br><br><br><br><br>
	<form action="login.mvc">
	<table id="menu">
	<tr align="center"><td colspan="2"><input type="text" class="inputarea" id="id" name="id" placeholder="아이디"></td></tr>
	<tr align="center"><td colspan="2"><input type="password" class="inputarea" id="pw" name="pw" placeholder="비밀번호"></td></tr>
	<tr><td align="center"><input type="submit" class="btn" id="login" value="로그인"></td>	
	<td><input type="button" class="btn" id="join" value="회원가입"></td></tr>
	</table>
	</form>
</div>


</body>
</html>