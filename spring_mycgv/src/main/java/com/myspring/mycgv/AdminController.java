package com.myspring.mycgv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	/*
	 *   notice/notice_write.do  --->  ������ > �������� �� �ۼ� ȭ��
	 */
	@RequestMapping(value="notice/notice_write.do", method = RequestMethod.GET)
	public String admin_notice_write() {
		return "admin/notice/notice_write";
	}
	
	/*
	 *   notice/notice_update.do  --->  ������ > �������� �� ���� ȭ��
	 */
	@RequestMapping(value="notice/notice_update.do", method = RequestMethod.GET)
	public String admin_notice_update(String nid, String rno) {
		return "admin/notice/notice_update";
	}
	
	/*
	 *   notice/notice_delete.do  --->  ������ > �������� �� ���� ȭ��
	 */
	@RequestMapping(value="notice/notice_delete.do", method = RequestMethod.GET)
	public String admin_notice_delete(String nid, String rno) {
		return "admin/notice/notice_delete";
	}
	
	/*
	 *   notice/notice_content.do  --->  ������ > �������� ������ ȭ�� ���
	 */
	@RequestMapping(value="notice/notice_content.do", method = RequestMethod.GET)
	public String admin_notice_content() {
		return "admin/notice/notice_content";
	}
	
	/*
	 *   member/member_content.do  --->  ������ > ȸ�� ������ ���
	 */
	@RequestMapping(value="member/member_content.do", method=RequestMethod.GET)
	public String admin_member_content() {
		return "admin/member/member_content";
	}
	
	/*
	 *   member/member_list.do  --->  ������ > ȸ������Ʈ ���
	 */
	@RequestMapping(value="member/member_list.do", method=RequestMethod.GET)
	public String admin_member_list() {
		return "admin/member/member_list";
	}
	
	/*
	 *   notice/notice_list.do  --->  ������ > �������� ����Ʈ ���
	 */
	@RequestMapping(value="notice/notice_list.do", method=RequestMethod.GET)
	public String admin_notice_list() {
		return "admin/notice/notice_list";
	}
	
	/*
	 *   admin.do  --->  ������ ���� ȭ��
	 */
	@RequestMapping(value="/admin.do", method=RequestMethod.GET)
	public String Admin() {
		return "admin/admin";
	}
}
