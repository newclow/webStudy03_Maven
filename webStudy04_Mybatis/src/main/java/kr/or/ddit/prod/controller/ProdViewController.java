package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

public class ProdViewController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//요청 파라미터
		String prod_id = req.getParameter("what");
		//요청 검증
		if (StringUtils.isBlank(prod_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		//BLL 의존관계 형성
		IProdService service = new ProdServiceImpl();
		//로직선택 컨텐츠확보
		ProdVO prod = service.retrieveProd(prod_id);
		//scope를 통해 model공유
		req.setAttribute("prod", prod);
		//vl선택 및 이동방식결정
		return "prod/prodView";
	}

}
