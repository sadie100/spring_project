<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.vo.*" %> 
<%
 	SessionVO svo = (SessionVO)session.getAttribute("svo");
	
 %>
 
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
		<div>
			<nav>
				<% if(svo != null){ %>
				<ul>
					<li><a href="#">안녕하세요~ <%=svo.getName() %>님~^^</a></li>
					<li><a href="http://localhost:9000/mycgv/login/logout.jsp">로그아웃</a></li>
					<li><a href="http://localhost:9000/mycgv/join/join.jsp">회원가입</a></li>
					<li><a href="http://localhost:9000/mycgv/mycgv/mycgv.jsp">MyCGV</a></li>
					<li><a href="http://localhost:9000/mycgv/board/board_list.jsp">게시판</a></li>
					<li><a href="http://localhost:9000/mycgv/notice/notice_list.jsp">공지사항</a></li>
					<li><a href="#">VIP라운지</a></li>
					<% if(svo.getId().equals("admin")){ %>
					<li><a href="http://localhost:9000/mycgv/admin/admin.jsp">Admin</a></li>
					<%} %>
				</ul>
				<% }else{ %>
				<ul>
					<li><a href="http://localhost:9000/mycgv/login.do">로그인</a></li>
					<li><a href="http://localhost:9000/mycgv/join.do">회원가입</a></li>
					<li><a href="http://localhost:9000/mycgv/mycgv.do">MyCGV</a></li>
					<li><a href="http://localhost:9000/mycgv/board_list.do">게시판</a></li>
					<li><a href="http://localhost:9000/mycgv/notice_list.do">공지사항</a></li>
					<li><a href="#">VIP라운지</a></li>
					<li><a href="http://localhost:9000/mycgv/admin.do">Admin</a></li>
				</ul>
				<% } %>
			</nav>	
			<div>
				<a href="http://localhost:9000/mycgv/index.do"><img src="http://localhost:9000/mycgv/images/h1_cgv.png"></a>
				<div>
					<img src="http://localhost:9000/mycgv/images/h2_cultureplex.png">
					<nav>
						<ul>
							<li><a href="#">영화</a></li>
							<li><a href="#">예매</a></li>
							<li><a href="#">극장</a></li>
							<li><a href="#">이벤트&컬쳐</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</header>
</body>
</html>