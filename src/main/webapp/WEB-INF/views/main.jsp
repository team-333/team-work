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
					<span class="study-tag">#공무원 #9급 #7급 #행정직 #지방직</span>
				</div>
			</div>
		</section>

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
					<span class="study-tag">#공무원 #9급 #7급 #행정직 #지방직</span>
				</div>
			</div>
		</section>

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
					<span class="study-tag">#공무원 #9급 #7급 #행정직 #지방직</span>
				</div>
			</div>
		</section>

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
					<span class="study-tag">#공무원 #9급 #7급 #행정직 #지방직</span>
				</div>
			</div>
		</section>

	</div>

</main>

</body>
</html>