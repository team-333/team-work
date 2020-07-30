<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>열공 | Main</title>

<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<<<<<<< HEAD
<link rel="stylesheet" href="${cpath}/css/board.css" />

<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
<script src="${cpath }/js/board.js"></script>
<script type="text/javascript">
</script>
=======
>>>>>>> acb4261052a19aface4b80c9e1cb31ff6eccb3fe
</head>
<body>
<%@ include file="header.jsp" %>
<main class="main-main">
	<!-- Modal-background -->
	<div id="myModal" class="modal"></div>
	
	<section class="groupList">
		<div class="groupList__profile">
			<img class="profile__pic" alt="" src="https://www.topstarnews.net/news/photo/first/201711/img_327473_1.jpg" />
			<a href="">홍진호</a>
		</div>
		<hr>
		<div class="groupTitle">Your Studies</div>
		<div class="group-wrapper"> 
			<!-- enter시 초기화 js 작성 -->
			<input type="text" placeholder="내 스터디 검색" />
		</div>
		
		<div class="gruopList__list">
			<ul>
				<li class="list-context"><a href=""><i class="fas fa-book"></i>(대충 스터디 이름)</a></li>
				<li class="list-context"><a href=""><i class="fas fa-book"></i>(대충 스터디 이름)</a></li>
				<li class="list-context"><a href=""><i class="fas fa-book"></i>(대충 스터디 이름)</a></li>
				<li class="list-context"><a href=""><i class="fas fa-book"></i>(대충 스터디 이름)</a></li>
			</ul>
		</div>
	</section>

	<section class="container">
			<!-- 게시물 쓰기 -->
			<article id="wr" style="height: 110px;">
				<div class="art1">
						<div class="head"><span>게시물 쓰기</span></div>
						<div class="img"></div>
						<div style="padding: 10px 10px 10px 80px">
							<textarea id="art1_box" class="box"></textarea>
						</div>
						<div id="art1_btn" class="btn">게 시</div>
				</div>
			</article>
		
			<!-- 게시물 목록  -->
			<article id="list_board"></article>
			<article style="height: 100px;"></article>
	</section>

</main>

</body>
</html>