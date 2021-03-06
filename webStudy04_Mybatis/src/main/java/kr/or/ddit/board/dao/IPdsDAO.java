package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PdsVO;

/**
 * @author 서신원
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      서신원        첨부파일을 관리를 위한 persistance layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface IPdsDAO {
	/**
	 * 파일 등록(메타데이터만)
	 * @param pds
	 * @return row count
	 */
	public int insertPds(PdsVO pds);
	
	/**
	 * 여러건의 첨부파일을 한번의 insert쿼리로 삽입
	 * @param board TODO
	 * @param session TODO
	 * @return
	 */
	public int insertPdsList(BoardVO board, SqlSession session);
	
	/**
	 * 다운로드용으로 사용될 조회 메소드
	 * @param pds_no
	 * @return 존재하지 않으면 null반환
	 */
	public PdsVO selectPds(long pds_no);
	
	/**
	 * 게시글 수정시 파일 삭제를 위한 메소드
	 * @param pds_no
	 * @param session TODO
	 * @return row count
	 */
	public int deletePds(long pds_no, SqlSession session);
	
	
	/**
	 * 게시글 삭제시 전체 파일을 삭제하기 위한 메소드
	 * @param board
	 * @param session TODO
	 * @return row count
	 */
	public int deletePdses(BoardVO board, SqlSession session);
}
