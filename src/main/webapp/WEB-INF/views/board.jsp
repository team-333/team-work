<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cpath">${pageContext.request.contextPath }</c:set>
<!DOCTYPE html>
<html>
<head>
<title>게시물</title>
</head>
<body>
	<link rel="stylesheet" href="${cpath}/css/board.css" />
	<!-- Header -->	
	<script src="https://kit.fontawesome.com/cc3f76d574.js"	crossorigin="anonymous" ></script>
	
	<header>
		<div class="wrapper">
			<input type="text" placeholder="검색기능" />
			<div class="search-button">
				<i class="fas fa-search"></i>

			</div>
		</div>
	</header>
	
	<!-- Jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
	
	<!-- Modal-background -->
	<div id="myModal" class="modal"></div>
	
	<!-- 게시물 쓰기 -->
	<section>
		<article id="wr" style="height: 110px;">
			<div class="art1">
					<div class="head"><span>게시물 쓰기</span></div>
					<div class="img"></div>
					<textarea id="art1_box" class="box"></textarea>
					<div id="art1_btn" class="btn">게 시</div>
			</div>
		</article>
	
	<!-- 게시물 목록 -->
	<c:forEach var="board" items="${list }">
		<article id="ls">
			<div class="art2">
				<div style="height: 80px;">
					<div style="position: absolute;">
						<span class="img"></span>
						<span class="name">${board.writer }</span>
						<span class="date">
							<fmt:formatDate pattern="YYYY년 MM월 dd일 hh:mm" value="${board.time }"/>
							</span>
						<span class="menu"></span>
					</div>
				</div>
				
				<div class="box">${board.context }</div>
				
				<div style="height: 30px;">
					<div style="position: absolute;">
						<span class="emoticon_icon"></span>
						<span class="emoticon_text">표정 짓기</span>
						<span class="comment_icon"></span>
						<span class="comment_text">댓글 쓰기</span>
					</div>
				</div>
			</div>
		</article>
	</c:forEach>
	
	<div id="test"></div>
	</section>


	<!-- Modal -->
	<script type="text/javascript">
   		$('#art1_box').on('click', function() {
    		$('#myModal').show();
       		$('#art1_btn').show();
       		$('#wr').css('margin-bottom', '200px')
    	   	$('#art1_box').css('height', '200px')
   	   	});
       
       $('#myModal').on('click', function() {
    	  	$('#myModal').hide();
    	  	$('#art1_btn').hide();
    	  	$('#wr').css('margin-bottom', '10px')
    	  	$('#art1_box').css('height', '60px')
       });
       
       
       $('#art1_btn').on('click', function() {
    	   var str = $('#art1_box').val().replace(/(?:\r\n|\r|\n)/g, '<br/>');
    	   console.log(str);	
    	   $("#test").html(str);
	       
       });
       
       
       
       
       
       
       
       
       
//     formatDate	// 나중에 쓸거임
       function getFormatDate(date){
    	    var year = date.getFullYear();              //yyyy
    	    var month = (1 + date.getMonth());          //M
    	    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    	    var day = date.getDate();                   //d
    	    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    	    return  year + '' + month + '' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
    	}
       
    </script>  
</body>
</html>


