package kr.or.ddit.web;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.utils.RFC2253Parser;

/**
 * 
 * Http 프로토콜의 request packageing 규칙
 * Request Line	: URL, Http Method, Protocol/version
 *  Http Method(Request Method) : 현재 요청의 목적/의도/수단/방식
 *  GET : 조회 (R) 
 *  	-> Body가 존재하지 않기때문에 전송할 최소한의 데이터를 Line의 URL에 덧붙여서 보냄(쿼리스트링)
 *  	-> ex) http://localhost/webStudy01/gugudan.do?minDan=3&maxDan=6
 *  	         주소 : 포트넘버/세부경로?QueryString
 *  				-> QueryString : segment1&segment2...
 *  				-> segment : param_name=param_value
 *  POST : (신규)전송 및 등록(C)
 *  PUT : 전송 및 수정(U)
 *  DELETE : 삭제 (D)
 *  TRACE : 디버깅
 *  OPTION : ex) http method의 지원여부 확인
 *  HEAD : 응답데이터를 body가 없는 형태로 받는다.
 * Request Header : 클라이언트와 요청에관한 부가정보(metadata)
 * 		->헤더명 : 헤더값 ...
 * Request Body(Contents, Message body) : method가 POST일경우 형성.
 *		: 클라이언트가 서버로 전송할 데이터들...(요청 파라미터)
 *
 *
 * *** client의 요청정보를 서버에서 확인하는 방법
 * HttpServletRequest의 활용
 * 1) 파라미터를 확보 : 기본적으로 모든파라미터는 문자열의 형태로 전송됨.
 * 		String getParameter(String name) : 하나의 파라미터 명으로 하나의 값이 전송.
 *      String[] getParameterValues(String name) : 동일한 파라미터명으로 여러개의 값이 전송.
 *      Enumeration getParameterNames() :  
 *      ** Map<String, String[]> getParameterMap()
 *      ** 요청파라미터에 특수문자가 포함된 경우,
 *      IETF에서 정의한 RFC2396규약에 따라
 *      모든 특수문자는 URLEncoding(Percent Encoding) 방식으로 인코딩되어 전송.
 *      인코딩한 특수문자 확보
 *      GET : 서버의 설정을 통한 디코딩 설성
 *      		-> server.xml -> URIEncoding : 특정 인코딩으로 고정
 *      			->useBodyEncodingForURI="true" 설정후 req.setCharacterEncoding(encoding) 사용가능
 *      POST : req.setCharacterEncoding(encoding) : 파라미터를 꺼내기전에 설정함.
 * 2) 요청 헤더의 확보     
 * 		String getHeader(String name) : 모든 헤더는 문자열로 전송
 * 		int getIntHeader(String name)
 * 		long getDateHeader(String name) : 날짜 확인시, new Date(long)
 * 		Enumeration getHeaders(String name)
 * 		Enumeration getHeaderNames()
 * 3) 기타 요청과 관련된 메소드들..
 * 		service 메소드에 기술됨 (참고)
 * 
 */


@WebServlet({ "/HttpRequestDescription", "/httpReq.do" })
public class HttpRequestDescription extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		//객체 초기화 직후 호출
		
	}

	public void destroy() {
		//객체 소멸 전 호출
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getCharacterEncoding());
		System.out.println(request.getContentType());
		System.out.println(request.getContentLength());
		System.out.println(request.getContextPath());
		System.out.println(request.getLocalAddr());
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemotePort());
		System.out.println(request.getLocalPort());
		System.out.println(request.getMethod());
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		System.out.println(request.getProtocol());
		
		//매 요청시마다 호출
		//-> Http method와 상관없이 실행될 코드를 넣는다.
		super.service(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
