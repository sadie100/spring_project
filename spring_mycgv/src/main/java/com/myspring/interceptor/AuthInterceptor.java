package com.myspring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mycgv.vo.SessionVO;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 * ��Ʈ�ѷ� ���� �� ȣ��Ǵ� �޼ҵ�(Dispatcher Servlet�� ����ä�� ����)
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception{
		//���� ���� ��������
		HttpSession session = request.getSession();
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		
		//���� üũ �� ��---> ��û ������(�״�� ����), ��---> �α��� ȭ�� �̵�(���ͼ�Ʈ)
		if(svo==null) {
			response.sendRedirect("http://localhost:9000/mycgv/login.do");
			return false;
		}
		
		return true;
	}
}
