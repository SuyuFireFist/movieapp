<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/jspHeader.jsp" %>  
<%@include file="../includes/header.jsp"%>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
        <style>
            .inline-block{ display: inline-block;}
        </style>
 <script type="text/javascript" src=
"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="container-fluid bg-light p-5">
<h1>게시판 리스트 보기</h1>
<hr><%@include file="./boardheader.jsp"%><hr>
<form:form modelAttribute="board" action="write" name="f" method="POST"> 
지역 : 
<div class="inline-block">
<input type="radio" name ="p_region" value="Gangnam">서울강남
<input type="radio" name ="p_region" value="Gangbuk">서울강북
<input type="radio" name ="p_region" value="Gyeonggi">경기
<input type="radio" name ="p_region" value="Incheon">인천
<input type="radio" name ="p_region" value="BuUl">부산/울산
<input type="radio" name ="p_region" value="DaeGyeong">대구/경상<br>
<input type="radio" name ="p_region" value="Daejeon">대전
<input type="radio" name ="p_region" value="Gangwon">강원
<input type="radio" name ="p_region" value="Jeolla">전라
<input type="radio" name ="p_region" value="Chungcheong">충청
<input type="radio" name ="p_region" value="Etc">기타
</div><font color="red"><form:errors path="p_region" /></font><p>
영화관 : 
<div class="p_cinema">
  	<div id="sido" style="float:left; margin:20px;">
  	<select name="sido" onchange="getText('sido')">
  		<option value="">특별시/도를 선택하세요</option>
  	</select>
  	</div>
  	  	<div id="sigu" style="float:left; margin:20px;">
  	<select name="sigu" onchange="getText('sigu')">
  		<option value="">시/구/군을 선택하세요</option>
  	</select>
  	</div>
  	    <div id="com" style="float:left; margin:20px;">
  	<select name="com" onchange="getText('com')">
  		<option value="">업체를 선택하세요</option>
  	</select>
  	</div>
  	<div id="cine" style="float:left; margin:20px;">
  	<select name="cine">
  		<option value="">영화관을 선택하세요</option>
  	</select>
  	</div>
  </div>
 <p> 
 <input type="hidden" name="p_cinema" value="cinema">
상영영화 : <input type="button" name="p_movie" value="영화검색" /><!-- onclick="window.open('list','window_name','width=430,height=500,location=no,status=no,scrollbars=yes')" --> 
<font color="red"><form:errors path="p_movie" /></font><p>
상영일 : <input type="date" name="p_wdate" required><p>
간단소개 : <form:input path="p_title" Placeholder="ex) 나이대, 성별, 특이사항"/><font color="red"><form:errors path="p_title" /></font><p>
내  용 : <form:textarea  path="p_content" rows="15" cols="80" Placeholder="영화정보, 소개글을 작성해주세요"/><font color="red"><form:errors path="p_content" /></font><p>
<a href="javascript:document.f.submit()"
onclick="if(!confirm('작성을 완료하시겠습니까?')){return false;}">[게시글등록]</a></form:form>
<a href="list">[게시글목록]</a>
</div>

<script>
	$(function(){
		cinemaselect();
	})
	
function cinemaselect(){
	$.ajax({
		url : "${path}/ajax/select",
		success : function(data){
			console.log(data)//시도 정보 배열 형태
			$.each(data, function(i,item){
				//i:인덱스
				//item : 데이터 정보, 시도데이터
				$("select[name='sido']").append(function(){
					return "<option>" + item +"</option>";
				})
			});
		}
	})
}
	function getText(name){
		let sidoval = $("select[name='sido']").val();
		let siguval = $("select[name='sigu']").val();
		let comval = $("select[name='com']").val();
		let disname;
		let toptext ="시구군을 선택하세요";
		let params = "";
		if(name == "sido"){
			params = "sido="+sidoval.trim();
			disname ="sigu";
		}else if(name =="sigu"){
			params = "sido="+sidoval.trim()+"&sigu="+siguval.trim();
			disname ="com";
			toptext="업체를 선택하세요";
		} else if(name =="com"){
			params = "sido="+sidoval.trim()+"&sigu="+siguval.trim()+"&com="+comval.trim();
			disname ="cine";
			toptext="영화관을 선택하세요";
		}else {
			return;
		}
	
		$.ajax({
			url : "${path}/ajax/select",
			type:"POST",
			data : params,
			success : function(data){
				console.log(data)
				var selhtml ="<select name='"+disname+"' onchange='getText(\"" + disname +"\")'>";
				selhtml += "<option value =' '>" + toptext + "</option>"
				$.each(data,function(i,item){
					selhtml +="<option>"+item + "</option>"
				});
				selhtml += "</select>";
				$("#"+disname).html(selhtml);
			}
		})
	}
	
	
	</script>
</body></html>