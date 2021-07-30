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
	 * logout.do : 로그아웃 처리
	 */
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		if(svo!=null)
			session.invalidate();
		
		return "/index";
	}
	
	/**
	 * login_check.do : 로그인 처리
	 */
	@RequestMapping(value="/login_check.do",method=RequestMethod.POST)
	public String login_check(MemberVO vo, HttpSession session) {
		String result_page = "";
		SessionVO svo = memberService.getLoginResult(vo);
		
		if(svo!=null){
			//로그인 성공시 ---->> svo 객체에 로그인 id, 회원명, 로그인결과 set
			svo.setId(vo.getId());
			session.setAttribute("svo", svo);
			result_page = "index";
		}else{
			result_page = "login/loginFail";
		}
		
		return result_page;
	}
	
	/**
	 * login.do : 로그인 화면
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "login/login";
	}
}
