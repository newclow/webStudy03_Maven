package kr.or.ddit.buyer.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BuyerVO;

public interface IOtherDAO {
	/**
	 * 상품카테고리 조회
	 * @return key : 상품코드, value : 상품명
	 */
	public Map<String, String> selectLprodList();
	
}
