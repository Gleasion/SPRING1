package kr.or.ddit.book.web;

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
public class BookModifyController {
	
	@Inject
	private IBookService servcie;
	
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public ModelAndView bookUpdateView(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> detailMap = servcie.selectBook(map);
		mav.addObject("book", detailMap);
		mav.setViewName("book/update");
		return mav;
	}
	
	// update
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public ModelAndView bookUpdate(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isUpdateSuccess = servcie.updateBook(map);
		if(isUpdateSuccess) {
			// ������Ʈ�� ���������� ������ ���ŵǾ��� �� Ȯ���� ���� ���������� �̵�
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}else {
			// ������ ���� �ʾ��� ���, GET �޼ҵ�� redirect�ϴ� ����� ������,
			// �󼼺��� ȭ���� �̿��� ���� ȭ���� �ٷ� ������ ���� �ִ�.
			mav = bookUpdateView(map);
		}
		
		return mav;
	}
	
	// delete
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public ModelAndView deleteBook(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isDeleteSueccess = servcie.removeBook(map);
		if(isDeleteSueccess){
			// ������ ���������� �� �������� �����Ƿ� ������� redirect �Ѵ�.
			mav.setViewName("redirect:/book/list.do");
		}else {
			// ������ ���������� �ٽ� ���������� �̵��Ͽ� ������ ���û�� �� �ֵ��� �����Ѵ�.
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}
		
		return mav;
		
	}
	
	
	
	
}
