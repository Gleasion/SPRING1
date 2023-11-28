package kr.or.ddit.notice.dao;

import java.util.List;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeDAO {

	public int insertNotice(NoticeVO noticeVO);

	public void incrementNoticeHit(int noticeno);

	public NoticeVO selectNotice(int noticeno);

	public int deleteNotice(int noticeNo);

	public int updateNotice(NoticeVO noticeVO);

	public List<NoticeVO> selectNoticeList();

	public List<NoticeVO> selectSearchNoticeList(PaginationInfoVO<NoticeVO> pagingVO);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);


}
