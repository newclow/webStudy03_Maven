<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  1. 파라미터 확보 -->
<!--  2. 검증(필수데이터 검증, 유효데이터 검증) -->
<!--  3. 불통 -->
<!--  	1) 필수데이터 누락 : 400 -->
<!--  	2) 우리가 관리하지 않는 멤버를 요구한 경우 : 404  -->
<!--  4. 통과    -->
<!--  	이동(맵에 있는 개인 페이지, 클라이언트가 멤버 개인페이지의 주소를 모르도록.) -->
<!--  	이동(맵에 있는 개인 페이지, getBTS 에서 원본 요청을 모두 처리했을 경우, UI 페이지로 이동할 때.) -->
 <%!
	 Map<String, String[]> singerMap = new LinkedHashMap<>();
{
	singerMap.put("B001", new String[]{"RM", "/WEB-INF/bts/rm.jsp"});	
	singerMap.put("B002", new String[]{"제이홉", "/WEB-INF/bts/jayhop.jsp"});	
	singerMap.put("B003", new String[]{"지민", "/WEB-INF/bts/jimin.jsp"});	
	singerMap.put("B004", new String[]{"진", "/WEB-INF/bts/jin.jsp"});	
	singerMap.put("B005", new String[]{"슈거", "/WEB-INF/bts/sugar.jsp"});	
	singerMap.put("B006", new String[]{"뷔", "/WEB-INF/bts/vue.jsp"});	
	singerMap.put("B007", new String[]{"정국", "/WEB-INF/bts/jeongguk.jsp"});	
}
%> 
<%
	String member = request.getParameter("member");
	int statusCode = 0;
	if(member == null || member.trim().length() == 0 ){
		statusCode = HttpServletResponse.SC_BAD_REQUEST;
	}else if(!singerMap.containsKey(member)){
		statusCode = HttpServletResponse.SC_NOT_FOUND;
	}
	if(statusCode!=0){
		response.sendError(statusCode);
		return;
	}
	String[] value = singerMap.get(member);
	String goPage = value[1];
	RequestDispatcher rd = request.getRequestDispatcher(goPage);
	rd.forward(request, response);
// 	response.sendRedirect(request.getContextPath() + goPage);
%>  












