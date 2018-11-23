package kr.or.ddit.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;

@WebServlet(value="/imgService")
public class ImageServiceServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터 확보 : 파라미터명(image)
		// 이미지 스트리밍...
		
		String param = req.getParameter("imageSel");
        
		if(param == null || param.trim().length() == 0) {
			resp.sendError(400);
			return;
		}
		
		File folder = (File)getServletContext().getAttribute("contentFolder");
		File imgFile = new File(folder, param);
		
		if(!imgFile.exists()) {
			resp.sendError(404);
			return;
		}
		// 쿠키값 : A,B
		String imgCookieValue = new CookieUtil(req).getCookieValue("imageCookie");
		String[] cookieValues = null;
		ObjectMapper mapper = new ObjectMapper();
		if(StringUtils.isBlank(imgCookieValue)) {
			cookieValues = new String[] { imgFile.getName() };
		}else {
			String[] cValues = mapper.readValue(imgCookieValue, String[].class);
			cookieValues = new String[cValues.length+1];
			System.arraycopy(cValues, 0, cookieValues, 0, cValues.length);
			cookieValues[cookieValues.length-1] = imgFile.getName();
		}
//		imgCookieValue = Arrays.toString(cookieValues);
//		imgCookieValue = imgCookieValue.replaceAll("[\\[\\]\\s]", "");
		// marshalling
		
		imgCookieValue = mapper.writeValueAsString(cookieValues);
		System.out.println(imgCookieValue);
		
		Cookie imageCookie = CookieUtil.createCookie(
							"imageCookie", imgCookieValue, 
							req.getContextPath(), TextType.PATH, 60*60*24*3);
		resp.addCookie(imageCookie);
		
		//검증
		ServletContext context = req.getServletContext();
		resp.setContentType(context.getMimeType(param));
		
		FileInputStream fis = new FileInputStream(imgFile);
		OutputStream out = resp.getOutputStream();
		
		byte[] buffer = new byte[1024];
		
		int pointer = -1;
		while((pointer = fis.read(buffer)) != -1){
			out.write(buffer, 0, pointer);
		}
		
		fis.close();
		out.close();
	}
}
