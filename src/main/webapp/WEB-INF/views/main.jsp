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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>

<script src="${cpath}/js/main.js"></script>

</head>
<body>

<%@ include file="header.jsp" %>

<main class="main-main">
	<section class="groupList">
		<div class="groupList__profile">
			<c:if test="${not empty login.pictureUrl }">
				<img class="profile__pic" alt="" src="${login.pictureUrl }" />
			</c:if>
			<c:if test="${empty login.pictureUrl }" >
				<img class="profile__pic" alt="" src="${cpath }/img/profile-picture-default.png" />
			</c:if>
			<a href="${cpath }/myprofile/${login.memberId}/">${login.username }</a>
		</div>
		<hr>
		<div class="groupTitle">Your Studies</div>
		<div class="group-wrapper"> 
			<!-- enter시 초기화 js 작성 -->
			<input id="searchtext" type="text" placeholder="내 스터디 검색" autocomplete="off"/>
		</div>
		
		<div id="mainstudylist"class="gruopList__list">
			<ul id="listparent">
				<c:forEach items="${memberStudylist}" var="study">
					<li class="list-context"><a href="${cpath}/study/${study.teamId}/"><i class="fas fa-book"></i>${study.teamName }</a></li>			
				</c:forEach>
			</ul>
		</div>
		<a class="makeGroup" href="${cpath }/makestudy/"> + 내 스터디 만들기</a>
	</section>

	<div class="main-wrapper">
	
		<section class="container" >
			<div class="study-img">
				<img alt="study-pic" src="https://t1.daumcdn.net/cfile/tistory/997E5C3C5BA1E68137">
			</div>
			<div class="study-intro">
				<div class="study-name-container">
					<span class="study-name">대충 스터디 이름</span>
					<span class="study-public">공개</span>
				</div>
				<div class="study-context-container">
					<span class="study-context">대충 스터디 소개글</span>
					<ul class="study-tag">
						<li>#공무원</li>
						<li>#9급</li>
						<li>#7급</li>
						<li>#행정직</li>
						<li>#지방직</li>
					</ul>
				</div>
			</div>
		</section>
		
		<c:forEach items="${studylist }" var="vo">
			<section class="container" style="cursor: pointer;" onclick="location.href = '${cpath}/study/${vo.teamId }/'" >
				<div class="study-img">
					<img alt="study-pic" src="${vo.teamPicture }">
				</div>
				<div class="study-intro">
					<div class="study-name-container">
						<span class="study-name">${vo.teamName }</span>
						<c:if test="${vo.teamPublic eq 0 }">
							<span class="study-public">공개</span>
						</c:if>
						<c:if test="${vo.teamPublic eq 1 }">
							<span class="study-public" style="color: red;">비공개</span>
						</c:if>
					</div>
					<div class="study-context-container">
						<span class="study-context">${vo.teamInfo }</span>
						<span class="study-tag">#공무원 #9급 #7급 #행정직 #지방직</span>
					</div>
				</div>
			</section>
		</c:forEach>

	</div>
</main>
<script>

	searchstudy();
	document.getElementById('searchtext').addEventListener('keyup',searchstudy);
</script>
</body>
</html>