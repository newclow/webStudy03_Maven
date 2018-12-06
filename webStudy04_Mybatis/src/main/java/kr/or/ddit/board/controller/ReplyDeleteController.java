package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.ReplyVO;

public class ReplyDeleteController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String bo_no = req.getParameter("bo_no");
		String rep_no = req.getParameter("rep_no");
		String rep_pass = req.getParameter("rep_pass");
		if (!StringUtils.isNumeric(bo_no)||!StringUtils.isNumeric(rep_no)||
				StringUtils.isBlank(rep_pass)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		// 자바빈의 setter를 통해 객체의 상태를 설정 - javabean pattern -> builder pattern
		ReplyVO reply = new ReplyVO();
		reply.setBo_no(Long.parseLong(bo_no));
		reply.setRep_no(Long.parseLong(rep_no));
		reply.setRep_pass(rep_pass);
		
		IReplyService service = new ReplyServiceImpl();
		ServiceResult result = service.deleteReply(reply);
		
		String view = null;
		String message = null;
		Map<String, String> errors = new HashMap<>();
		boolean requiredmarshalling = false;
		switch (result) {
		case OK:
			view = "redirect:/reply/replyList.do?bo_no"+reply.getBo_no();
			break;
		case FAILED:
			errors.put("errors", "true");
			errors.put("message", "서버오류");
			requiredmarshalling = true;
			break;
		case INVALIDPASSWORD:
			errors.put("errors", "true");
			errors.put("message", "비밀번호 틀림");
			requiredmarshalling = true;
			break;
		}
		if (requiredmarshalling) {
			resp.setContentType("application/json;charset=UTF-8"); //Mime설정
			ObjectMapper mapper = new ObjectMapper(); //마샬링하는 객체를 불러오는거
			try(
					PrintWriter out = resp.getWriter(); //보내주는거
					){
				mapper.writeValue(out, errors); // 먀샬링하면서 보내는주는거
			}
		}
		return view;
	}
}
