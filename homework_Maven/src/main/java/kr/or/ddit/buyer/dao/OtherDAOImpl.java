package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;

public class OtherDAOImpl implements IOtherDAO {
	
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public Map<String, String> selectLprodList() {
		try(
				SqlSession session = sqlSessionFactory.openSession();
		){
			IOtherDAO mapper = session.getMapper(IOtherDAO.class);
			return mapper.selectLprodList();
		}
		
		
	}
}
