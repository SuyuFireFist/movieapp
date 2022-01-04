<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="/WEB-INF/views/jspHeader.jsp" %>
<%@include file="../includes/header.jsp"%>
<%@include file="./boardheader.jsp"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 수정화면</title>
 <script type="text/javascript" src=
"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<form:form modelAttribute="board" action="update" method="POST"
		name="updateform">
<input type="hidden" name="seq" value="${board.seq}">
지역 : 
<div class="inline-block">
<input type="radio" name ="p_region" value="Gangnam"
${board.p_region eq 'Gangnam' ? 'checked':' '}>서울강남
<input type="radio" name ="p_region" value="Gangbuk"
${board.p_region eq 'Gangbuk' ? 'checked':' '}>서울강북
<input type="radio" name ="p_region" value="Gyeonggi"
${board.p_region eq 'Gyeonggi' ? 'checked':' '}>경기
<input type="radio" name ="p_region" value="Incheon"
${board.p_region eq 'Incheon' ? 'checked':' '}>인천
<input type="radio" name ="p_region" value="BuUl"
${board.p_region eq 'BuUl' ? 'checked':' '}>부산/울산
<input type="radio" name ="p_region" value="DaeGyeong"
${board.p_region eq 'DaeGyeong' ? 'checked':' '}>대구/경상<br>
<input type="radio" name ="p_region" value="Daejeon"
${board.p_region eq 'Daejeon' ? 'checked':' '}>대전
<input type="radio" name ="p_region" value="Gangwon"
${board.p_region eq 'Gangwon' ? 'checked':' '}>강원
<input type="radio" name ="p_region" value="Jeolla"
${board.p_region eq 'Jeolla' ? 'checked':' '}>전라
<input type="radio" name ="p_region" value="Chungcheong"
${board.p_region eq 'Chungcheong' ? 'checked':' '}>충청
<input type="radio" name ="p_region" value="Etc"
${board.p_region eq 'Etc' ? 'checked':' '}>기타
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
상영영화 <input type="button" name="p_movie" value="영화검색" onclick="window.open('list','window_name','width=430,height=500,location=no,status=no,scrollbars=yes')" />
<font color="red"><form:errors path="p_movie" /></font><p>
상영일 : <input type="date" name="p_wdate" required><p>

간단소개 : <form:input path="p_title" /><font color="red"><form:errors path="p_title" /></font><p>
내  용 : <form:textarea  path="p_content" rows="15" cols="80"/><font color="red"><form:errors path="p_content" /></font><p>
	<a href="javascript:document.updateform.submit()" onclick="if(!confirm('수정 하시겠습니까?')){return false;}">[수정]</a>&nbsp;
	<a href="list?pageNum=${param.pageNum }">[목록]</a>
</form:form>
<script>
	$(function(){
		cinemaselect();
	})
	
function cinemaselect(){
	$.ajax({
		url : "${path}/movie/ajax/select",
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
			url : "${path}/movie/ajax/select",
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
</body>
</html>