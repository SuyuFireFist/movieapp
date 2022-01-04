<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="utf-8"%>
<%@include file="../includes/header.jsp"%>

<c:url var="signUpPath" value="/signup" />
<script>
	$(document).ready(function() {
		$("#idCheck").click(function() {
			$.post( "/idCheck", { id: $("#id").val()} )
		    .done(function( data ) {
		    	$("#idResult").text(data);
		  });
		});
		$("#join").click(function() {		
			if($("#idResult").text().trim()=="사용가능한 아이디입니다."){
				$("form").submit();
			}else{
				alert("회원가입이 불가능 합니다");
			}
		});
	});
</script> 
<div class="container-fluid bg-light p-5">
<h1>회원가입 페이지</h1>
<hr>
<f:form modelAttribute="user" action="${ signUpPath }" method="post">
	<div class="form-group form-group-lg">
		<div class="form-group">
			<label>아이디</label>
			<f:input path="id" cssClass="form-control" /><hr>
			<input class="btn btn-primary btn-sm" type="button" id="idCheck" value="중복체크">
			<span id="idResult"></span>
			<f:errors path="id" element="div" cssClass="alert text-danger" />
		</div><br>
		<div class="form-group">
			<label>비밀번호</label>
			<f:password path="pwd" cssClass="form-control"
				placeholder="비밀번호" />
			<f:errors path="pwd" element="div" cssClass="alert text-danger" />
		</div><br>
		<div class="form-group">
			<label>닉네임</label>
			<f:input path="name" cssClass="form-control" />
			<f:errors path="name" element="div" cssClass="alert text-danger" />
		</div><br>
		<div class="form-group">
			<label>성별</label>
			<f:select path="gender" cssClass="form-control">
				<option value="남자">남자</option>
				<option value="여자">여자</option>
			</f:select>
			<f:errors path="gender" element="div" cssClass="alert text-danger" />
		</div><br>
		<div class="form-group">
			<label>이메일</label>
			<f:input path="email" cssClass="form-control" />
			<f:errors path="email" element="div" cssClass="alert text-danger" />
		</div><br>
		<div class="form-group">
			<label>전화번호</label>
			<f:input path="tel" cssClass="form-control" />
			<f:errors path="tel" element="div" cssClass="alert text-danger" />
		</div><br>
		<div class="form-group"></div>
		<div class="form-action">
			<s:csrfInput />
			<input type="submit" class="btn btn-primary btn-lg btn-block"
				value="회원 가입">
		</div>
	</div>
</f:form>
</div>