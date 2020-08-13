<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>열공 | Search </title>
<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<style type="text/css">
.search-wrapper {
	flex-direction: column;
	align-items : flex-start;
    width : 61vw;
    padding : 10px;
}

.search-wrapper .nameSearch,
.search-wrapper .tagSearch {
	min-width: 60%;
}

.search-wrapper .nameSearch .title,
.search-wrapper .tagSearch .title {
	margin-top: 20px;
    margin-left: 1vw;
    margin-bottom : 20px;
}

.search-wrapper .nameSearch .title strong,
.search-wrapper .tagSearch .title strong {
	font-weight: bolder;
	font-size : 1.15rem;
}

.search-wrapper .nameSearch .title b,
.search-wrapper .tagSearch .title b{
	font-size : 1.1rem;
}

.search-wrapper .nameSearch .title b {
	color : red;
}
.search-wrapper .tagSearch .title b {
	color : #2980B9;
}

.search-wrapper .nameSearch .container {
	display : flex;
	flex-direction :column;
	justify-content: flex-start;
	margin : 10px;
	margin-bottom : 15px;
	padding-bottom : 5px;
	border-bottom: 1px solid gray;
}

.search-wrapper .nameSearch .container .study-name-container {
	width: 95%;
    height: 45%;
    margin-bottom: 6px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
}

.search-wrapper .nameSearch .container .study-name-container .study-public {
	font-size: 11px;
    color: blue;
    min-width: 35px;
}

.search-wrapper .nameSearch .container .study-tag {
	font-size : 11px;
	color : gray;
	display : flex;
}

.search-wrapper .nameSearch .container .study-tag li {
	margin-right : 5px;
}
	
</style>
<body>

<%@ include file="header.jsp" %>

<main class="signup search-wrapper">
	<section class="nameSearch">
		<div class="title"><strong>‘${query}’</strong> <b>이름</b>으로 검색한 결과</div>
		
		<c:if test="${empty searchedByName }">
			<article class="container" >
				
					<div class="study-name-container">
						<span class="study-name">검색 결과가 없습니다.</span>
						
					</div>
	
			</article>
		</c:if>
	
		<c:forEach items="${searchedByName }" var="vo">
			<article class="container" >
			
				<div class="study-name-container">
					<span style="cursor: pointer;"onclick="location.href = '${cpath}/study/${vo.teamId }/'" class="study-name">${vo.teamName }</span>
					<c:if test="${vo.teamPublic eq 0 }">
						<span class="study-public">공개</span>
					</c:if>
					<c:if test="${vo.teamPublic eq 1 }">
						<span class="study-public" style="color: red;">비공개</span>
					</c:if>
				</div>

				<ul class="study-tag">
					<c:forEach items="${vo.tagList }" var="tag">
						<li><a href="${cpath}/search/?query=${tag.tagName}">#${tag.tagName}</a></li>
					</c:forEach>
				</ul>

			</article>
		</c:forEach>
	
	</section>
	

	<section class="tagSearch nameSearch">
		<div class="title">
			<strong>‘${query}’</strong> <b>태그</b>로 검색한 결과
		</div>
			
			<c:forEach items="${searchedByTag }" var="vo">
				<article class="container" >
				
					<div class="study-name-container">
						<span style="cursor: pointer;"onclick="location.href = '${cpath}/study/${vo.teamId }/'" class="study-name">${vo.teamName }</span>
						<c:if test="${vo.teamPublic eq 0 }">
							<span class="study-public">공개</span>
						</c:if>
						<c:if test="${vo.teamPublic eq 1 }">
							<span class="study-public" style="color: red;">비공개</span>
						</c:if>
					</div>
	
					<ul class="study-tag">
						<c:forEach items="${vo.tagList }" var="tag">
							<li><a href="${cpath}/search/?query=${tag.tagName}">#${tag.tagName}</a></li>
						</c:forEach>
					</ul>
	
				</article>
			</c:forEach>
			
			<c:if test="${empty searchedByTag }">
			<article class="container" >
				
					<div class="study-name-container">
						<span class="study-name">검색 결과가 없습니다.</span>
						
					</div>
	
			</article>
		</c:if>
			
	</section>		
</main>

</body>
</html>