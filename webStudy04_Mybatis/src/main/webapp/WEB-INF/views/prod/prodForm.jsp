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
	src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
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
		<c:if test="${not empty message }">
			alert("${message }");
		</c:if>
		$("[name$='date']").datepicker({
			dateFormat:"yy-mm-dd"
		});
		var pattern = "<option value='%V'>%T</option>";
		var prod_buyerTag = $("[name='prod_buyer']");
		var prod_lguTag = $("[name='prod_lgu']");
		prod_lguTag.on("change", function(){
			var prod_lgu = $(this).val();
			$.ajax({
				url:"${pageContext.request.contextPath }/prod/getBuyerList.do",
				data:{
					prod_lgu:prod_lgu
				},
				dataType:"json", // Accept, Content-Type
				success:function(resp){
					var options = pattern.replace("%V", "")
										 .replace("%T", "거래처 선택");
					$.each(resp, function(idx, buyer){
						options += pattern.replace("%V", buyer.buyer_id)
										  .replace("%T", buyer.buyer_name);
					});
					prod_buyerTag.html(options);
					prod_buyerTag.val("${prod.prod_buyer }");
				},
				error:function(){
					
				}
			});
		});
		prod_lguTag.val("${prod.prod_lgu }");
		prod_lguTag.trigger("change");
	});
</script>
</head>
<body>
	
	<form method="post">
	<input type="hidden" name="prod_id" value="${prod.prod_id }">
	<table>
		<tr>
			<th>상품명</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_name"
						value="${prod.prod_name }" /><span
						class="input-group-text error">${errors.prod_name }</span>
				</div></td>
		</tr>
		<tr>
			<th>분류코드</th>
			<td><div class="input-group">
					<select name="prod_lgu">
						<option value="">분류선택</option>
							<c:forEach var="lprod" items="${lprodList }" >
								<option value="${lprod['LPROD_GU'] }">${lprod['LPROD_NM'] }</option>
							</c:forEach>
					</select>
					<span class="input-group-text error">${errors.prod_lgu }</span>
				</div></td>
		</tr>
		<tr>
			<th>거래처코드</th>
			<td><div class="input-group">
					
					<select name="prod_buyer">
					
					</select>
					<span
						class="input-group-text error">${errors.prod_buyer }</span>
				</div></td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_cost"
						value="${prod.prod_cost }" /><span
						class="input-group-text error">${errors["prod_cost"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_price"
						value="${prod.prod_price }" /><span
						class="input-group-text error">${errors["prod_price"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>특판가</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_sale"
						value="${prod.prod_sale }" /><span
						class="input-group-text error">${errors["prod_sale"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>상품개요</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_outline"
						value="${prod.prod_outline }" /><span
						class="input-group-text error">${errors["prod_outline"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_detail"
						value="${prod.prod_detail }" /><span
						class="input-group-text error">${errors["prod_detail"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>이미지경로</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_img"
						value="${prod.prod_img }" /><span
						class="input-group-text error">${errors["prod_img"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>재고량</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_totalstock"
						value="${prod.prod_totalstock }" /><span
						class="input-group-text error">${errors["prod_totalstock"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_insdate"
						value="${prod.prod_insdate }" /><span
						class="input-group-text error">${errors["prod_insdate"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_properstock"
						value="${prod.prod_properstock }" /><span
						class="input-group-text error">${errors["prod_properstock"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>상품크기</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_size"
						value="${prod.prod_size }" /><span
						class="input-group-text error">${errors["prod_size"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>상품색상</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_color"
						value="${prod.prod_color }" /><span
						class="input-group-text error">${errors["prod_color"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_delivery"
						value="${prod.prod_delivery }" /><span
						class="input-group-text error">${errors["prod_delivery"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_unit"
						value="${prod.prod_unit }" /><span
						class="input-group-text error">${errors["prod_unit"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_qtyin"
						value="${prod.prod_qtyin }" /><span
						class="input-group-text error">${errors["prod_qtyin"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_qtysale"
						value="${prod.prod_qtysale }" /><span
						class="input-group-text error">${errors["prod_qtysale"] }</span>
				</div></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><div class="input-group">
					<input class="form-control" type="text" name="prod_mileage"
						value="${prod.prod_mileage }" /><span
						class="input-group-text error">${errors["prod_mileage"] }</span>
				</div></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="전송" />
				<input type="reset" value="취소" />
				<input type="button" value="목록으로" 
					onclick="location.href='${pageContext.request.contextPath }/prod/prodList.do';"
				/>
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>




















