<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>열공 | Message</title>
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
					href="${cpath }/myprofile/${login.memberId}/">${login.username }</a>
			</div>
			<hr>

			<div class="gruopList__list" style="margin-top: 30px;">
				<ul>
					<li class="list-context"><a
						href="${cpath }/myprofile/${login.memberId}/">내 프로필</a></li>
					<li class="list-context" id="mymessagedd"><label
						id="msbox_bold">쪽지함</label>
						<ul>
							<li class="list-context"><a
								href="${cpath }/mymessage/${login.memberId}/receiver/">받은쪽지함(${msgSize })</a></li>
							<li class="list-context"><a
								href="${cpath }/mymessage/${login.memberId}/sender/">보낸쪽지함(${msgSize2 })</a></li>
						</ul></li>
					<li class="list-context"><a
						href="${cpath }/mystudies/${login.memberId}/">내 스터디</a></li>
					<li class="list-context"><a href="${cpath }/myinfo/">내 정보</a></li>
					<li class="list-context"><a href="">로그 아웃</a></li>
					<li class="list-context"><a
						href="${cpath }/studydelete/${teamId}/">스터디 삭제</a></li>
				</ul>
			</div>
		</section>

		<section class="container profile-setting-container mymessagebox">
			<div class="topContent">
				<input type="checkbox" id="checkAllbox">
				<button id="ans">답장</button>
				<button id="deleteMessage">삭제</button>
			</div>

			<table class="messageListss">
				<c:if var="msgType1" test="${msgType eq 'sender'}">
					<c:forEach var="msg" items="${message }" varStatus="in">
						<tr id="paging" class="${in.count } noneview ">
							<th id="checkk"><input type="checkbox" id="checkOnebox"
								class=${msg.msgId }></th>
							<td>${msg.userName }<input type="hidden"
								value="${msg.sender }"></td>
							<td>${msg.context }</td>
							<td>${msg.time }</td>
						</tr>
					</c:forEach>
				</c:if>

				<c:if var="msgType2" test="${msgType eq 'receiver'}">
					<c:forEach var="msg" items="${message }" varStatus="in">
						<c:if var="readChk" test="${msg.readChk eq 1 }">
							<tr id="paging" class="${in.count } noneview falseRead"
								onclick="reading(this)">
								<th id="checkk"><input type="checkbox" id="checkOnebox"
									class=${msg.msgId }></th>
								<td>${msg.userName }<input type="hidden"
									value="${msg.sender }"></td>
								<td>${msg.context }</td>
								<td>${msg.time }</td>
							</tr>
						</c:if>

						<c:if var="readChk" test="${msg.readChk eq 0 }">
							<tr id="paging" class="${in.count } noneview tureRead">
								<th id="checkk"><input type="checkbox" id="checkOnebox"
									class=${msg.msgId }></th>
								<td>${msg.userName }<input type="hidden"
									value="${msg.sender }"></td>
								<td>${msg.context }</td>
								<td>${msg.time }</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
			</table>

			<div id="pageSize"></div>

			<div class="modals hiddens">
				<div class="md_overlays"></div>
				<div class="md_contents">
					<h2 id="md_sendMessage">쪽지</h2>
					<div class="modal_texts">
						<div class="addressTexts">
							<select id="addressSelect" name="emailAddress" required>
								<option value="none">== 받는 사람 목록 ==</option>
								<option id="receiverList" value="none"></option>
							</select>
							<div class="srch_form autocomplete" >

								<div class="autocomplete" autocapitalize="none">
									<input id="myInput" type="text" name="myCountry"
										placeholder="받는 사람  검색" class="searchM" autocomplete="off" />
								</div>

							</div>
							<div id="searchResult">
								<div id="divResult"></div>
							</div>
						</div>
						<textarea id="textContext"></textarea>
					</div>
					<button id="messageSend">전송</button>
				</div>
			</div>
		</section>

		<script>
			var userId = "${login.memberId}";
			var cpath = "${cpath}";
		</script>
		<script src="${cpath}/js/message.js"></script>

	</main>

</body>
</html>
