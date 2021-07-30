package com.myspring.mycgv;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycgv.vo.MemberVO;
import com.mycgv.vo.SessionVO;
import com.myspring.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * logout.do : �α׾ƿ� ó��
	 */
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		if(svo!=null)
			session.invalidate();
		
		return "/index";
	}
	
	/**
	 * login_check.do : �α��� ó��
	 */
	@RequestMapping(value="/login_check.do",method=RequestMethod.POST)
	public String login_check(MemberVO vo, HttpSession session) {
		String result_page = "";
		SessionVO svo = memberService.getLoginResult(vo);
		
		if(svo!=null){
			//�α��� ������ ---->> svo ��ü�� �α��� id, ȸ����, �α��ΰ�� set
			svo.setId(vo.getId());
			session.setAttribute("svo", svo);
			result_page = "index";
		}else{
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
