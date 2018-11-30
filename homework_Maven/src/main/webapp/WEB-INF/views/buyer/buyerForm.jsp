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
</head>
<body>
<h4>거래처 추가</h4>
<jsp:useBean id="buyer" class="kr.or.ddit.vo.BuyerVO" scope="request"></jsp:useBean>
<jsp:useBean id="errors" class="java.util.LinkedHashMap"></jsp:useBean>
<form action="" method="post">
<table>
		<tr>
			<th>거래처명</th>
			<td><input type="text" name="buyer_name"
				VALUE="<%=buyer.getBuyer_name()%>" /><span class="error"><%=errors.get("buyer_name")%></span></td>
		</tr>
		<tr>
			<th>거래품목</th>
			<td><input type="text" name="buyer_lgu"
				VALUE="<%=buyer.getBuyer_lgu()%>" /><span class="error"><%=errors.get("buyer_lgu")%></span></td>
		</tr>
		<tr>
			<th>거래처은행</th>
			<td><input type="text" name="buyer_bank"
				VALUE="<%=buyer.getBuyer_bank()%>" /><span class="error"><%=errors.get("buyer_bank")%></span></td>
		</tr>
		<tr>
			<th>계좌</th>
			<td><input type="text" name="buyer_bankno"
				VALUE="<%=buyer.getBuyer_bankno()%>" /><span class="error"><%=errors.get("buyer_bankno")%></span></td>
		</tr>
		<tr>
			<th>예금주</th>
			<td><input type="text" name="buyer_bankname"
				VALUE="<%=buyer.getBuyer_bankname()%>" /><span class="error"><%=errors.get("buyer_bankname")%></span></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="buyer_zip"
				VALUE="<%=buyer.getBuyer_zip()%>" /><span class="error"><%=errors.get("buyer_zip")%></span></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="buyer_add1"
				VALUE="<%=buyer.getBuyer_add1()%>" /><span class="error"><%=errors.get("buyer_add1")%></span></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><input type="text" name="buyer_add2"
				VALUE="<%=buyer.getBuyer_add2()%>" /><span class="error"><%=errors.get("buyer_add2")%></span></td>
		</tr>
		<tr>
			<th>거래처번호</th>
			<td><input type="text" name="buyer_comtel"
				VALUE="<%=buyer.getBuyer_comtel()%>" /><span class="error"><%=errors.get("buyer_comtel")%></span></td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td><input type="text" name="buyer_fax"
				VALUE="<%=buyer.getBuyer_fax()%>" /><span class="error"><%=errors.get("buyer_fax")%></span></td>
		</tr>
		<tr>
			<th>메일</th>
			<td><input type="text" name="buyer_mail"
				VALUE="<%=buyer.getBuyer_mail() %>" /><span class="error"><%=errors.get("buyer_mail") %></span></td>
		</tr>
		<tr>
			<th>입금자명</th>
			<td><input type="text" name="buyer_charger"
				VALUE="<%=buyer.getBuyer_charger() %>" /><span class="error"><%=errors.get("buyer_charger") %></span></td>
		</tr>
				<tr>
			<td colspan="2">
				<input type="submit" value="전송" />
				<input type="reset" value="취소" />
				<input type="button" value="목록으로" 
					onclick="location.href='<%=request.getContextPath() %>/buyer/buyerList.do';"
				/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>