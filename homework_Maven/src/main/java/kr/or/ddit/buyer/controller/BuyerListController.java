package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.BuyerVO;

public class BuyerListController implements ICommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		1. 요청과의 매핑설정
		
//		2. 요청분석(주소, 파라미터, 메소드, 헤더들)
		
//		3. B.L.L(basiness logic layer)와의 의종관계 형성
		IBuyerService service = new BuyerServiceImpl();
//		4. 로직선택
//		5. 컨텐츠(model)확보
		List<BuyerVO> buyerList = service.retrieveBuyerList();
//		6. v.l(view layer)를 선택
		String view = "buyer/buyerList";
//		7. scope를 통해 model 공유
		req.setAttribute("buyerList", buyerList);
//		8. 이동방식 결정하고 v.l로 
		return view;
	}
	
}
