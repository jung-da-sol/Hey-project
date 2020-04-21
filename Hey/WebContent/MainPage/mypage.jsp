<%@page import="dao.MappingDAO"%>
<%@page import="vo.MappingVO"%>
<%@page import="vo.SuccessVO"%>
<%@page import="dao.SuccessDAO"%>
<%@page import="vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page입니다.</title>
<link rel="stylesheet" type="text/css" href="mypageform.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document)
			.ready(
					function() {

						$("#edit").on('click', function() {
							location.href = "myedit.mvc";
						});
						
						$("#home").on('click', function() {
							location.href = "help.mvc";
						});

						$("#back").on('click', function() {
							window.history.back();
						});

						$("#setLevel").on('click', function() {
							window.name = "setLevel";
							openWin = window.open("setLevel.jsp","평가하기",
						  "width = 500, height = 500, top = 100, left = 200, location = no");
										});

					});
</script>

</head>
<body>

	
	<div>

		<div class=first>
			<table>
				<tr>
					<th>${sessionid}님정보</th>
				</tr>
				<tr>
					<td>완료건수 : ${fin}</td>
				</tr>
				<tr>
					<td>만족도 : ${sat}</td>
				</tr>
				<tr>
					<td><input type="button" value="홈" id="home"><input type="button" value="수정" id="edit"><jsp:include page="logoutprocess.jsp"/></td>
				</tr>
			</table>
		</div>

		<div class=second>
		<h1>Writer</h1>
			<table id="get">
				<tr>
					<td colspan="5">내가 쓴 글</td>
				<tr>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>신청자</th>
				</tr>
				<c:forEach items="${getHelpMelist}" var="b">
					<tr>
						<td>${b.seq}</td>
						<td><a href='helpdetail.jsp?seq=${b.seq}'>${b.title}</a></td>
						<td>${b.content}</td>
						<td>${b.writer}</td>
						<c:set var="bseq" value="${b.seq}" />
						<%
							int bseq = (int) pageContext.getAttribute("bseq");
								MappingDAO mdao = new MappingDAO();
								ArrayList<MappingVO> mvlist = mdao.getHelper_id(bseq);
						%>
						<td>
							<%
								if (mvlist.size() != 0) {
										out.print("O");
									}else{
										out.print("X");
									}
							%>
						</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<table>
				<tr>
					<td colspan="5">도움받기 완료(평가전)</td>
				<tr>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>평가하기</th>
				</tr>
				<c:forEach items="${gtlistB}" var="b">
					<tr>
						<td>${b.seq}<input type="hidden" value="${sessionid}"
							id="paramid"> <input type="hidden" value="${b.seq}"
							id="paramseq">
						</td>
						<td><a href='helpdetail.jsp?seq=${b.seq}'>${b.title}</a></td>
						<td>${b.content}</td>
						<td>${b.writer}</td>
						<td><input type="button" id="setLevel" value="평가하기"></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<table>
				<tr>
					<td colspan="5">도움받기 완료(평가 후)</td>
				<tr>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
				</tr>
				<c:forEach items="${gtlistA}" var="b">
					<tr>
						<td>${b.seq}</td>
						<td><a href='helpdetail.jsp?seq=${b.seq}'>${b.title}</a></td>
						<td>${b.content}</td>
						<td>${b.writer}</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<h1>Helper</h1>
			<table>
				<tr>
					<td colspan="4">도움주기 신청중</td>
				</tr>
				<tr>

					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
				</tr>
				<c:forEach items="${applylist}" var="b">
					<tr>
						<td>${b.seq}</td>
						<td><a href='helpdetail.jsp?seq=${b.seq}'>${b.title}</a></td>
						<td>${b.content}</td>
						<td>${b.writer}</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<table>
				<tr>
					<td colspan="4">도움주기 완료</td>
				<tr>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
				</tr>
				<c:forEach items="${gvlist}" var="b">
					<tr>
						<td>${b.seq}</td>
						<td><a href='helpdetail.jsp?seq=${b.seq}'>${b.title}</a></td>
						<td>${b.content}</td>
						<td>${b.writer}</td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>


</body>
</html>