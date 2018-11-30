<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/fmtDesc.jsp</title>
<style type="text/css">
	input[type='image']{
		width : 50px;
		height : 50px;
	}
</style>
</head>
<body>
<h4>국제화 태그(format태그, fmt태그)</h4>
<input type="image" src="<c:url value='/images/korea.png'/>" onclick="location.href='?locale=ko_KR';" />
<input type="image" src="<c:url value='/images/america.png'/>" onclick="location.href='?locale=en_US';" />
<pre>
	1. 국제화(i18n, internationalization)
		setLocale, setBundle, message
		1) 언어결정
		2) 메시지 번들 작성 (basename.locale) : 로케일정보는 basename에 포함되지 않음
		**setLocale : 로딩할 메시지 번들의 로케일을 결정
		3) 메시지 번들 로딩(setBundle/bundle)
		4) 메세지 키를 통해 메시지를 출력(message)
		<c:if test="${not empty param.locale }">
			<fmt:setLocale value="${param.locale }"/>
		</c:if>
		<fmt:bundle basename="kr.or.ddit.msgs.message">
			<fmt:message key="bow"/>
		</fmt:bundle>
		
		<%
			String[] zoneIds = TimeZone.getAvailableIDs();
			TimeZone zone = TimeZone.getTimeZone(zoneIds[2]);
			
		%>
		<%=zone.getDisplayName() %>
	2. 지역화(l10n, localization)
		파싱(parseNumber, parseDate)(문자열을 일정한 형식의 타입으로 변환하는것), 포맷팅(formatNumber, formatDate)(일정형식의 타입을 문자열로 변환하고 싶을때)
		<fmt:formatDate value="<%=new Date() %>" type="both" dateStyle="long" timeStyle="long" timeZone="<%=zone %>"/>
		<fmt:formatNumber value="100000" type="currency" var="numStr"/>
		${numStr }-----------------------
		<fmt:parseNumber value="${numStr }" type="currency" parseLocale="${param.locale }" var="number"/>
		${number * 3 }
		
</pre>
</body>
</html>








