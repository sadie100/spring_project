<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv.vo.SessionVO" %>

<% 
	SessionVO svo = (SessionVO)session.getAttribute("svo");
	if(svo !=null){
		session.invalidate();
		response.sendRedirect("../index.jsp");
	}
%>