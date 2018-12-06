package kr.or.ddit.buyer.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.BuyerVO;

public class BuyerUpdateController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String method = req.getMethod();
		IBuyerService service = new BuyerServiceImpl();
		if ("get".equalsIgnoreCase(method)) {
			String buyer_id = req.getParameter("what");
			if (StringUtils.isBlank(buyer_id)) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}
			BuyerVO buyer = service.retrieveBuyer(buyer_id);
			req.setAttribute("buyer", buyer);
			return "buyer/buyerView";
		}else if ("post".equalsIgnoreCase(method)) {
			BuyerVO buyer = new BuyerVO();
			req.setAttribute("buyer", buyer);
			try {
				BeanUtils.populate(buyer, req.getParameterMap());
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new CommonException(e);
			}
			String view = null;
			String message = null;
			Map<String, String> errors = new LinkedHashMap<>();
			req.setAttribute("errors", errors);
			boolean valid = validate(buyer, errors);
			if (valid) {
				ServiceResult result = service.modifiedBuyer(buyer);
				switch (result) {
				case OK:
					view = "redirect:/buyer/buyerList.do";
					break;
				case FAILED:
					view = "buyer/buyerView";
					message = "거래처 수정에 실패했습니다.";
					break;
				}
				req.setAttribute("message", message);
			}else {
				view = "buyer/buyerView";
			}
			return view;
		}else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	
	
	private boolean validate(BuyerVO buyer, Map<String, String> errors){
		boolean valid = true;
		if (StringUtils.isBlank(buyer.getBuyer_name())) {
			valid = false;
			errors.put("buyer_name", "거래처명 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_lgu())) {
			valid = false;
			errors.put("buyer_lgu", "거래품목 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_comtel())) {
			valid = false;
			errors.put("buyer_comtel", "거래처번호 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_fax())) {
			valid = false;
			errors.put("buyer_fax", "팩스번호 누락");
		}
		if (StringUtils.isBlank(buyer.getBuyer_mail())) {
			valid = false;
			errors.put("buyer_mail", "메일 누락");
		}
		return valid;
	}
	
	

	

}
