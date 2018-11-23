package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.MemberVO;

/**
 * 1. 요청과의 매핑 설정
 * 2. 요청 분석(주소, 파라미터, 메소드, 헤더들...)
 * 3. B.L.L 와의 의존관계 형성		
 * 4. 로직 선택
 * 5. 컨텐츠(Model) 확보
 * 6. V.L 를 선택
 * 7. Scope 를 통해 Model 공유
 * 8. 이동 방식을 결정하고, V.L로 이동.
 * 
 */
public class MemberDeleteController implements ICommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		if(StringUtils.isBlank(mem_id)||StringUtils.isBlank(mem_pass)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		IMemberService service = new MemberServiceImpl();
		ServiceResult result = service.removeMember(new MemberVO(mem_id, mem_pass));
		String view = null;
		String message = null;
		switch (result) {
		case INVALIDPASSWORD:
			message = "비번 오류";
//			view = "/member/memberView.do?who="+mem_id;
			view = "redirect:/member/mypage.do";
			break;
		case FAILED :
			message = "서버 오류, 미안~";
//			view = "/member/memberView.do?who="+mem_id;
			view = "redirect:/member/mypage.do";
			break;
		default:
//			view = "/member/memberList.do";
			view = "redirect:/common/message.jsp";
			message = "탈퇴약관 : 일주일이내에서 즐대!!! 같은 아이디로 재가입 불가" ;
			req.getSession().setAttribute("goLink", "/");
			req.getSession().setAttribute("isRemoved", new Boolean(true));
//			req.getSession().invalidate();
			break;
		}
		req.getSession().setAttribute("message", message);
		
		return view;
	}
}
















