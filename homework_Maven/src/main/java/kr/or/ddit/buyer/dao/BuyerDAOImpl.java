package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.ibstis.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.BuyerVO;

public class BuyerDAOImpl implements IBuyerDAO {
	
	SqlMapClient sql = CustomSqlMapClientBuilder.getSqlMapClient();

	@Override
	public List<BuyerVO> selectBuyerList() {
		try {
			return sql.queryForList("Buyer.selectBuyerList");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public BuyerVO selectBuyer(String buy_id) {
		try {
			return (BuyerVO) sql.queryForObject("Buyer.selectBuyer", buy_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
