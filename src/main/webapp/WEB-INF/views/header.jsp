<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<script src="https://kit.fontawesome.com/cc3f76d574.js"
	crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<header>
	<div class="header-wrapper">
		<form class="searchForm">
			<img class="searchForm__logo" alt="" src="${cpath}/img/logo.png"
				onclick="location.href='${cpath}/main/'" />
			<div class="searchForm-wrapper">
				<input class="searchForm__text" type="text"
					placeholder="스터디를 검색해보세요" /> <i class="fas fa-search"></i>
			</div>
		</form>

		<div class="profile">
	
	
				<a id="bellChk" href="${cpath }/mymessage/${login.memberId}/">
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
				<a href="${cpath }/myprofile/${login.memberId}/">내 프로필</a> <a
					href="${cpath }/mystudies/${login.memberId}/">내 스터디</a> <a
					href="${cpath }/myinfo/">내 정보</a> <a href="${cpath }/logout/">로그
					아웃</a>
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

	let sock = new WebSocket("ws://localhost:7070/yeol-gong/echo");
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	

	function webSocket(msg) {
	    sendMessage(msg);
	 }
	
	function sendMessage(msg) {
	sock.send(msg);
	 }

	
	function onMessage(msg) {
		var data = msg.data;
		console.log("data" + data)
		
		if(data != "connect"){
		alram();
		getNotificationPermission();
		notify(data);
		}
		
		
	}

	function notify(msg) {
	    var options = {
	        body: msg
	    }
	    
	    // 데스크탑 알림 요청
	    var notification = new Notification("yeol-gong", options);
	    
	    // 3초뒤 알람 닫기
	    setTimeout(function(){
	        notification.close();
	    }, 5000);
	}


	// 서버와 연결을 끊었을 때
	function onClose(evt) {
		console.log("연결끊김")
	}
	
	function getNotificationPermission() {
	    // 브라우저 지원 여부 체크
	    if (!("Notification" in window)) {
	        alert("데스크톱 알림을 지원하지 않는 브라우저입니다.");
	    }
	    // 데스크탑 알림 권한 요청
	    Notification.requestPermission(function (result) {
	        // 권한 거절
	        if(result == 'denied') {
	            alert('알림을 차단하셨습니다.\n브라우저의 사이트 설정에서 변경하실 수 있습니다.');
	            return false;
	        }
	    });
	}



	alram();

</script>