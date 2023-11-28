package kr.or.ddit.board.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/board")
public class BoardRetreiveController {
	
	@Inject
	private IBoardService service;
	
	// method = RequestMethod ������ ���ϸ� GET�� POST �Ѵ� ����
	@RequestMapping(value = "/list.do")
	public String boardList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
	// ���1
		// List<BoardVO> boardList = service.selectBoardList();
		// model.addAttribute("boardList", boardList);

	// ���2 - ����¡ ó��
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		
		// ���������� �˻��� �˻� ����, �˻� Ű����(searchWord)�˻��Ͽ��� ��
		if(StringUtils.isNotBlank(searchWord)) {	// �˻� Ű���尡 ������ if�� �ɸ�
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.selectBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BoardVO> dataList = service.selectBoardList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/list";
	}
	
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String boardDetail(int bono, Model model) {
		BoardVO boardVO = service.selectBoard(bono);
		model.addAttribute("board", boardVO);
		return "board/view";
	}
}
