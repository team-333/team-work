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
</head>
<body>

<%@ include file="header.jsp" %>

<main class="signup">

		<form method="POST">
			<span class="title">열공 회원가입</span>
			<span class="title2">하나의 아이디로 열공의 다양한 서비스를 이용해보세요.</span>
			<div class="signupForm-wrapper">
				<input class="signupForm" type="text" name="name" placeholder="이름"/>
			</div>
			<div class="signupForm-wrapper">
				<input class="signupForm" type="text" name="email" placeholder="이메일"/>
				<div class="emailCheck"></div>
			</div>			
			
			<div class="signupForm-wrapper">
				<input class="signupForm" type="password" name="password" placeholder="비밀번호"/>
			</div>			
			
			<div class="signupForm-wrapper">
				<input class="signupForm" type="password" placeholder="비밀번호 확인" />
			</div>			
			<div class="pwCheck"></div>
			
			<div>
				<input id="serviceCheck" type="checkbox" name="serviceCheck">
				<label for="serviceCheck">
					<span>열공에서 제공하는 서비스 약관에 동의합니다.</span>
				</label>
				<a href="">약관보기</a>
			</div>
			<button>가입하기</button>
			<div class="signup-social">다른 서비스 계정으로 가입</div>
		</form>
			<div class="signup-social__logo">
				<img alt="" src="${cpath }/img/naver_social.png">
				<img alt="" src="${cpath }/img/google_social.png">
			</div>
		

</main>


</body>
</html>