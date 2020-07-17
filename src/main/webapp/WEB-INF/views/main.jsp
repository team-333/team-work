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
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
</head>
<body style="height: 5000px;">

<%@ include file="header.jsp" %>

<main class="main-main">
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

	<section class="container" >
		<div>
		
		</div>
	</section>

</main>

</body>
</html>