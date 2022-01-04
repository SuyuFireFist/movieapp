<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid bg-light p-5">
<h1>영화추가 페이지</h1>
<hr>
<form action="<c:url value="update" />" method="post">

	<div class="form-group form-group-lg">
        <label class="control-label">번호</label>
        <input name="num" type="text" class="form-control">
    </div>
    <div class="form-group form-group-lg">
        <label class="control-label">제목</label>
        <input name="title" type="text" class="form-control">
    </div>
    <div class="form-group form-group-lg">
        <label class="control-label">부제</label>
        <input name="subtitle" type="text" class="form-control">
    </div>
	<div class="form-group form-group-lg">
        <label class="control-label">이미지</label>
        <input name="image" type="text" class="form-control">
    </div>
	<div class="form-group form-group-lg">
        <label class="control-label">개봉일자</label>
        <input name="pubdate" type="text" class="form-control">
    </div>
    <div class="form-group form-group-lg">
        <label class="control-label">배우</label>
        <input name="actor" type="text" class="form-control">
    </div>
    <div class="form-group form-group-lg">
        <label class="control-label">평점</label>
        <input name="userrating" type="text" class="form-control">
    </div><hr>
    <button type="submit" class="btn btn-lg btn-primary">전송</button>
</form>

</div>
</body>
</html>