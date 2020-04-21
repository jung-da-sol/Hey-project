<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap&subset=korean"
   rel="stylesheet">
<link rel= "stylesheet" type="text/css" href="helpform.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
   $("#helpme_btn").on('click',function(){
      location.href = "helpme.mvc";
   });
   $("#helpyou_btn").on('click',function(){
      location.href = "helplist.mvc";
   });
   $("#mypage_btn").on('click',function(){
      location.href = "mypage.mvc";
   });
});
</script>
</head>
<body>

<c:if test="${sessionid!=null}">
<%-- <h1>${sessionid}님 로그인되셨습니다.</h1> --%>
<!-- session이용 -->
<div class="hey">
   <span class="content"><img src="hey.jpg" width=500px></span> 
   <span class="blank"></span>
   <div class ="login">
   <form>
   <input type="button" id="helpme_btn" value="도와주세요"><br>
   <input type="button" id="helpyou_btn"value="도와드려요"><br>
   <input type="button" id="mypage_btn"value="마이페이지"><br>
   <jsp:include page="logoutprocess.jsp"></jsp:include>
   </form>
   </div>
</div>
</c:if>

<c:if test="${sessionid==null}">
<script> alert('로그인 실패'); </script>
<jsp:include page="login.jsp"></jsp:include>
</c:if>

</body>
</html>