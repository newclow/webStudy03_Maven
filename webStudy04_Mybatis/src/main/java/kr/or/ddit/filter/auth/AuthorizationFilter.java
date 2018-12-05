package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;

/**
 * @author 서신원
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      서신원        보호 자원에 접근하고 있는 유저에 대해 유저에 부여된 권한과 자원에 설저오딘 권한 매칭 여부 확인 
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public class AuthorizationFilter implements Filter {
	//보호자원 : 권한들
	private Map<String, String[]> securedResources;
	private String securedResourceInfo;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		securedResources = (Map<String, String[]>) request.getServletContext().getAttribute(AuthenticationFilter.SECUREDRESOURCEATTR);
		String uri = req.getRequestURI();
		int cpLength = req.getContextPath().length();
		uri = uri.substring(cpLength).split(";")[0];
		boolean pass = true;
		if (securedResources.containsKey(uri)) {
			MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
			String mem_auth = authMember.getMem_auth();
			String[] resAuthes = securedResources.get(uri);
			if(Arrays.binarySearch(resAuthes, mem_auth) < 0) {
				pass = false;
			}
		}
		if (pass) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	@Override
	public void destroy() {

	}

}
