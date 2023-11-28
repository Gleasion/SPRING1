package kr.or.ddit.free.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;
import sun.print.resources.serviceui;

@Controller
@RequestMapping("/free")
public class FreeRetreiveController {
	
	@Inject
	private IFreeService service;
	
	@RequestMapping(value = "/list.do")
	public String freeList(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord
			) {
		
		PaginationInfoVO<FreeVO> pagingVO = new PaginationInfoVO<FreeVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.selectFreeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<FreeVO> dataList = service.selectFreeList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "free/list";
	}

	
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String freeDetail(int freeno, Model model) {
		FreeVO freeVO = service.selectFree(freeno);
		
		model.addAttribute("free", freeVO);
		model.addAttribute("status","d");
		
		return "free/view";
	}
	
}
