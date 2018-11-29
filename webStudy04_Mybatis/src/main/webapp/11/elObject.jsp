<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/elObject.jsp</title>
</head>
<body>
<h4>EL의 기본객체</h4>
<pre>
	<%
		pageContext.setAttribute("test 5", "테스트벨류");
	%>
	1. Scope객체(Map&lt;String, Object&gt;) : pageScope, requestScope, sessionScope, applicationScope
<%-- 		${pageScope.test 5 },  --%>
		${pageScope["test 5"] } <!-- 안전한형태의 구조는 연상배열구조 -->
		${not empty sessionScope.authMember? sessionScope.authMember["mem_id"] : "로그인안함"}
	2. Parameter객체 : param(Map&lt;String, String&gt;), paramValues(Map&lt;String, String&gt;)
		${param.test }, ${param["test"] }
		${paramValues.test[1] }, ${paramValues["test"][1] }
	3. 요청 Header객체 : header(Map&lt;String, String&gt;), headerValues(Map&lt;String, String[]&gt;)
		${header.user-agent }, ${header["user-agent"] }
		<%-- ${headerValues.user-agent }, --%> 
		${headerValues["user-agent"] }
		
	4. Cookie객체 : cookie(Map&lt;String, Cookie&gt;)
		${cookie.JSESSIONID.value }, ${cookie["JSESSIONID"]["value"] }
	5. ContextParameter객체 : initParam(Map&lt;String, String&gt;)
		${initParam.contentFolder }, <%=application.getInitParameter("contentFolder") %> 
		${initParam["contentFolder"] }
	6. pageContext : PageContext
		<%=((HttpServletRequest)pageContext.getRequest()).getContextPath() %> 
		${pageContext.request.contextPath }
		${pageContext.request.remoteAddr }, <%=request.getRemoteAddr() %>
<%-- 		${pageContext.request.[""] } --%>
</pre>
</body>
</html>








