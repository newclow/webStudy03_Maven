<%@page import="kr.or.ddit.vo.PagingInfoVO"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PagingInfoVO<BuyerVO> pagingVO = (PagingInfoVO<BuyerVO>)request.getAttribute("pagingVO");
	List<BuyerVO> buyerList = pagingVO.getDataList();
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
<script type="text/javascript">
	$(function(){
		$("#listBody").on("click","tr",function(){
			var buyer_id = $(this).find("td:first").text();
			location.href="<%=request.getContextPath() %>/buyer/buyerView.do?what="+buyer_id;
		});
	});
</script>
</head>
<body>
<h4>거래처 목록 </h4>
<input type="button" class="btn btn-success" value="신규등록" 
	onclick="location.href='<%=request.getContextPath() %>/buyer/buyerInsert.do';"/>
<table class="table">
	<thead  class="thead-dark">
			<tr>
				<th>거래처ID</th>
				<th>거래처명</th>
<!-- 				<th>거래품목</th> -->
				<th>거래처은행</th>
				<th>계좌</th>
				<th>예금주</th>
<!-- 				<th>우편번호</th> -->
<!-- 				<th>주소</th> -->
<!-- 				<th>상세주소</th> -->
<!-- 				<th>거래처번호</th> -->
<!-- 				<th>팩스번호</th> -->
<!-- 				<th>메일</th> -->
<!-- 				<th>입금자명</th> -->
			</tr>
		</thead>
		<tbody id="listBody">


			<%-- 	<td><a href="<%=request.getContextPath()%>/buyer/buyerView.do?who=<%=buyer.getMem_id()%>"><%=buyer.getMem_name() %></a></td> --%>

			<%
				if (buyerList.size() > 0) {
					for (BuyerVO buyer : buyerList) {
			%>
			<tr>
				
				<td><%=buyer.getBuyer_id()%></td>
				<td><%=buyer.getBuyer_name()%></td>
				<td><%=buyer.getBuyer_bank()%></td>
				<td><%=buyer.getBuyer_bankno()%></td>
				<td><%=buyer.getBuyer_bankname()%></td>
<%-- 				<td><%=buyer.getBuyer_zip()%></td> --%>
<%-- 				<td><%=buyer.getBuyer_add1()%></td> --%>
<%-- 				<td><%=buyer.getBuyer_add2()%></td> --%>
<%-- 				<td><%=buyer.getBuyer_comtel()%></td> --%>
<%-- 				<td><%=buyer.getBuyer_fax()%></td> --%>
<%-- 				<td><%=buyer.getBuyer_mail()%></td> --%>
<%-- 				<td><%=buyer.getBuyer_charger()%></td> --%>
			</tr>
			<%
					}
				} else {
			%>
			<tr>
				<td colspan="5">거래처 목록이 없음.</td>
			</tr>
			<%
				}
%>
		</tbody>
		<tfoot>
		<tr>
			<td colspan="5">
				<nav aria-label="Page navigation example">
				 	<%=pagingVO.getPagingHTML() %> 
				</nav>			
			</td>
		</tr>
	</tfoot>
</table>
</body>
</html>