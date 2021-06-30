<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.mycgv.vo.*, com.mycgv.dao.*, java.util.*" %>
  <%
  String bid = request.getParameter("bid");
  String rno = request.getParameter("rno");
  
  BoardDAO dao = new BoardDAO();
  BoardVO vo = dao.getContent(bid);
  
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
		<section class="board_delete">
			<h1 class="title">게시판</h1>
			<div class="content_layout">
				<h3>정말로 삭제하시겠습니까?</h3>
				<img src="../images/img_1.jpg">
				<div>
				<!-- <form name="board_delete_form" action = "boardDeleteProcess.jsp" method = "get">
					<input type="hidden" name="bid" value="<%=bid%>">
				</form>	 -->
					<a href="boardDeleteProcess.jsp?bid=<%=bid%>"><button type="button" class="btn_style2">삭제 완료</button></a>
					<a href="board_content.jsp?bid=<%=bid%>&rno=<%=rno%>"><button type ="button" class="btn_style2">이전 페이지</button></a>
					<a href="board_list.jsp"><button type ="button" class="btn_style2">리스트</button></a>
				</div>
			</div>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>