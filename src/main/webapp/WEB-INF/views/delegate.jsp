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

    <style type="text/css">
    /* 토글 버튼 */
.tg-list {
text-align: center;
margin-top: 50px;
align-items: center;
}
.tg-list-item {
margin: 0 100px;
}
.tgl {
display: none;
}
.tgl, .tgl:after, .tgl:before, .tgl *, .tgl *:after, .tgl *:before, .tgl + .tgl-btn {
box-sizing: border-box;
}
.tgl::selection, .tgl:after::selection, .tgl:before::selection, .tgl *::selection, .tgl *:after::selection, .tgl *:before::selection, .tgl + .tgl-btn::selection {
background: none;
}
.tgl + .tgl-btn {
outline: 0;
display: block;
width: 90px;
height: 40px;
position: relative;
cursor: pointer;
user-select: none;
}
.tgl-skewed + .tgl-btn {
overflow: hidden;
transform: skew(-10deg);
backface-visibility: hidden;
transition: all 0.2s ease;
background: #888;
}
.tgl-skewed + .tgl-btn:after, .tgl-skewed + .tgl-btn:before {
transform: skew(10deg);
display: inline-block;
transition: all 0.2s ease;
width: 100%;
text-align: center;
position: absolute;
line-height: 2em;
font-weight: bold;
color: #fff;
text-shadow: 0 1px 0 rgba(0, 0, 0, 0.4);
}
.tgl-skewed + .tgl-btn:after {
left: 100%;
content: attr(data-tg-on);
}
.tgl-skewed + .tgl-btn:before {
left: 0;
content: attr(data-tg-off);
}
.tgl-skewed + .tgl-btn:active {
background: #888;
}
.tgl-skewed + .tgl-btn:active:before {
left: -10%;
}
.tgl-skewed:checked + .tgl-btn {
background: #86d993;
}
.tgl-skewed:checked + .tgl-btn:before {
left: -100%;
}
.tgl-skewed:checked + .tgl-btn:after {
left: 0;
}
.tgl-skewed:checked + .tgl-btn:active:after {
left: 10%;
}
.tgl-flip + .tgl-btn {
padding: 2px;
transition: all 0.2s ease;
font-family: sans-serif;
perspective: 100px;
}
.tgl-flip + .tgl-btn:after, .tgl-flip + .tgl-btn:before {
display: inline-block;
transition: all 0.4s ease;
width: 100%;
text-align: center;
position: absolute;
line-height: 2em;
font-weight: bold;
color: #fff;
position: absolute;
top: 0;
left: 0;
backface-visibility: hidden;
border-radius: 4px;
}
.tgl-flip + .tgl-btn:after {
content: attr(data-tg-on);
background: #28a56d;
transform: rotateY(-180deg);
}
.tgl-flip + .tgl-btn:before {
background: #c300ff83;
content: attr(data-tg-off);
}
.tgl-flip + .tgl-btn:active:before {
transform: rotateY(-20deg);
}
.tgl-flip:checked + .tgl-btn:before {
transform: rotateY(180deg);
}
.tgl-flip:checked + .tgl-btn:after {
transform: rotateY(0);
left: 0;
background: #7fc6a6;
}
.tgl-flip:checked + .tgl-btn:active:after {
transform: rotateY(20deg);
}
    </style>
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
					<li class="list-context"><a href="${cpath }/myinfo/${login.memberId}/">내 정보</a></li>
					<li class="list-context"><a href="">로그 아웃</a></li>


				</ul>


			</div>

		</section>


		<section class="container profile-setting-container">
			<div>
				<input type="button">
			</div>
			<div>
				<label class="switch"> <input id="toggleChk" type="checkbox" >
					<span class="slider round"></span>
				</label>
				<p id="off" class="">비공개</p>
				<p id="on" class="Pk">공개</p>
			</div>


   <div class="slideshow-container">

          <c:if var="waitChk" test="${empty wait}">
          <div class="mySlides fade">
            <div class="profile1"><br>
            신청한 회원이 없는 상황!!!!!!!
            </div>
        </div>
          </c:if>

          <c:if var="WaitChk" test="${not empty wait }">
          <c:forEach var="wait" items="${wait }">

                                      <div class="tg-list">
<div class="tg-list-item">
<input class="tgl tgl-flip" id="cb5" type="checkbox" />
<label class="tgl-btn" data-tg-off="가입 신청" data-tg-on="회 원" for="cb5"></label>
</div>
</div>




        <div class="mySlides fade">
            <div class="profile1"><br>
                <img src="${wait.pictureUrl}" class="profileIcon">

                <div id="pname">회원 이름 : ${wait.username }</div>
                <div id="pemail">회원 이메일 : ${wait.email }</div>
                <div id="pintro">회원 목표 : ${wait.introduce }</div>
                <div id="ptexts">회원 소개: ${wait.introduceContext }</div>


                <a href="${cpath }/MemberTeam/${wait.memberId }/${team.teamId }/1/"><button id="joins">승인</button></a>
                <a href="${cpath}/MemberTeam/${wait.memberId }/${team.teamId }/0/"><button id="nope">거절</button></a>
            </div>
        </div>
          </c:forEach>
          </c:if>

        <!-- <,> 표시 -->
        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next" onclick="plusSlides(1)">&#10095;</a>

    </div><br>

    <div style="text-align:center">

    	<c:if var="WaitChk" test="${empty wait }">
    	 <span class="dot" onclick="currentSlide(1)"></span> 
    	</c:if>

    	<c:if var="WaitChk" test="${not empty wait }">
    	<c:forEach var="wait" items="${wait }" varStatus="status">
        <span class="dot" onclick="currentSlide(${status.count})"></span> 
	    </c:forEach>
	    </c:if>
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
						<div class="cd" id="memberListChk" onclick="allCheckedBox();">전체</div>
						<c:forEach items="${member }" var="member">
							<div class="cd" id="memberListChk"
								onclick="eachCheckedBox(this);">${member.username }</div>

						</c:forEach>



					</div>
					<button style="width: 90%; height: 30px;" onclick="chkOK()">OK</button>
				</div>
			</div>

		</section>

	</main>


<script type="text/javascript">
	var publicChk = "${team.teamPublic}";
	var receiver = "${login.memberId}"
	var teamId = "${team.teamId}" 
	var cpath = "${cpath}"
</script>


	<script src="${cpath}/js/delegate.js"></script>


</body>
</html> 