package kr.or.ddit.free.dao;

import java.util.List;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IFreeDAO {

	public int insertFree(FreeVO freeVO);

	public void incresementFreeHit(int freeno);

	public FreeVO selectFree(int freeno);

	public int deleteFree(int freeNo);

	public int updateFree(FreeVO freeVO);

	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO);

	public List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO);

	public List<NoticeVO> selectFreeList();

}
