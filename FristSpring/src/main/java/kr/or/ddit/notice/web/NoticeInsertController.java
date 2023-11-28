package kr.or.ddit.notice.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	
	@Inject
	private INoticeService service;
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String noticeForm() {
		return "notice/form";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String noticeInsert(NoticeVO noticeVO, Model model) {
		String goPage = "";
		Map<String, Object> errors = new HashMap<String, Object>();
		
		if(StringUtils.isBlank(noticeVO.getNoticeTitle())) {
			errors.put("noticeTitle", "제목을 입력해주세요.");
		}
		if(StringUtils.isBlank(noticeVO.getNoticeContent())) {
			errors.put("noticeContent", "내용을 입력해주세요.");
		}
		
		if(errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("notice", noticeVO);
			goPage = "notice/form";
			
		}else{
			noticeVO.setNoticeWriter("n001");
			ServiceResult result = service.insertNotice(noticeVO);
			if(result.equals(ServiceResult.OK)) {
				goPage = "redirect:/notice/detail.do?noticeno=" + noticeVO.getNoticeNo();
			}else {
				goPage = "notice/form";
			}
		}
		return goPage;
		
	}
	
}
