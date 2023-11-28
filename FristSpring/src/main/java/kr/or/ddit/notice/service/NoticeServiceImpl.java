package kr.or.ddit.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.dao.INoticeDAO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class NoticeServiceImpl implements INoticeService{
	
	@Inject
	private INoticeDAO dao;
	
	// 공지사항 등록
	@Override
	public ServiceResult insertNotice(NoticeVO noticeVO) {
		ServiceResult result = null;
		int status = dao.insertNotice(noticeVO);
		
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result =ServiceResult.FAILED;
		}
		return result;
	}
	
	// 공지사항 상세조회
	@Override
	public NoticeVO selectNotice(int noticeno) {
		
		dao.incrementNoticeHit(noticeno);
		
		return dao.selectNotice(noticeno);
	}
	
	// 삭제
	@Override
	public ServiceResult deleteNotice(int noticeNo) {
		ServiceResult result = null;
		int status = dao.deleteNotice(noticeNo);
		
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	
	// 수정
	@Override
	public ServiceResult updateNotice(NoticeVO noticeVO) {
		ServiceResult result = null;
		int status = dao.updateNotice(noticeVO);
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	// 공지사항 목록 조회
	@Override
	public List<NoticeVO> selectNoticeList() {
		return dao.selectNoticeList();
	}
	
	// 검색기능 추가 목록 조회
	@Override
	public List<NoticeVO> selectSearchNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return dao.selectSearchNoticeList(pagingVO);
	}
	
	// 페이징 처리 count
	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return dao.selectNoticeCount(pagingVO);
	}

	// 페이징 + 검색 목록 조회
	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return dao.selectNoticeList(pagingVO);
	}

}
