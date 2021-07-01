<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.vo.*, com.mycgv.dao.*, java.util.*, com.mycgv.comms.*" %>
<%
String rpage = request.getParameter("page");	//최초 호출시에는 rpage=null
BoardDAO dao = new BoardDAO();	
Commons commons = new Commons();
HashMap map = commons.getPage(rpage, dao, "board");

int start = (int)map.get("start");
int end = (int)map.get("end");
ArrayList<BoardVO> list = dao.getList(start, end);

/*
//페이징 처리 - startCount, endCount 구하기
int startCount = 0;
int endCount = 0;
int pageSize = 5;	//한페이지당 게시물 수
int reqPage = 1;	//요청페이지	
int pageCount = 1;	//전체 페이지 수
int dbCount = dao.execTotalCount();	//DB에서 가져온 전체 행수 : 5 

//총 페이지 수 계산. pagecount : 현재 페이지
if(dbCount % pageSize == 0){
	pageCount = dbCount/pageSize;
}else{
	pageCount = dbCount/pageSize+1;
}

//요청 페이지 계산
if(rpage != null){
	reqPage = Integer.parseInt(rpage);
	startCount = (reqPage-1) * pageSize+1;
	endCount = reqPage *pageSize;
}else{
	startCount = 1;
	endCount = 5;
}
*/


//BoardDAO dao = new BoardDAO();
//ArrayList<BoardVO> list = dao.getList();
%>



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
		    totals: <%=map.get("dbCount")%>,	// total pages	
		    page: <%=map.get("rpage")%>,		// initial page		
		    pageSize: <%=map.get("pageSize")%>,			// max number items per page
		
		    // custom labels		
		    lastText: '&raquo;&raquo;', 		
		    firstText: '&laquo;&laquo;',		
		    prevText: '&laquo;',		
		    nextText: '&raquo;',
				     
		    btnSize:'sm'	// 'sm'  or 'lg'		
		});
		
		jQuery('#ampaginationsm').on('am.pagination.change',function(e){
			   jQuery('.showlabelsm').text('The selected page no: '+e.page);
	           $(location).attr('href', "http://localhost:9000/mycgv/board/board_list.jsp?page="+e.page);         
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
				<% for(BoardVO vo: list){ %>
				<tr>
					<td><%=vo.getRno() %></td>
					<td><a href="board_content.do?bid=<%=vo.getBid()%>&rno=<%=vo.getRno()%>"><%=vo.getBtitle() %></a></td>
					<td><%=vo.getBhit() %></td>
					<td><%=vo.getBdate() %></td>
				</tr>
				<% } %>
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