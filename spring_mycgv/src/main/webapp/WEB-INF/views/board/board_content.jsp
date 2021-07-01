<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.vo.*, com.mycgv.dao.*, java.util.*" %>
  <%
  String bid = request.getParameter("bid");
  String rno = request.getParameter("rno");
  
  BoardDAO dao = new BoardDAO();
  BoardVO vo = dao.getContent(bid);
  if(vo!=null) dao.getUpdateHit(bid);
  String content = vo.getBcontent().replace("\r\n","<br>");
  
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
	<jsp:include page="../header.jsp"></jsp:include>
 

	<!-- content -->
	<div class="content">
		<section class="board_content">
			<h1 class="title">게시판</h1>
			<table class="content_layout">
				<tr>
					<th>번호</th>
					<td><%= rno %></td>
					<th>날짜</th>
					<td><%= vo.getBdate() %></td>
					<th>조회수></th>
					<td><%= vo.getBhit() %></td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5"><%=vo.getBtitle() %></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="5"><%= content %><br><br><br>
					<%if(vo.getBfile()!=null){ %>
					<img src="http://localhost:9000/mycgv/upload/<%=vo.getBsfile() %>" width="400" height="200">
					<%} %>
					</td>
				</tr>
				<tr>
					<td colspan = "6">
						<a href="board_update.do?bid=<%= bid%>&rno=<%=rno%>"><button type="button" class="btn_style2">수정</button></a> 
						<a href="board_delete.do?bid=<%= bid%>&rno=<%=rno%>"><button type="button" class="btn_style2">삭제</button></a> 
						<a href="board_list.do"><button type="button" class="btn_style2">리스트</button></a> 
						<a href="http://localhost:9000/mycgv/index.do"><button type="button" class="btn_style2">홈으로</button></a>
					</td> 
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>