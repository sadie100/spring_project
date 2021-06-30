<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:9000/mycgv/css/mycgv.css">
<link rel="stylesheet" href="http://localhost:9000/mycgv/css/carousel.css">
<script src="js/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
 
</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>

	<!-- content -->
	 
	 <!-- 캐러셀 시작 -->
	 <div id="demo" class="carousel slide" data-ride="carousel">

	  <!-- Indicators -->
	  <ul class="carousel-indicators">
	    <li data-target="#demo" data-slide-to="0" class="active"></li>
	    <li data-target="#demo" data-slide-to="1"></li>
	    <li data-target="#demo" data-slide-to="2"></li>
	  </ul>
	  
	  <!-- The slideshow -->
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="images/carousel1.jpg" alt="Los Angeles" width="1100" height="500">
	    </div>
	    <div class="carousel-item">
	      <img src="images/carousel2.jpg" alt="Chicago" width="1100" height="500">
	    </div>
	    <div class="carousel-item">
	      <img src="images/carousel3.jpg" alt="New York" width="1100" height="500">
	    </div>
	  </div>
	  
	  <!-- Left and right controls -->
	  <a class="carousel-control-prev" href="#demo" data-slide="prev">
	    <span class="carousel-control-prev-icon"></span>
	  </a>
	  <a class="carousel-control-next" href="#demo" data-slide="next">
	    <span class="carousel-control-next-icon"></span>
	  </a>
	</div>
	 
	 <!-- 캐러셀 종료 -->
	 
	<div class="content">
		<section>
			<div><img src="http://localhost:9000/mycgv/images/h3_movie_selection.gif"></div>
			<article>
				<iframe width="740" height="388" src="https://www.youtube.com/embed/bQm0_3lgfvo" 
				title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; 
				clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				<img src="http://localhost:9000/mycgv/images/240x388.jpg">
			</article>
		</section>
		<section>
			<div><img src="http://localhost:9000/mycgv/images/h3_event.gif"></div>
			<article>
				<img src="http://localhost:9000/mycgv/images/img_1.jpg">
				<img src="http://localhost:9000/mycgv/images/img_2.jpg">
				<img src="http://localhost:9000/mycgv/images/img_3.jpg">
				<img src="http://localhost:9000/mycgv/images/img_4.jpg">				
			</article>
			<article>
				<img src="http://localhost:9000/mycgv/images/img_5.jpg">				
				<img src="http://localhost:9000/mycgv/images/img_6.jpg">				
				<img src="http://localhost:9000/mycgv/images/img_7.png">				
			
			</article>
			<article>
				<!-- 공지사항 -->
			</article>
		</section>
	</div>
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>