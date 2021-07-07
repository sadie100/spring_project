package com.myspring.mycgv;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.comms.Commons;
import com.mycgv.dao.BoardDAO;
import com.mycgv.vo.BoardVO;

@Controller
public class BoardController {
	
	/**
	 * board_update_proc.do ---> �Խ��� ���� ó��
	 */
	
	@RequestMapping(value="/board_update_proc.do",method=RequestMethod.POST)
	public ModelAndView board_update_proc(BoardVO vo, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		if(vo.getFile1().getSize()!=0) {	//���ο� ���� ����
			//1. �������� ��ġ
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\upload\\";
			
			//2. �����̸� ---> vo�� ����
			UUID uuid = UUID.randomUUID();
			vo.setBfile(vo.getFile1().getOriginalFilename());
			vo.setBsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
			System.out.println("bfile--->"+vo.getBfile());
			System.out.println("bsfile--->"+vo.getBsfile());
			
			//3. DB���� --> ������ �ִ� ��� update
			BoardDAO dao = new BoardDAO();
			String old_bsfile = dao.getBsfile(vo.getBid());
			boolean result =  dao.getUpdateResult(vo);
			
			if(result) {
				//4. DB ���� ���� ---> upload ������ ����
				File file = new File(root_path+attach_path+vo.getBsfile());
				vo.getFile1().transferTo(file);
				
				//���� upload ������ �����ϴ� ���� ����
				File old_file = new File(root_path+attach_path+old_bsfile);
				if(old_file.exists()) {
					old_file.delete();
				}
			}
			
		}else {	//���ο� ���� ���� X
			//DB���� --> ������ ���� ��� update
			BoardDAO dao = new BoardDAO();
			boolean result = dao.getUpdateResultNofile(vo);
		}
 	
 		mv.setViewName("redirect:/board_list.do");
 		
		return mv;
	}
	
	/**
	 * board_update.do ---> �Խ��� ���� ȭ��
	 */
	
	@RequestMapping(value="/board_update.do",method=RequestMethod.GET)
	public ModelAndView board_update(String bid, String rno) {
		ModelAndView mv = new ModelAndView();
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.getContent(bid);
		mv.setViewName("board/board_update");
		mv.addObject("vo", vo);
		mv.addObject("rno", rno);
		return mv;
	}
	
	
	
	/**
	 * board_delete_proc.do ---> �Խ��� ���� ó��
	 */
	
	@RequestMapping(value="/board_delete_proc.do",method=RequestMethod.GET)
	public ModelAndView board_delete_proc(String bid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		BoardDAO dao = new BoardDAO();
		String old_bsfile = dao.getBsfile(bid);
		boolean result = dao.getDeleteResult(bid);
		if(result) {
			mv.setViewName("redirect:/board_list.do");
			
			//���� upload ������ �����ϴ� ���� ����
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\upload\\";
			File old_file = new File(root_path+attach_path+old_bsfile);
			if(old_file.exists()) {
				old_file.delete();
			}
		}
	
	return mv;
}
	/**
	 * board_delete.do ---> �Խ��� ���� ȭ��
	 */
	
	@RequestMapping(value="/board_delete.do",method=RequestMethod.GET)
	public ModelAndView board_delete(String bid, String rno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/board_delete");
		mv.addObject("bid",bid);
		mv.addObject("rno",rno);
		
		return mv;
	}
	/**
	 * board_content.do ---> �Խ��� �󼼳��� ȭ��
	 */
	
	@RequestMapping(value="/board_content.do",method=RequestMethod.GET)
	public ModelAndView board_content(String bid, String rno) {
		  ModelAndView mv = new ModelAndView();
		  
		  BoardDAO dao = new BoardDAO();
		  BoardVO vo = dao.getContent(bid);
		  if(vo!=null) dao.getUpdateHit(bid);
		  String content = vo.getBcontent().replace("\r\n","<br>");
		  
		  mv.setViewName("board/board_content");
		  mv.addObject("vo",vo);
		  mv.addObject("rno",rno);
		  
		  return mv;
	}
	/**
	 * board_write_proc.do ---> �Խ��� �۾��� ó��
	 */
	
	@RequestMapping(value="/board_write_proc.do",method=RequestMethod.POST)
	public ModelAndView board_write_proc(BoardVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		String root_path="";
		String attach_path="";
		
		if(vo.getFile1().getSize()!=0) {
			
			//1. �������� ��ġ
			root_path = request.getSession().getServletContext().getRealPath("/");
			attach_path = "\\resources\\upload\\";
			
			//2. �����̸� ---> vo�� ����
			UUID uuid = UUID.randomUUID();
			vo.setBfile(vo.getFile1().getOriginalFilename());
			vo.setBsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
			System.out.println("bfile--->"+vo.getBfile());
			System.out.println("bsfile--->"+vo.getBsfile());
			
		}
			
			//3. DB ����
			BoardDAO dao = new BoardDAO();
			boolean result = dao.getInsertResult(vo);
		 	
		 	if(result){
		 		mv.setViewName("redirect:/board_list.do");
		 		
		 		//4. DB ���� ���� ---> upload ������ ����
				File file = new File(root_path+attach_path+vo.getBsfile());
				vo.getFile1().transferTo(file);
		 	}
		return mv;
	}
	/**
	 * board_write.do ---> �Խ��� �۾��� ȭ��
	 */
	
	@RequestMapping(value="/board_write.do",method=RequestMethod.GET)
	public String board_write() {
		return "board/board_write";
	}
	
	@RequestMapping(value="/board_list.do",method=RequestMethod.GET)
	public ModelAndView board_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		BoardDAO dao = new BoardDAO();	
		Commons commons = new Commons();
		HashMap map = commons.getPage(rpage, dao, "board");

		int start = (Integer)map.get("start");
		int end = (Integer)map.get("end");
		ArrayList<BoardVO> list = dao.getList(start, end);

		mv.setViewName("board/board_list");
		mv.addObject("list",list);
		mv.addObject("start",start);
		mv.addObject("end",end);
		mv.addObject("dbCount",map.get("dbCount"));
		mv.addObject("rpage",map.get("rpage"));
		mv.addObject("pageSize",map.get("pageSize"));
		
		
		
		return mv;
	}
	
	
}
