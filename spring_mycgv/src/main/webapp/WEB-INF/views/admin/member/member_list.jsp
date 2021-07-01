<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="com.mycgv.dao.MemberDAO, com.mycgv.vo.*, java.util.*" %>
    <% 
   /*  SessionVO svo = (SessionVO)session.getAttribute("svo");
	if(svo !=null){ */
    
    String rpage = request.getParameter("page");	//최초 호출시에는 rpage=null
    MemberDAO dao = new MemberDAO();	

    //페이징 처리 - startCount, endCount 구하기
    int startCount = 0;
    int endCount = 0;
    int pageSize = 3;	//한페이지당 게시물 수
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
    	startCount = reqPage;
    	endCount = pageSize;
    }

    ArrayList<MemberVO> list = dao.getList(startCount, endCount);

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
		    totals: <%=dbCount%>,	// total pages	
		    page: <%=rpage%>,		// initial page		
		    pageSize: <%= pageSize %>,			// max number items per page
		
		    // custom labels		
		    lastText: '&raquo;&raquo;', 		
		    firstText: '&laquo;&laquo;',		
		    prevText: '&laquo;',		
		    nextText: '&raquo;',
				     
		    btnSize:'sm'	// 'sm'  or 'lg'		
		});
		
		jQuery('#ampaginationsm').on('am.pagination.change',function(e){
			   jQuery('.showlabelsm').text('The selected page no: '+e.page);
	           $(location).attr('href', "http://localhost:9000/mycgv/admin/member/member_list.jsp?page="+e.page);         
	    });
		
 	});
</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../../header.jsp"></jsp:include>
 

	<!-- content -->
	<div class="content">
		<section class="member_list">
			<h1 class="title">관리자-회원관리</h1>
			<table border=1 class="content_layout">
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>성명</th>
					<th>핸드폰번호</th>
					<th>성별</th>
					<th>가입일자</th>
					<th>회원탈퇴</th>
				</tr>
				<% for(MemberVO vo : list){		%>
				<tr>
					<td><%= vo.getRno() %></td>
					<td><a href="member_content.do?id=<%=vo.getId() %>&rno=<%=vo.getRno()%>"><%=vo.getId() %></a></td>
					<td><%=vo.getName() %></td>
					<td><%= vo.getHp() %></td>
					<td><%= vo.getGender() %></td>
					<td><%= vo.getMdate() %></td>
					<td>
						<% if(vo.getChoice() == 0){ %>
						<button type="button" disabled>신청</button>
						<% }else{ %>
						<button type="button" >신청</button>
						<% } %>
					</td>
				</tr>
				<% } %>
				<tr>
						<td colspan=7><div id="ampaginationsm"></div></td>
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