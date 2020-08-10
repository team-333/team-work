<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 | My Info</title>
<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
</head>
<body>

<%@ include file="header.jsp" %>
<script src="${cpath}/js/myinfo.js"></script>

<main class="main-main">
	<section class="groupList">
		<div class="groupList__profile">
			<img class="profile__pic" alt="" src="${login.pictureUrl }" />
			<a id="mainUsername" href="${cpath }/myprofile/${login.memberId}/">${login.username }</a>
		</div>
		<hr>
		
		<div class="gruopList__list" style="margin-top: 30px;">
			<ul>
				<li class="list-context"><a href="${cpath }/myprofile/${login.memberId}/">내 프로필</a></li>
				<li class="list-context"><a href="${cpath }/mystudies/${login.memberId}/">내 스터디</a></li>
				<li class="list-context"><a href="${cpath }/myinfo/${login.memberId}" style="font-weight: 700; text-decoration: underline;">내 정보</a></li>
				<li class="list-context"><a href="${cpath }/logout/">로그 아웃</a></li>
			</ul>
		</div>
	</section>
	
	<!-- ajax로 update 처리 -->
	<section class="container profile-setting-container" >
		<article class="info-container">
			<div class="info-container__nametag">
				<div class="profile-setting__name">
					 <span id="title_username">${login.username }</span>
					 <i id="title_btn" class="fas fa-pencil-alt" onClick="change_username('${cpath}')"></i>
				</div>
				<div class="profile-setting__email">
					<span id="title_email">${login.email }</span>
					<i id="title_btn2" class="fas fa-pencil-alt"></i>
				</div>
			</div>
			
			<div class="profile-setting__pic profile-setting__intro__title">
				<c:if test="${empty login.pictureUrl }">
					<div id="preview" class="profile-pic__preview">
						<img alt="picture" src="${cpath }/img/profile-picture-default.png" />
					</div>
				</c:if>
				<c:if test="${not empty login.pictureUrl }">
					<div id="preview" class="profile-pic__preview">
						<img alt="picture" src="${login.pictureUrl}" />
					</div>
				</c:if>
				
				
				<form method="post" action="${cpath}/uploadpic/" enctype="multipart/form-data">
					<label for="profile-pic">업로드</label>
					<input type="file" id="profile-pic" name="profile-pic"/>
					<button class="profile-pic-btn">적용</button>
				</form>
			</div>	
		</article>
		
		<div class="profile-setting__intro__context profile-setting__password-change">
			<span>비밀번호 변경</span>
			
			<div id="pdiv" class="changeForm-wrapper">
				<input id="password" class="changeForm" type="password" name="cntpassword" placeholder="현재 비밀번호"/>
				<div class="passwordCheck"><img class= "icons" id="picons" src=""></div>
			</div>		
			
			
			<div id="pdiv" class="changeForm-wrapper">
				<input id="newpassword" class="changeForm" type="password" name="newpassword" placeholder="새로운 비밀번호"/>
				<div class="passwordCheck"><img class= "icons" id="p2icons" src=""></div>
			</div>			
			
			<div id="p2div"class="changeForm-wrapper">
				<input id="newpassword2" class="changeForm" type="password" name="newpassword2" placeholder="비밀번호 확인" />
				<div class="password2Check"><img class= "icons" id="p3icons" src=""></div>
			</div>

			<button id="submit">변경</button>
		</div>
		
		
	
	</section>

</main>

<script src="${cpath }/js/preview.js"></script>

<script src="${cpath }/js/myinfo2.js"></script>


</body>
</html>