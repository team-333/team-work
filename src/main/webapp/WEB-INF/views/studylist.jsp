<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
	
	<ul>
	<c:forEach items="${memberStudylist}" var="study">
		<li class="list-context"><a href="${cpath}/study/${study.teamId}/"><i class="fas fa-book"></i>${study.teamName }</a></li>			
	</c:forEach>
	</ul>

