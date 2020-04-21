<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정페이지입니다.</title>
<link rel= "stylesheet" type="text/css" href="myeditform.css" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
   function checkValue(){
        
      if(!document.edit.phone.value){
         alert("폰번호를 입력해주세요.");
         return false;
      }
      
      if(!document.edit.pw.value){
         alert("비밀번호를 입력해주세요.");
         return false;
      }
      
      if(document.edit.pw.value != document.edit.pwck.value){
         alert("비밀번호를 동일하게 입력해주세요.");
         return false;
      }

   }
   
   $(document).ready(function(){
   $("#cancel").on("click",function(){
      location.href = "mypage.mvc";
   });
   });

</script>
</head>
<body>

<div class="wrap">        
      <br><br>
        <b><font size="6" color="gray">회원정보 수정</font></b>
        <br><br><br>
        </div>
        
        <form action="editprocess.mvc" name="edit" onsubmit="return checkValue()">
            <table>
                <tr>
                    <td id="title" >아이디</td>
                    <td>
                        <input type="text" name="id" id="id" maxlength="50" readonly="readonly" value='${sessionid}'>
                     </td>
                </tr>
                        
                <tr>
                    <td id="title">비밀번호</td>
                    <td>
                        <input type="password" name="pw" maxlength="50"   >
                    </td>
                </tr>
                
                <tr>
                    <td id="title">비밀번호 확인</td>
                    <td>
                        <input type="password" name="pwck" maxlength="50" >
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">이름</td>
                    <td>
                        <input type="text" name="name" maxlength="50" value='${pre_inf.name}'>
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">성별</td>
                    <td>
                    <c:if test="${pre_inf.gender eq '남' }">
                        <input type="radio" name="gender" value="남" checked>남
                        <input type="radio" name="gender" value="여" >여
                    </c:if>
                    <c:if test="${pre_inf.gender ne '남' }">
                        <input type="radio" name="gender" value="남" >남
                        <input type="radio" name="gender" value="여" checked>여
                    </c:if>
                    </td>
                </tr>                   
                    
                <tr>
                    <td id="title">이메일</td>
                    <td>
                        <input type="text" name="mail1" maxlength="50" value="${pre_inf.email}">
                        @
                        <select name="mail2">
                            <option>naver.com</option>
                            <option>daum.net</option>
                            <option>gmail.com</option>
                            <option>nate.com</option>                        
                        </select>
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">휴대전화</td>
                    <td>
                        <input type="text" name="phone" value="${pre_inf.phone}" />
                    </td>
                </tr>
                
            </table>
            <br>
            <div class="wrap">
            <input type="submit" id="edit" value="수정완료">  
            <input type="button" id="cancel" value="취소">
      </div>        
        </form>
      
        <c:if test="${editstatus==1}">
      <script>
      
      alert('완료되었습니다.'); 
      location.href = "mypage.mvc";
      
      </script>
   </c:if>
</body>
</html>