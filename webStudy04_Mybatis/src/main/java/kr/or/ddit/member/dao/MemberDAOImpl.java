package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

public class MemberDAOImpl implements IMemberDAO {
	
	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertMember(MemberVO member) {
		try(
				SqlSession session = sqlSessionFactory.openSession(false);
				){
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			int rowCnt = mapper.insertMember(member);
			if (rowCnt > 0) {
				session.commit();
			}
			return rowCnt;
		}
	}

	@Override
	public long selectTotalRecord(PagingInfoVO pagingVO) {
		try(
			SqlSession session = sqlSessionFactory.openSession();
				){
//			return session.selectOne("kr.or.ddit.member.dao.IMemberDAO.selectTotalRecord", pagingVO);
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}

	@Override
	public List<MemberVO> selectMemberList(PagingInfoVO pagingVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
		){
//			return session.selectList("kr.or.ddit.member.dao.IMemberDAO.selectMemberList", pagingVO);
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			return mapper.selectMemberList(pagingVO);
		}
	}

	@Override
	public MemberVO selectMember(String mem_id) {
		try(
				SqlSession session = sqlSessionFactory.openSession();	
				){
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			return mapper.selectMember(mem_id);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			int rowCnt = mapper.updateMember(member);
			if (rowCnt > 0) {
				session.commit();
			}
			return rowCnt;
		}
		
	}
	

	@Override
	public int deleteMember(String mem_id) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IMemberDAO mapper = session.getMapper(IMemberDAO.class);
			int rowCnt = mapper.deleteMember(mem_id);
			if (rowCnt > 0) {
				session.commit();
			}
			return rowCnt;
		}
	}
}
