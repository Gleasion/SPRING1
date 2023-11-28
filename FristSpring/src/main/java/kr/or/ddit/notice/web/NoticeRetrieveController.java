package kr.or.ddit.notice.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping(value = "/notice")
public class NoticeRetrieveController {
	
	@Inject
	private INoticeService service;
	
	@RequestMapping(value = "/list.do")
	public String noticeList(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord
			) {
		
		PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.selectNoticeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
//		List<NoticeVO> dataList = service.selectSearchNoticeList(pagingVO);
		List<NoticeVO> dataList = service.selectNoticeList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "notice/list";
	}
	
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String noticeDetail(int noticeno, Model model) {
		NoticeVO noticeVO = service.selectNotice(noticeno);
		model.addAttribute("notice", noticeVO);
		model.addAttribute("status", "d");
		return "notice/view";
	}
}
