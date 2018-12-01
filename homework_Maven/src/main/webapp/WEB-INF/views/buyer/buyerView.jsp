<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BuyerVO buyer = (BuyerVO)request.getAttribute("buyer");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script type="text/javascript" 
	src="<%=request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>	
</head>
<body>
<table class="table">
		<tr>
			<th>거래처ID</th>
			<td><%=buyer.getBuyer_id()%></td>
		</tr>
		<tr>
			<th>거래처명</th>
			<td><%=buyer.getBuyer_name()%></td>
		</tr>
		<tr>
			<th>거래품목</th>
			<td><%=buyer.getBuyer_lgu()%></td>
		</tr>
		<tr>
			<th>거래처은행</th>
			<td><%=buyer.getBuyer_bank()%></td>
		</tr>
		<tr>
			<th>계좌</th>
			<td><%=buyer.getBuyer_bankno()%></td>
		</tr>
		<tr>
			<th>예금주</th>
			<td><%=buyer.getBuyer_bankname()%></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><%=buyer.getBuyer_zip()%></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><%=buyer.getBuyer_add1()%></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><%=buyer.getBuyer_add2()%></td>
		</tr>
		<tr>
			<th>거래처번호</th>
			<td><%=buyer.getBuyer_comtel()%></td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td><%=buyer.getBuyer_fax() %></td>
		</tr>
		<tr>
			<th>메일</th>
			<td><%=buyer.getBuyer_mail() %></td>
		</tr>
		<tr>
			<th>입금자명</th>
			<td><%=buyer.getBuyer_charger() %></td>
		</tr>
	</table>
</body>
</html>