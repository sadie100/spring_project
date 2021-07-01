<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.mycgv.dao.NoticeDAO, com.mycgv.vo.NoticeVO, java.util.*, com.mycgv.comms.*" %>
    <%
    String rpage = request.getParameter("page");	//최초 호출시에는 rpage=null
    NoticeDAO dao = new NoticeDAO();	
    Commons commons = new Commons();
    HashMap map = commons.getPage(rpage, dao, "notice");
    int start = (int)map.get("start");
    int end = (int)map.get("end");
    ArrayList<NoticeVO> list = dao.getList(start, end);
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
	           $(location).attr('href', "http://localhost:9000/mycgv/admin/notice/notice_list.do?page="+e.page);         
	    });
		
 	});
</script> 
</head>
<body>
	<!-- header -->
	<jsp:include page="../../header.jsp"></jsp:include>
 

	<!-- content -->
	<div class="content">
		<section class="board_list">
			<h1 class="title">관리자 - 공지사항</h1>
			<table border=1 class="content_layout">
				<tr>
					<td colspan="4">
						<a href="notice_write.do"><button type="button">글쓰기</button></a>
					</td>
				</tr>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>조회수</th>
					<th>등록일</th>
				</tr>
				<% for(NoticeVO vo : list){ %>
				<tr>
					<td><%= vo.getRno() %></td>
					<td><a href="notice_content.do?nid=<%=vo.getNid()%>&rno=<%=vo.getRno()%>"><%= vo.getNtitle() %></a></td>
					<td><%=vo.getNhit() %></td>
					<td><%=vo.getNdate() %></td>
				</tr>
				<% } %>
				<tr>
					<td colspan=4><div id="ampaginationsm"></div></td>
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../../footer.jsp"></jsp:include>
	
</body>
</html>