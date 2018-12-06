package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IReplyDAO;
import kr.or.ddit.board.dao.ReplyDAOImpl;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyServiceImpl implements IReplyService {
	IReplyDAO replyDAO = new ReplyDAOImpl();
	
	
	@Override
	public ServiceResult createReply(ReplyVO reply) {
		ServiceResult result = null;
		int rowCnt = replyDAO.insertReply(reply);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public Long retriveReplyCount(PagingInfoVO<ReplyVO> pagingVO) {
		return replyDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<ReplyVO> retriveReplyList(PagingInfoVO<ReplyVO> pagingVO) {
		return replyDAO.selectReplyList(pagingVO);
	}

	@Override
	public ServiceResult modifyReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult deleteReply(ReplyVO reply) {
		ServiceResult result = null;
		ReplyVO check = retriveReply(reply.getRep_no());
		if (check.getRep_pass().equals(reply.getRep_pass())) {
			int rowCnt = replyDAO.deleteReply(reply.getRep_no());
			if (rowCnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public ReplyVO retriveReply(long rep_no) {
		return replyDAO.selectReply(rep_no);
	}

}
