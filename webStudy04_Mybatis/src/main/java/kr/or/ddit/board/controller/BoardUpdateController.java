package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IPdsDAO;
import kr.or.ddit.board.dao.PdsDAOImpl;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.BoardVO;

public class BoardUpdateController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		1. v.l: "board/boardInsert"
//		2. 기존 첨부파일이 있다면 삭제 가능하도록
//		3. 새로운 첨부파일있다면 업로드
		String method = req.getMethod();
		if ("get".equalsIgnoreCase(method)) {
			String what = req.getParameter("what");
			if (StringUtils.isNumeric(what)) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}
			long bo_no = Long.parseLong(what);
			IBoardService service = new BoardServiceImpl();
			BoardVO board = service.retriveBoard(bo_no);
			req.setAttribute("board", board);
			return "board/boardInsert";
		} else if ("post".equalsIgnoreCase(method)) {
			BoardVO board = new BoardVO();	//command Object
			req.setAttribute("board", board);
			try {
				BeanUtils.populate(board, req.getParameterMap());
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			
			GeneralValidator validator = new GeneralValidator();
			Map<String, List<CharSequence>> errors = new LinkedHashMap<>();
			boolean valid = validator.validate(board, errors, UpdateGroup.class);
			
			String view = null;
			if (valid) {
				if (req instanceof FileUploadRequestWrapper) {
					List<FileItem> fileItems = ((FileUploadRequestWrapper) req).getFileItems("bo_file");
					board.setItemList(fileItems);
				}
				IBoardService service = new BoardServiceImpl();
				ServiceResult result = service.modifyBoard(board);
				switch (result) {
				case OK:
					//Post-redirect-Get : PRG패턴
					view = "redirect:/board/boardView.do?what="+board.getBo_no();
					break;
				case FAILED:
					view = "board/boardInsert";
					req.setAttribute("message", "서버오류로 실패");
					break;
				case INVALIDPASSWORD:
					view = "board/boardInsert";
					req.setAttribute("message", "비밀번호가 틀림");
					break;
				}
			}else {
				view = "board/boardInsert";
			}
			return view;
		}else {
			resp.sendError(405);
			return null;
		}
		
	}

}
