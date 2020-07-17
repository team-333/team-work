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


<script>
	function checkEmail(){
		const email = document.getelemnetById('email');
		
		if(email === ''){
			emailmsg = document.getElementById("emailmsg");
			emailmsg.innerText("NO");
			emailmsg.styel.color ='red';
			document.queryselector('email').focus();
			
		}
		else{
			emailmsg.innerText="ok";
		}
	
		const request = new XMLHttpRequest();
		request.open("POST", "${cpath}/emailcheck/", true);
		request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		
		request.onreadystatechange = function(){
			if (request.readyState == 4 && request.status == 200) {
				idmsg.innerHTML = request.responseText;
				if(request.responseText === 'ok') {
					emailmsg.style.color = 'blue';
					ajaxRet = true;
				}
				else {
					idmsg.innerText = request.responseText;
					idmsg.style.color = 'red'
					ajaxRet = false;
				}
			}
		
		request.send("email=" + email);	// POST
	}
	
	}
</script>




<%@ include file="header.jsp" %>

<main class="signup">

		<form method="POST" action="join">
			<span class="title">열공 회원가입</span>
			<span class="title2">하나의 아이디로 열공의 다양한 서비스를 이용해보세요.</span>
			<div class="signupForm-wrapper">
				<input class="signupForm" type="text" name="name" placeholder="이름"/>
			</div>
			
			<div class="signupForm-wrapper">
				<input class="signupForm" id="email" type="text" name="email" placeholder="이메일"/>
				<div class="emailCheck"></div>
				<span id=emailmsg></span>
			</div>			
			
			
			<div class="signupForm-wrapper">
				<input class="signupForm" id="password" type="password" name="password" placeholder="비밀번호"/>
			</div>			
			<span></span>
			<div class="signupForm-wrapper">
				<input class="signupForm" id="password2" type="password" placeholder="비밀번호 확인" />
			</div>			
			<div class="pwCheck"></div>
			<span id=pw2msg></span>
			<div>
				<input id="serviceCheck" type="checkbox" name="serviceCheck">
				<label for="serviceCheck">
					<span>열공에서 제공하는 서비스 약관에 동의합니다.</span>
				</label>
				<a href="">약관보기</a>
			</div>
			<button type="submit">가입하기</button>
			<div class="signup-social">다른 서비스 계정으로 가입</div>
		</form>
			<div class="signup-social__logo">
				<img alt="" src="${cpath }/img/naver_social.png">
				<img alt="" src="${cpath }/img/google_social.png">
			</div>
		
		<button onclick="checkEmail" value="check"></button>
		
<script>
	document.getElementById('emailmsg').addEventListener('keyup',checkEmail);
</script>
</main>


</body>
</html>