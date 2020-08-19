<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath">${pageContext.request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>열공 | ${teamInfo.teamName }</title>

<link rel="stylesheet" type="text/css" href="${cpath}/css/style.css" />
<link rel="stylesheet" href="${cpath}/css/board.css" />

<script src="https://kit.fontawesome.com/cc3f76d574.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>

<script src="${cpath }/js/board.js"></script>

<!-- 캘린더 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" href="${cpath}/css/calhome.css" />
<link rel="stylesheet" href="${cpath}/css/boardCalendar.css" />

</head>
<body>
<%! String page = "Hello"; %>
<%@ include file="header.jsp" %>
<main class="main-main">
	<!-- Modal-background -->
	<div id="myModal" class="modal"></div>
	
	<section class="groupList">
		<div class="thumbnail-wrapper">
			<div class="thumbnail"> 
				<div class="centered"> 
					<img src="${teamInfo.teamPicture }"> 
				</div> 
			</div> 
		</div>
		<hr>
		
		<div class="teamTitle groupTitle">${teamInfo.teamName }</div>
		<div class="group-wrapper"> 
			${teamInfo.teamInfo }
		</div>
		
		<div class="gruopList__list">
			스터디 캡틴 : ${captain.username}
		</div>
		<button onclick="location.href = '${cpath}/joininstudy/${teamInfo.teamId}/'">가입하기</button>
		<c:if test="${login.memberId eq teamInfo.delegate }">
			<!-- 관리 버튼 활성화 시키기 -->
			<button onClick="location.href = '${cpath}/delegate/${teamInfo.teamId }/'">관리</button>
		</c:if>
		
		<div class="group-calenda">	
			<div id="calendar_div_board"> 
				<div id="calendar_main_board">
					<div id="calendar_board" class="calendar_board">
						<div class="calendar_change_board">
							<div id="prev_board" class="prev_board"></div>

							<div id="calendar-current-board" class="calendar-current-board">
								<div id="current_month_board" class="current_month_board"></div>
								<div id="today_board" class="today_board"></div>
							</div>

							<div id="next_board" class="next_board"></div>
						</div>
						<table class="weekName_table_board">
							<tbody class="weekName_tbody_board">
								<tr class="weekName_board">
									<td class="sun">일</td>
									<td>월</td>
									<td>화</td>
									<td>수</td>
									<td>목</td>
									<td>금</td>
									<td class="sat">토</td>
								</tr>
							</tbody>
						</table>					
						<table id="calendar_week_board">
							<tbody id="calendar_date_board"></tbody>
						</table>
					</div> <!-- calendar -->
				</div>
			</div> <!-- calendar_div -->
		</div>
		
	</section>

	<!-- 게시물 화면 -->
	<section class="container-board">
		<article id="write-modal">
	       <article id="write-article" class="write-article">
	            <div class="write-header write-common">게시물 쓰기</div>
	            <div>
		            <div class="write-profile-img write-common"
		            style="background: url(${login.pictureUrl}); background-size: contain"></div>
		            <div id="write-function-icon" class="write-function-icon">
		            	<i id="fa-cal-alt" class="far fa-calendar-alt" onclick="calenda()"></i>
		            	<i class="fas fa-image"></i>
		            	<i class="fas fa-paperclip" onclick="fileAttachment()"></i>
		            </div>
					
	            </div>
	            
			<!-- 게시물 작성 -->
	            <div class="write-textbox">
	                <textarea id="write-textarea" onclick="modal(this.id)"></textarea>
	                
	                <!-- 일정 추가 -->
	                <div id="write-planCheck" class="board-planCheck">
	                	<div class="cal-icon"><i id="fa-cal-alt" class="far fa-calendar-alt"></i></div>
	                	<div id="plan-simple" class="plan-simple">
	                		<div id="plan-simple-title" class="plan-simple-title"></div>
	                		<div id="plan-simple-date" class="plan-simple-date"></div>
	                	</div>
	                	<div class="cal-icon"><i id="fa-ti-cancel" class="fas fa-times"></i></div>
	                </div>
	           	</div>
	   		    <div id="write-btn" class="write-btn">게시</div>
	       </article>
	       
	       <!-- 일정 작성 -->
	       <article id="write-function-area" class="write-function-area">
	       		<div id="addList_board">
					<form id="addForm_board">
						<div class="titleBox_board">
							<input type="text" name="title" class="Title" id="title"  required placeholder="제목">
						</div>
						<div class="dateTimeBox_board">
							<div class="dateBox_board">
								<i id="fa-cal-alt" class="far fa-calendar-alt"></i>&nbsp;&nbsp;<input type="date" id="registDate" class="datepicker1" name="registDate">
							</div>
							<div class="timeBox_board">
								<i id="fa-clk" class="far fa-clock"></i>&nbsp;&nbsp;<input type="time" id="regTime" class="RegTime" name="regTime" >
							</div>
						</div>
						<div class="contextBox_board">
							<div class="cotIcon_board"><i id="fa-ali-lef" class="fas fa-align-left"></i></div>
							<div class="cotArea_board"><textarea class="ContextArea_board" id="context" name="context" wrap="virtual" placeholder="내용 입력"></textarea></div>
						</div>
						<div class="BtnBoxes_board" id="BtnBoxes">
							<div class='cancelBox_board' class="Btns"><input type="button" onclick="cancelAddPlan()" value="취소"></div>
							<div class='saveBox_board' class="Btns"><input type="button" onclick="checkPlan()" value="확인"></div>
						</div>
					</form>
				</div>
	       </article>
		</article>
       
		<!-- 게시물 목록  -->
		<article id="list_board"></article>
		<article style="height: 300px; margin-bottom: 100px;"></article>
	</section>

</main>

<!-- 캘린더 -->
<script type="text/javascript" src="${cpath}/resources/js/boardCalendar.js"></script>

</body>
</html>
