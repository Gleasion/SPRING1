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
	
	// BookService 의존성 주입
	@Inject
	private IBookService service;
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView bookList(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> list = service.selectBookList(map);
		
		// 검색기능 추가
		// 목록 페이지에서는 keyword가 HTTP 파라미터가 있을 수도 있고, 없을 수도 있다.
		if(map.containsKey("keyword")) {
			mav.addObject("keyword", map.get("keyword"));
		}
		
		mav.addObject("bookList", list);
		mav.setViewName("book/list");
		return mav;
	}
	
	// @RequestParam 어노테이션에 의해 쿼리 스트링 파라미터를 읽을 수 있다.
	@RequestMapping(value="/detail.do", method=RequestMethod.GET)
	public ModelAndView bookDetail(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> detailMap = service.selectBook(map);
		String bookId = map.get("bookId").toString();
		
		// ModelAndView 객체 mav에 뷰로 전달할 데이터를 담는다.
		// book이라는 키의 이름으로 쿼리의 결과를 담는다.
		mav.addObject("bookId", bookId);
		mav.addObject("book", detailMap);
		mav.setViewName("book/detail");
		
		return mav;
	}
}
