<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {

		$("#logout").on('click', function() {
			alert("로그아웃 되었습니다.");
			location.href = "login.jsp";
		});


		
		
	});
</script>


</head>
<body>
	<%
		if (session.getAttribute("sessionid") != null) {
	%>
	<input type=button value="로그아웃" id=logout>
	<a href="mypage.mvc"><input type=button value="마이페이지" id="mypage"></a>
	<%
		} else {
			%>
		<script type="text/javascript">
		alert("로그인 해주세요.");
		location.href = "login.jsp";
		</script>
	<%
	} %>
</body>
</html>