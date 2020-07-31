<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<form name="popupForm" action="${cpath }/main/">

</form>
<script>

	var f = document.forms.popupForm;
	opener.name="parentPage";
	f.target=opener.name;
	f.submit()
	self.close();

</script>
</body>
</html>