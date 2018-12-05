package kr.or.ddit.board.dao;

import java.util.List;

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
 * 2018. 12. 4.      서신원        댓글 관리를 위한 persistence layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface IReplyDAO {
	/**
	 * 댓글 작성
	 * @param reply
	 * @return
	 */
	public int insertReply(ReplyVO reply);
	
	/**
	 * 특정 게시글의 댓글 목록 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ReplyVO> selectReplyList(PagingInfoVO<ReplyVO> pagingVO);
	
	/**
	 * 특정 게시글의 전체 댓글 수
	 * @param pagingVO
	 * @return
	 */
	public Long selectTotalRecord(PagingInfoVO<ReplyVO> pagingVO);
	
	/**
	 * 댓글 상세 조회
	 * @param rep_no
	 * @return 존재하지 않을시 null반환
	 */
	public ReplyVO selectReply(long rep_no);
	
	/**
	 * 댓글 수정
	 * @param reply_no
	 * @return row count
	 */
	public int updateReply(ReplyVO rep_no);
	
	/**
	 * 댓글 삭제
	 * @param rep_no
	 * @return
	 */
	public int deleteReply(long rep_no);
}
