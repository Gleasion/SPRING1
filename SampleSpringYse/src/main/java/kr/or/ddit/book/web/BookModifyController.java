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
			// 업데이트가 정상적으로 데이터 갱신되었을 때 확인을 위해 상세페이지로 이동
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}else {
			// 갱신이 되지 않았을 경우, GET 메소드로 redirect하는 방법도 있지만,
			// 상세보기 화면을 이용한 수정 화면을 바로 보여줄 수도 있다.
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
			// 삭제가 성공했으면 상세 페이지가 없으므로 목록으로 redirect 한다.
			mav.setViewName("redirect:/book/list.do");
		}else {
			// 삭제가 실패했으면 다시 상세페이지로 이동하여 삭제를 재요청할 수 있도록 제공한다.
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}
		
		return mav;
		
	}
	
	
	
	
}
