<%@page import="vo.SuccessVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var id = opener.document.getElementById("paramid").value;
	var seq = opener.document.getElementById("paramseq").value;
	var level = 0;
	$(document).ready(
			function() {
				$("#o").on(
						'click',
						function() {
							level = 1;
							location.href = "setLevel.mvc?id=" + id + "&seq="
									+ seq + "&level=" + level;
							window.opener.location.reload();
							alert("평가를 완료하였습니다.");
							close();

						});
				$("#x").on(
						'click',
						function() {
							level = -1;
							location.href = "setLevel.mvc?id=" + id + "&seq="
									+ seq + "&level=" + level;
							window.opener.location.reload();
							alert("평가를 완료하였습니다.");
							close();
						});

			});
</script>
<style type="text/css">
h1{
	text-align: center;
}
</style>
</head>
<body>
	<h1>만족하셨나요?</h1>

	<input type="button" id="o" value="만족">
	<input type="button" id="x" value="불만족">


</body>
</html>