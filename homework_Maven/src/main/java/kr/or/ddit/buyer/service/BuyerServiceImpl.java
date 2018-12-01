package kr.or.ddit.buyer.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

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
		ServiceResult result = null;
		if (buyerDAO.selectBuyer(buyer.getBuyer_id()) == null) {
			String str = buyerDAO.insertBuyer(buyer);
			if (StringUtils.isNotBlank(str)) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(PagingInfoVO<BuyerVO> pagingVO) {
		return buyerDAO.selectBuyerList(pagingVO);
	}

	@Override
	public long retrieveMemberCount(PagingInfoVO<BuyerVO> pagingVO) {
		return buyerDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public BuyerVO retrieveBuyer(String buy_id) {
		return buyerDAO.selectBuyer(buy_id);
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
