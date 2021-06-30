<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.mycgv.vo.*, com.mycgv.dao.*, java.io.File" %>
    <%
  String bid = request.getParameter("bid");
  
  BoardDAO dao = new BoardDAO();
  String bsfile = dao.getBsfile(bid);	//삭제되기 전에 불러와야 함
	boolean result = dao.getDeleteResult(bid);
	
	
	if(result){
		if(bsfile!=null){
			
		  String savePath = request.getServletContext().getRealPath("/upload");
		  
		  File file = new File(savePath+"/"+bsfile);
		  if(file.exists()){
			  if(file.delete()) System.out.println("파일삭제 완료");
		  }
		}
	 }

  
  if(result){
		response.sendRedirect("board_list.jsp");
	}
  
  %>