<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../includes/header.jsp"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script>
	$(document).ready(function(){
		$('.Allcheck').click(function(){
			if($('.Allcheck').prop('checked')){
				$('input:checkbox').prop('checked',true);
			}else{
				$('input:checkbox').prop('checked',false);
			}
		});
		 
	});
	var go = 
		function(seq){
			location.href="${pageContext.request.contextPath}/letterresult?letter_seq="+seq;
	};
	var lettersubmit = 
		function(index){
			if(index==1){
				 document.letterForm.action="<%=request.getContextPath() %>/deleteletter"
			 }
			 if(index==2){
					var values = document.getElementsByName("letter_seq");
					var count=0;
					for(var i=0;i<values.length;i++){
						if(values[i].checked){
							++count;
						}
					}
					if(count>=2){
						alert('하나만 선택해주세요!!');
					}else{ 
				 	document.letterForm.action="<%=request.getContextPath() %>/letterwrite"
					} 
			 }
	}
</script>
<%@include file="./lettertop.jsp"%>
<style>
.letlist:hover{
background-color: #bbbbbb;
cursor: pointer;
}
</style>
<div class="container-fluid bg-light p-5">
<h1>받은 쪽지함</h1>
<hr>
<div id="letterboard">
<form id="f">
<div id="lettertop" style="padding:7px;">
<input type="hidden" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" name="user_id"/>
<p style="display: inline;">
	<select name="select" style="margin: 5px; ">
		<option value="chkid">아이디</option>
		<option value="chktitle">제목</option>
		<option value="chkdate">날짜</option>
	</select>
</p>
<input type="text" size="30"/>
</div> 
</form>
<hr>
<form name="letterForm" method="post">
<div id="letterbutton">
<input type="submit" class="btn btn-primary" id="deleteletter" value="삭제" onclick="lettersubmit(1)" style="margin-left: 15px;" name="action">
<input type="submit" class="btn btn-primary" id="returnletter" value="답장" onclick="lettersubmit(2)" name="action">
<a class="btn btn-primary" href="<%=request.getContextPath()%>/letdontread?rcv_id=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}&&currentPage=1">안읽은쪽지</a>
</div>
<hr>
<div class="container">          
  <table class="table table-hover" style="text-align:center;">
    <thead>
      <tr>
        <th style="width:10%;"><input type="checkbox" class="Allcheck"/></th>
        <th style="width:15%;">보낸사람</th>
        <th style="width:60%;">제목</th>
        <th style="width:15%;">보낸날짜</th>
      </tr>
    </thead>
       <tbody>
       <c:if test = "${empty page.getList()}">
    	<tr><td colspan="4"><%="쪽지가 없습니다."%></td></tr>
    	</c:if>
    <c:if test = "${not empty page.getList()}">
      <c:forEach var="a" items="${page.getList()}">
      	<input type="hidden" value="${a.readyn}"/>
      <tr>
      	<td style="text-align:center;"><input type="checkbox" class="checkl" name="letter_seq" value="${a.letter_seq}"/></td>
      	<td>${a.user_id}</td>
      	<td><div class="letlist" onclick="javascript:go(${a.letter_seq})">${a.title}</div></td>
      	<td>${a.write_date}</td>
      </tr>
      </c:forEach>
      </c:if>
    </tbody>
  </table>
  <nav aria-label="Page navigation example" style="width: 240px; margin-left: auto;margin-right: auto;">
  <ul class="pagination">
  	<c:if test="${page.currentPage > 5}">
    <li class="page-item">
      <a class="page-link" href="<%=request.getContextPath()%>/lettermyrcv?rcv_id=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}&&currentPage=${page.startPage-1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    <c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="i" step="1">
    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/lettermyrcv?rcv_id=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}&&currentPage=${i}">${i}</a></li>
    </c:forEach>
    <c:if test="${page.totalPage != page.endPage }">
    <li class="page-item">
      <a class="page-link" href="<%=request.getContextPath()%>/lettermyrcv?rcv_id=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}&&currentPage=${page.endPage+1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
</nav>
</div>
</form>
</div>
</div>
