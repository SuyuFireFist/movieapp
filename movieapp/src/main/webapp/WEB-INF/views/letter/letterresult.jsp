<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../includes/header.jsp"%>
<%@include file="./lettertop.jsp"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script>
	var lettersubmit=
		function(index){
			if(index==1){
				document.writeform.action="<%=request.getContextPath() %>/letterwrite?letter_seq=${letsv.letter_seq}"
			}
			if(index==2){
				document.writeform.action="<%=request.getContextPath() %>/deleteletter?letter_seq=${letsv.letter_seq}"	
			}
	};
</script>
<div class="container-fluid bg-light p-5">
<h1>쪽지 보기</h1>
<hr>
<form method="post" name="writeform">
<div id="letterbutton">
<input type="submit" class="btn" id="deleteletter" value="삭제" style="margin-left: 15px;" onClick="lettersubmit(2)">
<input type="submit" class="btn" id="returnletter" value="답장" onClick="lettersubmit(1)">
</div>
<hr>
</form>
<input type="hidden" value="letsv.letter_seq"/>
<div>
<p class="p1">&nbsp;&nbsp;&nbsp;&nbsp;${letsv.user_id}님의 내게 쓴 쪽지</p>
<p class="p1">&nbsp;&nbsp;&nbsp;&nbsp;보낸시간 &nbsp;&nbsp; ${letsv.write_date}</p>
</div>
<hr>
<textarea name="content" rows="15" cols="80" style="margin-left: 15px; background-color: " readonly="readonly">${letsv.content}</textarea>
<hr>
</div>