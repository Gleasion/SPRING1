package kr.or.ddit.free.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;

@Controller
@RequestMapping("/free")
public class FreeModifyController {
	
	@Inject
	private IFreeService service;
	
	@RequestMapping(value = "/view.do", method = RequestMethod.GET)
	public String freeUpdateForm(int freeno, Model model) {
		FreeVO freeVO = service.selectFree(freeno);
		model.addAttribute("free", freeVO);
		model.addAttribute("status", "u");
		return "free/view";
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String freeUpdate(FreeVO freeVO, Model model) {
		String goPage = "";
		ServiceResult result = service.updateFree(freeVO);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/detail.do?freeno=" + freeVO.getFreeNo();
		}else {
			model.addAttribute("free", freeVO);
			model.addAttribute("status","u");
			goPage = "free/form";
		}
		
		return goPage;
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String freeDelete(int freeNo, Model model) {
		String goPage = "";
		ServiceResult result = service.deleteFree(freeNo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/list.do";
		}else {
			goPage = "redirect:/free/detail.do?freeno=" + freeNo;
		}
		
		return goPage;
		
	}
}
