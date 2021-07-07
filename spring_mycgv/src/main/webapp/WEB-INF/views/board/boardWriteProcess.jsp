<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="com.mycgv.vo.*, com.mycgv.dao.*" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%> 
<%
	//파일이 저장될 서버의 경로. 되도록이면 getRealPath를 이용하자.
	String savePath = request.getServletContext().getRealPath("/upload");
	System.out.println(savePath);
	
	//파일 크기 15MB로 제한
	int sizeLimit = 1024*1024*15;
	                                                      
	//↓ request 객체,               ↓ 저장될 서버 경로,       ↓ 파일 최대 크기,    ↓ 인코딩 방식,       ↓ 같은 이름의 파일명 방지 처리
	//(HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
	//아래와 같이 MultipartRequest를 생성만 해주면 파일이 업로드 된다.(파일 자체의 업로드 완료)
	MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
	
	//파일명 확인
	System.out.println("화면UI이름=" + multi.getOriginalFileName("bfile") );
	System.out.println("폴더저장이름=" + multi.getFilesystemName("bfile")  );
	
	
    BoardVO vo = new BoardVO();
 	vo.setBtitle(multi.getParameter("btitle"));
 	vo.setBcontent(multi.getParameter("bcontent"));
 	vo.setBfile(multi.getOriginalFileName("bfile"));
 	vo.setBsfile(multi.getFilesystemName("bfile"));
 	
 	
 	
 	BoardDAO dao = new BoardDAO();
 	boolean result = dao.getInsertResult(vo);
 	
 	if(result){
 		response.sendRedirect("board_list.jsp");
 	}
	
%>
