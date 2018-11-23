<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
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
	$(function(){
		<%
			String message = (String)request.getAttribute("message");
			if(StringUtils.isBlank(message)){
				message = (String)session.getAttribute("message");
				session.removeAttribute("message");
			}
			if(StringUtils.isNotBlank(message)){
			%>
				alert('<%=message%>');
			<%
			}
		%>
		
		$(".date").datepicker({
			dateFormat : "yy-mm-dd"
		});
		$("#delBtn").on("click", function(){
			var chk = confirm(" 정말로 탈퇴 ?");
			if(chk){
				var password = prompt("비밀번호 입력");
				if(password){
					document.delForm.mem_pass.value=password;
	// 				$("[name='mem_pass']").val(password);
					document.delForm.submit();
				}
			}
		});	
	});
</script>
</head>
<body>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" />
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request" />
<%
 boolean mutable = false;
 MemberVO authMember = (MemberVO) session.getAttribute("authMember");
 if(authMember!=null && !"ROLE_ADMIN".equals(authMember.getMem_auth())){
	if(authMember.getMem_id().equals(member.getMem_id())){
		mutable = true;
	}
 }
 if(mutable){
%>
<form name="delForm" method="post" action="<%=request.getContextPath()%>/member/memberDelete.do">
 	<input type="hidden" name="mem_id" value="<%=member.getMem_id() %>"/>
 	<input type="hidden" name="mem_pass" />
</form>
<form action="<%=request.getContextPath() %>/member/memberUpdate.do" method="post">
<%
 }	
%>	
<h4>회원정보 상세조회 및 수정폼</h4>
	<table>
			<tr>
				<th>회원아이디</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_id"
							value="<%=member.getMem_id()%>" />
							<span class="error input-group-text"><%=errors.get("mem_id")%></span>
					</div></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_pass"
							value="<%=member.getMem_pass()%>" />
							<span class="error input-group-text"><%=errors.get("mem_pass")%></span>
					</div></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_name"
							value="<%=member.getMem_name()%>" />
							<span class="error input-group-text"><%=errors.get("mem_name")%></span>
					</div></td>
			</tr>
			<tr>
				<th>주민번호</th>
				<td><div class="input-group">
						<input class="form-control col-md-2" type="text" name="mem_regno1"
							value="<%=member.getMem_regno1()%>" disabled="disabled" />
						<span class="input-group-text">-</span>	
						<input class="form-control col-md-2" type="text" name="mem_regno2"
							value="<%=member.getMem_regno2()%>" disabled="disabled"/>
							<span class="error input-group-text"><%=errors.get("mem_regno1")%></span>
							<span class="error input-group-text"><%=errors.get("mem_regno2")%></span>
					</div></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><div class="input-group">
						<input class="form-control date" type="text" name="mem_bir"
							value="<%=member.getMem_bir()%>" disabled="disabled"/>
							<span class="error input-group-text"><%=errors.get("mem_bir")%></span>
					</div></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_zip"
							value="<%=member.getMem_zip()%>" />
							<span class="error input-group-text"><%=errors.get("mem_zip")%></span>
					</div></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_add1"
							value="<%=member.getMem_add1()%>" />
							<span class="error input-group-text"><%=errors.get("mem_add1")%></span>
					</div></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_add2"
							value="<%=member.getMem_add2()%>" />
							<span class="error input-group-text"><%=errors.get("mem_add2")%></span>
					</div></td>
			</tr>
			<tr>
				<th>집전번</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_hometel"
							value="<%=member.getMem_hometel()%>" />
							<span class="error input-group-text"><%=errors.get("mem_hometel")%></span>
					</div></td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_comtel"
							value="<%=member.getMem_comtel()%>" />
							<span class="error input-group-text"><%=errors.get("mem_comtel")%></span>
					</div></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_hp"
							value="<%=member.getMem_hp()%>" />
							<span class="error input-group-text"><%=errors.get("mem_hp")%></span>
					</div></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_mail"
							value="<%=member.getMem_mail()%>" />
							<span class="error input-group-text"><%=errors.get("mem_mail")%></span>
					</div></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_job"
							value="<%=member.getMem_job()%>" />
							<span class="error input-group-text"><%=errors.get("mem_job")%></span>
					</div></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_like"
							value="<%=member.getMem_like()%>" />
							<span class="error input-group-text"><%=errors.get("mem_like")%></span>
					</div></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><div class="input-group">
						<input class="form-control" type="text" name="mem_memorial"
							value="<%=member.getMem_memorial()%>" />
							<span class="error input-group-text"><%=errors.get("mem_memorial")%></span>
					</div></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><div class="input-group">
						<input class="form-control date" type="text" name="mem_memorialday"
							value="<%=member.getMem_memorialday()%>" />
							<span class="error input-group-text"><%=errors.get("mem_memorialday")%></span>
					</div></td>
			</tr>
		<tr>
			<th>마일리지</th>
			<td><%=member.getMem_mileage()%></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><%="Y".equals(member.getMem_delete())?"탈퇴":"활동중" %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" class="btn btn-info" value="뒤로가기" 
					onclick="history.back();"
				/>
				<%
				if(mutable){
				%>
				<input type="submit" class="btn btn-success" value="수정" />
				<input type="reset" class="btn btn-info" value="취소" />
				<input type="button" value="탈퇴" id="delBtn"/>
				<%
				}
				%>
			</td>
		</tr>
	</table>
<%
if(mutable){
%>	
</form>	
<%
}
%>
</body>
</html>





















