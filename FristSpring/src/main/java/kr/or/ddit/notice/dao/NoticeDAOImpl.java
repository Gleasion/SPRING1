package kr.or.ddit.notice.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class NoticeDAOImpl implements INoticeDAO{

	@Inject
	private SqlSessionTemplate sqlSession;
	
	// 공지사항 등록
	@Override
	public int insertNotice(NoticeVO noticeVO) {
		return sqlSession.insert("Notice.insertNotice", noticeVO);
	}
	
	// 조회수 증가
	@Override
	public void incrementNoticeHit(int noticeno) {
		sqlSession.update("Notice.incresementNoticeHit", noticeno);
	}
	
	// 공지사항 조회
	@Override
	public NoticeVO selectNotice(int noticeno) {
		return sqlSession.selectOne("Notice.selectNotice", noticeno);
	}
	
	// 공지사항 삭제
	@Override
	public int deleteNotice(int noticeNo) {
		return sqlSession.delete("Notice.deleteNotice", noticeNo);
	}
	
	// 수정
	@Override
	public int updateNotice(NoticeVO noticeVO) {
		return sqlSession.update("Notice.updateNotice", noticeVO);
	}
	
	// 목록 조회
	@Override
	public List<NoticeVO> selectNoticeList() {
		return sqlSession.selectList("Notice.selectNoticeList_");
	}

	// 페이징 처리된 목록
	@Override
	public List<NoticeVO> selectSearchNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectList("Notice.selectSearchNoticeList", pagingVO);
	}

	// 페이징 count
	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectOne("Notice.selectNoticeCount", pagingVO);
	}

	// 페이징 + 검색 목록 조회
	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectList("Notice.selectNoticeList", pagingVO);
	}
}
