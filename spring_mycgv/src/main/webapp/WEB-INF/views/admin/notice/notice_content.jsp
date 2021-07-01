<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.dao.NoticeDAO, com.mycgv.vo.NoticeVO, java.util.*" %>
<%
	String nid = request.getParameter("nid");
	String rno = request.getParameter("rno");
	
	NoticeDAO dao = new NoticeDAO();
	NoticeVO vo = dao.getContent(nid);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/mycgv/css/mycgv.css">

</head>
<body>
	<!-- header -->
	<jsp:include page="../../header.jsp"></jsp:include>
 

	<!-- content -->
	<div class="content">
		<section class="board_content">
			<h1 class="title">관리자 - 공지사항</h1>
			<table class="content_layout">
				<tr>
					<th>번호</th>
					<td><%= rno %></td>
					<th>날짜</th>
					<td><%= vo.getNdate() %></td>
					<th>조회수</th>
					<td><%= vo.getNhit() %></td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5"><%=vo.getNtitle() %></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="5"><%=vo.getNcontent() %><br><br><br>
					<%if(vo.getNfile()!=null){%>
					<img src="http://localhost:9000/mycgv/upload/<%= vo.getNsfile() %>" width="200" height="100">
					<%} %>
					</td>
				</tr>
				<tr>
					<td colspan = "6">
						<a href="notice_update.do?nid=<%=nid%>&rno=<%=rno%>"><button type="button" class="btn_style2">수정</button></a> 
						<a href="notice_delete.do?nid=<%=nid%>&rno=<%=rno%>"><button type="button" class="btn_style2">삭제</button></a> 
						<a href="notice_list.do"><button type="button" class="btn_style2">리스트</button></a> 
						<a href="http://localhost:9000/mycgv/admin.do"><button type="button" class="btn_style2">관리자 홈으로</button></a>
					</td> 
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../../footer.jsp"></jsp:include>
	
</body>
</html>