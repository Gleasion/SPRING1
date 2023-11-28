package kr.or.ddit.book.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.book.dao.IBookDAO;

/* �Ϲ������� ���� ���̾�� �������̽��� Ŭ������ �Բ� ����Ѵ�.
 * �������� ���� Ŭ������ �����ϴ� ���� �����ϰ� �������̽��� ���� �����ϴ� ���� �����ϴ� �����ӿ�ũ�̴�. */
@Service
public class BookServiceImpl implements IBookService{
	
	/* Service Ŭ������ ������ Ŭ������ ��ġ�ϴ� ���̴�.
	 * ������ MVC �������� ���� Ŭ������ ��Ʈ�ѷ��� DAO�� �����ϴ� ������ �Ѵ�.
	 * 
	 * @Service�� �������� ���� Ŭ�������� �˷��ش�.
	 * 
	 * �����ͺ��̽� ������ ���� BookDAO �ν��Ͻ��� ���Թ޴´�.
	 * Ŭ������ �̸��� Impl�� ������ ���� implements�� ���ڷ� ������ ������.
	 * Impl�� �ٰ� �Ⱥٰ� ���� Ŭ�������� �������̽����� �����ϱ� ����. */
	@Autowired
	private IBookDAO dao;
	
	/**
	 * <p>å ���</p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_min
	 * @param map�� ����� å ������
	 * @return ���� å ID, ���� null
	 */
	@Override
	public String insertBook(Map<String, Object> map) {
		
		// affectRowCount �������� ������� �� ���� ����.
		// insert ������ �Է��� �����ϸ� 1, �����ϸ� 0�� �����Ѵ�.
		int affectRowCount = dao.insertBook(map);
		
		if(affectRowCount > 0) {
			// ����� ������ ��, map �ν��Ͻ��� book ���̺��� pk�� book_id�� ����ִ�.
			return map.get("book_id").toString();
		}
		return null;
	}

	/**
	 * <p>å �󼼺���</p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_min
	 * @param map å ID
	 * @return ID�� �ش��ϴ� å ����
	 */
	@Override
	public Map<String, Object> selectBook(Map<String, Object> map) {
		
		// ���� �� detail �Լ��� dao�� ȣ���� ����� �ٷ� �����ϴ� �ϸ� �Ѵ�.
		return dao.selectBook(map);
	}
	
	/**
	 * <p>å ����</p>
	 * @since SampleSpringYse
	 * @author ddit_min
	 * @param map å ID
	 * @return ���� 1, ���� 0
	 */
	@Override
	public boolean updateBook(Map<String, Object> map) {
		
		int affectRowCount = dao.update(map);
		
		return affectRowCount == 1;
	}
	
	/**
	 * <p>å ����</p>
	 * @since SampleSpringYse
	 * @author ddit_min
	 * @param map å ID
	 * @return ���� 1, ���� 0
	 */
	@Override
	public boolean removeBook(Map<String, Object> map) {
		
		int affectRowCount = dao.delete(map);
		
		return affectRowCount == 1;
	}
	
	/**
	 * <p>å ���</p>
	 * @since SampleSpringYse
	 * @author ddit_min
	 * @param map å Ű����
	 * @return ���� ����Ʈ(å), ���� null
	 */
	@Override
	public List<Map<String, Object>> selectBookList(Map<String, Object> map) {
		return dao.selectBookList(map);
	}
}
