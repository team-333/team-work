<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>열공 | Sign Up</title>
<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script src="${cpath}/js/join1.js" var=123></script>
</head>
<body>

<%@ include file="header.jsp" %>

<main class="signup">

		<form id="joinForm" method="post" action="${cpath }/join/">
			<span class="title">열공 회원가입</span>
			<span class="title2">하나의 아이디로 열공의 다양한 서비스를 이용해보세요.</span>
			<div class="signupForm-wrapper">
				<input class="signupForm" type="text" id="username" name="username" placeholder="이름"/>
			</div>
			<div id="ediv" class="signupForm-wrapper">
				<input class="signupForm" type="text" id="email" name="email" placeholder="이메일"/>
				
				<div class="emailCheck"><img class="icons" id="eicons" src=""></div>
			</div>			
			
			<div id="pdiv" class="signupForm-wrapper">
				<input class="signupForm" type="password" id="password" name="password" placeholder="비밀번호"/>
				<div class="passwordCheck"><img class= "icons" id="picons" src=""></div>
			</div>			
			
			<div id="p2div"class="signupForm-wrapper">
				<input class="signupForm" type="password" id="password2" placeholder="비밀번호 확인" />
				<div class="password2Check"><img class= "icons" id="p2icons" src=""></div>
			</div>			
			<div class="pwCheck"></div>
			
			<div>
				<input id="serviceCheck" type="checkbox" name="serviceCheck">
				<label for="serviceCheck">
					<span>열공에서 제공하는 서비스 약관에 동의합니다.</span>
				</label>
				<a href="">약관보기</a>
			</div>
<!-- 			<input id="joinSubmit" type="button" value="가입하기"> -->
				
			<button id ="joinSubmit">가입하기</button>
			<div class="signup-social">다른 서비스 계정으로 가입</div>
		</form>
			<div class="signup-social__logo">
				<img alt="" src="${cpath }/img/naver_social.png">
				<img alt="" src="${cpath }/img/google_social.png">
			</div>
		
</main>
<script src="${cpath}/js/join2.js"></script>
</body>
</html>