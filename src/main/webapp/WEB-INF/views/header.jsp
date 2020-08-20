<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style>
.popup .popuptext {
    visibility: hidden;
    position: absolute;
    width: 305px;
	height: 250px;
    border: 1px solid black;
    background-color: white;
    border-radius: 5px;
    padding: 10px;
    z-index: 1;
    top: 130%;
    left: -50%;
    margin-left: -80px;  
}
.popupList {
  display:block;
  height: 30px;
  width: 160px;
}
.popupList .popup-ul {
   width: 290px;
    position: relative;
    zoom: 1;
}
.popupList .popup-li {
width: 266px;
height: 30px;
}
/* Popup arrow */
.popup .popuptext::after {
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}
.popup-btn{
float: right;
}
.popup .show {
  visibility: visible;
  -webkit-animation: fadeIn 0.45s;
  animation: fadeIn 0.45s
}
.naveralarm {
    height: 36px;
    padding-left: 15px;
    line-height: 20px;
    zoom: 1;
}
@-webkit-keyframes fadeIn {
  from {opacity: 0;}
  to {opacity: 1;}
}
@keyframes fadeIn {
  from {opacity: 0;}
  to {opacity:1 ;}
}
.bellChk { 
     display: block; 
     height: 38px; 
     border-top: 1px solid #ebebeb; 
     background-color: #f8f8f8; 
     text-align: center; 
     font-weight: bold; 
    text-decoration: none;     
    letter-spacing: -1px; 
     line-height: 38px;
      
 } 

</style>

<header>
	<div class="header-wrapper">
		<form class="searchForm" id="searchform" action="${cpath}/search/">
			<img class="searchForm__logo" alt="" src="${cpath}/img/logo.png" onclick="location.href='${cpath}/main/'"/>
			<div class="searchForm-wrapper">
				<input class="searchForm__text" type="text" name="query" placeholder="스터디를 검색해보세요"/>
				<i id="submiticon" class="fas fa-search"></i>
			</div>
		</form>
		
		<div class="profile">
		
		
		<div class="popup" onclick="myFunction()">
            <span class="container15" id="badge">
               <i style="cursor: pointer;" class="fas fa-bell pinkstyle"></i>
               <span id="readChk" class="badge-num">0</span>
            </span>
            
            <div class="popuptext" id="myPopup">
               <div class="naveralarm">
                  <strong style="font-weight: bold;">전체 알림</strong>
               </div>

				<div>
					<div id="popupList" class="popupList">
						<ul id="popup-ul" class="popup-ul"></ul>
						<div id="poput-bottem"></div>
					</div>
				</div>
            </div>
         </div>



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
            <a href="${cpath }/logout/">로그아웃</a>
         </div>
      </div>
	</div>
</header>

<script type="text/javascript">
	function myFunction() {
		var popup = document.getElementById("myPopup");
		popup.classList.toggle("show");
	}

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

	function submit(){
		document.getElementById('searchform').submit();
	}
	
	document.getElementById("submiticon").addEventListener("click",submit);
	
	
	
	function alram() {
		   
	   const request = new XMLHttpRequest();
	   request.open("GET", "${cpath}/alram/${login.memberId}/",   true);
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
	   let sock = new WebSocket("ws://localhost:8080/yeol-gong/echo");
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
	   alram();
	   
	   
	   function alramContext() {
		   
		   const request = new XMLHttpRequest();
		   request.open("GET", "${cpath}/alarmContext/${login.memberId}/",   true);
		   request.setRequestHeader('Content-type',
		         'application/json; charset=UTF-8');
		   request.onreadystatechange = function() {
		      if (request.readyState == 4) {
		         if (request.status == 200) {
		            	
		        	 var popup = document.getElementById("popupList");
		        	 var ul = document.getElementById("popup-ul");
		        	 var bottem = document.getElementById("poput-bottem");
						
		        	
						var list = new Array();
						list = JSON.parse(request.response);
						
						if(list.length != 0 ){
							
						for(i=0; i < list.length; i++){
							
							
							
							var a = document.createElement('a');
							a.href = '${cpath}'+list[i].movePage;
							a.innerText = list[i].context;
							
							var btn = document.createElement('button');
							btn.className = "popup-btn";
							btn.innerText = "x";
							
							var li = document.createElement('li');
							li.className = "popup-li";
							
							li.appendChild(a);
							li.appendChild(btn);
							ul.appendChild(li);
							popup.appendChild(ul);
					
						}
						var a1 = document.createElement('a');
						a1.id ='bellChk';
						a1.href = "${cpath }/mymessage/${login.memberId}/receiver/";
						a1.innerText = "내 알림 전체보기";
						var li1 = document.createElement('li');
						li1.className = "popup-li";
						li1.appendChild(a1);
						ul.appendChild(li1);
						popup.appendChild(ul);
			         	}
						
						else{
							var div = document.createElement('div');
							div.className = "popup-li";
							div.innerText = "알람없음"
							bottem.appendChild(li);
							popup.appendChild(ul);
						}
						}
		      }
		   }
		   request.send();
		   
		   }
	   
	   alramContext();
	</script>