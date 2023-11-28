package kr.or.ddit.board.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardInsertController {

	@Inject
	private IBoardService service;
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String boardForm() {
		return "board/form";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String boardInsert(BoardVO boardVO, Model model) {
		String goPage = ""; // 응답으로 보낼 페이지 정보
		Map<String, Object> errors = new HashMap<String, Object>();	// 에러 정보를 담을 Map
		
		if(StringUtils.isBlank(boardVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요.");
		}
		if(StringUtils.isBlank(boardVO.getBoContent())) {
			errors.put("boContent","내용을 입력해주세요.");
		}
		
		if(errors.size() > 0) {						// 에러 발생
			model.addAttribute("errors", errors);
			model.addAttribute("board", boardVO);
			goPage = "board/form";
		}else {										// 정상
			boardVO.setBoWriter("a001");			// 작성자(하드코딩)
			ServiceResult result = service.insertBoard(boardVO);
			if(result.equals(ServiceResult.OK)) {	// 성공
				goPage = "redirect:/board/detail.do?bono=" + boardVO.getBoNo();
			}else {									// 실패
				goPage = "board/form";
			}
		}
		return goPage;

	}
	
}
