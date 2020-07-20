<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 | Main</title>
<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
</head>
<body>

<%@ include file="header.jsp" %>

<main class="main-main">
	<section class="groupList">
		<div class="groupList__profile">
			<img class="profile__pic" alt="" src="https://www.topstarnews.net/news/photo/first/201711/img_327473_1.jpg" />
			<a href="">홍진호</a>
		</div>
		<hr>
		
		<div class="gruopList__list" style="margin-top: 30px;">
			<ul>
				<li class="list-context"><a href="${cpath }/myprofile/">내 프로필</a></li>
				<li class="list-context"><a href="${cpath }/mystudies/">내 스터디</a></li>
				<li class="list-context"><a href="${cpath }/myinfo/" style="font-weight: 700; text-decoration: underline;">내 정보</a></li>
				<li class="list-context"><a href="">로그 아웃</a></li>
			</ul>
		</div>
	</section>
	
	<!-- ajax로 update 처리 -->
	<section class="container profile-setting-container" >
		<div class="profile-setting__name">
			 <span>홍진호</span>
			 <i class="fas fa-pencil-alt"></i>
		</div>
		<div class="profile-setting__intro__title">
			<span>Title</span>
			<i class="fas fa-pencil-alt"></i>
		</div>
		<div class="profile-setting__intro__context">
			<div class="textarea"> 폭풍저그 홍진호가 간다!<br>
			 야, 세르게이! 작은 고추의 매운 맛을 보여주마!
				<i class="fas fa-pencil-alt"></i>
			</div>
		</div>
		<div class="profile-setting__email">
			<span> kong22@ongame.net</span>
			<i class="fas fa-pencil-alt"></i>
		</div>
		
	
	</section>

</main>

</body>
</html>