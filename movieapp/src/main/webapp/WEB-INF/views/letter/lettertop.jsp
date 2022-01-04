<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<div class="container-fluid bg-light">
<li class="list-group list-group-horizontal">
<form action="<%=request.getContextPath()%>/letterwrite">
<p style="margin-top: 1rem;">
<button class="btn btn-primary" id="sendletter">쪽지쓰기</button>
</form>
<hr>
<p style="margin-top: 1rem;"><a class="btn btn-primary" href="<%=request.getContextPath()%>/lettermyrcv?rcv_id=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}&&currentPage=1">받은쪽지함</a></p>
<hr>
<p style="margin-top: 1rem;"><a class="btn btn-primary" href="<%=request.getContextPath()%>/lettermysend?user_id=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}&&currentPage=1">보낸쪽지함</a></p>
<hr>
</li>
</div>