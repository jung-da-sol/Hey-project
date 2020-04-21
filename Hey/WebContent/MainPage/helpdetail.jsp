<%@page import="dao.BoardDAO"%>
<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	BoardDAO dao = new BoardDAO();
	BoardVO vo = dao.getBoardDetail(seq);
	System.out.println(vo.getPw());
	//BoardVO vo = new BoardVO();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=vo.getTitle()%></title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		
		 $("#home").on("click", function confirmId(){
			 location.href = "help.jsp";
		 });
		
		

	}); </script>
<style>
th {
	font-size: xx-large;
	height: 10%;
}

tr.btn_part {
	height: 10%;
}

tr.info_part {
	height: 5%;
}


td#logout{
height: 10%}

td#idtd {
	width: 20%;
}

td#date {
	width: 60%;
}

td#edit {
	width: 30%;
}

table {
	width: 600px;
	height: 600px;
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
	margin: auto;
}

table tr td {
	border: 1px solid black;
}

table tr.btn_part td {
	border: none;
}

a {
	text-decoration: none;
}

/* button {
	height: 100%;
	width: 100%;
	background-color: gold;
	border : 0;
} */
button {
	color: navy;
	font-size: large;
	font-family: 고딕;
	font-weight: bold;
	position: relative;
	height: 100%;
	width: 100%;
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

button:active{
	-webkit-transform: translate(0, 3px);
	-moz-transform: translate(0, 3px);
	transform: translate(0, 3px);
}

input#pw {
	width: 95%;
	height: 100%;
}
</style>
</head>
<body>
	
	<input type="hidden" id="user_id" value="${sessionid}">
	<input type="hidden" id="seq" value="<%=vo.getSeq()%>">
	<table>
		<tr>
			<th colspan="3"><%=vo.getTitle()%> </th>
		</tr>
		<tr class="info_part">
			<td id="idtd">작성자 : <span id="id"><%=vo.getWriter()%></span></td>
			<td id="date" colspan="2">날짜 : <%=vo.getIndate()%></td>
		</tr>
		<tr class="info_part">
			<td colspan="2">지역 : <%
				if (vo.getGugun() == null) {
					out.print("무관");
				} else {
					out.print(vo.getSido() + " / " + vo.getGugun());
				}
			%></td>
			<td>성별 : <%=vo.getGender()%></td>
		</tr>
		<tr>
			<td colspan="3" id="content"><%=vo.getContent()%></td>
		</tr>
		<tr class="btn_part"> 
	
	</tr>
	</table>
		
	<input type="hidden" id="state" value=<%=vo.getState()%>>

	<script>
        let tableFooter = document.getElementsByClassName('btn_part')[0];
        let user = document.getElementById('user_id').value;
        let writer = document.getElementById('id').innerText;
        let state = document.getElementById('state').value;
        let editWindow;


        
        
        

        if (user !== writer) {
			


        	tableFooter.innerHTML += '<td id="home"><a href="help.jsp">홈</td>';
            tableFooter.innerHTML += '<td id="doing"><button id="help_req">도와줄게요</button></td>';
            tableFooter.innerHTML += '<td id="logout"><a href="login.jsp">로그아웃</td>';
            let helpBtn = document.getElementById('help_req');
            let xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    let count = parseInt(JSON.parse(this.responseText).count);
                    if (count !== 0) {
                        helpBtn.disabled = 'disabled';
                        helpBtn.innerText = '신청완료';
                    }
                	if (state == "false") {
            		helpBtn.disabled = 'disabled';
                    helpBtn.innerText = '매칭완료';
            		}
                }
            };
            xhttp.open("GET", "getcount.mvc?seq="+${param.seq}, true);
            xhttp.send();

            helpBtn.addEventListener('click', (ev) => {
            	let xhttp = new XMLHttpRequest()
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        let count = parseInt(JSON.parse(this.responseText).c)
                        if (count !== 0) {
                            helpBtn.disabled = 'disabled'
                            helpBtn.innerText = '신청완료'
                        }
                    }
                };
                
                let data = "?seq="+${param.seq}+"&helper_id=" + "${sessionid}";
                xhttp.open("GET", "reqmapping.mvc"+data, true); 
                xhttp.send();
            });

        } else {
            tableFooter.innerHTML = '<td id="doing" colspan="3"><button id="help_list">도와주려는 사람들</button></td>';
            let tb = document.getElementsByTagName("table")[0];
            tb.innerHTML += '<tr class="btn_part"><td id="edit"><input type="password" id="pw" placeholder="암호"></button></td>' 
	            + '<td id="edit"><button id="edit_btn">수정</button></td>' 
	            + '<td id="delete"><button id="delete_btn">삭제</button></td></tr>';
            let editBtn = document.getElementById('edit_btn');
            let delBtn = document.getElementById('delete_btn');
 
            document.getElementById('help_list').addEventListener('click', (ev) => {
                let listsindow = window.open('helperlist.jsp?seq=' + ${ param.seq }, "", "width=600px,height=600px");
            });
            
            editBtn.addEventListener('click', (ev) => {
	            	
	                let data = "seq=" + document.getElementById('seq').value + "&pw=" + document.getElementById('pw').value;
	
	                function pwcheck(response) {
	                	let result = response.trim();
	                    if (result === 'true') {
	                        editWindow = window.open('helpedit.jsp?seq=' + ${ param.seq }, "", "width=600px");
	                    } else {
	                        alert('암호 틀렸습니다');
	                    }
	                }
	
	                doPostAjax("checkpw.jsp",data, pwcheck);
            });

            delBtn.addEventListener('click', (ev) => {

                if (confirm("정말 삭제하시겠습니까??") == true) {    //확인

                    let data = "seq=" + document.getElementById('seq').value + "&pw=" + document.getElementById('pw').value;

                    function pwcheck(response) {
                    	let result = response.trim();
                        if (result === 'true') {
                            function del(responseText) {
                                if (responseText == 1) {
                                    location.href = "helplist.mvc"; //일단 성공이고뭐고 목록으로 보냄
                                } else {
                                    alert('삭제 실패했습니다. 관리자에게 문의하십시오');
                                }
                            };

                            doPostAjax("delete.jsp", data, del);
                        } else {
                            alert('암호 틀렸습니다');
                        }
                    }

                    doPostAjax("checkpw.jsp",data, pwcheck);

                }
            })
        }


        function doPostAjax(addr, data, func) {
            let delreq = new XMLHttpRequest()

            delreq.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {

                    func(this.responseText);

                }
            };

            delreq.open("POST", addr, true);
            delreq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            delreq.send(data);
        }

    </script>
</body>

</html>