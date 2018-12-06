package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.CommonException;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.board.dao.IPdsDAO;
import kr.or.ddit.board.dao.PdsDAOImpl;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PdsVO;

public class BoardServiceImpl implements IBoardService {
	IBoardDAO boardDAO = new BoardDAOImpl();
	IPdsDAO pdsDAO = new PdsDAOImpl();
	
	
	@Override
	public ServiceResult createBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
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
	public int modifyBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServiceResult removeBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PdsVO downloadPds(long pds_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
