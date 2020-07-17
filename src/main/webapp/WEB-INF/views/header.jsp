<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<div class="header-wrapper">
		<form class="searchForm">
			<img onclick="location.reload(true);" class="searchForm__logo" alt="" src="${cpath}/img/logo.png" />
			<div class="searchForm-wrapper">
				<input class="searchForm__text" type="text" placeholder="스터디를 검색해보세요"/>
				<i class="fas fa-search"></i>
			</div>
		</form>
		
		<div class="profile">
			<div class="profile__pic">
				<img alt="profile pic" src="${cpath}/img/logo.png" />
			</div>
			<div id="profile__menu" class="profile__menu"><i class="far fa-caret-square-down"></i> </div>
			<div id="profile__menus" class="profile__menus">
				<a href="">내 프로필</a>
				<a href="">내 그룹</a>
				<a href="">내 정보</a>
				<a href="">로그 아웃</a>
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
	document.getElementById("profile__menu").addEventListener("click",dropMenu);
	
</script>