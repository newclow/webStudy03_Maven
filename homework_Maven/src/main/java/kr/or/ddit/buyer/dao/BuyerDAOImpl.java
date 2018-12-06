package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.db.ibstis.CustomSqlMapClientBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerDAOImpl implements IBuyerDAO {
	
	SqlMapClient sqlMapClient = CustomSqlMapClientBuilder.getSqlMapClient();

	@Override
	public String insertBuyer(BuyerVO buyer) {
		try {
				return (String) sqlMapClient.insert("Buyer.insertBuyer", buyer);
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingInfoVO<BuyerVO> buyerVO) {
		try {
				return sqlMapClient.queryForList("Buyer.selectBuyerList", buyerVO);
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public long selectTotalRecord(PagingInfoVO<BuyerVO> buyerVO) {
		try {
				return (Long) sqlMapClient.queryForObject("Buyer.selectTotalRecord", buyerVO);
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public BuyerVO selectBuyer(String buy_id) {
		try {
				return (BuyerVO) sqlMapClient.queryForObject("Buyer.selectBuyer", buy_id);
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		try {
				return sqlMapClient.update("Buyer.updateBuyer", buyer);
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public int deleteBuyer(String buy_id) {
		// TODO Auto-generated method stub
		return 0;
	}


}
