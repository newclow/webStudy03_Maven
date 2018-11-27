package kr.or.ddit.prod.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	
	IProdDAO prodDAO = new ProdDAOImpl();

	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
			String str = prodDAO.insertProd(prod);
			if (StringUtils.isNotBlank(str)) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
			return result;
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		return prodDAO.selectProd(prod_id);
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
		return null;
	}

}
