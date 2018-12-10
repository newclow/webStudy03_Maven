package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.board.dao.IPdsDAO;
import kr.or.ddit.board.dao.PdsDAOImpl;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.PdsVO;

public class DownloadController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String pds_no = req.getParameter("what");
		if (StringUtils.isBlank(pds_no)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		IBoardService service = new BoardServiceImpl();
		PdsVO pds = service.downloadPds(Long.parseLong(pds_no));
		
		String fileName = pds.getPds_filename();
		
//		File fileToDownload = new File(filename);
//	    resp.setContentLength((int) fileToDownload.length());
//	    FileInputStream fis = new FileInputStream(fileToDownload);
		
		resp.setContentType("application/octet-stream"); //응답 타입을 8비트로 지정
		String agent = req.getHeader("User-Agent"); //header에 있는 유저에이전트를 가져옴
		if(StringUtils.containsIgnoreCase(agent, "msie") 
				|| StringUtils.containsIgnoreCase(agent, "trident")) { //
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); //fileName의 바이트의 인코딩지정
		resp.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");//파일이름에 관한 것, 띄어쓰기도 포함
		resp.setHeader("Content-Length", pds.getPds_size()+""); //파일 사이즈를string 변환하여 지정
		File saveFolder = new File("d:/boardFiles"); //저장파일 위치
		File downloadFile = new File(saveFolder, pds.getPds_savename());//어디에 어떤이름으로 파일을만듬
		
		try(
				OutputStream out = resp.getOutputStream(); //output하는 객체를 불러옴
		){
			FileUtils.copyFile(downloadFile, out); //다운로드한 파일을 카피하면서 보내줌
		}
		return null;
	}

}
