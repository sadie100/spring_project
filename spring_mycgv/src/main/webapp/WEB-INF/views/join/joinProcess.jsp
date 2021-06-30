<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "com.mycgv.dao.MemberDAO, com.mycgv.vo.MemberVO" %>
 
 <jsp:useBean id="vo" class="com.mycgv.vo.MemberVO"  />
 <jsp:setProperty name="vo" property="*"></jsp:setProperty>
 
 
 <%
 	
 	MemberDAO dao = new MemberDAO();
 	boolean join_result = dao.getInsertResult(vo);
 	
 	if(join_result == true){
 		response.sendRedirect("joinSuccess.jsp");
 	}else{
 		response.sendRedirect("http://localhost:9000/mycgv/errorPage.jsp");
 	}
 /*	
 	System.out.println(id);
 	System.out.println(pass);
 	System.out.println(name);
 	System.out.println(sex);
 	System.out.println(email);
 	System.out.println(number);
 	System.out.println(hlist);
 	System.out.println(intro);
 	
*/
%>
        
