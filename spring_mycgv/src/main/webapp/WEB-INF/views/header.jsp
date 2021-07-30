<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.vo.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
 	SessionVO svo = (SessionVO)session.getAttribute("svo");
 --%>
 
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
				<c:choose>
				<c:when test="${sessionScope.svo!=null }">
				<ul>
					<li><a href="#">안녕하세요~ ${sessionScope.svo.name }님~^^</a></li>
					<li><a href="http://localhost:9000/mycgv/logout.do">로그아웃</a></li>
					<li><a href="http://localhost:9000/mycgv/join.do">회원가입</a></li>
					<li><a href="http://localhost:9000/mycgv/mycgv.do">MyCGV</a></li>
					<li><a href="http://localhost:9000/mycgv/board_list.do">게시판</a></li>
					<li><a href="http://localhost:9000/mycgv/notice_list.do">공지사항</a></li>
					<li><a href="#">VIP라운지</a></li>
					<c:if test="${sessionScope.svo.id=='admin' }">
					<li><a href="http://localhost:9000/mycgv/admin.do">Admin</a></li>
					</c:if>
				</ul>
				</c:when>
				<c:otherwise>
				<ul>
					<li><a href="http://localhost:9000/mycgv/login.do">로그인</a></li>
					<li><a href="http://localhost:9000/mycgv/join.do">회원가입</a></li>
					<li><a href="http://localhost:9000/mycgv/mycgv.do">MyCGV</a></li>
					<li><a href="http://localhost:9000/mycgv/board_list.do">게시판</a></li>
					<li><a href="http://localhost:9000/mycgv/notice_list.do">공지사항</a></li>
					<li><a href="#">VIP라운지</a></li>
					<!-- <li><a href="http://localhost:9000/mycgv/admin.do">Admin</a></li>-->
				</ul>
				</c:otherwise>
				</c:choose>
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