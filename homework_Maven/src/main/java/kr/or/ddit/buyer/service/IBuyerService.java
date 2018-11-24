package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;

/**
 * @author 서신원
 * @since 2018. 11. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 11. 24.      서신원        
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface IBuyerService {
	
	/**
	 * 거래처 목록 조회
	 * @return 존재하지 않을시 size == 0
	 */
	public List<BuyerVO> retrieveBuyerList();
	
	/**
	 * 거래처 조회
	 * @param buy_id
	 * @return 존재하지않을시 null
	 */
	public BuyerVO retrieveBuyer(String buy_id);
}
