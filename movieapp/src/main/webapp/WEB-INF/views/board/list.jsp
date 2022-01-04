<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jspHeader.jsp" %>
<%@include file="../includes/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<style type="text/css">
	.page{text-align:center; vertical-align:top;}
</style>
</head>
<body>
<div class="container-fluid bg-light p-5">
<h1>게시판 리스트 보기</h1>
<hr><%@include file="./boardheader.jsp"%><hr>
<table class="page" width="100%">
<c:if test="${listcount>0 }" >
		<tr><td colspan="4">Movie 게시판</td><td>글개수:${listcount }</td></tr>
		<tr><th>번호</th><th>제목</th><th>영화관</th><th>관람날짜</th><th>조회수</th></tr>
		<c:forEach var="board" items="${boardlist }">
		<tr><td>${boardno }</td>
		<c:set var="boardno" value="${boardno - 1 }" />
		<td style="text-align : left;">
	<a href="detail?seq=${board.seq }">${board.p_title }</a></td>
		<td>${board.p_cinema }</td>
		<td><fmt:formatDate value = "${board.p_wdate }" pattern="yy-MM-dd" /> </td>
		<td>${board.p_hit }</td></tr>
		</c:forEach>
	<tr><td colspan="5">
		<c:if test="${pageNum > 1 }">
			<a href="javascript:listpage(${pageNum-1})">[이전]</a></c:if>
			<c:if test="${pageNum <= 1 }">[이전]</c:if>
		<c:forEach var="a" begin="${startpage }" end="${endpage }">
			<c:if test="${a == pageNum }">[${a }] </c:if>
			<c:if test="${a != pageNum }">
			<a href="javascript:listpage(${a})">[${a }]</a></c:if>
		</c:forEach>
		<c:if test="${pageNum < maxpage }"><a href="javascript:listpage(${pageNum+1})">[다음]</a></c:if>
		<c:if test="${pageNum >= maxpage }">[다음]</c:if></td></tr>
</c:if>
<c:if test="${listcount ==0 }">
			<tr><td colspan="5">등록된 게시물이 없습니다.</td></tr></c:if>
		

		<tr><td colspan="5" align="right">
		<a href="write">[글쓰기]</a></td></tr>
	</table>
</div>
</body>
</html>