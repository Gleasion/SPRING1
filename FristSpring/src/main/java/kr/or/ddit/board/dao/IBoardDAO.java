package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardDAO {

	public int insertBoard(BoardVO boardVO);

	public void incrementHit(int bono);

	public BoardVO selectBoard(int bono);

	public int updateBoard(BoardVO boardVO);

	public int deleteBoard(int boNo);

	public List<BoardVO> selectBoardList();

	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);

	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);


}
