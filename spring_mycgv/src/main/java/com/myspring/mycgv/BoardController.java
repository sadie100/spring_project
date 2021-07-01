package com.myspring.mycgv;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycgv.comms.Commons;
import com.mycgv.dao.BoardDAO;
import com.mycgv.vo.BoardVO;

@Controller
public class BoardController {
	
	/**
	 * board_update.do ---> �Խ��� ���� ȭ��
	 */
	
	@RequestMapping(value="/board_update.do",method=RequestMethod.GET)
	public String board_update(String bid, String rno) {
		
		return "board/board_update";
	}
	/**
	 * board_delete.do ---> �Խ��� ���� ȭ��
	 */
	
	@RequestMapping(value="/board_delete.do",method=RequestMethod.GET)
	public String board_delete(String bid, String rno) {
		
		return "board/board_delete";
	}
	/**
	 * board_content.do ---> �Խ��� �󼼳��� ȭ��
	 */
	
	@RequestMapping(value="/board_content.do",method=RequestMethod.GET)
	public String board_content(String bid, String rno) {
	
		return "board/board_content";
	}
	/**
	 * board_write.do ---> �Խ��� �۾��� ȭ��
	 */
	
	@RequestMapping(value="/board_write.do",method=RequestMethod.GET)
	public String board_write() {
		return "board/board_write";
	}
	
	@RequestMapping(value="/board_list.do",method=RequestMethod.GET)
	public String board_list() {
		return "board/board_list";
	}
	
	
}
