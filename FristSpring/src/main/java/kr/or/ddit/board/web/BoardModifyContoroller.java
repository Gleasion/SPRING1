package kr.or.ddit.board.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardModifyContoroller {
	
	@Inject
	private IBoardService service;
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String boardUpdateForm(int boNo, Model model) {
		BoardVO boardVO = service.selectBoard(boNo);
		model.addAttribute("board", boardVO);
		model.addAttribute("status", "u");	// insert와 구분하기 위한 status
		return "board/form";
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String boardUpdate(BoardVO boardVO, Model model) {
		String goPage = "";
		ServiceResult result = service.updateBoard(boardVO);
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/board/detail.do?bono=" + boardVO.getBoNo();
		}else {
			model.addAttribute("board", boardVO);
			model.addAttribute("status", "u");
			goPage = "board/form";
		}
		return goPage;
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String boardDelete(int boNo, Model model) {
		String goPage = "";
		ServiceResult result = service.deleteBoard(boNo);
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/board/list.do";
		}else {
			goPage = "redirect:/board/detail.do?bono=" + boNo;
		}
		return goPage;
	}
	
	
}
