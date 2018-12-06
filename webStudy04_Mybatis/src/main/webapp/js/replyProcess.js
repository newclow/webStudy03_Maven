/**
 * 게시글 상세조회의 덧글 처리
 */

	function replyListMaker(resp) {
		if (resp.error) {
			alert(resp.message); 					
		} else { // 등록 성공
			var html = "";
			if(resp.dataList){
				$.each(resp.dataList, function(idx, reply){
					html += "<tr id='TR_"+reply.rep_no+"'>";
					html += "<td>"+reply.rep_writer+"</td>";
					html += "<td>"+reply.rep_ip+"</td>";
					html += "<td>"+reply.rep_content+"</td>";
					html += "<td>"+reply.rep_date+"&nbsp;<span data-toggle='modal' class='replyDelBtn'>[삭제]</span></td>";
					html += "</tr>";
				});
			}else{
				html += "<tr><td colspan='4'>데이터 없음.</td></tr>";
			}
			pagingArea.html(resp.pagingHTML);	
			listBody.html(html);
			replyForm[0].reset();
		}
	}
	
	function pagingReply(page, bo_no){
		$.ajax({
			url:$.getContextPath()+"/reply/replyList.do",
			data:{
				bo_no:bo_no,
				page:page
			},
			dataType:"json",
			success:replyListMaker,
			error:function(resp){
				console.log(resp.status);
			}
		});
	}
	
	
	$(function(){
		pagingArea = $("#pagingArea");
		listBody = $("#listBody");
 		replyForm = $("[name='replyForm']");
 		var delModal = $("#replyDeleteModal");
 		
 		listBody.on("click", ".replyDelBtn" ,function(){
 			var trId = $(this).closest("tr").prop("id");
 			var rep_no = trId.substring(trId.indexOf("_")+1);
 			delModal.find("#rep_no").val(rep_no);
 			delModal.modal("show");
 		});
 		
 		$("#modalBtn").on("click", function(){
 			var action = replyForm.attr("action");
 			replyForm.attr("action", $.getContextPath()+"/reply/replyDelete.do");
 			var rep_no = delModal.find("#rep_no").val();
 			var rep_pass = delModal.find("#rep_pass").val();
 			replyForm.find("[name='rep_no']").val(rep_no);
 			replyForm.find("[name='rep_pass']").val(rep_pass);
 			replyForm.submit();
 			replyForm.attr("action", action);
 			replyForm[0].reset();
 			$("#modalForm")[0].reset(); 			
 			delModal.modal("hide");
 		});
 		
 		replyForm.ajaxForm({
 			dataType:'json',
 			success:replyListMaker,
 			error:function(resp){
 				alert(resp.status);
 			}
 		});
 		
	});