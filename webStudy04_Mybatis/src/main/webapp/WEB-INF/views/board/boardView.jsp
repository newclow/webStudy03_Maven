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
<script src="http://malsup.github.com/jquery.form.js"></script> 
<script type="text/javascript" src="<c:url value='/js/replyProcess.js' />"></script>
<script type="text/javascript">
	$.getContextPath = function(){
		return "${pageContext.request.contextPath}";
	}
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
			<th>첨부파일</th>
			<td>
				<c:forEach items="${board.pdsList }" var="pds" varStatus="vs">
					<c:url value="/board/download.do" var="downloadURL">
						<c:param name="what" value="${pds.pds_no }" />
					</c:url>
					<a href="${downloadURL }">${pds.pds_filename }</a>
					<c:if test="${not vs.last }">&nbsp;|&nbsp;</c:if>
				</c:forEach>
			</td>
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
		<tr>
			<td colspan="2">
				<c:url value="/board/boardUpdate.do" var="updateURL">
					<c:param name="what" value="${board.bo_no }"/>
				</c:url>
				<input type="button" value="수정" onclick="location.href='${updateURL}';">
				<c:url value="/board/boardDelete.do" var="deleteURL">
					<c:param name="bo_no" value="${board.bo_no }"/>
					<c:param name="bo_pass" value="${board.bo_pass }"/>
				</c:url>
				<input type="button" value="삭제" onclick="location.href='${deleteURL}';">
			</td>
		</tr>
	</table>
	<form action="${pageContext.request.contextPath}/reply/replyInsert.do"
		name="replyForm">
		<input type="hidden" name="rep_no" /> <input type="hidden"
			value="${board.bo_no }" name="bo_no" /> <input type="hidden"
			value="${pageContext.request.remoteAddr}" name="rep_ip" />
		<table>
			<tr>
				<td>작성자<input type="text" name="rep_writer" /> 비번<input
					type="password" name="rep_pass" />
				</td>
			</tr>
			<tr>
				<td>내용<textarea name="rep_content" rows="3" cols="100"></textarea>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="댓글쓰기" /></td>
			</tr>
		</table>
	</form>
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
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4">
				<nav aria-label="Page navigation" id="pagingArea">
				</nav>
			</td>
		</tr>
	</tfoot>
	</table>
<!-- Modal -->
<div class="modal fade" id="replyDeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<form onsubmit="return false;" id="modalForm">
      		<input type="hidden" id="bo_no" value="${board.bo_no }" />
      		<input type="hidden" id="rep_no" />
			비밀번호 : <input type="text" id="rep_pass" />
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" id="modalBtn">삭제</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	function paging(page){
		pagingReply(page, ${board.bo_no});
	}
	paging(1);
</script>
</body>
</html>








