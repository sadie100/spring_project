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
		<section class="mycgv">
			<h1 class="title">마이CGV</h1>
			<div class="content_layout">
				<div><a href="myinfo_update.jsp">정보수정</a></div>
				<div>예약확인</div>
			</div>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>

<%-- <% }else{ %> --%>
	<!-- <script>
		window.alert("로그인 후 사용 가능합니다.");
		location.href = "http://localhost:9000/mycgv/login/login.jsp";
	</script> -->
<%-- <% } %> --%>