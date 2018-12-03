package kr.or.ddit.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;
import kr.or.ddit.filter.wrapper.TestRequestWrapper;

public class FileUploadCheckFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 필터 초기화");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//요청필터링
		String contentType = request.getContentType();
		if (contentType != null && contentType.startsWith("multipart/")) {
			HttpServletRequest req = (HttpServletRequest)request;
			int sizeThreshold = 10240;
			File repository = new File("d:/temp");
			FileUploadRequestWrapper wrapper = new FileUploadRequestWrapper(req, sizeThreshold, repository);
			logger.info("{}에서 multipart request가 wrapper로 변경됨", getClass().getSimpleName(), wrapper.getClass().getSimpleName());
			chain.doFilter(wrapper, response);
		} else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		logger.info("{} 필터 소멸");
	}

}
