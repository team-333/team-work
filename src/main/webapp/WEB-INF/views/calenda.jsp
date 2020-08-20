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

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" type="text/css" href="${cpath}/resources/css/calhome.css">

</head>
<body>
<%@ include file="header.jsp" %>
<main class="main-main">
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
		<c:if test="${login.memberId eq teamInfo.delegate }">
			<!-- 관리 버튼 활성화 시키기 -->
			<button onClick="location.href = '${cpath}/delegate/${teamInfo.teamId }/'">관리</button>
		</c:if>
	</section>

	<section class="container">
		<div id="calendar_div"> 
			<div id="calendar_main">
				<div id="calendar" class="calendar">
					<div class="calendar_change">			
						<div id="current_year_month">
							<div id="current_year"></div>년&nbsp;&nbsp;
							<div id="current_month"></div>
						</div>
						
						<div class="calBoxes">
							<div id="calBtns">
								<div id="prev"><i class="fas fa-angle-double-left"></i></div>
								<div id="today">Today</div>
								<div id="next"><i class="fas fa-angle-double-right"></i></div>
							</div>
			
							<div class="change-cal-hide">
								<input type="date" id="change-cal-date" class="change-cal-date">
								<button class="change-cal-move" onclick="changeDate()">&#10095;</button>
							</div>
						
						</div>
						
					</div>
					<table class="weekName_table">
						<tbody id = weekName_tbody>
							<tr class="weekName">
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
					<table id="calendar_week">
						<tbody id="calendar_date"></tbody>
					</table>
				</div> <!-- calendar -->
			</div>
		</div> <!-- calendar_div -->
		<div id="listModal">
			<div id="todoList">
				<div id="listhd">
					<div id="dateDiv">
						<div id="mini_date"></div>
						<div id="mini_day"></div>
					</div>
					<div id="BtnDiv">
                       <div id="deleteBtn"><i class="far fa-trash-alt"></i></div>					
                       <div id="selectBtn"><i class="fas fa-tasks"></i></div>
                       <div id="listBtn"><i class="fas fa-list-ul"></i></div>
                       <div id="updateBtn"><i class="far fa-edit"></i></div>
					</div>
				</div>
				<div id="showList">
					<div id="loadList"></div>
					<div id="plus">
						<i class="fas fa-plus-circle"></i>
					</div>
				</div>

				<div id="addList">
					<form id="addForm">
						<div class="titleBox">
							<input type="text" name="title" class="Title" id="title"  required="required" placeholder="제목" autofocus="autofocus">
						</div>
						<div class="dateBox">
							<i class="far fa-calendar-alt"> &nbsp;</i>  <input type="text" id="registDate" class="RegDate" name="registDate" placeholder="yyyy-mm-dd">
						</div>
						<div class="timeBox">
							<i class="far fa-clock"></i> &nbsp; <input type="time" id="regTime" class="RegTime" name="regTime" >
						</div>
						<div class="contextBox">
							<div class="cotIcon"><i class="fas fa-align-left"></i></div>
							<div class="cotArea"><textarea class="ContextArea" id="context" name="context" wrap="virtual" placeholder="내용 입력"></textarea></div>
						</div>
						<div class="BtnBoxes" id="BtnBoxes">
							<div class='cancelBox' class="Btns"><input type="button" onclick="cancelList()" value="취소"></div>
							<div class='saveBox' class="Btns"><input type="button" onclick="checkValue()" value="저장"></div>
						</div>
					</form>
				</div>

				<div id="updateList">
					<form id="updateForm">
						<div class="titleBox">
							<input type="text" name="title" class="Title" id="uTitle"  required="required" >
						</div>
						<div class="dateBox">
							<i class="far fa-calendar-alt"></i> : <input type="text" class="RegDate" id="uDate" class="datepicker1" name="registDate" placeholder="yyyy-mm-dd">
						</div>
						<div class="timeBox">
							<i class="far fa-clock"></i> : <input type="time" class="RegTime" id="uTime" name="regTime">
						</div>
						<div class="contextBox">
							<div class="cotIcon"><i class="fas fa-align-left"></i></div>
							<div class="cotArea"><textarea class="ContextArea" id="uContext" name="context" wrap="virtual" placeholder="내용 입력"></textarea></div>
						</div>
						<input type="hidden" name=inherence id="uInc">
						<div class="uptBtnBoxes" id="uptBtnBoxes">
							<div class='cancelBox' class="Btns"><input type="button" onclick="cancelUpdate()" value="취소"></div>
							<div class='saveBox' class="Btns"><input type="button" onclick="updateList()" value="저장"></div>
						</div>
					</form>
				</div>
			</div><!-- todoList -->
		</div><!-- listModal -->
		<script type="text/javascript" src="${cpath}/resources/js/calendars.js" ></script>
		<script type="text/javascript" src="${cpath}/resources/js/calendarTodoList.js"></script>
	</section>

</main>

</body>
</html>