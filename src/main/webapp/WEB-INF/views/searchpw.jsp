<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath">${pageContext.request.contextPath }</c:set>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cpath }/css/style.css"/>
<meta charset="UTF-8">
<title>열공 | 임시비밀번호 찾기</title>
</head>
<script type="text/javascript">
	function send(){
		const title =document.getElementById('title2');
		title.style.display='none';
		console.log(title);	
	}

</script>
<body>
<%@ include file="header.jsp" %>
	
	<main class="searchpw">
		<form action="${cpath }/searchpw/" method="post">
			<span class="title">열공 비밀번호 찾기</span>
			<span class="title2" id="title">이메일로 임시 비밀번호를 발급받으세요.</span>
			<div class="signupForm-wrapper">
				<input class= "singupForm" type="text" name="email" placeholder ="이메일을 입력하세요">
			</div>
			<button >비밀번호 발송</button>
			
		</form>
		
	
	</main>
</body>
</html>