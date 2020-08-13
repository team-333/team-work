<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>열공 | Message </title>
<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

<%@ include file="header.jsp" %>
<script src="${cpath}/js/profile.js"></script>

<main class="main-main">
   <section class="groupList">
      <div class="groupList__profile">
         <img class="profile__pic" alt="" src="${login.pictureUrl }" />
         <a href="${cpath }/myprofile/${login.memberId}/">${login.username }</a>
      </div>
      <hr>

      <div class="gruopList__list" style="margin-top: 30px;">
         <ul>
            <li class="list-context"><a href="${cpath }/myprofile/${login.memberId}/">내 프로필</a></li>
            <li class="list-context"><a href="${cpath }/mymessage/${login.memberId}/" style="font-weight: 700; text-decoration: underline;">쪽지함</a></li>
            <li class="list-context"><a href="${cpath }/mystudies/${login.memberId}/">내 스터디</a></li>
            <li class="list-context"><a href="${cpath }/myinfo/">내 정보</a></li>
            <li class="list-context"><a href="">로그 아웃</a></li>
         </ul>
      </div>
   </section>

   <section class="container profile-setting-container">
      <div class="buttonsM">
         <span class="buttonsAns">
            <button id="ans">답장</button>
            <button id="deleteMessage">삭제</button>         
         </span>
      </div><br>

        <table class="messageListss">
            <tr id="messagesss">
                <th id="checkk"><input type="checkbox" id="checkAllbox"></th>
                <th>보낸 사람</th>
                <th>제목</th>
                <th>받은 시간</th>
            </tr>
            <tr>
                <th id="checkk"><input type="checkbox" id="checkOnebox"></th>
                <td>이름</td>
                <td>note</td>
                <td>2020-08-08</td>
            </tr>
        </table>

        <div class="modals hiddens">
              <div class="md_overlays"></div>
              <div class="md_contents">
                  <h2>쪽지</h2>
                  <div class="modal_texts">
                      <div class="addressTexts">
                          <select id="addressSelect" name="emailAddress" required>
                              <option value="none"> == 받는 주소 선택 == </option>
                              <option value="">주소1</option>
                              <option value="">주소2</option>
                              <option value="">주소3</option>
                          </select>
                      </div>
                      <textarea wrap="virtual"></textarea>
                  </div>
                  <button id="messageSend" onclick="">전송</button>
              </div>
          </div>
   </section>

   <script>
        const openButton = document.getElementById("ans");
        const modal = document.querySelector(".modals");
        const overlay = modal.querySelector(".md_overlays");
        const closeButton = modal.querySelector("button");
        const openModal = () => {
            modal.classList.remove("hiddens");
        }
        const closeModal = () => {
            modal.classList.add("hiddens");
        }
        openButton.addEventListener("click", openModal);
        overlay.addEventListener("click", closeModal);
        closeButton.addEventListener("click", closeModal);
    </script>

</main>

</body>
</html> 