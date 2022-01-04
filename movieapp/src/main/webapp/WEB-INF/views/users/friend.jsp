<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../includes/header.jsp"%>

<div class="container-fluid bg-light p-5">
<h1>친구목록 페이지</h1>
<hr>
<li class="list-group list-group-horizontal">
  <a class="list-group-item" href="/mypage">내정보수정</a>
  <a class="list-group-item" href="/friend">친구목록</a>
  <a class="list-group-item" href="/friendreq">친구신청목록</a>
</li>
<hr>
<h2>친구목록</h2>
<ul class="list-group">
	<c:forEach var="a" items="${friend}">
		<li class="list-group-item">${a}
			<div align="right">
				<button class="btn btn-primary btn-sm">쪽지보내기</button>
				<a href="/frienddel?friend=${a}" class="btn btn-primary btn-sm">친구삭제</a>
			</div>
		</li>
	</c:forEach>
</ul>
<hr>
<h2>친구 요청목록</h2>
	<%@include file="./friendres.jsp"%>
	
</div>

