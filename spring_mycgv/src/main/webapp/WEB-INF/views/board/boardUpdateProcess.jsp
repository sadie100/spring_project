<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="com.mycgv.vo.*, com.mycgv.dao.*, java.io.*" %>
  <%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%> 
    <%
	  //파일이 저장될 서버의 경로. 되도록이면 getRealPath를 이용하자.
  	String savePath = request.getServletContext().getRealPath("/upload");
  	
  	//파일 크기 15MB로 제한
  	int sizeLimit = 1024*1024*15;
  	                                                      
  	//↓ request 객체,               ↓ 저장될 서버 경로,       ↓ 파일 최대 크기,    ↓ 인코딩 방식,       ↓ 같은 이름의 파일명 방지 처리
  	//(HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
  	//아래와 같이 MultipartRequest를 생성만 해주면 파일이 업로드 된다.(파일 자체의 업로드 완료)
  	MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
  	
  	//파일명 확인
  	System.out.println("화면UI이름=" + multi.getOriginalFileName("bfile") );
  	System.out.println("폴더저장이름=" + multi.getFilesystemName("bfile")  );
  	
  	//기존 파일 그대로 유지 ---> bfile : null값이 됨
  	//새로운 파일 선택 & 파일 수정 ---> bfile: 선택된 파일명
  	
  	
    BoardVO vo = new BoardVO();
 	BoardDAO dao = new BoardDAO();
 	boolean result = false;
 	
  	if(multi.getOriginalFileName("bfile")!=null){
  		//새로운 파일 선택
	 	vo.setBid(multi.getParameter("bid"));
	 	vo.setBtitle(multi.getParameter("btitle"));
	 	vo.setBcontent(multi.getParameter("bcontent"));
	 	vo.setBfile(multi.getOriginalFileName("bfile"));
	 	vo.setBsfile(multi.getFilesystemName("bfile"));
	 	
	 	result = dao.getUpdateResult(vo);	//파일 포함 업데이트
	 	
	 	if(result){
		 	//기존파일을 upload 폴더에서 삭제(DB에서 새 파일 업데이트한 후 삭제해야 함)
		 	System.out.println(multi.getParameter("bsfile_old"));
		 	System.out.println(savePath + "/" + multi.getParameter("bsfile_old"));
			
		 	String old_file_path = savePath + "/" + multi.getParameter("bsfile_old");
		 	File old_file = new File(old_file_path);
		 	if(old_file.exists()){	//파일이 존재하면 true
		 		if(old_file.delete()){	//파일 삭제하면 결과가 boolean값으로 나옴.
		 			System.out.println("파일 삭제 완료!!");
		 		}
		 	}
	 	}
	 	
  	}else{
  		//기존 파일 유지
	 	vo.setBid(multi.getParameter("bid"));
	 	vo.setBtitle(multi.getParameter("btitle"));
	 	vo.setBcontent(multi.getParameter("bcontent"));
	 	
	 	result = dao.getUpdateResultNofile(vo);	//파일 포함 X 업데이트
  	}
  	
 	
 	if(result){
 		response.sendRedirect("board_list.jsp");
 	}
    %>
    