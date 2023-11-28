package kr.or.ddit.book.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * @Repository�� �����Ϳ� �����ϴ� Ŭ�������� ����Ѵ�.
 * �ش� ������̼��� �ִ� Ŭ������ �������� �����͸� �����ϴ� Ŭ������� �����Ͽ� �ڹ� ��(Java Bean)���� ����ؼ� �����Ѵ�.
 * 
 * SqlSessionTemplate ��ü�� �ɹ� ������ �����ϴ� ������ Mapper xml�� �����Ű�� ���ؼ���.
 * �ش� ��ü ���� @Autowired �Ǵ� @Inject�� �ٿ��� SqlSessionTemplate ��ü�� ����� �� �ֵ��� �Ѵ�.
 * �̷��� ���¸� '������ ����'�̶�� �Ѵ�.(�ʵ� ������[Field Injection])
 * 
 * SqlSessionTemplate ��ü�� new Ű���带 ���� ���� �������� �ʰ�, ������ ����(Dependecy Injection - DI)�� ���� ���Թ޴´�.
 * �������� �̸� ����� ���� SqlSessionTemplate Ÿ�� ��ü�� BookDAO ��ü�� �����Ѵ�.
 * �ش� ������ ���������� �ڵ� ����Ǹ�, �����ڰ� ���� SqlSessionTemplate ��ü�� �����ϴ� �� ���� ��ٷ� ����� �� �ִ�.
 * 
 * SqlSessionTemplate ��ü�� root-context.xml���� �����ص� ��ü�̱⵵ �ϰ�, ������ ���۵� �� �������� �̸� xml�� �о� ��ü�� �ν��Ͻ�ȭ �صд�.
 */
@Repository
public class BookDAOImpl implements IBookDAO{
	
	/* ���� XML�� �����Ű�� ���ؼ� SqlSessionTemplate ��ü�� �ɹ� ������ �����Ѵ�.
	 * @Autowired�� �ٿ��� SqlSessionTemplate ��ü�� ����� �� �ְ� �Ѵ�. */
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/* # sqlSessionTemplate.insert()
	 * 
	 * 1) ù��° �Ķ���ʹ� SQL Mapper�� id�̴�.
	 * 	book_SQL.xml���� namespace�� ������ 'Book'�� insert ������ �����ϱ� ���� ���� insert���� id�� ���� 'insert'�̴�.
	 * 	mybatis�� ���ӽ����̽� + id �������� ������ ã�Ƽ� �����Ѵ�.
	 * 2) �ι�° �Ķ���ʹ� ������ ������ �������̴�.
	 * 	mapper �� insert ������ �����ϱ� ���� ���޵Ǿ� ���� parameterType�� map�̴�.
	 * 
	 * �ܺο��� DAO���� map�� title, category, price�� ������� �Ѿ�´�.
	 * �׸���, useGeneratedKeys�� keyProperty�� ���� ���п� book ���̺��� pk�� book_id �׸��� �����. */
	@Override
	public int insertBook(Map<String, Object> map) {
		/*  useGeneratedKeys�� keyProperty�� ������ ����
		 *  ������ ����ǰ� ���� �Ķ���ͷ� ���޵� map ��ü�� book ���̺��� pk�� book_id �׸��� �����.
		 *  
		 *  # ���� map :::
		 *  {
		 *  	"title" : "����",
		 *  	"category" : "IT",
		 *  	"price" : 1000
		 *  }
		 *  
		 *  # ���� ���� �� map :::
		 *  {
		 *  	"title" : "����",
		 *  	"category" : "IT",
		 *  	"price" : 1000,
		 * 		"book_id" : 1
		 *  }
		 *  
		 * sqlSessionTemplate.insert()�� ��ȯ ���� ������ ������ ���� �� ��(row count)��.
		 * insert ������ ��� �����ϸ� 1���� ��(row)�� ����Ƿ� 1�� �����ϰ� �����ϸ� 0�� �����Ѵ�. */
		return sqlSessionTemplate.insert("Book.insert",map);
	}
	
	// select one
	@Override
	public Map<String, Object> selectBook(Map<String, Object> map) {
		/** ���� ��� ��� 
		 * 0�� : selectOne null ��ȯ
		 * ������: TooManyResultException ���� �߻�
		 * pk�� ������ ���� �������� ���� > ��� 0�� �ƴϸ� 1�� */ 
		return sqlSessionTemplate.selectOne("Book.selectBook", map);
	}
	
	// update
	@Override
	public int update(Map<String, Object> map) {
		return sqlSessionTemplate.update("Book.update", map);
	}
	
	// delete
	@Override
	public int delete(Map<String, Object> map) {
		return sqlSessionTemplate.delete("Book.delete", map);
	}

	@Override
	public List<Map<String, Object>> selectBookList(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("Book.selectBookList", map);
	}

}
