<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv.dao.MemberDAO" %>
<%
	String id = request.getParameter("id");
//System.out.println("id==> "+id);
	MemberDAO dao = new MemberDAO();
	int result = dao.getIdCheck(id);
	
	out.write(String.valueOf(result));
%>