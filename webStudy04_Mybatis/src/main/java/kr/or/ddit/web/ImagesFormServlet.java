package kr.or.ddit.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.utils.CookieUtil;

public class ImagesFormServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");

		ServletContext context =  req.getServletContext();
		File folder = (File)context.getAttribute("contentFolder");
		
		String[] filenames = folder.list((File dir, String name) -> {
			String mime = context.getMimeType(name);
		    return mime.startsWith("image/");
		});
		
		
		
		StringBuffer sb = new StringBuffer();
		//String pattern = "<option value='%s'>%s</option>";
		for(int i = 0; i < filenames.length; i++) {
			sb.append("<option value='"+filenames[i]+"'>"+filenames[i]+"</option>");
		}
		
//		int start = html.indexOf("@option");	
//		int end = start + "@option".length();	
//		
		
//		String replaceText = sb.toString();
//		html.replace(start,end,replaceText);
		
		req.setAttribute("optionsAttr", sb.toString());
		
		// JSON 형태 기록.
		String imgCookieValue = new CookieUtil(req).getCookieValue("imageCookie");
		StringBuffer imgTags = new StringBuffer();
		if(imgCookieValue!=null) {
			// unmarshalling
			ObjectMapper mapper = new ObjectMapper();
			String[] imgNames = mapper.readValue(imgCookieValue, String[].class);
			
			String imgPattern = "<img src='imgService?imageSel=%s' />";
			for(String imgName : imgNames) {
				imgTags.append(
					String.format(imgPattern, imgName)	
					);
			}
		}
		req.setAttribute("imgTags", imgTags);
//		start = html.indexOf("@images");	
//		end = start + "@images".length();
//		html.replace(start, end, imgTags.toString());
		String view = "/WEB-INF/views/imageView.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.include(req, resp);
		
//		PrintWriter out = resp.getWriter();
//		out.println(html);
//		out.close();
		
	}
}








