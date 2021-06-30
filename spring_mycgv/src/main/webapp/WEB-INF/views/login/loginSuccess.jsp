<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "com.mycgv.vo.*" %>
 <%
 	SessionVO svo = (SessionVO)session.getAttribute("svo");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/mycgv/css/mycgv.css">
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/mycgv/js/mycgv.js"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	

	<!-- content -->
	<div class="content">
		<section class="login">
			<h1 class="title">로그인</h1>
			<h3> 로그인에 성공하셨습니다 >>> <%=svo.getId()%>, <%= svo.getName() %></h3>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>