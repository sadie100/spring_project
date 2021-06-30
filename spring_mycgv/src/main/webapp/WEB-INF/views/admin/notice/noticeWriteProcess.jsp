<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.dao.NoticeDAO, com.mycgv.vo.NoticeVO" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%> 

<%
	String savePath = request.getServletContext().getRealPath("/upload");

	int sizeLimit = 1024*1024*15;
	
	MultipartRequest multi = new MultipartRequest(request,savePath,sizeLimit,"UTF-8",new DefaultFileRenamePolicy());
	
	System.out.println("화면UI이름="+multi.getOriginalFileName("nfile"));
	System.out.println("폴더저장이름="+multi.getFilesystemName("nfile"));
	
	NoticeVO vo = new NoticeVO();	
	vo.setNtitle(multi.getParameter("ntitle"));
	vo.setNcontent(multi.getParameter("ncontent"));
	vo.setNfile(multi.getOriginalFileName("nfile"));
	vo.setNsfile(multi.getFilesystemName("nfile"));

	NoticeDAO dao = new NoticeDAO();
	boolean result = dao.getInsertResult(vo);
	if(result){
		response.sendRedirect("notice_list.jsp");
	}
	
%>