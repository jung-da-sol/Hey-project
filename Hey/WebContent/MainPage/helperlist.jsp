<%@page import="dao.BoardDAO"%>
<%@page import="vo.SuccessVO"%>
<%@page import="dao.SuccessDAO"%>
<%@page import="vo.MappingVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.MappingDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
   int seq = Integer.parseInt(request.getParameter("seq"));
   SuccessDAO sdao = new SuccessDAO();
   ArrayList<SuccessVO> slist = sdao.getMatchingList();
   boolean isMatching = false;
   String helper = "";
   for (int i = 0; i < slist.size(); i++) {
      if (slist.get(i).getBoard_seq() == seq) {
         isMatching = true;
         helper = slist.get(i).getHelper_id();
         break;
      }
   }
   MappingDAO mdao = new MappingDAO();

   ArrayList<MappingVO> mvlist = mdao.getHelper_id(seq);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
button {
   color: navy;
   font-size: large;
   font-family: 고딕;
   font-weight: bold;
   margin: auto;
   position: relative;
   height: 100%;
   width: 100px;
   background-color: gold;
   display: block;
   -webkit-border-radius: 6px;
   -moz-border-radius: 6px;
   border-radius: 60px;
   text-align: center;
   -webkit-transform: translate(0, 0);
   -moz-transform: translate(0, 0);
   transform: translate(0, 0);
   -webkit-transition: all .2s ease;
   -moz-transition: all .2s ease;
   -ms-transition: all .2s ease;
   -o-transition: all .2s ease;
   transition: all .2s ease;
   cursor: pointer;
   overflow: hidden;
}

button:active {
   -webkit-transform: translate(0, 3px);
   -moz-transform: translate(0, 3px);
   transform: translate(0, 3px);
}

table {
   width: 100%;
   margin: auto;
   text-align: center;
   border-collapse: collapse;
}

table tr {
   border: 1px solid black;
}
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
   <h1>지니 지원자 목록</h1>
   <table>
      <%
         for (int i = 0; i < mvlist.size(); i++) {
      %>
      <tr>
         <td id="id_<%=i%>"><%=mvlist.get(i).getHelper_id()%></td>
         <td>
            <%
               if (!isMatching) {
            %>
            <button id="helper_<%=i%>">결정</button> <%
    } else if (mvlist.get(i).getHelper_id().equals(helper)) {
 %> 마이 지니 <%
    }
    }
 %>
         </td>
      </tr>
   </table>

   <button id="close">닫기</button>

   <script type="text/javascript">
 $('#close').on('click',()=>{window.close()})
    <%if (!isMatching) {%>
       for(let i=0;i<$('tr').length;i++){
         $('#helper_'+i).on('click',()=>{
            $.ajax({
               url:'matchinghelper.jsp', //주소
               type:'get', // 전송방식
               data:{'id':$('#id_'+i).text(),'seq':${param.seq} }, //json형태 데이터
               datatype:'application/json',
               success:disableBTN,
               error:(e)=>{console.log(e)},
               complete:(e)=>{console.log(e)}
            })
            
         })
      }
      
      function disableBTN(result){
         if(result.trim()!=1){
         alert("등록 실패했습니다")
         return;
      }


            alert("결정되었습니다.");
             <%BoardDAO bdao = new BoardDAO();
          bdao.updateBoardState(seq);%>
             location.reload();

      }
   <%}%>
       
    
    </script>
</body>
</html>