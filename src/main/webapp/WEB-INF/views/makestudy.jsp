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
<script type="text/javascript">
</script>
<style>

.hashTag-wrapper {
	display : flex;
	align-items: center;
	height: 24px;
	margin-bottom : 5px;
}

.hashTag-title{
        background-color: #009dff;
    color: white;
    height: 70%;
    width: 53px;
    border-bottom-right-radius: 15px;
    border-top-right-radius: 15px;
    font-size: 0.7rem;
    text-align: left;
    padding-left: 2px;
    line-height: 14px;
    margin-right : 5px;
}

.hashTagForm {
	width: 60px;
    height: 85%;
    border: 1px solid #919191;
    border-radius: 2px;
    font-size: 0.8rem;
    padding-left : 3px;
}
.hashTag__context {
	height: 100%;
    font-size: 0.9rem;
}

.hashTag {
	display: flex;
    align-items: center;
    opacity: 0.8;
}

.hashTag .hashTag__value {
	font-size: 0.85rem;
    margin-right: 3px;
}

.deleteHashTag {
	font-size: 0.7rem;
    color: red;
    font-weight: bolder;
    margin-right: 5px;
}

</style>

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
		<form style="width : 100%;" method="POST" enctype="multipart/form-data">
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
						<input class="changeForm" type="text" name="teamName" placeholder="스터디 이름"/>
					</div>		
									
					<div id="pdiv" class="changeForm-wrapper">
						<textarea name="teamInfo">스터디 소개글</textarea>
					</div>		
					
					<script type="text/javascript">
						
					
						const addHashTagEnter = (event) => {
							if (event.keyCode === 13) {
								const inputForm = document.getElementById("hashTagForm");
								addHashTag(inputForm.value);
							}						
						}
					
						const addHashTag = (context) => {
							console.log(context);
						}
						
					</script>	
					
					<div class="hashTag-wrapper">
						<div class="hashTag-title">HashTag</div>
						<div class="hashTag">
							<div class="hashTag__value">강아지</div>
							<input type="hidden" class="hashTag__context" name="hashTag" disabled value="강아지">
							<div id="deleteHashTag" class="deleteHashTag">X</div>
						</div>
						<input class="hashTagForm" id="hashTagForm" type="text">
					</div>
					
					
					<input id="serviceCheck" type="checkbox" name="teamPublic">
					<label for="serviceCheck">
						<span>비공개 여부</span>
					</label>
			
					<button>만들기</button>
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