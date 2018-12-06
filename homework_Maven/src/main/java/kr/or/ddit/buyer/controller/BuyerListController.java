package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.dao.IOtherDAO;
import kr.or.ddit.buyer.dao.OtherDAOImpl;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerListController implements ICommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String searchWord = req.getParameter("searchWord");
		String searchType = req.getParameter("searchType");
		
//		1. 요청과의 매핑설정
		String page = req.getParameter("page");
		int currentPage =1;
//		2. 요청분석(주소, 파라미터, 메소드, 헤더들)
		if (StringUtils.isNumeric(page)) {
			currentPage = Integer.parseInt(page);
		}
		PagingInfoVO<BuyerVO> pagingVO = new PagingInfoVO<>(5,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchWord(searchWord);
		pagingVO.setSearchType(searchType);
//		3. B.L.L(basiness logic layer)와의 의종관계 형성
		IBuyerService service = new BuyerServiceImpl();
		IOtherDAO otherDAO = new OtherDAOImpl();
		Map<String, String> lprodList = otherDAO.selectLprodList();
		req.setAttribute("lprodList", lprodList);
//		4. 로직선택
//		5. 컨텐츠(model)확보
		long totalRecord  = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BuyerVO> buyerList = service.retrieveBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
//		6. v.l(view layer)를 선택
		String view = "buyer/buyerList";
//		7. scope를 통해 model 공유
		req.setAttribute("pagingVO", pagingVO);
//		8. 이동방식 결정하고 v.l로 
		return view;
	}
	
}
