<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 | My Profile</title>
<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

<%@ include file="header.jsp" %>
<script src="${cpath}/js/profile.js"></script>

<main class="main-main">
	<section class="groupList">
		<div class="groupList__profile">
			<img class="profile__pic" alt="" src="${login.pictureUrl }" />
			<a href="${cpath }/myprofile/${login.memberId}/">${login.username }</a>
		</div>
		<hr>
		
		<div class="gruopList__list" style="margin-top: 30px;">
			<ul>
				<li class="list-context"><a href="${cpath }/myprofile/${login.memberId}/"  style="font-weight: 700; text-decoration: underline;">내 프로필</a></li>
				<li class="list-context"><a href="${cpath }/mystudies/${login.memberId}/">내 스터디</a></li>
				<li class="list-context"><a href="${cpath }/myinfo/${login.memberId}/">내 정보</a></li>
				<li class="list-context"><a href="">로그 아웃</a></li>
			</ul>
		</div>
	</section>
	
	<!-- ajax로 update 처리 -->
	<section class="container profile-setting-container" >
		<div class="profile-setting__intro__title" >
			<c:if test="${empty login.introduce }">
				<div id="title">프로필 제목을 입력하세요</div>
				<i id="title_btn" class="fas fa-pencil-alt" onClick="change_title('${cpath}')" ></i>
			</c:if>
			<c:if test="${not empty login.introduce }">
				<div id="title">${login.introduce }</div>
				<i id="title_btn" class="fas fa-pencil-alt" onClick="change_title('${cpath}')"></i>
			</c:if>
		</div>
		<c:if test="${empty login.introduceContext }">	
			<div class="profile-setting__intro__context">
				<div id="context" class="textarea"> 
					내용을 입력하세요.
				</div>
				<i id="title_btn2" class="fas fa-pencil-alt" onClick="change_context('${cpath}')"></i>
			</div>
		</c:if>

		<c:if test="${not empty login.introduceContext }">
			<div class="profile-setting__intro__context">
				<div id="context" class="textarea"> 
					${login.introduceContext }
				</div>
				<i id="title_btn2" class="fas fa-pencil-alt" onClick="change_context('${cpath}')"></i>
			</div>
		</c:if>
		
		<div class="profile-setting__email">
			<span>${login.email }</span>
		</div>
		
	
	</section>

</main>


</body>
</html>