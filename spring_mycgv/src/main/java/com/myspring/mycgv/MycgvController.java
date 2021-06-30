package com.myspring.mycgv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MycgvController {
	
	@RequestMapping(value="/mycgv.do", method=RequestMethod.GET)
	public String Mycgv() {
		return "mycgv/mycgv";
	}
}
