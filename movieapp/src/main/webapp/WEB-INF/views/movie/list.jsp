<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s"%>
<%-- /WEB-INF/view/movie/list.jsp --%>
<%@include file="../includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 검색 결과</title>
<script type="text/javascript">
	function listpage(page){
		document.searchform.pageNum.value=page;
		document.searchform.submit();
	}
</script>
<style>
th, td{
	border: 2px solid #444444;
}
</style>
</head>
<body>
	<!-- 영화정보 업데이트 (관리자 전용 버튼) -->
	<s:authorize access="hasRole('ADMIN')">
		<input type="button" value="영화정보 업데이트" onclick="location.href='update'">
	</s:authorize>
	<div class="container-fluid bg-light p-5">
	<h1>영화 리스트 뷰 페이지</h1>
	<hr>
	<table border="1" style="width:100%">
		<tr><td colspan="5">
			<div style="display:inline;">
				<form action="list" method="post" name="searchform">
				<input type="hidden" name="pageNum" value="1">
				<select name="searchtype" style="width:100px;">
				<option value="title" selected>제목</option>
				<option value="genre">장르</option>
				<option value="director">감독</option>
				<option value="actor">배우</option>
				</select>
				<script>
					document.searchform.searchtype.value='${param.searchtype}'
				</script>
				<input type="text" name="searchcontent"
					value="${param.searchcontent}" style="width:250px;">
				<input type="submit" value="검색">
				</form>
			</div></td></tr>
		<c:if test="${movieListCount>0}">
			<tr>
				<td colspan="4" align="center"><h1>영화 검색 결과</h1></td>
			</tr>
				<td colspan="4">총 ${movieListCount} 건이 검색되었습니다.</td>
			<tr>
			</tr>
			<c:forEach var="searchedMovie" items="${searchedMovieList}">
				<tr>
					<c:set var="movieListno" value="${movieListno-1}" />
					<td align="center">
						<c:if test="${!empty searchedMovie.image}"><a href="detail?num=${searchedMovie.num}"><img src="${searchedMovie.image}" width="200px"></a></c:if>
					</td>
					<td>
						<a href="detail?num=${searchedMovie.num}">${searchedMovie.title}</a><br>
						${searchedMovie.director} / 	${searchedMovie.pubdate}
					</td>
				</tr>
			</c:forEach>
			<tr align="center">
				<td colspan="5">
					<c:if test="${pageNum>1}"><a href="javascript:listpage(${pageNum-1})">[이전]</a></c:if> 
					<c:if test="${pageNum<=1}">[이전]</c:if>
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a==pageNum}">[${a}]</c:if>
						<c:if test="${a!=pageNum}">
							<a href="javascript:listpage(${a})">[${a}]</a>
						</c:if>
					</c:forEach> <c:if test="${pageNum<maxpage }"><a href="javascript:listpage(${pageNum+1})">[다음]</a>
					</c:if> <c:if test="${pageNum>=maxpage }">[다음]</c:if></td>
			</tr>
		</c:if>
		<c:if test="${movieListCount==0}">
			<tr>
				<td colspan="5">검색 결과가 없습니다.</td>
			</tr>
		</c:if>
	</table>
	</div>


</body>
</html>