<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jspHeader.jsp" %>
<%@include file="../includes/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세보기</title>
<style type="text/css">
	.table{text-align:center; vercial-align:center; border:1px solid black;}
	.poster{height:250px; border-width:0px; text-align:center; vertical-align:center;padding:0px; border:1px solid black;}
	.title{height:100px; border:1px solid black;}
	.content{height:150px; border:1px solid black;}
</style>
</head>
<body>
<div class="container-fluid bg-light p-5">
<h1>게시글 상세정보 보기</h1>
<hr><%@include file="./boardheader.jsp"%><hr>
<table class="table">
	<tr><td class="poster">${board.p_file_path }</td></tr>
	<tr><td class="title">${board.p_title }</td></tr>	   
	<tr><td class="content">${board.p_content }</td></tr>
	<tr><td>
			<a href="update?seq=${board.seq}">[수정]</a>
			<a href="delete?seq=${board.seq }"
			 onclick="if(!confirm('삭제 하시겠습니까?')){return false;}">[삭제]</a>
			<a href="list">[게시물목록]</a>
</td></tr></table>
</div>
</body>
</html>