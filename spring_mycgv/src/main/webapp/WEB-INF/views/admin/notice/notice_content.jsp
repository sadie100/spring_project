<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
					<td>${rno }</td>
					<th>날짜</th>
					<td>${vo.ndate }</td>
					<th>조회수</th>
					<td>${vo.nhit }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5">${vo.ntitle }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="5">${content }<br><br><br>
					<c:if test="${vo.nsfile ne null }">
						<img src="http://localhost:9000/mycgv/upload/${vo.nsfile }" width="200" height="100">
					</c:if>
					</td>
				</tr>
				<tr>
					<td colspan = "6">
						<a href="notice_update.do?nid=${vo.nid }&rno=${rno}"><button type="button" class="btn_style2">수정</button></a> 
						<a href="notice_delete.do?nid=${vo.nid }&rno=${rno}"><button type="button" class="btn_style2">삭제</button></a> 
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