package kr.or.ddit.notice.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeModifyController {
	
	@Inject
	private INoticeService service;
	
	@RequestMapping(value = "/view.do", method = RequestMethod.GET)
	public String noticeUpdateForm(int noticeno, Model model) {
		NoticeVO noticeVO = service.selectNotice(noticeno);
		model.addAttribute("notice", noticeVO);
		model.addAttribute("status", "u");
		return "notice/view";
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String noticeUpdate(NoticeVO noticeVO, Model model) {
		String goPage = "";
		ServiceResult result = service.updateNotice(noticeVO);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/notice/detail.do?noticeno=" + noticeVO.getNoticeNo();
		}else {
			model.addAttribute("notice", noticeVO);
			model.addAttribute("status", "u");
			goPage = "notice/form";
		}
		return goPage;
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String noticeDelete(int noticeNo, Model model) {
		String goPage = "";
		ServiceResult result = service.deleteNotice(noticeNo);
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/notice/list.do";
		}else {
			goPage = "redirect:/notice/detail.do?noticeno=" + noticeNo;
		}
		return goPage;
	}
	
}
