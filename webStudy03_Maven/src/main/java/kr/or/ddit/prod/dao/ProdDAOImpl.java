package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.ibatis.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {
	SqlMapClient sql = CustomSqlMapClientBuilder.getSqlMapClient();

	@Override
	public String insertProd(ProdVO prod) {
		return null;
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		try {
			return (ProdVO) sql.queryForObject("Prod.selectProd", prod_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long selectTotalRecord(PagingInfoVO<ProdVO> pagingVO) {
		try {
			return (Long) sql.queryForObject("Prod.selectTotalRecord", pagingVO); //래퍼타입으로 다운캐스팅을 하기위해 long이아니라 Long이다.
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ProdVO> selectProdList(PagingInfoVO<ProdVO> pagingVO) {
		try {
			return sql.queryForList("Prod.selectProdList", pagingVO);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		return 0;
	}

}
