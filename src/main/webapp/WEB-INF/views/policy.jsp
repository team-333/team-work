<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>열공 | Policy </title>
<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script src="${cpath}/js/join1.js"></script>
</head>
<body>

<%@ include file="loginheader.jsp" %>

<main class="signup">

		<div class="policy">${text }</div>
		
		<button class="policyBtn" onclick="history.go(-1)">뒤로가기</button>
</main>
<script src="${cpath}/js/join2.js"></script>
</body>
</html>