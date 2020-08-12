<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<header>
	<div class="header-wrapper">
		<form id="searchform" class="searchForm" action="${cpath }/headersearch/">
			<img class="searchForm__logo" alt="" src="${cpath}/img/logo.png" onclick="location.href='${cpath}/main/'"/>
			<div class="searchForm-wrapper">
				<input name="searchtext"class="searchForm__text" type="text" value="${searchtext }" placeholder="스터디를 검색해보세요"/>
				<i id="submiticon"class="fas fa-search"></i>
			</div>
		</form>
		
		<div class="profile">
			<div class="profile__pic">
				<img alt="profile pic" src="${login.pictureUrl }" />
			</div>
			<div id="profile__menu" class="profile__menu"><i class="far fa-caret-square-down"></i> </div>
			<div id="profile__menus" class="profile__menus">
				<a href="${cpath }/myprofile/${login.memberId}/">내 프로필</a>
				<a href="${cpath }/mystudies/${login.memberId}/">내 스터디</a>
				<a href="${cpath }/myinfo/${login.memberId}/">내 정보</a>
				<a href="${cpath }/logout/">로그 아웃</a>
			</div>
		</div>
	</div>
</header>

<script type="text/javascript">

	function dropMenu () {
		document.getElementById("profile__menus").classList.toggle("show");
	}
	
	window.onclick = (event) => {
		if (!event.target.matches('.fa-caret-square-down')){
			const dropmenu = document.getElementsByClassName("profile__menus");
			for (i = 0 ; i < dropmenu.length; i++){
				const openMenu = dropmenu[i];
				if(openMenu.classList.contains('show')){
					openMenu.classList.remove('show');
				}
			}
		}
		
	}
	function submit(){
		document.getElementById('searchform').submit();
	}
	document.getElementById("profile__menu").addEventListener("click",dropMenu);
	document.getElementById("submiticon").addEventListener("click",submit);
</script>