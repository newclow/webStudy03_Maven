package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import kr.or.ddit.db.ibstis.CustomSqlMapClientBuilder;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingInfoVO;

public class BuyerDAOImpl implements IBuyerDAO {
	
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();	
	
	@Override
	public int insertBuyer(BuyerVO buyer) {
		
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			int row = mapper.insertBuyer(buyer);
			session.commit();
			return row;
		}
	}	

	@Override
	public List<BuyerVO> selectBuyerList(PagingInfoVO<BuyerVO> buyerVO) {
		
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerList(buyerVO);
		}
		
	}
		

	@Override
	public long selectTotalRecord(PagingInfoVO<BuyerVO> buyerVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectTotalRecord(buyerVO);
		}
	}

	@Override
	public BuyerVO selectBuyer(String buy_id) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyer(buy_id);
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.updateBuyer(buyer);
		}
	}

	@Override
	public int deleteBuyer(String buy_id) {
		// TODO Auto-generated method stub
		return 0;
	}


}
