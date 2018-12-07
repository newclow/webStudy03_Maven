package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BoardVO;

public class BoardInsertController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String method = req.getMethod();
		if ("get".equalsIgnoreCase(method)) {
			return "board/boardInsert";
		} else if ("post".equalsIgnoreCase(method)) {
			BoardVO board = new BoardVO();
			req.setAttribute("board", board);
			try {
				BeanUtils.populate(board, req.getParameterMap());
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			//validator적용 framework
			GeneralValidator validator = new GeneralValidator();
			Map<String, List<CharSequence>> errors = new LinkedHashMap<>();
			boolean valid = validator.validate(board, errors, InsertGroup.class);
			//적용끝
			String view = null;
			if (valid) {
				//첨부파일 넣기시작
				if (req instanceof FileUploadRequestWrapper) { //랩퍼인지 확인
					List<FileItem> fileItems = ((FileUploadRequestWrapper) req).getFileItems("bo_file");
					board.setItemList(fileItems);
				}
				//첨부파일끝
				IBoardService service = new BoardServiceImpl();
				ServiceResult result = service.createBoard(board);
				switch (result) {
				case OK:
					view = "redirect:/board/boardList.do";
					break;
				case FAILED:
					view = "board/boardInsert";
					req.setAttribute("message", "서버오류");
					break;
				}
			}else {
				view = "board/boardInsert";
			}
			return view;
		}else {
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
}
