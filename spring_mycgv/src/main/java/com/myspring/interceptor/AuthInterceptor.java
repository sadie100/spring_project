package com.myspring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mycgv.vo.SessionVO;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 * 컨트롤러 실행 전 호출되는 메소드(Dispatcher Servlet이 가로채서 실행)
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception{
		//세션 정보 가져오기
		HttpSession session = request.getSession();
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		
		//세션 체크 후 유---> 요청 페이지(그대로 진행), 무---> 로그인 화면 이동(인터셉트)
		if(svo==null) {
			response.sendRedirect("http://localhost:9000/mycgv/login.do");
			return false;
		}
		
		return true;
	}
}
