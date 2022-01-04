<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../includes/header.jsp"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<div class="container-fluid bg-light p-5">
<h1>내가 요청한 친구목록 페이지</h1>
<hr>
<li class="list-group list-group-horizontal">
  <a class="list-group-item" href="/mypage">내정보수정</a>
  <a class="list-group-item" href="/friend">친구목록</a>
  <a class="list-group-item" href="/friendreq">친구신청목록</a>
</li>
<hr>
<h2>요청한 친구목록</h2>
<%@include file="./friendreqlist.jsp"%>
<hr>
<h2>친구신청</h2>
<form action="friendreqsearch" method="post">
	<input type="text" class = "fr_id" name="fr_id" id="fr_id">
	<input type="submit" class = "idsend" value="신청 ">
</form>
<span class="result">${result}</span>
</div>
<script>
	$(function(){
		$('.idsend').click(function(){
			var fr_id = $('.fr_id').val();
			console.log(fr_id);
			$.ajax({	
				url: '${pageContext.request.contextPath }/users/friendreq.jsp',
				method: 'post',
				data: 'fr_id='+fr_id,
				success: function(data){
					$('.result').empty();
					$('.result').html(data);
				}
			});
		});
		$('.fr_id').change(function() {
			$('.result').html("");
		});
	});
</script>