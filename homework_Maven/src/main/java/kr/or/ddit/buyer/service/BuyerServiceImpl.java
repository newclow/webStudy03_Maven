package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.CommonException;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.vo.BuyerVO;

public class BuyerServiceImpl implements IBuyerService {
	
	IBuyerDAO buyerDAO = new BuyerDAOImpl();

	@Override
	public List<BuyerVO> retrieveBuyerList() {
		return buyerDAO.selectBuyerList();
	}

	@Override
	public BuyerVO retrieveBuyer(String buy_id) {
		BuyerVO buyer = buyerDAO.selectBuyer(buy_id);
		if (buyer == null) {
			throw new CommonException(buy_id+"에 해당하는 회원은 없습니다.");
		}
		return buyer;
	}

}
