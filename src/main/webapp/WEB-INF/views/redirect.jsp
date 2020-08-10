<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>

<html>
<head>
<meta charset="UTF-8">
<title>Alert</title>
</head>
<body>
<script type="text/javascript">
	const msg = '${msg}';

	if (msg === "로그인실패") {
		location.href="${cpath}/${url}";
	}
	else if (msg === "로그인성공"){
		location.href="${cpath}/${url}";
	}
	else if (msg == "가입완료") {
		alert('회원 가입 완료!');
		location.href = "${cpath}/${url}";
	}
	else if (msg == "가입실패"){
		alert('회원가입 실패 잠시 후 다시 시도해 주세요!');
		location.href = "${cpath}/${url}";
	}
	else if (msg == "가입 신청 완료"){
		alert('가입 신청 완료!');
		location.href = "${cpath}/${url}";
	} 
	
	else if (msg == "승인 완료"){
		alert('승인 완료!');
		location.href = "${cpath}/${url}";
	} 
	
	else if (msg == "이미 가입신청된 멤버"){
		alert('이미 가입신청이 되어있습니다.');
		location.href = "${cpath}/${url}";
	} 
	
	else if (msg == "이미 가입된 멤버"){
		alert('등록된 스터디입니다.');
		location.href = "${cpath}/${url}";
	}
	else if (msg == "조장은 탈퇴가 안됩니다."){
		alert('조장은 탈퇴가 안됩니다.');
		location.href = "${cpath}/${url}";
	}
	else if (msg == "탈퇴완료"){
		alert('탈퇴완료');
		location.href = "${cpath}/${url}";
	}
	else if (msg == "upload"){
		alert('변경완료');
		location.href = "${cpath}/${url}";
	}
	else if (msg == "fail"){
		alert('변경실패');
		location.href = "${cpath}/${url}";
	}
	
	location.replace("${cpath}/${url}");
	
</script>

</body>
</html> 