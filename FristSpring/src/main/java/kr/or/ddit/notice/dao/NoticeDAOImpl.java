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
	
	// �������� ���
	@Override
	public int insertNotice(NoticeVO noticeVO) {
		return sqlSession.insert("Notice.insertNotice", noticeVO);
	}
	
	// ��ȸ�� ����
	@Override
	public void incrementNoticeHit(int noticeno) {
		sqlSession.update("Notice.incresementNoticeHit", noticeno);
	}
	
	// �������� ��ȸ
	@Override
	public NoticeVO selectNotice(int noticeno) {
		return sqlSession.selectOne("Notice.selectNotice", noticeno);
	}
	
	// �������� ����
	@Override
	public int deleteNotice(int noticeNo) {
		return sqlSession.delete("Notice.deleteNotice", noticeNo);
	}
	
	// ����
	@Override
	public int updateNotice(NoticeVO noticeVO) {
		return sqlSession.update("Notice.updateNotice", noticeVO);
	}
	
	// ��� ��ȸ
	@Override
	public List<NoticeVO> selectNoticeList() {
		return sqlSession.selectList("Notice.selectNoticeList_");
	}

	// ����¡ ó���� ���
	@Override
	public List<NoticeVO> selectSearchNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectList("Notice.selectSearchNoticeList", pagingVO);
	}

	// ����¡ count
	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectOne("Notice.selectNoticeCount", pagingVO);
	}

	// ����¡ + �˻� ��� ��ȸ
	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return sqlSession.selectList("Notice.selectNoticeList", pagingVO);
	}
}
