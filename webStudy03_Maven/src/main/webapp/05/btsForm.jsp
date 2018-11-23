<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public Map<String, String[]> singerMap = new LinkedHashMap<>();
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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/btsForm.jsp</title>
<script type="text/javascript">
	function changeHandler(){
		document.btsForm.submit();
	}
</script>
</head>
<body>
<form name="btsForm" action="<%=request.getContextPath() %>/05/getBTS.jsp">
	<select name="member" onchange="changeHandler();">
		<option value="">멤버 선택</option>
		<%
			for(Entry<String, String[]> entry : singerMap.entrySet()){
				out.println(String.format(
					"<option value='%s'>%s</option>", entry.getKey(),
							entry.getValue()[0]
						));
			}
		%>
	</select>
</form>
</body>
</html>
















