package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.BoardException;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.board.dao.IPdsDAO;
import kr.or.ddit.board.dao.PdsDAOImpl;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PdsVO;

public class BoardServiceImpl implements IBoardService {
	IBoardDAO boardDAO = new BoardDAOImpl();
	IPdsDAO pdsDAO = new PdsDAOImpl();
	
	private int processFiles(BoardVO board, SqlSession session) {
		int rowCnt = 0;
		List<PdsVO> pdsList = board.getPdsList();
		File saveFolder = new File("d:/boardFiles");
		if (!saveFolder.exists()) saveFolder.mkdirs();
		if (pdsList != null) {
	//		if (1 == 1) 
	//		throw new RuntimeException("트랜잭션 관리 여부 확인을 위한 강제 예외");
			
			for (PdsVO pds : pdsList) {
				pds.setBo_no(board.getBo_no());
			}
			rowCnt += pdsDAO.insertPdsList(board, session);
			for (PdsVO pds : pdsList) {
				try(
					InputStream in = pds.getItem().getInputStream();	
				){
					FileUtils.copyInputStreamToFile(in, new File(saveFolder, pds.getPds_savename()));
				}catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
		Long[] delFiles = board.getDelFiles();
		if(delFiles!=null) {
			String[] saveNames = new String[delFiles.length];
			for(int idx=0; idx<delFiles.length; idx++) {
				saveNames[idx] = pdsDAO.selectPds(delFiles[idx])
									   .getPds_savename(); 
				rowCnt += pdsDAO.deletePds(delFiles[idx], session);
			}
			// 파일 삭제
			for(String savename : saveNames) {
				FileUtils.deleteQuietly(new File(saveFolder, savename));
			}
		}
		
		return rowCnt;
	}
	
	
	
	@Override
	public ServiceResult createBoard(BoardVO board) {
		//트랜잭션적용부분
		try(
			SqlSession session = CustomSqlSessionFactoryBuilder.getSqlSessionFactory().openSession(false);
		){
			ServiceResult result = null;
			int rowCnt = boardDAO.insertBoard(board, session);
			int check = 1;
			//2.저장공간만들기
			
			if (rowCnt > 0) {
				if (board.getPdsList() != null) {
					check += board.getPdsList().size();
				}
				rowCnt += processFiles(board, session);
			}	
			result = ServiceResult.FAILED;
			if (rowCnt >= check) {
				result = ServiceResult.OK;
				session.commit();
			}
			return result;
		}
	}

	@Override
	public long retriveBoardCount(PagingInfoVO<BoardVO> pagingVO) {
		return boardDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<BoardVO> retriveBoardList(PagingInfoVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);
	}

	@Override
	public BoardVO retriveBoard(long bo_no) {
		BoardVO board = boardDAO.selectBoard(bo_no);
		if (board == null) {
			throw new CommonException(bo_no+"에 해당하는 게시글이 없습니다.");
		}
		boardDAO.incrementHit(bo_no);
		return board;
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		try(
				SqlSession session = 
						CustomSqlSessionFactoryBuilder.getSqlSessionFactory()
													  .openSession(false);
		){
			BoardVO savedBoard = retriveBoard(board.getBo_no());
			ServiceResult result = null;
			if(savedBoard.getBo_pass().equals(board.getBo_pass())) {
				int rowCnt = boardDAO.updateBoard(board, session);
				int check = rowCnt;
				if(rowCnt > 0) {
					// 저장 위치
					if (board.getPdsList() != null) {
						check += board.getPdsList().size();
					}
					if (board.getDelFiles() != null) {
						check += board.getDelFiles().length;
					}
					rowCnt += processFiles(board, session);
				}
				if(rowCnt >= check ) {
					session.commit();
					result = ServiceResult.OK;	
				}else {
					result = ServiceResult.FAILED;
				} // rowCnt 체크 if end
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			} // 비번 체크 if end
			return result;
		}
	}

	@Override
	public ServiceResult removeBoard(BoardVO board) {
		try(
				SqlSession session = 
				CustomSqlSessionFactoryBuilder.getSqlSessionFactory()
											  .openSession(false);
		){
			ServiceResult result = null;
			BoardVO check = retriveBoard(board.getBo_no());
			if (check.getBo_pass().equals(board.getBo_pass())) {
				int rowCnt = boardDAO.deleteBoard(board.getBo_no());
				int checkCnt = rowCnt;
				if (rowCnt > 0) {
					if (board.getPdsList() != null) {
						rowCnt += pdsDAO.deletePdses(board, session);
					}
					
				}
				if(rowCnt >= checkCnt ) {
					session.commit();
					result = ServiceResult.OK;	
				}else {
					result = ServiceResult.FAILED;
				} // rowCnt 체크 if end
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
			return result;
		}
	}

	@Override
	public PdsVO downloadPds(long pds_no) {
		PdsVO pds = pdsDAO.selectPds(pds_no);
		if (pds == null) {
			throw new BoardException(pds_no+"파일은 존재하지 않습니다.");
		}
		return pds;
	}

}
