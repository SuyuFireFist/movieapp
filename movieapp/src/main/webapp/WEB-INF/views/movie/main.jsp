<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@include file="../includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page!</title>
</head>
<body>
	<h1>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}님 일일 박스 오피스 TOP10 정보에요!</h1>
	<!-- serach BAR -->
	<div class="input-group mb-3">
		<button class="btn btn-outline-secondary dropdown-toggle"
			type="button" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</button>
		<ul class="dropdown-menu">
			<li><a class="dropdown-item" href="#">Action</a></li>
			<li><a class="dropdown-item" href="#">Another action</a></li>
			<li><a class="dropdown-item" href="#">Something else here</a></li>
			<li><hr class="dropdown-divider"></li>
			<li><a class="dropdown-item" href="#">Separated link</a></li>
		</ul>
		<input type="text" class="form-control"
			aria-label="Text input with dropdown button">
	</div>
	<!-- serach BAR end -->

	<%-- <h4>${mov.rank}</h4>
		<h4>${mov.movieNm}</h4>
		<h4>${mov.openDt}</h4>
		<h4>${mov.audiAcc}</h4> --%>

	<div class="row row-cols-1 row-cols-md-5 g-10">
		<c:set var="km" value="${blist}"></c:set>
		<c:forEach var="mov" items="${nlist}" varStatus="status">
			<div class="card">
				<img
					src="${mov.image}"
					height="300" width="180" class="card-img-top" alt="...">
				<div class="card-body">
					<h3 class="card-title">${mov.title}</h5>
					<p class="card-text">${mov.actor}</p>
					<h6>개봉: ${km[status.count-1].openDt}</h6>
					<p>누적관객: ${km[status.count-1].audiAcc}</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- <div class="col">
			<div class="card">
				<img src="https://upload.wikimedia.org/wikipedia/ko/thumb/f/f6/%EC%95%84%EC%9D%B4%EC%96%B8%EB%A7%A8_3_%ED%8F%AC%EC%8A%A4%ED%84%B0.jpg/220px-%EC%95%84%EC%9D%B4%EC%96%B8%EB%A7%A8_3_%ED%8F%AC%EC%8A%A4%ED%84%B0.jpg" height="300" width="180" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">영화2</h5>
					<p class="card-text">재밌는 영화2</p>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card">
				<img src="..." class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">영화3</h5>
					<p class="card-text">재밌는 영화3</p>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card">
				<img src="..." class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">영화4</h5>
					<p class="card-text">영화4</p>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card">
				<img src="..." class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">영화5</h5>
					<p class="card-text">재밌는 영화5</p>
				</div>
			</div>
		</div>
	</div> -->
</body>
</html>