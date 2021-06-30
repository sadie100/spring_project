<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.mycgv.dao.NoticeDAO, com.mycgv.vo.NoticeVO, java.util.*" %>
   <%
   	NoticeDAO dao = new NoticeDAO();
    ArrayList<NoticeVO> list = dao.getList();
   	
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
		<section class="board_list">
			<h1 class="title">공지사항</h1>
			<table border=1 class="content_layout">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>조회수</th>
					<th>등록일</th>
				</tr>
				<% for(NoticeVO vo:list){ %>
				<tr>
					<td><%=vo.getRno() %></td>
					<td><a href="notice_content.jsp?nid=<%=vo.getNid() %>&rno=<%=vo.getRno() %>"><%=vo.getNtitle() %></a></td>
					<td><%=vo.getNhit() %></td>
					<td><%=vo.getNdate() %></td>
				</tr>
				<% } %>
				<tr>
					<td colspan="4"><< 1   2   3   4   5  >></td>
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>	
</body>
</html>