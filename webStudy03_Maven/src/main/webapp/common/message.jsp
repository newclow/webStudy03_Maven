<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 공통 메시지 뷰 </h4>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>
<pre>
안내사항
<%=member.getMem_name() %> 회원님 탈퇴 후 1주일간은 같은 아이디로 가입할수없습니다.
남아 있는 포인트 <%=member.getMem_mileage() %> 점은 소멸되는 점 유의해주길 바랍니다.
그동안 이용해 주셔서 감사합니다.
</pre>
<%
	String goLink = (String)session.getAttribute("goLink");
	String message = (String)session.getAttribute("message");
	if(StringUtils.isNotBlank(goLink) && StringUtils.isNotBlank(message)){
		%>
		<%=message %> <br />
		<a href="<%=request.getContextPath()%><%=goLink %>">메인페이지로</a>
		<%
		session.removeAttribute("message");
		session.removeAttribute("goLink");
	}
	if(new Boolean(true).equals(session.getAttribute("isRemoved"))){
		session.invalidate();
	}
%>
</body>
</html>













