<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import = "com.mycgv.dao.MemberDAO, com.mycgv.vo.*" %>
<jsp:useBean id="vo" class="com.mycgv.vo.MemberVO"></jsp:useBean>
<jsp:setProperty name="vo" property="*" />

<%
	MemberDAO dao = new MemberDAO();
	SessionVO svo = dao.getLoginResult(vo);
	
	if(svo.getResult() == 1){
		//로그인 성공시 ---->> svo 객체에 로그인 id, 회원명, 로그인결과 set
		svo.setId(vo.getId());

		session.setAttribute("svo", svo);
		
		response.sendRedirect("../index.jsp");
	}else{
		response.sendRedirect("loginFail.jsp");
	}

%>
