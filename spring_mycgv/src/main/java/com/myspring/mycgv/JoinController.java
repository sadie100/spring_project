package com.myspring.mycgv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycgv.dao.MemberDAO;
import com.mycgv.vo.MemberVO;

@Controller
public class JoinController {

	/*
	 * idcheck_proc.do ---> 아이디 중복체크 처리
	 */
	@ResponseBody	//요청한 jsp body에 바로 보낸다는 의미
	@RequestMapping(value="/idcheck_proc.do",method=RequestMethod.GET)
	public String idcheck_proc(String id) {
		MemberDAO dao = new MemberDAO();
		int result = dao.getIdCheck(id);
		return String.valueOf(result);
	}
	/*
	 * join_proc.do ---> 회원가입 처리
	 */
	
	@RequestMapping(value="/join_proc.do",method=RequestMethod.POST)
	public String join_proc(MemberVO vo) {
		String result_page = "";
		MemberDAO dao = new MemberDAO();
	 	boolean join_result = dao.getInsertResult(vo);
	 	
	 	if(join_result == true){
	 		//response.sendRedirect("joinSuccess.jsp");
	 		result_page="join/joinSuccess";
	 		}else{
	 		//response.sendRedirect("http://localhost:9000/mycgv/errorPage.jsp");
			result_page="errorPage";
	 	}
	 	
		return result_page;
	}
	/*
	 * join.do ---> 회원가입 화면
	 */
	@RequestMapping(value="/join.do",method=RequestMethod.GET)
	public String join() {
		return "join/join";
	}
}
