package com.myspring.mycgv;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.comms.Commons;
import com.mycgv.vo.MemberVO;
import com.mycgv.vo.NoticeVO;
import com.myspring.service.BoardNoticeService;
import com.myspring.service.MemberService;

@Controller
public class AdminController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardNoticeService noticeService;
	
	/*
	 *   notice/notice_select_delete_proc.do  --->  관리자 > 공지사항 리스트 선택 삭제 처리
	 */
	@RequestMapping(value="notice/notice_select_delete_proc.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_select_delete_proc(String chkList){
		ModelAndView mv = new ModelAndView();
		
		StringTokenizer st = new StringTokenizer(chkList, ",");	//String 객체를 특정 값을 기준으로 잘라주는 역할(여기선 ,)
		String[] stArray = new String[st.countTokens()];
		for(int i=0; i<stArray.length;i++) {
			stArray[i] = st.nextToken();
		}
		
		int result = noticeService.getSelectDelete(stArray);
		if(result!=0) {
			mv.setViewName("redirect:/notice/notice_list.do");
		}
		
		return mv;
	}
	
	/*
	 *   notice/notice_write_proc.do  --->  관리자 > 공지사항 글 작성 화면
	 */
	@RequestMapping(value="notice/notice_write_proc.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_write_proc(NoticeVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		String root_path="";
		String attach_path="";
		
		if(vo.getFile1().getSize()!=0) {
			
			//1. 파일저장 위치
			root_path = request.getSession().getServletContext().getRealPath("/");
			attach_path = "\\resources\\upload\\";
			
			//2. 파일이름 ---> vo에 저장
			UUID uuid = UUID.randomUUID();
			vo.setNfile(vo.getFile1().getOriginalFilename());
			vo.setNsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
			System.out.println("bfile--->"+vo.getNfile());
			System.out.println("bsfile--->"+vo.getNsfile());
			
		}
			
		
			//NoticeDAO dao = new NoticeDAO();
			boolean result = noticeService.getInsertResult(vo);
			
			
			if(result){
				mv.setViewName("redirect:/notice/notice_list.do");
				
				//4. DB 연동 성공 ---> upload 폴더에 저장
				File file = new File(root_path+attach_path+vo.getNsfile());
				vo.getFile1().transferTo(file);
			}
			
			return mv;
		}
		
	/*
	 *   notice/notice_write.do  --->  관리자 > 공지사항 글 작성 화면
	 */
	@RequestMapping(value="notice/notice_write.do", method = RequestMethod.GET)
	public String admin_notice_write() {
		return "admin/notice/notice_write";
	}
	
	/*
	 *   notice/notice_update_proc.do  --->  관리자 > 공지사항 글 수정 처리
	 */
	@RequestMapping(value="notice/notice_update_proc.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_update_proc(NoticeVO vo, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//NoticeDAO dao = new NoticeDAO();
		
		if(vo.getFile1().getSize()!=0) {	//새로운 파일 선택
			//1. 파일저장 위치
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\upload\\";
			
			//2. 파일이름 ---> vo에 저장
			UUID uuid = UUID.randomUUID();
			vo.setNfile(vo.getFile1().getOriginalFilename());
			vo.setNsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
			System.out.println("bfile--->"+vo.getNfile());
			System.out.println("bsfile--->"+vo.getNsfile());
			
			//3. DB연동 --> 파일이 있는 경우 update
			String old_nsfile = noticeService.getFile(vo.getNid());
			boolean result =  noticeService.getUpdateResult(vo);
			
			if(result) {
				//4. DB 연동 성공 ---> upload 폴더에 저장
				File file = new File(root_path+attach_path+vo.getNsfile());
				vo.getFile1().transferTo(file);
				
				//기존 upload 폴더에 존재하는 파일 삭제
				File old_file = new File(root_path+attach_path+old_nsfile);
				if(old_file.exists()) {
					old_file.delete();
				}
			}
			
		}else {	//새로운 파일 선택 X
			//DB연동 --> 파일이 없는 경우 update
			boolean result = noticeService.getUpdateResultNofile(vo);
		}
		
		mv.setViewName("redirect:/notice/notice_list.do");
		return mv;
	}
	/*
	 *   notice/notice_update.do  --->  관리자 > 공지사항 글 수정 화면
	 */
	@RequestMapping(value="notice/notice_update.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid, String rno) {
		ModelAndView mv = new ModelAndView();
		//NoticeDAO dao = new NoticeDAO();
		NoticeVO vo = (NoticeVO)noticeService.getContent(nid);
		
		mv.setViewName("admin/notice/notice_update");
		mv.addObject("vo",vo);
		mv.addObject("rno",rno);
		return mv;
	}
	
	/*
	 *   notice/notice_delete_proc.do  --->  관리자 > 공지사항 글 삭제 처리
	 */
	@RequestMapping(value="notice/notice_delete_proc.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_delete_proc(String nid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		//NoticeDAO dao = new NoticeDAO();
		String old_nsfile = noticeService.getFile(nid);
		boolean result = noticeService.getDeleteResult(nid);
		
		if(result){
			mv.setViewName("redirect:/notice/notice_list.do");
			
			//기존 파일 삭제
			String root_path = request.getSession().getServletContext().getRealPath("/");
			String attach_path = "\\resources\\upload\\";
			File old_file = new File(root_path+attach_path+old_nsfile);
			if(old_file.exists()) old_file.delete();
		}
		return mv;
	}

	/*
	 *   notice/notice_delete.do  --->  관리자 > 공지사항 글 삭제 화면
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
	 *   notice/notice_content.do  --->  관리자 > 공지사항 상세정보 화면 출력
	 */
	@RequestMapping(value="notice/notice_content.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid, String rno) {
		ModelAndView mv = new ModelAndView();
		//NoticeDAO dao = new NoticeDAO();
		NoticeVO vo = (NoticeVO)noticeService.getContent(nid);
		String content = vo.getNcontent().replace("\r\n", "<br>");
				
		mv.setViewName("admin/notice/notice_content");
		mv.addObject("vo",vo);
		mv.addObject("content",content);
		mv.addObject("rno",rno);
		return mv;
	}
	
	/*
	 *   member/member_content.do  --->  관리자 > 회원 상세정보 출력
	 */
	@RequestMapping(value="member/member_content.do", method=RequestMethod.GET)
	public ModelAndView admin_member_content(String id, String rno) {
		ModelAndView mv = new ModelAndView();
		//MemberDAO dao = new MemberDAO();
    	MemberVO vo = memberService.getContent(id);
    	
    	mv.setViewName("admin/member/member_content");
    	mv.addObject("vo",vo);
    	mv.addObject("rno",rno);
    	
    	
    	return mv;
	}
	
	/*
	 *   member/member_list.do  --->  관리자 > 회원리스트 출력
	 */
	@RequestMapping(value="member/member_list.do", method=RequestMethod.GET)
	public ModelAndView admin_member_list(String rpage) {
		ModelAndView mv = new ModelAndView();
	    //MemberDAO dao = new MemberDAO();	
	    Commons com = new Commons();
	    HashMap<String,Integer> map = com.getPage(rpage, memberService, "member");
	    int startCount = (Integer)map.get("start");
	    int endCount = (Integer)map.get("end");
	    ArrayList<MemberVO> list = memberService.getList(startCount, endCount);

	    mv.setViewName("admin/member/member_list");
	    mv.addObject("list",list);
	    mv.addObject("dbCount",map.get("dbCount"));
	    mv.addObject("rpage",map.get("rpage"));
	    mv.addObject("pageSize",map.get("pageSize"));
		
		return mv;
	}
	
	/*
	 *   notice/notice_list.do  --->  관리자 > 공지사항 리스트 출력
	 */
	@RequestMapping(value="notice/notice_list.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_list(String rpage) {
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

		
		mv.setViewName("admin/notice/notice_list");
		mv.addObject("list",list);
		mv.addObject("dbCount",map.get("dbCount"));
		mv.addObject("rpage",map.get("rpage"));
		mv.addObject("pageSize",map.get("pageSize"));
		
		return mv;
	}
	
	/*
	 *   admin.do  --->  관리자 메인 화면
	 */
	@RequestMapping(value="/admin.do", method=RequestMethod.GET)
	public String Admin() {
		return "admin/admin";
	}
}
