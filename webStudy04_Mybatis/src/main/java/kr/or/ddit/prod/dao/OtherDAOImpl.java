package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;

public class OtherDAOImpl implements IOtherDAO {
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<Map<String, Object>> selectLprodList() {
		try (
				SqlSession session = sqlSessionFactory.openSession();
				){
			IOtherDAO mapper = session.getMapper(IOtherDAO.class);
			return mapper.selectLprodList();
//			Map map = session.selectMap("kr.or.ddit.prod.dao.IOtherDAO.selectLprodList", "LPROD_GU");
//			return map;
		} 
	}

	@Override
	public List<BuyerVO> selectBuyerList(String buyer_lgu) {
		try(
				SqlSession session = sqlSessionFactory.openSession();	
		){
			IOtherDAO mapper = session.getMapper(IOtherDAO.class);
			return mapper.selectBuyerList(buyer_lgu);
		}
	}

	@Override
	public List<String> selectLprod(String lprod_gu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> selectBuyer(String buyer_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
