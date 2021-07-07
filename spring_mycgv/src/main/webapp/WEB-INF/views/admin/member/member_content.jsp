<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<section class="member_content">
			<h1 class="title">관리자-회원관리</h1>
			<table class="content_layout">
				<tr>
					<th>번호</th>
					<td>${rno }</td>
					<th>아이디</th>
					<td>${vo.id }</td>
					<th>성명</th>
					<td>${vo.name }</td>
					<th>성별</th>
					<td>${vo.gender }</td>
				</tr>
				<tr>
					<th>이메일주소</th>
					<td colspan="7">${vo.email }</td>
					
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td colspan="7">${vo.hp }</td>
				</tr>
				<tr>
					<th>취미</th>
					<td colspan="7">${vo.hlist }</td>
				</tr>
				<tr>
					<th>자기소개</th>
					<td colspan="7">${vo.intro }</td>
				</tr>
				<tr>
					<td colspan = "8">
						<a href="member_list.do"><button type="button" class="btn_style2">리스트</button></a> 
						<a href="http://localhost:9000/mycgv/admin.do"><button type="button" class="btn_style2">관리자 홈</button></a>
					</td> 
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../../footer.jsp"></jsp:include>
	
</body>
</html>

<%-- <% }else{ %>
	<script>
		window.alert("로그인 후 사용 가능합니다.");
		location.href("http://localhost:9000/mycgv/login/login.jsp");
	</script>
<% } %> --%>