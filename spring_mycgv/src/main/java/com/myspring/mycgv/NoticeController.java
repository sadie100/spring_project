package com.myspring.mycgv;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.comms.Commons;
import com.mycgv.vo.NoticeVO;
import com.myspring.service.BoardNoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private BoardNoticeService noticeService;
	
	@RequestMapping(value = "/notice_content.do", method=RequestMethod.GET)
	public ModelAndView Notice_content(String nid, String rno) {		
		ModelAndView mv = new ModelAndView();
		//NoticeDAO dao = new NoticeDAO();
		NoticeVO vo = (NoticeVO)noticeService.getContent(nid);
		
		if(vo!=null) noticeService.getUpdateHit(nid);
		String content = vo.getNcontent().replace("\r\n", "<br>");
		
		mv.setViewName("notice/notice_content");
		mv.addObject("vo",vo);
		mv.addObject("content",content);
		mv.addObject("rno",rno);
		
		return mv;
	}
	@RequestMapping(value = "/notice_list.do", method=RequestMethod.GET)
	public ModelAndView Notice_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		//NoticeDAO dao = new NoticeDAO();	
		Commons commons = new Commons();
		HashMap map = commons.getPage(rpage, noticeService, "notice");

		int start = (Integer)map.get("start");
		int end = (Integer)map.get("end");
		ArrayList<Object> olist = noticeService.getList(start, end);
		ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
		for(Object obj:olist) {
			NoticeVO vo = (NoticeVO)obj;
			list.add(vo);
		} 

		mv.setViewName("notice/notice_list");
		mv.addObject("list",list);
		mv.addObject("dbCount",map.get("dbCount"));
		mv.addObject("rpage",map.get("rpage"));
		mv.addObject("pageSize",map.get("pageSize"));
		
		
		
		return mv;
	}
}
