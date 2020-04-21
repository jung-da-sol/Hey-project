<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 화면</title>
<link rel= "stylesheet" type="text/css" href="loginform.css" />

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script>
$(document).ready(function(){
	
function checkValue()
{
   if(!document.join.id.value){
      alert("아이디를 입력해주세요.");
      return false;
   }
   
   if(!document.join.password.value){
      alert("비밀번호를 입력해주세요.");
      return false;
   }
   
   if(!document.join.password.value != document.join.passwordcheck.value){
      alert("비밀번호를 동일하게 입력해주세요.");
      return false;
   }
   
   return true;
}

$("#idcheck").on("click",confirmId);
function confirmId(){
	if($("#id").val() == ""){
		alert("ID를 입력하세요");
		return;
	}
	url = "confirmid.jsp?id=" + $("#id").val();
	open(url, "confirm", "width=300px, height=300px left=100px, top=100px");
}

$("#cancel").on("click",function(){
	location.href = "login.jsp";
});

});
</script>
</head>
<body>
<div class="wrap">        
      <br><br>
        <b><font size="6" color="gray">회원가입</font></b>
        <br><br><br>
        </div>
        <!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
        <!-- 값(파라미터) 전송은 POST 방식, 전송할 페이지는 JoinPro.jsp -->
        <form action="joinprocess.mvc" name="join" onsubmit="return checkValue()">
            <table>
                <tr>
                    <td id="title">아이디</td>
                    <td>
                        <input type="text" name="id" id="id" maxlength="50" required="required">
                       &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="중복확인" id="idcheck">    
                    </td>
                </tr>
                        
                <tr>
                    <td id="title">비밀번호</td>
                    <td>
                        <input type="password" name="pw" maxlength="50" required="required">
                    </td>
                </tr>
                
                <tr>
                    <td id="title">비밀번호 확인</td>
                    <td>
                        <input type="password" name="passwordcheck" maxlength="50" required="required">
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">이름</td>
                    <td>
                        <input type="text" name="name" maxlength="50" required="required">
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">성별</td>
                    <td>
                        <input type="radio" name="gender" value="남" >남
                        <input type="radio" name="gender" value="여" >여
                    </td>
                </tr>                    
                <tr>
                    <td id="title">이메일</td>
                    <td>
                        <input type="text" name="mail1" maxlength="50" required="required">
                        @
                        <select name="mail2">
                            <option value="naver.com"></option>
                            <option value="daum.net"></option>
                            <option value="gmail.com"></option>
                            <option value="nate.com"></option>                        
                        </select>
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">휴대전화</td>
                    <td>
                        <input type="text" name="phone" required="required"/>
                    </td>
                </tr>
                
            </table>
            <br>
            <div class="wrap">
            <input type="submit" id="join" value="가입" />  <input type="button" id="cancel" value="취소">
			</div>        
        </form>
         
	<c:if test="${joinstatus==1}">
		<script> 
		var result = confirm('완료되었습니다.'); 
		if(result || !result){
			location.href = "help.jsp";
		}
		</script>
	</c:if>


</body>
</html>