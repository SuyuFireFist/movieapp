<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="utf-8"%>
<%@include file="../includes/header.jsp"%>

<div class="container-fluid bg-light p-5">
<h1>유저정보 수정 페이지</h1>
<hr>

<li class="list-group list-group-horizontal">
  <a class="list-group-item" href="/mypage">내정보수정</a>
  <a class="list-group-item" href="/friend">친구목록</a>
  <a class="list-group-item" href="/friendreq">친구신청목록</a>
</li>
<hr>
<form action="<c:url value='/mypage/update' />" method="post">
        <div class="form-group">
			<label>아이디</label>
			<f:input  path="id" name="id" value="${user.id}" readonly="true"  cssClass="form-control" />
			<f:input  path="id" name="pwd" value="${user.pwd}" readonly="true"  cssClass="form-control" type="hidden" />
			<f:errors path="id" element="div" cssClass="alert text-danger" />
		</div><br>
		<div class="form-group">
			<label>닉네임</label>
			<f:input path="id" name="name" value="${user.name}" cssClass="form-control" />
			<f:errors path="id" element="div" cssClass="alert text-danger" />
		</div><br>
		<div class="form-group">
			<label>성별</label>
			<f:select path="id" name="gender" cssClass="form-control" value="${user.gender}">
				<option value="남자">남자</option>
				<option value="여자">여자</option>
			</f:select>
			<f:errors path="id" element="div" cssClass="alert text-danger" />
		</div><br>
		<div class="form-group">
			<label>이메일</label>
			<f:input path="id" name="email" value="${user.email}" cssClass="form-control" />
			<f:errors path="id" element="div" cssClass="alert text-danger" />
		</div><br>
		<div class="form-group">
			<label>전화번호</label>
			<f:input path="id" name="tel" value="${user.tel}"  cssClass="form-control" />
			<f:errors path="id" element="div" cssClass="alert text-danger" />
		</div><hr>
		<button type="submit" class="btn btn-lg btn-primary">변경하기</button>
</form>
</div>
