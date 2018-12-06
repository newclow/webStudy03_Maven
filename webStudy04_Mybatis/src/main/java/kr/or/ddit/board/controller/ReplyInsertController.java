package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
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

public class ReplyInsertController implements ICommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ReplyVO reply = new ReplyVO(); //command object
		try {
			BeanUtils.populate(reply, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new CommonException(e);
		}
		String view = null;
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(reply, errors);
		if (valid) { //검증
			IReplyService service = new ReplyServiceImpl();
			ServiceResult result = service.createReply(reply);
			switch (result) {
			case OK:
				view = "redirect:/reply/replyList.do?bo_no="+reply.getBo_no();
				break;
			case FAILED:
				errors.put("errors", "server");
				errors.put("message", "서버오류");
				break;
			}
			
		}else {
			errors.put("errors", "valid");
			errors.put("message", "검증실패");
		}
		
		ObjectMapper mapper = new ObjectMapper(); //마샬링하는 객체를 불러오는거
		try(
			PrintWriter out = resp.getWriter(); //보내주는거
		){
			mapper.writeValue(out, errors); // 먀샬링하면서 보내는주는거
		}
		return view;
	}
	
	
	private boolean validate(ReplyVO reply ,Map<String, String> errors){
		boolean valid = true;
		if (StringUtils.isBlank(reply.getRep_writer())) {
			valid = false;
			errors.put("rep_writer", "작성자 누락");
		}
		if (StringUtils.isBlank(reply.getRep_pass())) {
			valid = false;
			errors.put("rep_pass", "비밀번호 누락");
		}
		if (reply.getRep_content() != null && reply.getRep_content().length() > 100) {
			valid = false;
			errors.put("rep_content", "내용의 길이는 100글자 미안");
		}
		return valid;
	}

}
