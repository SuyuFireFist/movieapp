<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8"%>
<%@include file="../includes/header.jsp"%>

<div class="container-fluid bg-light p-5">
<h1>로그인 페이지</h1>
<hr>
<form action="<c:url value='/user/login' />" method="post">
	<div class="form-group form-group-lg">
		<div class="form-group">
			<label>아이디</label> <input type="text" name="id" class="form-control"
				placeholder="아이디">
		</div>
		<div class="form-group">
			<label>비밀번호</label> <input type="password" name="pwd"
				class="form-control" placeholder="비밀번호">
		</div>
		<div class="form-group">
			<input type="hidden" name="${ _csrf.parameterName }"
				value="${ _csrf.token }">
		</div>
		<hr>
		<div class="form-action">
			<input type="submit" class="btn btn-primary btn-lg" value="로그인">
			<a href="signup" class="btn btn-success btn-lg">회원 가입</a>
		</div>
	</div>
</form>
</div>
