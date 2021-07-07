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
		<section class="board_delete">
			<h1 class="title">관리자 - 공지사항</h1>
			<div class="content_layout">
				<h3>정말로 삭제하시겠습니까?</h3>
				<img src="../../images/img_1.jpg">
				<div>
					<a href="notice_delete_proc.do?nid=${nid }"><button type="button" class="btn_style2">삭제 완료</button></a>
					<a href="notice_content.do?nid=${nid }&rno=${rno}"><button type ="button" class="btn_style2">이전 페이지</button></a>
					<a href="notice_list.do"><button type ="button" class="btn_style2">리스트</button></a>
				</div>
			</div>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../../footer.jsp"></jsp:include>
	
</body>
</html>