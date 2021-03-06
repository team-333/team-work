<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>열공 | Make Study</title>

<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script> -->

</head>
<body>

<%@ include file="header.jsp" %>
<script src="${cpath }/js/hashTag.js"></script>

<main class="main-main">
	<section class="groupList">
		<div class="groupList__profile">
			<img class="profile__pic" alt="" src="${login.pictureUrl }" />
			<a href="${cpath }/myprofile/${login.memberId}/">${login.username }</a>
		</div>
		<hr>
		<div class="groupTitle">Your Studies</div>
		<div class="group-wrapper"> 
			<!-- enter시 초기화 js 작성 -->
			<input type="text" placeholder="내 스터디 검색" />
		</div>
		
		<div class="gruopList__list">
			<ul>
				<c:forEach items="${memberStudylist}" var="study">
					<li class="list-context"><a href="${cpath}/study/${study.teamId}/"><i class="fas fa-book"></i>${study.teamName }</a></li>			
				</c:forEach>
			</ul>
		</div>
		<a class="makeGroup" href=""> + 내 스터디 만들기</a>
	</section>

	<section class="container profile-setting-container" >
		<form id="makeform" style="width : 100%;" method="POST" enctype="multipart/form-data">
			<article class="info-container">			
				<div class="profile-setting__pic profile-setting__intro__title" style="margin-right: 10px;">
					<span>스터디 대표 사진</span>
					<div id="preview" class="profile-pic__preview" style="margin-top : 10px;">
						<img alt="picture" src="${cpath }/img/profile-picture-default.png" />
					</div>
					<label for="profile-pic">업로드</label>
					<input type="file" id="profile-pic" name="teamPicture"/>
				</div>	
			
				<div class="profile-setting__intro__context profile-setting__password-change">
					<span>내 스터디 만들기</span>
					
					<div id="pdiv" class="changeForm-wrapper">
						<input id="studyname" class="changeForm" type="text" name="teamName" placeholder="스터디 이름" required/>
					</div>		
									
					<div id="pdiv" class="changeForm-wrapper">
						<textarea style="resize:none; width: 100%; height : 15vh;" class="teamInfo" name="teamInfo">스터디 소개글</textarea>
					</div>		
					
					<div class="hashTag-wrapper" id="hashTag-wrapper">
						<div class="hashTag-title">HashTag</div>
						<input class="hashTagForm" id="hashTagForm" type="text" autocomplete="off"/>
					</div>
					
					
					<input id="serviceCheck" type="checkbox" name="teamPublic">
					<label for="serviceCheck">
						<span>비공개 여부</span>
					</label>
			
					<button id="formsubmit">만들기</button>
				</div>
				
			</article>
		</form>
		
	
	</section>


</main>
<script type="text/javascript">
	document.getElementById("hashTagForm").addEventListener('keydown', function(event) {
		  if (event.keyCode === 13) {
			event.preventDefault();
			addHashTagEnter(event);
		  };
		}, true);
</script>
<script src="${cpath }/js/preview.js"></script>
</body>
</html>