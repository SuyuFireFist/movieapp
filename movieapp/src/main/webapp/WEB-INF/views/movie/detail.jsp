<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- /WEB-INF/view/movie/detail.jsp --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 상세보기</title>
<style type="text/css">
th, td{
	border: 2px solid #444444;
}
</style>
</head>
<body>
<div class="container-fluid bg-light p-5">
<h1>영화 상세정보</h1>
<hr>
	<table border="1" style="width: 100%">
		<!-- 영화 상세정보 -->
		<tr>
			<td align="center" width="250"><img src="${movie.image}"
				width="200"></td>
			<td>영화제목: ${movie.title}<br>영문제목: ${movie.subtitle}<br>
				개봉일자: ${movie.pubdate}<br>영화감독: ${movie.director}<br>영화배우: ${movie.actor}<br>영화평점: ${movie.userrating}<br>
			</td>
		</tr>
		
		<!-- 리뷰 작성란 -->
		<form:form modelAttribute="review" method="post" action="reviewWrite" name="reviewform">
		<tr align="center">
			<td>
				<!-- 별점 입력란 (적당한 뷰 찾기) -->
				별점 : 
    			<select name="userrating">
    			<option>1</option>
    			<option>2</option>
    			<option>3</option>
    			<option>4</option>
    			<option>5</option>
    			</select>
    		</td>
			<td>
    				<input type="hidden" name="movie_seq" value="${movie.num}">
					<input type="text" name="review">
					<input type="submit" size="100" value="리뷰쓰기">
			</td>
		</tr>
		</form:form>

		<!-- 리뷰 -->
		<c:if test="${reviewListCount>0}">
			<c:forEach var="review" items="${reviewList}">
				<tr>
					<td>userrating : ${review.userrating}<br>
						${review.user_id}
					</td>
					<td>${review.review}

					<!-- 로그인 유저와 review.user_id가 같거나 / 관리자일 경우만 삭제 띄우기 -->
					<%-- <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username} == ${review.user_id}"> --%>
					<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username == review.user_id}">
						<a href="reviewDelete?seq=${review.seq}&movie_seq=${movie.num}">(삭제)</a>
					</c:if>
						
					</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4">총 ${reviewListCount} 건의 리뷰가 있습니다.</td>
			</tr>
			
			<!-- 페이징 -->
			<tr align="center">
				<td colspan="5" width="100"><c:if test="${pageNum>1}">
						<a href="detail?num=${movie.num}&pageNum=${pageNum-1}">[이전]</a>
					</c:if> <c:if test="${pageNum<=1}">[이전]</c:if> <c:forEach var="a"
						begin="${startpage}" end="${endpage}">
						<c:if test="${a==pageNum}">[${a}]</c:if>
						<c:if test="${a!=pageNum}">
							<a href="detail?num=${movie.num}&pageNum=${a}">[${a}]</a>
						</c:if>
					</c:forEach> <c:if test="${pageNum<maxpage }">
						<a href="detail?num=${movie.num}&pageNum=${pageNum+1}">[다음]</a>
					</c:if> <c:if test="${pageNum>=maxpage }">[다음]</c:if></td>
			</tr>
		</c:if>
		<c:if test="${reviewListCount==0}">
			<tr>
				<td colspan="5">등록된 리뷰가 없습니다.</td>
			</tr>
		</c:if>
		
	</table>
	<hr>
	<input class="btn btn-primary btn-lg" type="button" value="목록" onclick="location.href='list'">
	
	
	
	
	<!-- 같은 장르의 다른 영화 -->
	<c:if test="${sameDirectorMovieList.size() != 1}">
	<hr>이 감독의 다른 영화<hr>
	<table style="width: 80%">
		<tr>
			<c:forEach var="sameDirectorMovie" items="${sameDirectorMovieList}">
			<c:if test="${movie.num != sameDirectorMovie.num}">
				<td>
					<a href="detail?num=${sameDirectorMovie.num}"><img src="${sameDirectorMovie.image}" width="100px"><br>
					${sameDirectorMovie.title}</a>
				</td>
			</c:if>
			</c:forEach>
		</tr>

	</table>
	</c:if>
</div>
</body>
</html>