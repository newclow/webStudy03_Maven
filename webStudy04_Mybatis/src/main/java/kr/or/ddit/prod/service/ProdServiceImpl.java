package kr.or.ddit.prod.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	
	IProdDAO prodDAO = new ProdDAOImpl();

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
			int rowCnt = prodDAO.insertProd(prod);
			if (rowCnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
			return result;
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prod = prodDAO.selectProd(prod_id);
		if (prod == null) {
			throw new CommonException(prod_id + "상품이 없습니다.");
		}
		return prod;
	}

	@Override
	public long retrieveProdCount(PagingInfoVO<ProdVO> pagingVO) {
		return prodDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingInfoVO<ProdVO> pagingVO) {
		return prodDAO.selectProdList(pagingVO);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ServiceResult result = null;
		retrieveProd(prod.getProd_id()); //중요 위에서 가져옴
		int rowCnt = prodDAO.updateProd(prod);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public MemberVO retrieveCustomers() {
		return prodDAO.selectCustomers();
	}

}
