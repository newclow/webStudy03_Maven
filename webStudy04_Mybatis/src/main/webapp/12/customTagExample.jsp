<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.ddit.or.kr/loopTag" prefix="loop" %>
<%@ taglib uri="http://www.ddit.or.kr/makeSelect" prefix="mSel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/customTagExample.jsp</title>
</head>
<body>
	<loop:myforEach loopCount="5" data="테스트"/>
	<hr/>
	<c:set var="test" value="locParam"/>
	<mSel:makeLocaleSelect name="${test }" />
</body>
</html>