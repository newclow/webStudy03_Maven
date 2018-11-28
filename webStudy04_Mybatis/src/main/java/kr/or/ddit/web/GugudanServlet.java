package kr.or.ddit.web;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import javax.servlet.annotation.*;

@WebServlet(value="/gugudan.do")
public class GugudanServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String minDanStr = req.getParameter("minDan");
		String maxDanStr = req.getParameter("maxDan");

		int min = 2;
		int max = 9;
		if(minDanStr != null && minDanStr.matches("\\d")) {
			min = Integer.parseInt(minDanStr);
		}
		
		if(maxDanStr != null && maxDanStr.matches("\\d")) {
			max = Integer.parseInt(maxDanStr);
		}
		
		// 2~9단 까지 구구단을 table 태그를 이용하여 출력
		//단 한행에 한단씩 출력
		//테스트 시에 /gugudan.do요청을 사용 
		
		InputStream in = this.getClass().getResourceAsStream("gugudan.205");
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		StringBuffer html = new StringBuffer();
		String temp = null;
		while((temp = br.readLine()) != null){
			html.append(temp);
		}	
		
		StringBuffer sb = new StringBuffer();
		for(int i=min; i<=max; i++){
			sb.append("<tr>");
			for(int j=1; j<=9; j++){
				//sb.append(String.valueOf(i)+"*"+String.valueOf(j)
				//		+"="+String.valueOf(i*j)
				//		+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append(String.format("<td>%d * %d = %d</td>", i, j, i*j));
			}
			//sb.append("<br>");
			sb.append("</tr>");
		}
		int start = html.indexOf("@gugudan");	
		int end = start + "@gugudan".length();	
		String replaceText = sb.toString();
		
		html.replace(start,end,replaceText);
		
		PrintWriter out = resp.getWriter();
		out.println(html);
//		out.close();
	}
	

}