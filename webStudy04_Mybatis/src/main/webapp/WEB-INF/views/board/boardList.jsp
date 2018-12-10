<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> 
<script src="http://malsup.github.com/jquery.form.js"></script> 
<script type="text/javascript">

	function ${pagingVO.funcName}(page){
		document.searchForm.page.value = page;
		document.searchForm.submit();
	}
	$(function(){
	  	var navTag = $('#navtag');
		var bodyTag = $('#bodylist');
		var searchForm = $('[name="searchForm"]');
		
		$(window).on("popstate", function(event){
			console.log(event);
			if (event.originalEvent.state) {
				var pagingVO = event.originalEvent.state;
	 			var body="";
	 			var boardList = pagingVO.dataList;
	 			if(boardList){
	 				$.each(boardList,function(i, board){
	 					body+="<tr><td>"+board.rnum+"</td>";
	 					body+="<td>"+board.bo_no+"</td>";
	 					body+="<td>"+board.bo_title+"</td>";
	 					body+="<td>"+board.bo_writer+"</td>";
	 					body+="<td>"+board.bo_date+"</td>";
	 					body+="<td>"+board.bo_hit+"</td>";
	 					body+="<td>"+board.bo_rcmd+"</td></tr>";
	 				})
	 			}else{
	 				body+="<tr><td colspan='7'>데이터없음</td></tr>";
	 			}
	 			navTag.html(pagingVO.pagingHTML);
	 			bodyTag.html(body);
			}
		});
		
		searchForm.ajaxForm({ 
		       dataType:  'json', 
		       success:  function (data){
				var body="";
				var boardList = data.dataList;
				if(boardList){
					$.each(boardList,function(i, board){
						body+="<tr><td>"+board.rnum+"</td>";
						body+="<td>"+board.bo_no+"</td>";
						body+="<td>"+board.bo_title+"</td>";
						body+="<td>"+board.bo_writer+"</td>";
						body+="<td>"+board.bo_date+"</td>";
						body+="<td>"+board.bo_hit+"</td>";
						body+="<td>"+board.bo_rcmd+"</td></tr>";
					})
				}else{
					body+="<tr><td colspan='7'>데이터없음</td></tr>";
				}
				navTag.html(data.pagingHTML);
				bodyTag.html(body);
				//비동기처리성공 -> push state on history(state, title, url)
				var pageNum = searchForm.find('[name="page"]').val();
				var queryString = searchForm.serialize();
				history.pushState(data, pageNum+" 페이지", "?"+queryString);
				$('[name="page"]').val("");
			},
		 });
		 
		 <c:url var="boardView" value="/board/boardView.do"/>
		 $("#bodylist").on("click", "tr",function(){
			var bo_no = $(this).find("td:nth-child(2)").text();
			location.href = "${boardView}?what="+bo_no;
		 });
	})
</script>
</head>
<body>
<h4>게시판</h4>
<table class="table">
	<thead class="thead-dark">
		<tr>
			<th>순번</th>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>추천수</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody id="bodylist">
		<c:set var="boardList" value="${pagingVO.dataList }" />
		<c:if test="${not empty boardList }">
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>${board.rnum }</td>
					<td>${board.bo_no }</td>
					<td>${board.bo_title }</td>
					<td>${board.bo_writer }</td>
					<td>${board.bo_hit }</td>
					<td>${board.bo_rcmd }</td>
					<td>${board.bo_date }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty boardList }">
			<tr>
				<td colspan="7">게시글이 없음</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<nav id="navtag">${pagingVO.pagingHTML }</nav>
				<form name="searchForm">
				<input type="hidden" name="page" />
				<select name="searchType">
					<option value="all">전체</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="writer">작성자</option>
				</select>
				<script type="text/javascript">
					$('[name="searchType"]').val("${param.searchType}");
				</script>
				<input type="text" name="searchWord" value="${pagingVO.searchWord }"/>
				<input type="submit" value="검색" />
				</form>
				<input type="button" value="글쓰기" onclick="location.href='${pageContext.request.contextPath}/board/boardInsert.do';" />
			</td>
		</tr>
	</tfoot>
</table>

</body>
</html>