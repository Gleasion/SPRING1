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
	
	// method = RequestMethod 설정을 안하면 GET과 POST 둘다 가능
	@RequestMapping(value = "/list.do")
	public String boardList(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
	// 방법1
		// List<BoardVO> boardList = service.selectBoardList();
		// model.addAttribute("boardList", boardList);

	// 방법2 - 페이징 처리
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		
		// 브라우저에서 검색한 검색 유형, 검색 키워드(searchWord)검색하였을 때
		if(StringUtils.isNotBlank(searchWord)) {	// 검색 키워드가 있으면 if에 걸림
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
