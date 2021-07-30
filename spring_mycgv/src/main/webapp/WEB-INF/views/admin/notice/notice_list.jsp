<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		
		//페이징 처리 시작
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
	           $(location).attr('href', "http://localhost:9000/mycgv/notice/notice_list.do?rpage="+e.page);         
	    });
		
		//페이징 처리ㅣ 종료
		
		//전체선택 & 전체해제
		$("#all").click(function(){
			var all_check = $(this).is(":checked");
			if(all_check){
				//모두 선택
				$("input[type=checkbox]").prop("checked", true);
			}else{
				//모두 해제
				$("input[type=checkbox]").prop("checked", false);
			}
		});
		
		//선택 삭제 버튼 처리
		$("#btnDelete").click(function(){
			var choice = confirm("정말로 삭제하시겠습니까?");
			if(choice){
				//삭제 진행 : 여러개를 담는 그릇(배열)
				var chkList = new Array();
				$("input[type=checkbox]:checked").each(function(i){    //선택자를 자동으로 for문 돌려주는 each. i는 자동으로 돌아감
					//체크된 값 중에 all을 제외하고 배열에 담는다
					if($(this).val()!="all"){
						chkList[i] = $(this).val();
					}
				});	
				
				//삭제하는 페이지 호출
				$(location).attr("href","notice_select_delete_proc.do?chkList="+chkList);
			}else{
				$("input[type=checkbox]").prop("checked", false);
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
		<section class="board_list">
			<h1 class="title">관리자 - 공지사항</h1>
			<table border=1 class="content_layout">
				<tr>
					<td colspan="4">
						<a href="notice_write.do"><button type="button">글쓰기</button></a>
						<button type="button" id="btnDelete">선택 삭제</button>
					</td>
				</tr>
				<tr>
					<th><input type="checkbox" name="ntotal" id="all" value="all"></th>
					<th>번호</th>
					<th>제목</th>
					<th>조회수</th>
					<th>등록일</th>
				</tr>
				<c:forEach var="vo" items ="${list }">
				<tr>
					<td><input type="checkbox" name="ntotal" id="part" value="${vo.nid }"></td>
					<td>${vo.rno }</td>
					<td><a href="notice_content.do?nid=${vo.nid }&rno=${vo.rno}">${vo.ntitle }</a></td>
					<td>${vo.nhit }</td>
					<td>${vo.ndate }</td>
				</tr>
				</c:forEach>
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