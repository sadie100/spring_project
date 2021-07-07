<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/mycgv/css/mycgv.css">
<script src="http://localhost:9000/mycgv/js/jquery-3.6.0.min.js"></script>
<style>
	.fileCover{
		width: 150px;
		display : inline-block;
		font-size: 13px;
		margin-left: -355px;
		background-color: white;
		padding: 2px 0 2px 2px;
	}
</style>
<script>
	$(document).ready(function(){
		$("#nfile").change(function(){
			if(window.FileReader){
				var filename = $(this)[0].files[0].name;
				$("#fileCover").text("").text(filename);
			}
		});
	});
</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../../header.jsp"></jsp:include>
 

	<!-- content -->
	<div class="content">
		<section class="board_write">
			<h1 class="title">관리자 - 공지사항</h1>
			<form name="notice_update" action="notice_update_proc.do" method="post" enctype= "multipart/form-data">
				<input type="hidden" name="nid" value="${vo.nid }">
				<table class="content_layout">
					<tr>
						<th>제목</th>
						<td><input type="text" name="ntitle" value="${vo.ntitle }"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="ncontent">${vo.ncontent } </textarea></td>
					</tr>
					<tr>
						<th>파일</th>
						<td>
						<c:choose>
							<c:when test="">
								<input type="file" name="file1" id="nfile"><span class="fileCover" id="fileCover">${vo.nfile }</span>
							</c:when>
							<c:otherwise>
								<input type="file" name="file1" id="nfile"><span class="fileCover" id="fileCover">선택한 파일 없음</span>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type ="submit" class="btn_style2">수정 완료</button>
							<button type ="reset" class="btn_style2">취소</button>
							<a href="notice_content.do?nid=${vo.nid }&rno=${rno}"><button type ="button" class="btn_style2">이전 페이지</button></a>
							<a href="notice_list.do"><button type ="button" class="btn_style2">리스트</button></a>
						</td>
					</tr>
				</table>
			</form>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../../footer.jsp"></jsp:include>
	
</body>
</html>