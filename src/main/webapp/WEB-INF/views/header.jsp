<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<header>
	<div class="header-wrapper">
		<form class="searchForm" id="searchform" action="${cpath}/search/">
			<img class="searchForm__logo" alt="" src="${cpath}/img/logo.png" onclick="location.href='${cpath}/main/'"/>
			<div class="searchForm-wrapper">
				<input class="searchForm__text" type="text" name="searchtext" placeholder="스터디를 검색해보세요"/>
				<i id="submiticon" class="fas fa-search"></i>
			</div>
		</form>
		
		<div class="profile">
		
			<a href="">
				<span class="container15" id="badge">
					<i class="fas fa-bell pinkstyle"></i>
					<span id="readChk" class="badge-num">0</span>
				</span>
			</a>
		
			<div class="profile__pic">
				<img alt="profile pic" src="${login.pictureUrl }" />
			</div>
			
			<div id="profile__menu" class="profile__menu">
				<i class="far fa-caret-square-down"></i>
			</div>
			
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
	document.getElementById("profile__menu").addEventListener("click",dropMenu);
	
	function alram() {
		
		const request = new XMLHttpRequest();
		request.open("GET", "${cpath}/alram/${login.memberId}/",	true);
		request.setRequestHeader('Content-type',
				'application/json; charset=UTF-8');
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				if (request.status == 200) {
					
					var readChk = request.response;
					readChk = (readChk *1);
					
					if(readChk > 99){
						readChk = "99+";
					}
					
					
					document.getElementById("readChk").innerText = readChk;
					
				}
			}
		}
		request.send();
		
	}
	alram();
	
	function submit(){
		document.getElementById('searchform').submit();
	}
	document.getElementById("submiticon").addEventListener("click",submit);
	
		
</script>