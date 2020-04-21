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
$(document).ready(function(){
	$("#home").on('click', function() {
		alert("홈으로 이동하였습니다.");
		location.href = "login.mvc";
		
	});
	
});
</script>
</head>
<body>


	<%
		if (session.getAttribute("sessionid") != null) {
	%>

	<input type=button value="Home" id=home>
	
	<%String sessionid = (String)session.getAttribute("sessionid"); 
		session.setAttribute("sessionid", sessionid);
	
		}
	%>

</body>
</html>