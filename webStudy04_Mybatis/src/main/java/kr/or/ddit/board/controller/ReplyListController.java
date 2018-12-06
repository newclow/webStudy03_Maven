package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyListController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String bo_no = req.getParameter("bo_no");
		String page = req.getParameter("page");
		if (!StringUtils.isNumeric(bo_no)) { //bo_no이 long이므로 numeric으로 값이 있는지 확인
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		long currentPage = 1;
		if (StringUtils.isNumeric(page)) { //page에 값이 있으면 String을 다시 long으로 치환하여 현재 페이지에 값을 대입
			currentPage = Long.parseLong(page);
		}
		PagingInfoVO<ReplyVO> pagingVO = new PagingInfoVO<>(); //pagingVO 객체화하여
		pagingVO.setCurrentPage(currentPage); //pagingVO의 값중 현재페이지를 set한다
		IReplyService replyService = new ReplyServiceImpl(); 
		ReplyVO searchVO = new ReplyVO(); //replyVO를 객체화한다
		searchVO.setBo_no(Long.parseLong(bo_no));
		pagingVO.setSearchVO(searchVO);
		long totalRecord = replyService.retriveReplyCount(pagingVO); //현재페이지가 set된 pagingVO를 넣어 댓글갯수를 구한다
		pagingVO.setTotalRecord(totalRecord); //총 댓글 갯수를 pagingVO에 넣어 연산하여 페이징처리하게한다.
		List<ReplyVO> replyList = replyService.retriveReplyList(pagingVO); //댓글목록 가져와
		pagingVO.setDataList(replyList); //dataList로 set해서 List형식의 VO를 가져온다
		
		
		resp.setContentType("application/json;charset=UTF-8"); //
		ObjectMapper mapper = new ObjectMapper(); //마샬링하는 객체를 불러오는거
		try(
			PrintWriter out = resp.getWriter(); //보내주는거
		){
			mapper.writeValue(out, pagingVO); // 먀샬링하면서 보내는주는거
		}
		return null;
	}

}
