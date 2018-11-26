package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

/**
 * @author 서신원
 * @since 2018. 11. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 11. 26.      서신원        상품관리를 위한 Persistence Layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface IProdDAO {
	/**
	 * 신규상품 등록
	 * @param prod 등록할 상품의 정보를 가진 vo
	 * @return 신규등록된 상품코드
	 */
	public String insertProd(ProdVO prod);
	/**
	 * 상품정보 상세조회
	 * @param prod_id 조회할 상품코드
	 * @return 존재하지 않으면 null반환
	 */
	public ProdVO selectProd(String prod_id);
	/**
	 * 페이징 처리를 위해 전체 상품 수 조회
	 * @param pagingVO
	 * @return
	 */
	public long selectTotalRecord(PagingInfoVO<ProdVO> pagingVO);
	/**
	 * 페이징 처리된 상품 목록 조회
	 * @param pagingVO
	 * @return 존재하지 않으면, size()==0
	 */
	public List<ProdVO>selectProdList(PagingInfoVO<ProdVO> pagingVO);
	/**
	 * 상품 정보 수정
	 * @param prod 수정할 정보를 가진 VO
	 * @return 수정 성공여부 (>0)
	 */
	public int updateProd(ProdVO prod);
}
