package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

public class ProdListController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ProdVO searchVO = new ProdVO();
		searchVO.setProd_lgu(req.getParameter("prod_lgu"));
		searchVO.setProd_buyer(req.getParameter("prod_buyer"));
		searchVO.setProd_name(req.getParameter("prod_name"));
		
		
		
//		1. 요청과의 매핑설정
		String page = req.getParameter("page"); //어떤 페이지가 필요한지 잡아준다
		int currentPage =1;
//		2. 요청분석(주소,파라미터,메소드,헤더들)
		if (StringUtils.isNumeric(page)) { //정규표현식을 사용하지않고 숫자를 확인
			currentPage = Integer.parseInt(page); //page를 parse함
		}
		PagingInfoVO<ProdVO> pagingVO = new PagingInfoVO<ProdVO>(7,4); //페이지vo를 인스턴스화하며 스크린사이즈 블록사이즈 7 4를 줌
		pagingVO.setCurrentPage(currentPage); //스타트로우와 엔드로우가 결정됨
		pagingVO.setSearchVO(searchVO);
//		3. BLL와의 의존관계 형성
		IProdService service = new ProdServiceImpl();
		IOtherDAO otherDAO = new OtherDAOImpl();
		Map<String , String> lprodList = otherDAO.selectLprodList();
		List<BuyerVO> buyerList = otherDAO.selectBuyerList(null);
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
//		4. 로직선택
//		5. 콘텐츠(model) 확보
		long totalRecord = service.retrieveProdCount(pagingVO); //페이지수를 가져오기위해
		pagingVO.setTotalRecord(totalRecord); //전체페이지가 연산됨
		List<ProdVO> prodList = service.retrieveProdList(pagingVO); //페이지목록을 가져오기위해 
		pagingVO.setDataList(prodList);	//넣어주면 pagingvo에 모든 페이지데이터가 들어가게됨
		
		//Accept헤더를 통해 동기/비동기 요청 여부를 확인하고
		//동기요청이라면 view Layer를 통해 응답이 전송
		//비동기 요청이라면 pagingVO를 marshalling 한 JSON응답이 전송
		
		String accept = req.getHeader("Accept");
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			//JSON
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, pagingVO);
			}
			return null;
		}else {
			//HTML
			req.setAttribute("pagingVO", pagingVO);
			return "prod/prodList";
		}
		
//		6. v.l 선택
//		7. scope를 통해 model공유
//		8. 이동방식 결정하고 vl로 이동
	}

}
