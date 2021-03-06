<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>열공 | Home</title>
<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />

<script src="https://kit.fontawesome.com/cc3f76d574.js"
	crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script src="${cpath}/js/home.js"></script>
<script type="text/javascript">
function wi(event){
	event.preventDefault();
	var url="${url}";
	window.open(url,"","width =400,height=400,left=600");
}
</script>
</head>
<body>
	<video id="videoP" muted autoplay loop>

		<strong>Your browser does not support the video tag.</strong>
	</video>

	<!-- 각 input에 focus 효과 주기 -->
	<main>
		<img class="home_title" alt="" src="${cpath}/img/logo.png" />
		<section class="home__section">
			<form class="login_form" method="post" action="${cpath }/login/">
				<span class="login_form__title">이메일로 로그인</span>
				<div style="margin-bottom : 10px;" class="userid__wrapper">
					<i class="fas fa-envelope-open-text"></i> 
					<input id= "email" name ="email" class="login_form__userid" type="text" />
				</div>
				
				<div style="width: 320px; min-width: 200px; margin-right : 10px; display: flex; justify-content: flex-end;">
					<input style="margin-right : 3px; cursor: pointer; width:0.9rem;height:0.9rem;"id="cookiecheck" type="checkbox" value=""><label style="font-size:0.9rem" id="cookiename">아이디저장</label>
				</div>
				
				<div style="margin-top: 5px; " class="userpw__wrapper">
					<i class="fas fa-lock"></i> <input class="login_form__userpw"
						id ="password" name="password" type="password" />
				</div>
				<div class="login_form__sign">
					<a href="${cpath }/searchpw/">비밀번호를 잊으셨나요?</a> <a href="${cpath}/signup/">처음이신가요?</a>
				</div>
				

				<button id="sub">로그인</button>
				<img onclick="wi(event)" class="social-login__naver" alt="" src="${cpath}/img/네이버 아이디로 로그인.PNG" />

			</form>
		</section>
	</main>
	<script src="${cpath}/js/bg.js"></script>
	
	

</body>
</html>
