<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 | Main</title>
<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<style>
    html, body, input { 
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    button {
        background: rgba(79, 76, 231, 0.808);
        color: rgba(255, 255, 255, 0.74);
        font-size: 18px;
        cursor: pointer;
    }

    .list-box { 
        width: 500px;
        margin: 100px auto;
        border: 1px solid #eee;
        padding: 20px 30px 50px;
        background: rgba(131, 110, 248, 0.267);
    }

    .list-box h1 {
        padding-bottom: 10px;
        text-align: center;
        color: #4e3030;
        border-bottom: 1px solid #737d9c;
    }

    .write-box {
        width: 100%;
        height: 35px;
        font-size: 0;
    }

    .write-box input {
        width: 400px;
        font-size: 20px;
        border: none;
        padding: 0 10px;
        height: 100%;
    }

    .write-box button {
        width: 100px;
        border: none;
        height: 100%;
    }

    .list-table {
        border-spacing:0px;
        border-collapse: collapse;
        width: 100%;
        margin: 20px 0;
    }

    th, td {
        padding: 10px 20px;
        font-size: 20px;
    }

    th { background: #9bd4e5cb; }

    td { text-align: right; color: #fff; }

    tbody td:first-child { text-align: center; }

    .btn-area { text-align: center; }

    .btn-area button { height: 35px; padding: 0 10px; border: none; }

    .modal{
        position: fixed;
        top: 0; left: 0;
        width: 100%; height: 100%;
        display: flex;
        justify-content: center;
        align-items : center;
    }

    .md_overlay {
        background-color: rgba(0, 0, 0, 0.6);
        width: 100%; height: 100%;
        position: absolute;
    }

    .md_content {
        width: 20%;
        position: relative;
        padding: 50px 100px;
        background-color: white;
        text-align: center;
        border-radius: 6px;
        box-shadow: 0 10px 20px rgba(0,0,0,0.20), 0 6px 6px rgba(0, 0, 0, 0.20);
    }

    h1 {
        margin:0; 
        padding: 5px;
    }

    .hidden { display: none; }

    .modal_text { padding: 16px; }
    
</style>

</head>
<body>

<%@ include file="header.jsp" %>

<main class="main-main">
	<section class="groupList">
		<div class="groupList__profile">
			<img class="profile__pic" alt="" src="https://www.topstarnews.net/news/photo/first/201711/img_327473_1.jpg" />
			<a href="">홍진호</a>
		</div>
		<hr>
		
		<div class="gruopList__list" style="margin-top: 30px;">
			<ul>
				<li class="list-context"><a href="${cpath }/myprofile/"  style="font-weight: 700; text-decoration: underline;">내 프로필</a></li>
				<li class="list-context"><a href="${cpath }/mystudies/">내 스터디</a></li>
				<li class="list-context"><a href="${cpath }/myinfo/">내 정보</a></li>
				<li class="list-context"><a href="">로그 아웃</a></li>
			</ul>
		</div>
	</section>
	
	<!-- ajax로 update 처리 -->
	<section class="container profile-setting-container" >
		<div class="profile-setting__intro__title">
			<span>Title</span>
			<i class="fas fa-pencil-alt"></i>
		</div>
		<div class="profile-setting__intro__context">
			<div class="textarea"> 폭풍저그 홍진호가 간다!<br>
			 야, 세르게이! 작은 고추의 매운 맛을 보여주마!
				<i class="fas fa-pencil-alt"></i>
			</div>
		</div>
		<div class="profile-setting__email">
			<span> kong22@ongame.net</span>
			<i class="fas fa-pencil-alt"></i>
		</div>
		
		<div class="list-box">
        <h1>공지</h1>
        <div class="write-box">
        <input type="text" class="text-basic">
        <button type="button" id="btnAdd">추가</button>
        </div>
        <table class="list-table">
        <colgroup>
        <col width="10%">
        <col width="90%">
        </colgroup>
        <thead>
        <tr>

        <th><span id="open">전체</span></th>
        </tr>
        </thead>
        <tbody id="listBody">
        
       
        </tbody>
        </table>
        <div class="btn-area">
        <button type="button" id="btnDelAll">전체 삭제</button>
        <button type="button" id="btnDelLast">마지막 항목 삭제</button>
        </div>
        </div>

       <div class="modal hidden">
       <div class="md_overlay"></div>
       <div class="md_content">
         <h1>멤버 목록</h1>
         <div class="modal_text">
            <table>
            	<tr><td><input type="checkbox"></td><th>전체</th></tr>
                   <c:forEach var="memberList" items="${memberList }">
                   <tr>
                   <td><input type="checkbox"></td>
                   <th>${memberList.username }</th>
                   </tr>
                   
                   </c:forEach> 

                </table>
         </div>
         <button style="width: 90%; height: 30px;">X</button>
       </div>
       </div>

	
	</section>

</main>

<script>


document.getElementById('btnAdd').addEventListener('click', addList); // 추가
document.getElementById('btnDelAll').addEventListener('click', delAllEle); // 전체삭제
document.getElementById('btnDelLast').addEventListener('click', delLastEle); // 마지막 요소 삭제



const openButton = document.getElementById("open");
const modal = document.querySelector(".modal");
const overlay = modal.querySelector(".md_overlay");
const closeButton = modal.querySelector("button");

const openModal = () => {
   modal.classList.remove("hidden");
}
const closeModal = () => {
   modal.classList.add("hidden");
}
openButton.addEventListener("click", openModal);
closeButton.addEventListener("click", closeModal);

   



// 추가
function addList() {
    var contents = document.querySelector('.text-basic');
if (!contents.value) {
alert('내용을 입력해주세요.');
contents.focus();
return false;
}
var tr = document.createElement('tr');
var td02 = document.createElement('td');
td02.innerHTML = contents.value;
tr.appendChild(td02);
document.getElementById('listBody').appendChild(tr);
contents.value='';
contents.focus();

}
// 전체삭제
function delAllEle() {
    var list = document.getElementById('listBody');
var listChild = list.children;
for(var i=0; i<listChild.length; i++) {
list.removeChild(listChild[i])
i--;
document.getcl
}
}
// 마지막 항목 삭제
function delLastEle() {
    var body = document.getElementById('listBody');
var list = document.querySelectorAll('#listBody > tr');
if(list.length > 0) {
var liLen = list.length-1;
body.removeChild(list[liLen]);
} else {
alert('삭제할 항목이 없습니다.')
return false;
}
}


 </script>

</body>
</html>