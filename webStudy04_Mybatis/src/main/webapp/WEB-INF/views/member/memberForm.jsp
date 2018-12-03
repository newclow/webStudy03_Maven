<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function() {
		<c:if test="${not empty message }">
		alert("${message }");
		</c:if>
		$("[type='date']").datepicker({
			dateFormat : "yy-mm-dd"
		});
	});
</script>
</head>
<body>
	<h4>회원 가입</h4>
	${memberVO.request.member }
<%-- 	<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" /> --%>
<%-- 	<jsp:useBean id="errors" class="java.util.LinkedHashMap" --%>
<%-- 		scope="request"></jsp:useBean> --%>
	<form method="post" enctype="mutipart/form-data">
		<table>
			<tr>
				<th>회원아이디</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_id"
							value="${member.mem_id }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_id"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_pass"
							value="${member.mem_pass }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_pass"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_name"
							value="${member.mem_name }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_name"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>주민번호</th>
				<td><div class="input-group">
						<input class="form-control col-md-3" type="text" name="mem_regno1"
							value="${member.mem_regno1 }" /> <span class="input-group-text">-</span>
						<input class="form-control col-md-3" type="text" name="mem_regno2"
							value="${member.mem_regno2 }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_regno1"] }</span> <span
								class="error">${errors["mem_regno2"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_bir"
							value="${member.mem_bir }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_bir"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_zip"
							value="${member.mem_zip }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_zip"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_add1"
							value="${member.mem_add1 }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_add1"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_add2"
							value="${member.mem_add2 }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_add2"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>집전번</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_hometel"
							value="${member.mem_hometel }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_hometel"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_comtel"
							value="${member.mem_comtel }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_comtel"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_hp"
							value="${member.mem_hp }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_hp"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_mail"
							value="${member.mem_mail }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_mail"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_job"
							value="${member.mem_job }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_job"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_like"
							value="${member.mem_like }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_like"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_memorial"
							value="${member.mem_memorial }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_memorial"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_memorialday"
							value="${member.mem_memorialday }" />
						<div class="input-group-text">
							<span class="error">${errors["mem_memorialday"] }</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="등록"
					class=" btn btn-success" /> <input type="reset" value="취소"
					class="btn btn-info" /></td>
			</tr>
		</table>
	</form>
</body>
</html>








