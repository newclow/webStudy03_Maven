<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script type="text/javascript" 
	src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script type="text/javascript">
	function paging(page){
		$.ajax({
			url:"${pageContext.request.contextPath}/reply/replyList.do",
			data:{
				bo_no:${board.bo_no},
				page:page
			},
			dataType:"json",
			success:function(resp){
				var html = "";
				if(resp.dataList){
					$.each(resp.dataList, function(idx, reply){
						html += "<tr>";
						html += "<td>"+reply.rep_writer+"</td>";
						html += "<td>"+reply.rep_ip+"</td>";
						html += "<td>"+reply.rep_content+"</td>";
						html += "<td>"+reply.rep_date+"</td>";
						html += "</tr>";
					});
				}else{
					html += "<tr><td colspan='4'>데이터 없음.</td></tr>";
				}
				pagingArea.html(resp.pagingHTML);	
				listBody.html(html);
			},
			error:function(resp){
				console.log(resp.status);
			}
		});
	}
	$(function(){
		pagingArea = $("#pagingArea");
		listBody = $("#listBody");
 		paging(1);
	});
</script>
</head>
<body>
	<table class="table">
		<tr>
			<th>게시글 번호</th>
			<td>${board.bo_no}</td>
		</tr>
		<tr>
			<th>게시글 작성자</th>
			<td>${board.bo_writer}</td>
		</tr>
		<tr>
			<th>IP</th>
			<td>${board.bo_ip}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${board.bo_mail}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.bo_title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.bo_content}</td>
		</tr>
		<tr>
			<th>작성날짜</th>
			<td>${board.bo_date}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.bo_hit}</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>${board.bo_rcmd}</td>
		</tr>
	</table>
	<table>
	<thead>
		<tr>
			<th>댓글 작성자</th>
			<th>댓글작성 IP</th>
			<th>댓글내용</th>
			<th>댓글단 날짜</th>
		</tr>
	</thead>
	<tbody id="listBody">
		<c:set var="replyList" value='${board.replyList }' />
	   	<c:forEach items="${replyList }" var="reply">
	   		<tr>
	   			<td>${reply.rep_writer }</td>
	   			<td>${reply.rep_ip }</td>
	   			<td>${reply.rep_content }</td>
	   			<td>${reply.rep_date }</td>
	   		</tr>
	   	</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4">
				<nav aria-label="Page navigation" id="pagingArea">
					${pagingVO.pagingHTML }
				</nav>
			</td>
		</tr>
	</tfoot>		
	</table>
</body>
</html>








