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
<script src="https://kit.fontawesome.com/cc3f76d574.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>



</head>
<body>

	<%@ include file="header.jsp"%>
	<script src="${cpath}/js/profile.js"></script>

	

	<main class="main-main">
		<section class="groupList">
			<div class="groupList__profile">
				<img class="profile__pic" alt="" src="${login.pictureUrl }" /> <a
					href="">${login.username }</a>
	
			</div>

			<hr>

			<div class="gruopList__list" style="margin-top: 30px;">
				<ul>


					<li class="list-context"><a
						href="${cpath }/myprofile/${login.memberId}/"
						style="font-weight: 700; text-decoration: underline;">내 프로필</a></li>


					<li class="list-context"><a
						href="${cpath }/mystudies/${login.memberId}/">내 스터디</a></li>
					<li class="list-context"><a href="${cpath }/myinfo/">내 정보</a></li>
					<li class="list-context"><a href="">로그 아웃</a></li>
					<li class="list-context"><a href="${cpath }/studydelete/${teamId}/">스터디 삭제</a></li>


				</ul>


			</div>

		</section>


		<section class="container profile-setting-container">

			<div>
				<label class="switch"> <input id="toggleChk" type="checkbox">
					<span class="slider round"></span>
				</label>
				<p id="off" class="">비공개</p>
				<p id="on" class="Pk">공개</p>
			</div>


			<div class="tg-list">
				<div class="tg-list-item">
					<input class="tgl tgl-flip" id="cb5" type="checkbox"> <label
						class="tgl-btn" data-tg-off="가입 신청" data-tg-on="회 원" for="cb5"
						onclick="toggle()"></label>
				</div>
			</div>




			<div class="slideshow-container" id="memberList">
				<div id="memberListAjax"></div>
				<!-- <,> 표시 -->
				<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a
					class="next" onclick="plusSlides(1)">&#10095;</a>
			</div>
			<br>


			<div style="text-align: center" id="slideChk">
				<div id="slideRemove">
					<span class="dot" onclick="currentSlide(1)"></span>
				</div>
			</div>














			<div class="list-box">

				<h1>알람</h1>

				<div class="write-box">
					<input type="text" class="text-basic">
					<button type="button" class="sendbtn" id="btnAdd">전송</button>
				</div>

				<table class="list-table">
					<colgroup>
						<col width="10%">
						<col width="90%">
					</colgroup>

					<thead>
						<tr>
							<th id="open"><span>회원 목록</span></th>
						</tr>
					</thead>

					<tbody id="listBody"></tbody>
				</table>

			</div>

			<div class="modal hidden">
				<div class="md_overlay"></div>
				<div class="md_content">
					<h1>멤버 목록</h1>
					<div class="modal_text">
						<div class="cd" id="memberListChk" onclick="allCheckedBox();">
						<span id="idChk" class="null">전체</span></div>
						<c:forEach items="${member }" var="member">
							<div class="cd" id="memberListChk"
								onclick="eachCheckedBox(this);">
								<span id="idChk" class="${member.memberId }">${member.username }</span>
							</div>

						</c:forEach>



					</div>
					<button style="width: 90%; height: 30px;" onclick="chkOK()">OK</button>
				</div>
			</div>

		</section>


	</main>

	
	
	<script type="text/javascript">
		var publicChk = "${team.teamPublic}";
		var sender = "${login.memberId}"
		var teamId = "${team.teamId}"
		var cpath = "${cpath}"
		var cnt = 0;
	</script>
	
	<script src="${cpath}/js/delegate.js"></script>
	
	
	
</body>
</html>