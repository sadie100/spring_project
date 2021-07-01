<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "com.mycgv.vo.SessionVO" %>
<% 
	/* SessionVO svo = (SessionVO)session.getAttribute("svo");
	if(svo !=null){ */
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
		<section class="admin">
			<h1 class="title">관리자 페이지</h1>
			<div class="content_layout">
				<a href="member/member_list.do"><div>회원 관리</div></a>
				<a href="notice/notice_list.do"><div>공지사항 관리</div></a>
			</div>
		</section>
		</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>

<%-- <% }else{ %>
	<script>
		window.alert("로그인 후 사용 가능합니다.");
		location.href("http://localhost:9000/mycgv/login/login.jsp");
	</script>
<% } %> --%>