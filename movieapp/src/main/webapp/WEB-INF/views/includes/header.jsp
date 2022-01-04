<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
      <a class="navbar-brand" href="#">4FLIX</a>
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/movie/main">홈으로!</a>
        </li>
        
        <sec:authorize access="isAuthenticated()">
        	<li class="nav-item">
        		<a class="nav-link active" href="/movie/list">영화검색</a>
        	</li>
        </sec:authorize>
        
        <sec:authorize access="isAnonymous()">
	        <c:url var="loginUrl" value="/login" />
		        <li class="nav-item">
		          <a class="nav-link active" href="${loginUrl}">로그인</a>
		        </li>
        </sec:authorize>
        
        <sec:authorize access="isAuthenticated()">
        	<c:url var="logoutUrl" value="/logout" />
        		<form action="${logoutUrl}" method="post" class="nav-item">
        			<button type="submit" class="btn btn-default">로그아웃</button>
               		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        		</form>
        </sec:authorize>
        
        <li class="nav-item">
          <a class="nav-link active" href="/board/list">같이볼사람</a>
        </li>
        
        <sec:authorize access="isAuthenticated()">
        	<li class="nav-item">
        		<a class="nav-link active" href="/mypage">마이페이지</a>
        	</li>
        </sec:authorize>
        
        <sec:authorize access="isAuthenticated()">
        	<li class="nav-item">
        		<a class="nav-link active" href="/letterhome?currentPage=1">쪽지</a>
        	</li>
        </sec:authorize>
        
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
</body>
</html>