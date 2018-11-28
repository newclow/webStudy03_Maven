package kr.or.ddit.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.web.calculate.Mime;
import kr.or.ddit.web.calculate.Operator;

public class CalculateServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext application = getServletContext();
		String contentFolder = application.getInitParameter("contentFolder");
		File folder = new File(contentFolder);
		application.setAttribute("contentFolder", folder);
		System.out.println(getClass().getSimpleName()+" 초기화");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 확보(입력태그의 name속성 값으로 이름 결정)

		String operatorStr = req.getParameter("operator");
		String num1 = req.getParameter("leftOp");
		String num2 = req.getParameter("rightOp");
		
		int realnum1 = 0;
		int realnum2 = 0;
		// 검증
		boolean valid = true;
		if(num1 == null || !num1.matches("\\d+")
				|| num2 == null || !num2.matches("\\d{1,6}")) {
			valid = false;
		}
		
		Operator operator = null;
		
		//enum 활용
		try {
			operator = Operator.valueOf(operatorStr.toUpperCase());
		} catch (Exception e) {
			//e.printStackTrace();
			valid = false;
		}
		
		
		if(operatorStr == null || (!operatorStr.equals("+") && !operatorStr.equals("-") 
				&& !operatorStr.equals("/") && !operatorStr.equals("*"))) {
			
		}
		
		if(!valid) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
/*		if(num1 == null || num1.trim().length() == 0) {
			resp.sendError(400);
			return;
		}
		
		if(num2 == null || num2.trim().length() == 0) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		if(num1.matches("[0-9]{1,9}")) {
			realnum1 = Integer.parseInt(num1);
		}
		
		if(num2.matches("[0-9]{1,9}")) {
			realnum2 = Integer.parseInt(num2);
		}*/
		// 통과
		//	-> 연산자에 따라 연산 수행
		// 	    일반 텍스트의 형태로 연산 결과를 제공. 
		// 연산결과 : 2 * 3 = 6
		
		realnum1 = Integer.parseInt(num1);
		realnum2 = Integer.parseInt(num2);
		String pattern = "%d %s %d = %d";
		String result = String.format(pattern, realnum1, operator.getSign(), 
								realnum2, operator.operate(realnum1, realnum2));
		
		String accept = req.getHeader("Accept");
		
		System.out.println(accept);
		String mime = null;
		
		//enum 적용하기
		Mime eMime = null;
		
		int start = accept.indexOf("/")+1;
		int end = accept.indexOf(",");
		String str = accept.substring(start, end);
		
		try {
			System.out.println(str.toUpperCase());
			eMime = Mime.valueOf(str.toUpperCase());
		}catch (Exception e) {
			
		}
		
		System.out.println("contentType = "+eMime.getContentType());
		resp.setContentType(eMime.getContentType());
		PrintWriter out = resp.getWriter();
		
		System.out.println("result = "+eMime.getRealMimeType(result));
		out.println(eMime.getRealMimeType(result));
		out.close();
	}
}
