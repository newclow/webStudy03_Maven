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

import kr.or.ddit.member.controller.MemberListController;
import kr.or.ddit.member.controller.MemberUpdateController;
import kr.or.ddit.member.controller.MemberViewController;
import kr.or.ddit.member.controller.MyPageController;

public class FrontController extends HttpServlet {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, ICommandHandler> handlerMap; //handlerMap -> key = uri/ value = new 모든Controller()
	private String mappingInfo; 
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		//ServletConfig -> servlet confignation 환경설정을 가져온다.
		
		//맵인스턴스화
		handlerMap = new HashMap<>();
		
		//ServletConfig -> web.xml
		
		//mappingInfo -> kr.or.ddit.urihandlermapping을 가져옴
		mappingInfo = config.getInitParameter("mappingInfo");
		
		//kr.or.ddit.urihandlermapping을 번들로 읽어옴
		//파일 내용 : uri : quarified name
		ResourceBundle bundle = ResourceBundle.getBundle(mappingInfo);
		
		//map과 구성이 비슷해서 같은형식으로 뺀다.
		//key = uri 즉 key값만 set로 가져옴
		Set<String> keySet =bundle.keySet();
		
		//key값을 가지고 맵에 세팅하기위해 for문을 돌림
		for(String uri : keySet) {
			
			//번들의 value값을 가져옴
			String qualifiedName = bundle.getString(uri);
			try {
				//qualifiedName으로 class를 가져옴 => kr.or.ddit.buyer.controller.BuyerListController -> buyerListController
				Class<ICommandHandler> handlerClz = (Class<ICommandHandler>) Class.forName(qualifiedName.trim());
				
				//인스턴스화진행
				//인스턴스화한 controller는 ICommandHandler타입으로 다받을수있음 상위인터페이스이므로 
				//ex) map = new hashmap
				ICommandHandler handler = handlerClz.newInstance();
				
				// key = uri(경로) / 인스턴스화 컨트롤러 객체 = handler 
				handlerMap.put(uri.trim(), handler);
				
				//로그찍어주는것
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
		String uri = req.getRequestURI(); //uri를 따온다
//		/member/memberList.do
		int cpLength = req.getContextPath().length(); //자를부분을 가져오고
		uri = uri.substring(cpLength).split(";")[0]; //전체경로와 자를부분을 잘라 나머지경로를 준다. /member/memberList.do 이런식으로
		System.out.println(uri);
		
		//key값(/member/memberList.do)을 가지고 handlerMap에서 value(각 컨트롤러 인스턴스)를 뽑음
		ICommandHandler handler = handlerMap.get(uri);
		
		//검증
		if(handler==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, 
					"해당 서비스는 제공하지 않습니다.");
			return;
		}
//		7. 뷰선택
//		8. 뷰로 이동
		
		//ICommandHandler 객체의 process메소드에서 반환된 경로정보를 view변수에 저장
		String view = handler.process(req, resp);
		
		//redirect인지 rd인지 골라서 이동
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

















