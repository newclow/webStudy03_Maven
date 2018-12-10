package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.web.calculate.Mime;

@CommandHandler
public class BoardListController{

	@URIMapping("/board/boardList.do")
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int currentPage = 1;
//		1. 요청과의 매핑 설정
		String page = req.getParameter("page");
		String searchWord = req.getParameter("searchWord");
		String searchType = req.getParameter("searchType");
		
//		2. 요청 분석(주소, 파라미터, 메소드, 헤더들...)
		if(StringUtils.isNumeric(page)) {
			currentPage = Integer.parseInt(page);
		}
		PagingInfoVO<BoardVO> pagingVO = new PagingInfoVO<BoardVO>(10, 5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchWord(searchWord);
		pagingVO.setSearchType(searchType);
//		3. B.L.L 와의 의존관계 형성
		IBoardService service = new BoardServiceImpl();
		long totalRecord = service.retriveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> boardList = service.retriveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		String accept = req.getHeader("Accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType(Mime.JSON.getContentType()); 
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, pagingVO);				
				return null;
			}
		}else {
		// HTML
			req.setAttribute("pagingVO", pagingVO);
			return "board/boardList";
		}
	}

}
