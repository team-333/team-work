<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>열공 | ${teamInfo.teamName }</title>

<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<link rel="stylesheet" href="${cpath}/css/board.css" />

<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
<script src="${cpath }/js/board.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<%@ include file="header.jsp" %>
<main class="main-main">
	<!-- Modal-background -->

	
	
	<div id="myModal" class="modal"></div>
	
	<section class="groupList">
		<div class="thumbnail-wrapper">
			<div class="thumbnail"> 
				<div class="centered"> 
					<img src="${teamInfo.teamPicture }"> 
				</div> 
			</div> 
		</div>
		<hr>
		
		<div class="teamTitle groupTitle">${teamInfo.teamName }</div>
		<div class="group-wrapper"> 
			${teamInfo.teamInfo }
		</div>
		
		<div class="gruopList__list">
			스터디 캡틴 : ${captain.username}
		</div>
		<button onclick="location.href = '${cpath}/joininstudy/${teamInfo.teamId}/'">가입하기</button>
		<button>관리</button>
		<a class="makeGroup" href="${cpath }/makestudy/"> + 내 스터디 만들기</a>
	</section>

	<section class="container">
			<!-- 게시물 쓰기 -->
			<article id="wr" style="height: 110px;">
				<div class="art1">
						<div class="head"><span>게시물 쓰기</span></div>
						<div class="img"></div>
						<div style="padding: 10px 10px 10px 80px">
							<textarea id="art1_box" class="box"></textarea>
						</div>
						<div id="art1_btn" class="btn">게 시</div>
				</div>
			</article>
		
			<!-- 게시물 목록  -->
			<article id="list_board"></article>
			<article style="height: 100px;"></article>
	</section>

</main>

</body>
</html>