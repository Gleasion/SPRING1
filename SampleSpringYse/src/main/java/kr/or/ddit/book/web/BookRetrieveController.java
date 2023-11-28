package kr.or.ddit.book.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.book.service.IBookService;

@Controller
@RequestMapping("/book")
public class BookRetrieveController {
	
	// BookService ������ ����
	@Inject
	private IBookService service;
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView bookList(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> list = service.selectBookList(map);
		
		// �˻���� �߰�
		// ��� ������������ keyword�� HTTP �Ķ���Ͱ� ���� ���� �ְ�, ���� ���� �ִ�.
		if(map.containsKey("keyword")) {
			mav.addObject("keyword", map.get("keyword"));
		}
		
		mav.addObject("bookList", list);
		mav.setViewName("book/list");
		return mav;
	}
	
	// @RequestParam ������̼ǿ� ���� ���� ��Ʈ�� �Ķ���͸� ���� �� �ִ�.
	@RequestMapping(value="/detail.do", method=RequestMethod.GET)
	public ModelAndView bookDetail(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> detailMap = service.selectBook(map);
		String bookId = map.get("bookId").toString();
		
		// ModelAndView ��ü mav�� ��� ������ �����͸� ��´�.
		// book�̶�� Ű�� �̸����� ������ ����� ��´�.
		mav.addObject("bookId", bookId);
		mav.addObject("book", detailMap);
		mav.setViewName("book/detail");
		
		return mav;
	}
}
