package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FrontController extends HttpServlet {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, ICommandHandler> handlerMap;
	private String mappingInfo;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		handlerMap = new HashMap<>();
		mappingInfo = config.getInitParameter("mappingInfo");
		ResourceBundle bundle = 
				ResourceBundle.getBundle(mappingInfo);
		Set<String> keySet =bundle.keySet();
		for(String uri : keySet) {
			String qualifiedName = bundle.getString(uri);
			try {
				Class<ICommandHandler> handlerClz = 
						(Class<ICommandHandler>) Class.forName(qualifiedName.trim());
				ICommandHandler handler = handlerClz.newInstance();
				handlerMap.put(uri.trim(), handler);
				logger.info(" {} 대한 핸들러 {} 등록", uri, qualifiedName);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				logger.error("{} 에 대한 핸들러 : {} 에서 문제발생 {}\n", 
								uri, qualifiedName, e.getMessage());
				continue;
			}
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();
//		/member/memberList.do
		int cpLength = req.getContextPath().length();
		uri = uri.substring(cpLength).split(";")[0];
		System.out.println(uri);
		
		ICommandHandler handler = handlerMap.get(uri);
		
		if(handler==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, 
					"해당 서비스는 제공하지 않습니다.");
			return;
		}
//		7. 뷰선택
//		8. 뷰로 이동
		String view = handler.process(req, resp);
		String prefix = "/WEB-INF/views/";
		String suffix = ".jsp";
		if(view!=null) {
			boolean redirect = view.startsWith("redirect:");
			if(redirect) {
				view = view.substring("redirect:".length());
				resp.sendRedirect(req.getContextPath()+view);
			}else {
				RequestDispatcher rd = req.getRequestDispatcher(prefix+view+suffix);
				rd.forward(req, resp);
			}
		}else {
			if(!resp.isCommitted()) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
							"커맨드 핸들러에서 뷰가 선택되지 않았습니다.");
			}
		}
	}
	
}

















