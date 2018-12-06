package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyDAOImpl implements IReplyDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertReply(ReplyVO reply) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			int row = mapper.insertReply(reply);
			session.commit();
			return row;
		}
	}

	@Override
	public List<ReplyVO> selectReplyList(PagingInfoVO<ReplyVO> pagingVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectReplyList(pagingVO);
		}
	}

	@Override
	public Long selectTotalRecord(PagingInfoVO<ReplyVO> pagingVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}

	@Override
	public ReplyVO selectReply(long rep_no) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			return mapper.selectReply(rep_no);
		}
	}

	@Override
	public int updateReply(ReplyVO rep_no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReply(long rep_no) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
				){
			IReplyDAO mapper = session.getMapper(IReplyDAO.class);
			int row = mapper.deleteReply(rep_no);
			session.commit();
			return row;
		}
	}

}
