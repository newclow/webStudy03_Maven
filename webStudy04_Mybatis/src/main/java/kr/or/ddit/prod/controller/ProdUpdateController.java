package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.prod.dao.IOtherDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

public class ProdUpdateController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String method = req.getMethod();
		IOtherDAO dao = new OtherDAOImpl();
		IProdService service = new ProdServiceImpl();
		List<Map<String, Object>> lprodList = dao.selectLprodList();
		req.setAttribute("lprodList", lprodList);
		if ("get".equalsIgnoreCase(method)) {
			String prod_id = req.getParameter("what");
			if (StringUtils.isBlank(prod_id)) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}
			ProdVO prod = service.retrieveProd(prod_id);
			req.setAttribute("prod", prod);
			return "prod/prodForm";
		}else if ("post".equalsIgnoreCase(method)) {
			ProdVO prod = new ProdVO();
			req.setAttribute("prod", prod);
			try {
				BeanUtils.populate(prod, req.getParameterMap());
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new CommonException(e);
			}
			String view = null;
			String message = null;
			Map<String, String> errors = new LinkedHashMap<>();
			req.setAttribute("errors", errors);
			boolean valid = validate(prod, errors);
			if (valid) {
				if (req instanceof FileUploadRequestWrapper) {
					String prodImagesUrl = "/prodImages";
					String prodImagesPath = req.getServletContext().getRealPath(prodImagesUrl);
					File prodImagesFolder = new File(prodImagesPath);
					if (!prodImagesFolder.exists()) {
						prodImagesFolder.mkdirs();
					}
					FileItem fileItem = ((FileUploadRequestWrapper) req).getFileItem("prod_image");
					if (fileItem != null) {
						String savename = UUID.randomUUID().toString();
						File saveFile = new File(prodImagesFolder, savename);
						try(
								InputStream in = fileItem.getInputStream();
								){
							FileUtils.copyInputStreamToFile(in, saveFile);
						}
						prod.setProd_img(savename);
					}
				}
				ServiceResult result = service.modifyProd(prod);
				switch (result) {
				case OK:
					view = "redirect:/prod/prodList.do";
					break;
				case FAILED:
					view = "prod/prodForm";
					message = "상품 수정이 실패했습니다.";
					break;
				}
				req.setAttribute("message", message);
			}else {
				view = "prod/prodForm";
			}
			return view;
		}else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(prod.getProd_id())) {
			valid = false;
			errors.put("prod_id", "상품아이디 누락");
		}
		if (StringUtils.isBlank(prod.getProd_name())) {
			valid = false;
			errors.put("prod_name", "상품명 누락");
		}
//		if (StringUtils.isBlank(prod.getProd_lgu())) {
//			valid = false;
//			errors.put("prod_lgu", "분류명 누락");
//		}
//		if (StringUtils.isBlank(prod.getProd_buyer())) {
//			valid = false;
//			errors.put("prod_buyer", "거래처정보 누락");
//		}
		if (prod.getProd_cost() == null) {
			valid = false;
			errors.put("prod_buyer", "구매가 누락");
		}
		if (prod.getProd_price() == null) {
			valid = false;
			errors.put("prod_price", "판매가 누락");
		}
		if (prod.getProd_sale() == null) {
			valid = false;
			errors.put("prod_sale", "할인가 누락");
		}
		if (StringUtils.isBlank(prod.getProd_outline())) {
			valid = false;
			errors.put("prod_outline", "상품개요 누락");
		}
		if (StringUtils.isBlank(prod.getProd_detail())) {
			valid = false;
			errors.put("prod_detail", "상세정보 누락");
		}
		if (prod.getProd_totalstock() ==null) {
			valid = false;
			errors.put("prod_totalstock", "재고량 누락");
		}
//		if (StringUtils.isNotBlank(prod.getProd_insdate())) {
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			// formatting : 특정 타입의 데이터를 일정 형식의 문자열로 변환.
//			// parsing : 일정한 형식의 문자열을 특정 타입의 데이터로 변환.
//			try{
//				formatter.parse(prod.getProd_insdate());
//			}catch(ParseException e){
//				valid = false;
//				errors.put("prod_insdate", "입고일 누락");
//			}
//		}
		if (prod.getProd_properstock() == null) {
			valid = false;
			errors.put("prod_properstock", "적정재고 누락");
		}
		if (StringUtils.isBlank(prod.getProd_size())) {
			valid = false;
			errors.put("prod_size", "상품크기 누락");
		}
		if (StringUtils.isBlank(prod.getProd_color())) {
			valid = false;
			errors.put("prod_color", "상품색상 누락");
		}
		if (StringUtils.isBlank(prod.getProd_delivery())) {
			valid = false;
			errors.put("prod_delivery", "배송방법 누락");
		}
		if (StringUtils.isBlank(prod.getProd_unit())) {
			valid = false;
			errors.put("prod_unit", "단위 누락");
		}
		if (prod.getProd_qtyin() == null) {
			valid = false;
			errors.put("prod_qtyin", "입고량 누락");
		}
		if (prod.getProd_qtysale() == null) {
			valid = false;
			errors.put("prod_qtysale", "판매량 누락");
		}
//		if (prod.getProd_mileage() == null) {
//			valid = false;
//			errors.put("prod_mileage", "마일리지 누락");
//		}
		return valid;
	}
	

}
