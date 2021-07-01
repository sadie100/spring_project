package com.myspring.mycgv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	/*
	 *   notice/notice_write.do  --->  관리자 > 공지사항 글 작성 화면
	 */
	@RequestMapping(value="notice/notice_write.do", method = RequestMethod.GET)
	public String admin_notice_write() {
		return "admin/notice/notice_write";
	}
	
	/*
	 *   notice/notice_update.do  --->  관리자 > 공지사항 글 수정 화면
	 */
	@RequestMapping(value="notice/notice_update.do", method = RequestMethod.GET)
	public String admin_notice_update(String nid, String rno) {
		return "admin/notice/notice_update";
	}
	
	/*
	 *   notice/notice_delete.do  --->  관리자 > 공지사항 글 삭제 화면
	 */
	@RequestMapping(value="notice/notice_delete.do", method = RequestMethod.GET)
	public String admin_notice_delete(String nid, String rno) {
		return "admin/notice/notice_delete";
	}
	
	/*
	 *   notice/notice_content.do  --->  관리자 > 공지사항 상세정보 화면 출력
	 */
	@RequestMapping(value="notice/notice_content.do", method = RequestMethod.GET)
	public String admin_notice_content() {
		return "admin/notice/notice_content";
	}
	
	/*
	 *   member/member_content.do  --->  관리자 > 회원 상세정보 출력
	 */
	@RequestMapping(value="member/member_content.do", method=RequestMethod.GET)
	public String admin_member_content() {
		return "admin/member/member_content";
	}
	
	/*
	 *   member/member_list.do  --->  관리자 > 회원리스트 출력
	 */
	@RequestMapping(value="member/member_list.do", method=RequestMethod.GET)
	public String admin_member_list() {
		return "admin/member/member_list";
	}
	
	/*
	 *   notice/notice_list.do  --->  관리자 > 공지사항 리스트 출력
	 */
	@RequestMapping(value="notice/notice_list.do", method=RequestMethod.GET)
	public String admin_notice_list() {
		return "admin/notice/notice_list";
	}
	
	/*
	 *   admin.do  --->  관리자 메인 화면
	 */
	@RequestMapping(value="/admin.do", method=RequestMethod.GET)
	public String Admin() {
		return "admin/admin";
	}
}
