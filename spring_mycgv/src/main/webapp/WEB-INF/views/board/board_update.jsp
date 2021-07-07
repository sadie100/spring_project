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
<script src="http://localhost:9000/mycgv/js/mycgv.js"></script>
<style>
	span#frame {
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
	$("input[type=file]").on('change',function(){
		if(window.FileReader){
			var filename = $(this)[0].files[0].name;
			$("#frame").text("").text(filename);
		}
	});
});
</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
 

	<!-- content -->
	<div class="content">
		<section class="board_write">
			<h1 class="title">게시판</h1>
			<form name="board_update_form" action="board_update_proc.do" method="post" enctype = "multipart/form-data">
				<input type="hidden" name="bid" value="${vo.bid }">
				<input type="hidden" name="bsfile_old" value="${vo.bsfile }">
				<table class="content_layout">
					<tr>
						<th>제목</th>
						<td><input type="text" name="btitle" value="${vo.btitle }" id="btitle"></td>
					</tr>
					<tr> 
						<th>내용</th>
						<td><textarea name="bcontent">${vo.bcontent }</textarea></td>
					</tr>
					<tr>
						<th>파일</th>
						<td>
							<c:choose>
							<c:when test="${vo.bfile ne null }">
								<input type="file" name="file1"><span id="frame">${vo.bfile }</span>
							</c:when>
							<c:otherwise>
								<input type="file" name="file1"><span id="frame">선택된 파일 없음</span>
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type ="button" class="btn_style2" id="btnUpdate">수정 완료</button>
							<button type ="reset" class="btn_style2">취소</button>
							<a href="board_content.do?bid=${vo.bid }&rno=${rno}"><button type ="button" class="btn_style2">이전 페이지</button></a>
							<a href="board_list.do"><button type ="button" class="btn_style2">리스트</button></a>
						</td>
					</tr>
				</table>
			</form>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>