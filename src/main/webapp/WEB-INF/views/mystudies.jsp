<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 | My Study</title>
<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
</head>
<body>

<%@ include file="header.jsp" %>

<main class="main-main">
	<section class="groupList">
		<div class="groupList__profile">
			<img class="profile__pic" alt="" src="${login.pictureUrl }" />
			<a href="${cpath }/myprofile/${login.memberId}/">${login.username }</a>
		</div>
		<hr>
		
		<div class="gruopList__list" style="margin-top: 30px;">
			<ul>
				<li class="list-context"><a href="${cpath }/myprofile/${login.memberId}/">내 프로필</a></li>
				<li class="list-context"><a href="${cpath }/mystudies/${login.memberId}/" style="font-weight: 700; text-decoration: underline;">내 스터디</a></li>
				<li class="list-context"><a href="${cpath }/myinfo/${login.memberId}/">내 정보</a></li>
				<li class="list-context"><a href="">로그 아웃</a></li>
			</ul>
		</div>
	</section>
	
	<!-- ajax로 update 처리 -->
	<section class="container profile-setting-container" >
		<c:if test="${not empty memberStudylist }">
			<div class="profile-setting__name studies-list">
				<c:forEach items="${memberStudylist}" var="study">			
					<div class="study-container">
						<a href="${cpath }/study/${study.teamId}/"><i style="margin-right:10px;" class="fas fa-book"></i>${study.teamName }</a>
						<div class="studyBtns">
							<c:if test="${study.delegate eq login.memberId }">
								<a href="${cpath }/delegate/${study.teamId}/"><button>관리</button></a>
							</c:if>
							<a href="${cpath}/signout/${study.teamId}/${login.memberId}/"><button>탈퇴</button></a>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${empty memberStudylist }">
			<div style="padding: 30px 0;font-weight: 700;font-size: 1.1rem;" class="study-container">
				스터디에 가입해 보세요!
			</div>
		</c:if>
	</section>

</main>


</body>
</html>