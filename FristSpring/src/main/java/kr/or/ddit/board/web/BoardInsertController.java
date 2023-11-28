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
		String goPage = ""; // �������� ���� ������ ����
		Map<String, Object> errors = new HashMap<String, Object>();	// ���� ������ ���� Map
		
		if(StringUtils.isBlank(boardVO.getBoTitle())) {
			errors.put("boTitle", "������ �Է����ּ���.");
		}
		if(StringUtils.isBlank(boardVO.getBoContent())) {
			errors.put("boContent","������ �Է����ּ���.");
		}
		
		if(errors.size() > 0) {						// ���� �߻�
			model.addAttribute("errors", errors);
			model.addAttribute("board", boardVO);
			goPage = "board/form";
		}else {										// ����
			boardVO.setBoWriter("a001");			// �ۼ���(�ϵ��ڵ�)
			ServiceResult result = service.insertBoard(boardVO);
			if(result.equals(ServiceResult.OK)) {	// ����
				goPage = "redirect:/board/detail.do?bono=" + boardVO.getBoNo();
			}else {									// ����
				goPage = "board/form";
			}
		}
		return goPage;

	}
	
}
