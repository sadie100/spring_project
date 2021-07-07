<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.vo.*, com.mycgv.dao.*, java.util.*, com.mycgv.comms.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/mycgv/css/mycgv.css">
<link rel="stylesheet" href="http://localhost:9000/mycgv/css/am-pagination.css">
<script src="http://localhost:9000/mycgv/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/mycgv/js/am-pagination.js"></script>
<script>
	$(document).ready(function(){
		
		var pager = jQuery('#ampaginationsm').pagination({
		
		    maxSize: 7,	    		// max page size
		    totals: ${dbCount},	// total pages	
		    page: ${rpage},		// initial page		
		    pageSize: ${pageSize},			// max number items per page
		
		    // custom labels		
		    lastText: '&raquo;&raquo;', 		
		    firstText: '&laquo;&laquo;',		
		    prevText: '&laquo;',		
		    nextText: '&raquo;',
				     
		    btnSize:'sm'	// 'sm'  or 'lg'		
		});
		
		jQuery('#ampaginationsm').on('am.pagination.change',function(e){
			   jQuery('.showlabelsm').text('The selected page no: '+e.page);
	           $(location).attr('href', "http://localhost:9000/mycgv/board_list.do?rpage="+e.page);         
	    });
		
 	});
</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
 

	<!-- content -->
	<div class="content">
		<section class="board_list">
			<h1 class="title">게시판</h1>
			<table border=1 class="content_layout">
				<tr>
					<td colspan="4">
						<a href="board_write.do"><button type="button">글쓰기</button></a>
					</td>
				</tr>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>조회수</th>
					<th>등록일</th>
				</tr>
				<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.rno }</td>
					<td><a href="board_content.do?bid=${vo.bid }&rno=${vo.rno}">${vo.btitle}</a></td>
					<td>${vo.bhit}</td>
					<td>${vo.bdate}</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan=4><div id="ampaginationsm"></div></td>
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>