package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.MemberVO;

public class MemberUpdateController implements ICommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		요청 검증
//		통과
//		불통시 memberview.jsp로돌아감 각상황마다 에러메시지와 입력했던 기존데이터
//		수정성공(redirect) 사항과 수성실패(dispetcher)시 memberview가는 방식이 다르다  
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
//		member.setMem_id(req.getParameter("mem_id"));
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new CommonException(e);
		}
		String goPage = null;
		String message = null;
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		if (valid) {
			IMemberService service = new MemberServiceImpl();
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				goPage = "member/memberView"; //디스패치형식이므로 server에서 server로 갈수있어서 이런형식임
				message = "비번이 틀렸습니다";
				break;
			case FAILED:
				goPage = "member/memberView";
				message = "서버 오류로 인한 실패, 잠시 뒤 다시 하셈.";
				break;
			case OK:
//				goPage = "/member/memberView.do?who="+member.getMem_id(); //redirect형식으로 server에서 server로 갈수 없어 (/WEB-INF/는 serverSide) webservlet으로 형식을 지정해주고 그 형식을 불러서 이동한다
				goPage = "redirect:/member/mypage.do";
				break;
			}
			req.setAttribute("message", message);
		} else {
			goPage = "member/memberView";
		}
		
		return goPage;
		
	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMem_id())) {
			valid = false;
			errors.put("mem_id", "회원아이디 누락");
		}
		if (StringUtils.isBlank(member.getMem_pass())) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_name())) {
			valid = false;
			errors.put("mem_name", "회원명 누락");
		}
		
		if (StringUtils.isBlank(member.getMem_zip())) {
			valid = false;
			errors.put("mem_zip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_add1())) {
			valid = false;
			errors.put("mem_add1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMem_add2())) {
			valid = false;
			errors.put("mem_add2", "주소2 누락");
		}
		if (StringUtils.isBlank(member.getMem_hometel())) {
			valid = false;
			errors.put("mem_hometel", "집전번 누락");
		}
		if (StringUtils.isBlank(member.getMem_comtel())) {
			valid = false;
			errors.put("mem_comtel", "회사전번 누락");
		}
		if (StringUtils.isBlank(member.getMem_mail())) {
			valid = false;
			errors.put("mem_mail", "이메일 누락");
		}
		if(StringUtils.isNotBlank(member.getMem_bir())){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// formatting : 특정 타입의 데이터를 일정 형식의 문자열로 변환.
			// parsing : 일정한 형식의 문자열을 특정 타입의 데이터로 변환.
			try{
				formatter.parse(member.getMem_bir());
			}catch(ParseException e){
				valid = false;
				errors.put("mem_bir", "날짜 형식 확인");
			}
		}
		return valid;
	}
}
