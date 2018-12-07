<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cPath" value="${pageContext.request.contextPath }" scope="application"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://jqueryui.com/resources/demos/style.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="${cPath }/js/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script> 
<script src="${cPath }/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function(){
		<c:if test="${not empty message}" >
			alert("${message}");
		</c:if>
	});
</script>
</head>
<body>
<form action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="bo_ip" value="${pageContext.request.remoteAddr }"  >
<table class="table">
			<tr>
				<th>작성자</th>
				<td><div class="input-group">
						<input type="text" name="bo_writer" VALUE="${board.bo_writer}" /><span
							class="error">${errors["bo_writer"] }</span>
					</div></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><div class="input-group">
						<input type="text" name="bo_pass" VALUE="${board.bo_pass}" /><span
							class="error">${errors["bo_pass"] }</span>
					</div></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><div class="input-group">
						<input type="text" name="bo_mail" VALUE="${board.bo_mail}" /><span
							class="error">${errors["bo_mail"] }</span>
					</div></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><div class="input-group">
						<input type="text" name="bo_title" VALUE="${board.bo_title}" /><span
							class="error">${errors["bo_title"] }</span>
					</div></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="bo_title" />
					<input type="file" name="bo_title" />
					<input type="file" name="bo_title" />
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><div class="input-group">
						<textarea cols="50" rows="10" name="bo_content" id="bo_content">${board.bo_content}</textarea>
						<span class="error">${errors["bo_content"] }</span>
					</div></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록">
					<input type="reset" value="취소">
					<input type="button" value="목록으로" 
					onclick="location.href='${pageContext.request.contextPath}/board/boardList.do';" /> <!-- 끝에 ;을 빼먹지 말자 -->
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		CKEDITOR.replace( 'bo_content' );
	</script>
</body>
</html>