<%@page import="kr.or.ddit.web.useragent.SystemType"%>
<%@page import="kr.or.ddit.web.useragent.BrowserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/userAgent.jsp</title>
</head>
<body>
<%
	String userAgent = request.getHeader("User-Agent");
	BrowserType browser = BrowserType.getBrowerType(userAgent);
	String browserMsg = "당신의 브라우저는 %s 입니다.";
	String name = browser.getBrowserName();
	String systemMsg = "당신의 시스템은 %s 입니다.";
	SystemType system = SystemType.getSystemType(userAgent);
	String systemName = system.getSystemName();
%>
<div id="msgArea">
	<%=String.format(browserMsg, name) %><br />
	<%=String.format(systemMsg, systemName) %>
</div>

</body>
</html>






