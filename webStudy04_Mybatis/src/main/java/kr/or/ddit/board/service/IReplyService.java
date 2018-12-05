package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;

/**
 * @author 서신원
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      서신원        댓글 관리용 business logic layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface IReplyService {
	/**
	 * 댓글작성
	 * @param reply
	 * @return OK FAILED
	 */
	public ServiceResult createReply(ReplyVO reply);
	/**
	 * 특정 게시글의 덧글수
	 * @param pagingVO
	 * @return 없다면 0
	 */
	public Long retriveReplyCount(PagingInfoVO<ReplyVO> pagingVO);
	
	/**
	 * 특정 게시글의 덧글목록
	 * @param pagingVO
	 * @return 없다면 size() == 0
	 */
	public List<ReplyVO> retriveReplyList(PagingInfoVO<ReplyVO> pagingVO);
	
	/**
	 * 댓글 수정
	 * @param reply
	 * @return BoardException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyReply(ReplyVO reply);
	
	/**
	 * 댓글 삭제
	 * @param reply
	 * @return BoardException, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult deleteReply(ReplyVO reply);
	
}
