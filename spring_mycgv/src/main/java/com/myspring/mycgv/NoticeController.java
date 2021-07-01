package com.myspring.mycgv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoticeController {
	
	@RequestMapping(value = "/notice_content.do", method=RequestMethod.GET)
	public String Notice_content() {
		return "notice/notice_content";
	}
	@RequestMapping(value = "/notice_list.do", method=RequestMethod.GET)
	public String Notice_list() {
		return "notice/notice_list";
	}
}
