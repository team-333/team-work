<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<title>Insert title here</title>
</head>
<body>

</body>
<script type="text/javascript">
	
	alert('${msg}');
	location.href= "${cpath}/${url}";
</script>
</html>