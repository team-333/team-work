history.scrollRestoration = "manual";	// 새로 고침 시 스크롤 위치 복원(AUTO), 복원안함(manual)

//Board_menu
function menubtn(data){
	$(data.children[0]).css('display','inherit');
	
	window.onclick = (event) => {
		console.log(event.target.classList);
	}
	
}

//function dropMenu () {
//	document.getElementById("profile__menus").classList.toggle("show");
//}
//
//window.onclick = (event) => {
//	if (!event.target.matches('.fa-caret-square-down')){
//		const dropmenu = document.getElementsByClassName("profile__menus");
//		for (i = 0 ; i < dropmenu.length; i++){
//			const openMenu = dropmenu[i];
//			if(openMenu.classList.contains('show')){
//				openMenu.classList.remove('show');
//			}
//		}
//	}
//	
//}
//document.getElementById("profile__menu").addEventListener("click",dropMenu);


$(document).ready(function(){
//	게시물 목록
var page = 1;
$(function() {
	getList(page);
	page++;
})

$(window).scroll(function(){
	if($(window).scrollTop() >= $(document).height() - $(window).height()){
		getList(page);
		page++;
	}
})

function getFormatDate(date){
	var year = date.getFullYear();              //yyyy
	var month = (1 + date.getMonth());          //M
	month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
	var day = date.getDate();                   //d
	day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
	var hour = date.getHours();
	hour = hour >= 10 ? hour : '0' + hour;          //day 두자리로 저장
	var minute = date.getMinutes();
	minute = minute >= 10 ? minute : '0' + minute;          //day 두자리로 저장
	return  year + '년 ' + month + '월 ' + day + '일 ' + hour + ':' + minute;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}

function getList(page){
	$.ajax({
		type: 'POST',
		dataType: 'json',
		data: {'page': page},
		url: '../yeol-gong/selectBoard',
		success: function(data){
			let text = '';
			
			if(page == 1)	$('#list_board').html(text);
			
			$.each(data, function(i, d){
				var time = getFormatDate(new Date(d.time));
				text += `
						<div class="art2">
							<div style="height: 80px;">
								<div>
									<span class="img"></span>
									<span class="name">${d.writer}</span>
									<span class="date">
										${time}
									</span>
									<span class="menu" onclick="menubtn(this);">
										<span id="board_menu">
											<div>수정하기</div>
											<div>삭제하기</div>	
										</span>
									</span>
								</div>
							</div>
							
							<div class="box">${d.context}</div>
							
							<div style="height: 40px; padding-bottom: 10px;">
									<span class="emoticon_icon"></span>
									<span class="emoticon_text">감정 표현</span>
									<span class="comment_icon"></span>
									<span class="comment_text">댓글 쓰기</span>
							</div>
						</div>
				`;
			})
			
			if(page == 1)	$('#list_board').html(text);
			else			$('#list_board').append(text);
				
			
		},
		error: function(e){
			if(e.status == 300){
				alert("데이터를 가져오기 실패");
			};
		}
	});
}
	
//	게시물 등록
$('#art1_btn').on('click', function() {
	var data = {
		writer: "고병재",
		context: $('#art1_box').val().replace(/(?:\r\n|\r|\n)/g, '<br/>'),
	}
	
	$.ajax({
		url: "../yeol-gong/insertBoard",
		data: data,
		type: "POST",
		success: function(){	// 게시물 작성 성공 시 초기화 및 목록 새로고침
			page = 1;
			getList(page);
			$('#art1_box').val('');
			$('#myModal').trigger('click');
		},
		error: function(){
			console.log('게시물 등록 실패');
		}
	})
	
});



//	Modal
$('#art1_box').on('click', function() {
	$('#myModal').show();
	$('#art1_btn').show();
	$('#wr').css('margin-bottom', '200px');
	$('#art1_box').css('height', '200px');
});

$('#myModal').on('click', function() {
	$('#myModal').hide();
	$('#art1_btn').hide();
	$('#wr').css('margin-bottom', '10px');
	$('#art1_box').css('height', '60px');
});


})
