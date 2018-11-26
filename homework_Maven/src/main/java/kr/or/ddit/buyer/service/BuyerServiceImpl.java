package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerServiceImpl implements IBuyerService {
	
	IBuyerDAO buyerDAO = new BuyerDAOImpl();

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		return null;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingInfoVO<BuyerVO> pagingVO) {
		return null;
	}

	@Override
	public long retrieveMemberCount(PagingInfoVO<BuyerVO> pagingVO) {
		return 0;
	}

	@Override
	public BuyerVO retrieveBuyer(String buy_id) {
		return null;
	}

	@Override
	public ServiceResult modifiedBuyer(BuyerVO buyer) {
		return null;
	}

	@Override
	public ServiceResult removeBuyer(BuyerVO buyer) {
		return null;
	}


}
