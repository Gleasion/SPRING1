package kr.or.ddit.book.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.book.service.IBookService;

// @Contoroller: �������� �������� ��û(request)�� �޾Ƶ��̴� ��Ʈ�ѷ���� �����ؼ� �ڹ� ��(JAVA Bean)���� ����ؼ� ����
@Controller
@RequestMapping("/book")
public class BookInsertController {
	
	// Autowired �� Inject ���� �ƹ��ų� ����ص� ������
	@Autowired
	private IBookService service;
	
	/*
	 * @RequestMapping
	 * - ��û URL�� � �޼ҵ尡 ó������ ���θ� ����
	 * 
	 * method �Ӽ��� http ��û �޼ҵ带 �ǹ��Ѵ�.
	 * �Ϲ����� �� ������ ���߿��� GET �޼ҵ�� �����͸� �������� ���� ��쿡,
	 * POST �޼ҵ�� �����Ͱ� ����� ��� ���ȴ�.
	 * 
	 * ModelAndView�� ��Ʈ�ѷ��� ��ȯ�� �����͸� ����ϴ� ��(Model)�� ȭ���� ����ϴ� ��(View)�� ��θ� ���ĳ��� ��ü�̴�.
	 * ModelAndView�� �����ڿ� ���ڿ� Ÿ�� �Ķ���Ͱ� �ԷµǴ� ���� ��ζ�� �����Ѵ�.
	 * 
	 * ���� ��θ� 'book/form'�� ���� ���·� �����ϴ� ������ ��û(request)�� �ش��ϴ� url�� mapping �Ǵ� ȭ���� ��� ����
	 * 	ViewResolver��� �༮�� ���� ���� �޴´�.
	 * �޾Ƽ�, suffix�� prefix �Ӽ��� ���ؼ�
	 * 	�տ��� '/WEB-INF/views/'�� ���̰�
	 * 	�ڿ��� '.jsp'�� �ٿ�
	 * ���� ��ġ�� �ش��ϴ� jsp ������ ã���ش�.
	 */
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public ModelAndView bookForm() {
		return new ModelAndView("book/form");
	}

	/* �������� ������ �Ͼ�Ƿ� http �޼ҵ�� POST������� ó��
	 * @RequestParam�� HTTP �Ķ���͸� Map ������ �ڵ����� ���ε��Ѵ�.
	 * Map Ÿ���� ���� @RequestParam�� �ٿ��߸� HTTP �Ķ������ ���� map�� ���ε� ���ش�. */
	@RequestMapping(value = "/form.do", method = RequestMethod.POST)
									// RequestParam �Ⱦ��� map�� ������ �ȵ���
	public ModelAndView insertBook(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		// ���� �޼ҵ� insertBook ȣ��
		// ���񽺿��� bookId�� ���� �����Ѵ�.
		String bookId = service.insertBook(map);
		
		if(bookId == null) {
			
			// ������ �Է��� ������ ��� �ٽ� �����͸� �Է¹޾ƾ��ϹǷ� ���� ȭ������ redirect�Ѵ�.
			// ModelAndView ��ü�� .setViewName �޼ҵ带 ���� ���� ��θ� ������ �� �ִ�.
			mav.setViewName("redirect:/book/form.do");
			// �� ��ΰ� redirect:�� �����ϸ� �������� �� ������ ã�ư��� ���� �ƴ϶�
			// �� �������� �ּ�(/form.do)�� �����Ѵ�.
			
		}else {
			
			// ������ �Է��� �����ϸ� �� �������� �̵��Ѵ�.
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}
		
		return mav;
		
	}
	
	
	
	
	
	
	
	
}
