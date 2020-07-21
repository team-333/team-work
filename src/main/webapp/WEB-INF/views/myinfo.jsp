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
		<article class="info-container">
			<div class="info-container__nametag">
				<div class="profile-setting__name">
					 <span>홍진호</span>
					 <i class="fas fa-pencil-alt"></i>
				</div>
				<div class="profile-setting__email">
					<span> kong22@ongame.net</span>
					<i class="fas fa-pencil-alt"></i>
				</div>
			</div>
			
			<div class="profile-setting__pic profile-setting__intro__title">
				<div id="preview" class="profile-pic__preview">
					<img alt="picture" src="${cpath }/img/profile-picture-default.png" />
				</div>
				<label for="profile-pic">업로드</label>
				<input type="file" id="profile-pic" name="profile-pic"/>
			</div>	
		</article>
		
		<div class="profile-setting__intro__context profile-setting__password-change">
			<span>비밀번호 변경</span>
			
			<div id="pdiv" class="changeForm-wrapper">
				<input class="changeForm" type="password" name="cntpassword" placeholder="현재 비밀번호"/>
				<div class="passwordCheck"><img class= "icons" id="picons" src=""></div>
			</div>		
			
			
			<div id="pdiv" class="changeForm-wrapper">
				<input class="changeForm" type="password" name="newpassword" placeholder="새로운 비밀번호"/>
				<div class="passwordCheck"><img class= "icons" id="picons" src=""></div>
			</div>			
			
			<div id="p2div"class="changeForm-wrapper">
				<input class="changeForm" type="password" name="newpassword2" placeholder="비밀번호 확인" />
				<div class="password2Check"><img class= "icons" id="p2icons" src=""></div>
			</div>

			<button>변경</button>
		</div>
		
		
	
	</section>

</main>

<script src="${cpath }/js/preview.js"></script>

</body>
</html>