<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/example.jsp</title>
<style type="text/css">
	.one{background-color: yellow;}
	.four{background-color: green;}
	.green{color:green;}
	.yellow{color:yellow;}
	.red{color:red;}
	.silver{color:silver;}
	.aqua{color:aqua;}
	.orange{color:orange;}
</style>
</head>
<body>
	<form>
		주소입력 : <input type="url" name="siteUrl" value="" placeholder="https://www.naver.com">
		<label><input type="checkbox" name="toSource" value="source" ${param.toSource eq 'source' ? "checked" : "" }/>소스로 보기</label>
		<input type="submit" value="실행"/> 
	</form>
	<c:if test="${not empty param.siteUrl }">
		<c:import url="${param.siteUrl }" var="site"/>
		<div style="border : 1px solid red">
			<c:out value="${site }" escapeXml="${param.toSource eq 'source' }"></c:out>
		</div>
	</c:if>

	
	
	
	
<!-- 	2단부터 9단까지 구구단을 승수 1~9까지 table태그로 출력 -->
<!-- 	첫번째 단은 파란색배경 -->
<!-- 	네번째 단은 빨간색 배경 -->
	<c:set var="minDan" value="${empty param.minDan? 2 : param.minDan }"></c:set>
	<c:set var="maxDan" value="${empty param.maxDan? 9 : param.maxDan }"></c:set>
	<form action="">
		최소단:<input type="number" name="minDan" value="${minDan }">
		최대단:<input type="number" name="maxDan" value="${maxDan }">
		<input type="submit" value="실행">
	</form>
	<table>
		<c:forEach var="dan" begin="${minDan }" end="${maxDan }" step="1" varStatus="lts">
			<c:set var="colorClz" value="normal" />
			<c:if test="${lts.first }">
				<c:set var="colorClz" value="one"></c:set>
			</c:if>
			<c:if test="${lts.count eq 4 }">
				<c:set var="colorClz" value="four"></c:set>
			</c:if>
			<tr class="${colorClz }">
				<c:forEach var="sue" begin="1" end="9" step="1">
					<td>${dan }*${sue } = ${dan*sue }</td>
				</c:forEach>
			<tr>
		</c:forEach>
	</table>






	<form action="">
		당신의 나이는?<input type="number" name="age" value="${param.age }" />
		<input type="submit" value="전송">
	</form>
<!-- 	age 파라미터가 있다면 -->
<!-- 	1. age가 20대면 "반갑다 친구야" green-->
<!-- 	2. age가 30대면 "반갑다 형님" yellow-->
<!-- 	3. age가 40대면 "건강관리" red-->
<!-- 	4. age가 50대면 "빠덜" silver-->
<!-- 	5. age가 그이상이면 "뭔일로?" aqua-->
<!-- 	6. age가 20대미만이면 "피방가자" orange-->
	<c:set var="age" value="${param.age }"></c:set>
	<c:if test="${not empty age }">
		<c:choose>
			<c:when test="${age lt 20 }">
				<c:set var="message" value="피방가자"></c:set>	
				<c:set var="clz" value="orange"></c:set>	
			</c:when>
			<c:when test="${19 lt age and age lt 30 }">
				<c:set var="message" value="반갑다 친구야"></c:set>
				<c:set var="clz" value="green"></c:set>		
			</c:when>
			<c:when test="${29 lt age and age lt 40 }">
				<c:set var="message" value="반갑다 형님"></c:set>
				<c:set var="clz" value="yellow"></c:set>		
			</c:when>
			<c:when test="${39 lt age and age lt 50 }">
				<c:set var="message" value="건강관리"></c:set>	
				<c:set var="clz" value="red"></c:set>	
			</c:when>
			<c:when test="${49 lt age and age lt 60 }">
				<c:set var="message" value="빠덜"></c:set>
				<c:set var="clz" value="silver"></c:set>		
			</c:when>
			<c:otherwise>
				<c:set var="message" value="뭔일로?"></c:set>	
				<c:set var="clz" value="aqua"></c:set>	
			</c:otherwise>
		</c:choose>
		<div id="msgArea" class="${clz }">
			${message }
		</div>
	</c:if>
</body>
</html>