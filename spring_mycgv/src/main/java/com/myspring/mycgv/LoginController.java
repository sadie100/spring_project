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
	 * login_check.do : 로그인 처리
	 */
	@RequestMapping(value="/login_check.do",method=RequestMethod.POST)
	public String login_check(MemberVO vo) {
		String result_page = "";
		MemberDAO dao = new MemberDAO();
		SessionVO svo = dao.getLoginResult(vo);
		
		if(svo.getResult() == 1){
			//로그인 성공시 ---->> svo 객체에 로그인 id, 회원명, 로그인결과 set
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
	 * login.do : 로그인 화면
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "login/login";
	}
}
