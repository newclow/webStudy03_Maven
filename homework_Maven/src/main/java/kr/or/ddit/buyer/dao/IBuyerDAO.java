package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author 서신원
 * @since 2018. 11. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 11. 21.      서신원        거래처관리를 위한 Persistence Layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface IBuyerDAO {
	
	/**
	 * 거래처 추가
	 * @param buyer
	 * @return 성공하면 (>0)
	 */
	public String insertBuyer(BuyerVO buyer);
	
	/**
	 * 페이징 처리된 거래처 목록 조회
	 * @param buyerVO
	 * @return 존재하지 않으면 size == 0
	 */
	public List<BuyerVO> selectBuyerList(PagingInfoVO<BuyerVO> buyerVO);
	
	/**
	 * 페이징 처리를 위한 전체 상품수 조회
	 * @param buyerVO
	 * @return 
	 */
	public long selectTotalRecord(PagingInfoVO<BuyerVO> buyerVO);
	
	/**
	 * 거래처 조회
	 * @param buy_id
	 * @return 존재하지 않으면 null 반환
	 */
	public BuyerVO selectBuyer(String buy_id);
	
	/**
	 * 거래처 정보를 수정
	 * @param buy_id
	 * @return 성공하면 (>0)
	 */
	public int updateBuyer(String buy_id);
	
	/**
	 * 거래처를 중단(그러나 삭제는 하지 않는다 내역에서 거래를 중단했다고 표시(즉 update))
	 * @param buy_id
	 * @return 성공하면 (>0)
	 */
	public int deleteBuyer(String buy_id);
}
