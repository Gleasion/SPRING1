package kr.or.ddit.main.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.notice.dao.INoticeDAO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NoticeVO;

@Controller
public class MainController {
	
	@Inject
	private IBoardService boardService;
	
	@Inject
	private INoticeService noticeService;
	
	@Inject
	private IFreeService freeSerivce;
	
	@RequestMapping(value = {"/","/main.do"}, method = RequestMethod.GET)
	public String main(Model model) {
		List<BoardVO> boardList = boardService.selectBoardList();
		model.addAttribute("boardList",boardList);
		
		List<NoticeVO> noticeList = noticeService.selectNoticeList();
		model.addAttribute("noticeList",noticeList);
		
		List<NoticeVO> freeList = freeSerivce.selectFreeList();
		model.addAttribute("freeList",freeList);
		
		return "main";
	}

	
	
}
