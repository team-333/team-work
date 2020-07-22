<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cpath">${pageContext.request.contextPath }</c:set>
<!DOCTYPE html>
<html>
<head>
<title>게시물</title>
</head>
<body>
	<link rel="stylesheet" href="${cpath}/css/board.css" />
	<!-- Jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
	<script src="${cpath }/js/board.js"></script>
	<!-- Header -->	
	<script src="https://kit.fontawesome.com/cc3f76d574.js"	crossorigin="anonymous" ></script>
	
	<header>
		<div class="wrapper">
			<input type="text" placeholder="검색기능" />
			<div class="search-button">
				<i class="fas fa-search"></i>

			</div>
		</div>
	</header>
	
	
	<!-- Modal-background -->
	<div id="myModal" class="modal"></div>
	
	<section>
	<!-- 게시물 쓰기 -->
		<article id="wr" style="height: 110px;">
			<div class="art1">
					<div class="head"><span>게시물 쓰기</span></div>
					<div class="img"></div>
					<textarea id="art1_box" class="box"></textarea>
					<div id="art1_btn" class="btn">게 시</div>
			</div>
		</article>
	
	<!-- 게시물 목록 -->
		<article id="list"></article>
		<article style="height: 100px;"></article>
	</section>
</body>
</html>


