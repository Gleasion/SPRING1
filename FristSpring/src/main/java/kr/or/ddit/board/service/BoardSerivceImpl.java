package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class BoardSerivceImpl implements IBoardService{
	
	@Inject
	private IBoardDAO dao;
	
	@Override
	public ServiceResult insertBoard(BoardVO boardVO) {
		ServiceResult result = null;
		int status = dao.insertBoard(boardVO);
		
		if(status > 0) {	// 정상적으로 등록
			result = ServiceResult.OK;
		}else {				// 실패
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public BoardVO selectBoard(int bono) {
		
		dao.incrementHit(bono);	// 조회수 증가
		
		return dao.selectBoard(bono);
	}

	@Override
	public ServiceResult updateBoard(BoardVO boardVO) {
		ServiceResult result = null;
		int status = dao.updateBoard(boardVO);
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteBoard(int boNo) {
		ServiceResult result = null;
		
		int status = dao.deleteBoard(boNo);
		
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<BoardVO> selectBoardList() {
		return dao.selectBoardList();
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		return dao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO) {
		return dao.selectBoardList(pagingVO);
	}

}
