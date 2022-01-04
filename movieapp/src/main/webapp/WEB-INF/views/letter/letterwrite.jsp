<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/header.jsp"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>


	<script>
	function sendbtn(){
		alert("쪽지를 성공적으로 전송했습니다.");
	}
	</script>
	<%@include file="./lettertop.jsp"%>
	<div class="container-fluid bg-light p-5">
	<h1>쪽지쓰기</h1>
	<hr>
	<form action="<%=request.getContextPath() %>/sendletter" method="post">
	<input type="hidden" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" name="user_id"/>
	
	&nbsp;&nbsp;&nbsp;<%="받는사람"%>
	<c:if test="${not empty letrcvid}">
	<input name="rcv_id" type="text" size="40" style="margin-left: 15px; margin-bottom:5px" value="${letrcvid}" readonly>
	</c:if>
	<c:if test="${empty letrcvid}">
	<input name="rcv_id" type="text" placeholder="여러명은 쉼표(,)로 구분" size="40" style="margin-left: 15px; margin-bottom:5px">
	</c:if>
<!-- 	<input type="button" class="btn" value="찾기" id="findfr" style="margin-bottom:5px"/> -->
	<hr>
	&nbsp;&nbsp;&nbsp;<%="제목 :"%><input type="text" size="45" name="title" style="margin:3px"/><br>
	<textarea name="content" rows="15" cols="80" id="letterarea" style="margin-left: 15px;"></textarea>
	<br><input type="checkbox" style="margin-left: 15px;"/><%="보낸쪽지함에 저장"%><br>
	
	<input type="submit" value="보내기" class="btn btn-primary" id="sendletter2" style="margin-left: 15px;" onClick="sendbtn();"/><br>
	</form>
	</div>