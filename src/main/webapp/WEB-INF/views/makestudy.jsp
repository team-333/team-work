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
<script src="${cpath }/js/board.js"></script>
<script type="text/javascript">
</script>
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
		<a class="makeGroup" href=""> + 내 스터디 만들기</a>
	</section>

	<section class="container profile-setting-container" >
		<form style="width : 100%;">
			<article class="info-container">			
				<div class="profile-setting__pic profile-setting__intro__title" style="margin-right: 10px;">
					<span>스터디 대표 사진</span>
					<div id="preview" class="profile-pic__preview" style="margin-top : 10px;">
						<img alt="picture" src="${cpath }/img/profile-picture-default.png" />
					</div>
					<label for="profile-pic">업로드</label>
					<input type="file" id="profile-pic" name="profile-pic"/>
				</div>	
			
				<div class="profile-setting__intro__context profile-setting__password-change">
					<span>내 스터디 만들기</span>
					
					<div id="pdiv" class="changeForm-wrapper">
						<input class="changeForm" type="text" placeholder="스터디 이름"/>
					</div>		
									
					<div id="pdiv" class="changeForm-wrapper">
						<textarea>스터디 소개글</textarea>
					</div>			
					
					<input id="serviceCheck" type="checkbox" name="serviceCheck">
					<label for="serviceCheck">
						<span>비공개 여부</span>
					</label>
			
					<button>만들기</button>
				</div>
				
			</article>
		</form>
		
	
	</section>


</main>

</body>
</html>