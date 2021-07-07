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
import com.mycgv.dao.MemberDAO;
import com.mycgv.dao.NoticeDAO;
import com.mycgv.vo.MemberVO;
import com.mycgv.vo.NoticeVO;

@Controller
public class AdminController {
	
	/*
	 *   notice/notice_write_proc.do  --->  ������ > �������� �� �ۼ� ȭ��
	 */
	@RequestMapping(value="notice/notice_write_proc.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_write_proc(NoticeVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		String root_path="";
		String attach_path="";
		
		if(vo.getFile1().getSize()!=0) {
			
			//1. �������� ��ġ
			root_path = request.getSession().getServletContext().getRealPath("/");
			attach_path = "\\resources\\upload\\";
			
			//2. �����̸� ---> vo�� ����
			UUID uuid = UUID.randomUUID();
			vo.setNfile(vo.getFile1().getOriginalFilename());
			vo.setNsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
			System.out.println("bfile--->"+vo.getNfile());
			System.out.println("bsfile--->"+vo.getNsfile());
			
		}
			
		
			NoticeDAO dao = new NoticeDAO();
			boolean result = dao.getInsertResult(vo);
			
			
			if(result){
				mv.setViewName("redirect:/notice/notice_list.do");
				
				//4. DB ���� ���� ---> upload ������ ����
				File file = new File(root_path+attach_path+vo.getNsfile());
				vo.getFile1().transferTo(file);
			}
			
			return mv;
		}
		
	/*
	 *   notice/notice_write.do  --->  ������ > �������� �� �ۼ� ȭ��
	 */
	@RequestMapping(value="notice/notice_write.do", method = RequestMethod.GET)
	public String admin_notice_write() {
		return "admin/notice/notice_write";
	}
	
	/*
	 *   notice/notice_update_proc.do  --->  ������ > �������� �� ���� ó��
	 */
	@RequestMapping(value="notice/notice_update_proc.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_update_proc(NoticeVO vo, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		NoticeDAO dao = new NoticeDAO();
		
		if(vo.getFile1().getSize()!=0) {	//���ο� ���� ����
			//1. �������� ��ġ
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\upload\\";
			
			//2. �����̸� ---> vo�� ����
			UUID uuid = UUID.randomUUID();
			vo.setNfile(vo.getFile1().getOriginalFilename());
			vo.setNsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
			System.out.println("bfile--->"+vo.getNfile());
			System.out.println("bsfile--->"+vo.getNsfile());
			
			//3. DB���� --> ������ �ִ� ��� update
			String old_nsfile = dao.getNsfile(vo.getNid());
			boolean result =  dao.getUpdateResult(vo);
			
			if(result) {
				//4. DB ���� ���� ---> upload ������ ����
				File file = new File(root_path+attach_path+vo.getNsfile());
				vo.getFile1().transferTo(file);
				
				//���� upload ������ �����ϴ� ���� ����
				File old_file = new File(root_path+attach_path+old_nsfile);
				if(old_file.exists()) {
					old_file.delete();
				}
			}
			
		}else {	//���ο� ���� ���� X
			//DB���� --> ������ ���� ��� update
			boolean result = dao.getUpdateResultNofile(vo);
		}
		
		mv.setViewName("redirect:/notice/notice_list.do");
		return mv;
	}
	/*
	 *   notice/notice_update.do  --->  ������ > �������� �� ���� ȭ��
	 */
	@RequestMapping(value="notice/notice_update.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid, String rno) {
		ModelAndView mv = new ModelAndView();
		NoticeDAO dao = new NoticeDAO();
		NoticeVO vo = dao.getContent(nid);
		
		mv.setViewName("admin/notice/notice_update");
		mv.addObject("vo",vo);
		mv.addObject("rno",rno);
		return mv;
	}
	
	/*
	 *   notice/notice_delete_proc.do  --->  ������ > �������� �� ���� ó��
	 */
	@RequestMapping(value="notice/notice_delete_proc.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_delete_proc(String nid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		NoticeDAO dao = new NoticeDAO();
		String old_nsfile = dao.getNsfile(nid);
		boolean result = dao.getDeleteResult(nid);
		
		if(result){
			mv.setViewName("redirect:/notice/notice_list.do");
			
			//���� ���� ����
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\upload\\";
			File old_file = new File(root_path+attach_path+old_nsfile);
			if(old_file.exists()) old_file.delete();
		}
		return mv;
	}

	/*
	 *   notice/notice_delete.do  --->  ������ > �������� �� ���� ȭ��
	 */
	@RequestMapping(value="notice/notice_delete.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid, String rno) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("nid",nid);
		mv.addObject("rno",rno);
		mv.setViewName("admin/notice/notice_delete");
		
		return mv;
	}
	
	/*
	 *   notice/notice_content.do  --->  ������ > �������� ������ ȭ�� ���
	 */
	@RequestMapping(value="notice/notice_content.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid, String rno) {
		ModelAndView mv = new ModelAndView();
		NoticeDAO dao = new NoticeDAO();
		NoticeVO vo = dao.getContent(nid);
		String content = vo.getNcontent().replace("\r\n", "<br>");
				
		mv.setViewName("admin/notice/notice_content");
		mv.addObject("vo",vo);
		mv.addObject("content",content);
		mv.addObject("rno",rno);
		return mv;
	}
	
	/*
	 *   member/member_content.do  --->  ������ > ȸ�� ������ ���
	 */
	@RequestMapping(value="member/member_content.do", method=RequestMethod.GET)
	public ModelAndView admin_member_content(String id, String rno) {
		ModelAndView mv = new ModelAndView();
		MemberDAO dao = new MemberDAO();
    	MemberVO vo = dao.getContent(id);
    	
    	mv.setViewName("admin/member/member_content");
    	mv.addObject("vo",vo);
    	mv.addObject("rno",rno);
    	
    	
    	return mv;
	}
	
	/*
	 *   member/member_list.do  --->  ������ > ȸ������Ʈ ���
	 */
	@RequestMapping(value="member/member_list.do", method=RequestMethod.GET)
	public ModelAndView admin_member_list(String rpage) {
		ModelAndView mv = new ModelAndView();
	    MemberDAO dao = new MemberDAO();	
	    Commons com = new Commons();
	    HashMap<String,Integer> map = com.getPage(rpage, dao, "member");
	    int startCount = (Integer)map.get("start");
	    int endCount = (Integer)map.get("end");
	    ArrayList<MemberVO> list = dao.getList(startCount, endCount);

	    mv.setViewName("admin/member/member_list");
	    mv.addObject("list",list);
	    mv.addObject("dbCount",map.get("dbCount"));
	    mv.addObject("rpage",map.get("rpage"));
	    mv.addObject("pageSize",map.get("pageSize"));
		
		return mv;
	}
	
	/*
	 *   notice/notice_list.do  --->  ������ > �������� ����Ʈ ���
	 */
	@RequestMapping(value="notice/notice_list.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		NoticeDAO dao = new NoticeDAO();	
		Commons commons = new Commons();
		HashMap map = commons.getPage(rpage, dao, "notice");

		int start = (Integer)map.get("start");
		int end = (Integer)map.get("end");
		ArrayList<NoticeVO> list = dao.getList(start, end);

		mv.setViewName("admin/notice/notice_list");
		mv.addObject("list",list);
		mv.addObject("dbCount",map.get("dbCount"));
		mv.addObject("rpage",map.get("rpage"));
		mv.addObject("pageSize",map.get("pageSize"));
		
		return mv;
	}
	
	/*
	 *   admin.do  --->  ������ ���� ȭ��
	 */
	@RequestMapping(value="/admin.do", method=RequestMethod.GET)
	public String Admin() {
		return "admin/admin";
	}
}
