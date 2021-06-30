package com.myspring.mycgv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycgv.dao.MemberDAO;
import com.mycgv.vo.MemberVO;
import com.mycgv.vo.SessionVO;

@Controller
public class LoginController {
	
	/**
	 * login_check.do : �α��� ó��
	 */
	@RequestMapping(value="/login_check.do",method=RequestMethod.POST)
	public String login_check(MemberVO vo) {
		String result_page = "";
		MemberDAO dao = new MemberDAO();
		SessionVO svo = dao.getLoginResult(vo);
		
		if(svo.getResult() == 1){
			//�α��� ������ ---->> svo ��ü�� �α��� id, ȸ����, �α��ΰ�� set
			//svo.setId(vo.getId());
			//session.setAttribute("svo", svo);
			//response.sendRedirect("../index.jsp");
			result_page = "index";
		}else{
			//response.sendRedirect("loginFail.jsp");
			result_page = "login/loginFail";
		}
		
		return result_page;
	}
	
	/**
	 * login.do : �α��� ȭ��
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "login/login";
	}
}
