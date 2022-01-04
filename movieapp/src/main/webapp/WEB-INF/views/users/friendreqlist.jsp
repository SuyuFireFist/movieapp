<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="list-group">
	<c:forEach var="reqlist" items="${freqlist}">
		<li class="list-group-item">${reqlist}
			<div align="right">
				<a href="/frienddel?friend=${reqlist}" class="btn btn-primary btn-sm">삭제</a>
			</div>
		</li>
	</c:forEach>
</ul>