history.scrollRestoration = "manual";	// 새로 고침 시 스크롤 위치 복원(AUTO), 복원안함(manual)

// 게시물 목록
var page = 1;	// 목록 페이지 초기값
var scllCheck = {
		page: page,		// 목록 페이지
		scrollMax: '', 	// 이전 목록의 문서길이
		scrollSave: '',	// 현재 스크롤 위치
		teamid: window.location.href.split("/")[5],	// 그룹 아이디
};

$(function() {	getList(page); })	// 문서 로딩 후 1페이지 출력

// 스크롤 조회
// 스크롤 버그 : 일부 브라우저는 소수점으로 표현하여 올림 처리 : 브라우저 문제 ㅡㅡ;
$(window).scroll(function(){
	scrollCheck = Math.ceil($(window).scrollTop()) >= $(document).height() - $(window).height();
	endCheck = scllCheck.scrollMax !== $(document).height();

	if(scrollCheck && endCheck){
		page++;
		getList(page);
		
		scllCheck.page = page;
		scllCheck.scrollMax = $(document).height();;
	}
	
})

//날짜 초기화
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

//게시판 목록 불러오기
function getList(page){
	$.ajax({
		type: 'POST',
		dataType: 'json',
		data: {'page': page, 'teamid' : scllCheck.teamid},
		url: '../selectBoard/',
		success: function(data){
			let text = '';
			let numbers = [];
			let imgs = [];
			
			if(page == 1)	$('#list_board').html(text);
			$.each(data[0], function(i, d){
				var time = getFormatDate(new Date(d.time));
				var parent = "parentNode.parentNode";
				
				imgs[i] = data[1][i];	// 이미지 주소 저장
				numbers[i] = d.num;		// 페이지 주소 저장
				
				text += `
			       <article id="read-pageNum-${d.num}" class="read-article">
			            <div class="read-profile-img"></div>
			            <div class="read-info">
			                <span class="read-name">${d.writer}</span>
			                <span class="read-time">${time}</span>
			            </div>
			            <div class="read-menu">
			            	<span class="read-menu-icon" onclick="menubtn(parentNode)"></span>
			            </div>
			            <div class="read-box"><div class="read-context">${d.context}</div></div>
			            <div class="read-it">
			                <div class="read-emotion">
			                    <span class="read-emotion-icon"></span>
			                    <span class="read-emotion-text">감정표현</span>
			                </div>
			                <div class="read-comment" onclick="commentbtn(${parent})">
			                    <span class="read-comment-icon"></span>
			                    <span class="read-comment-text">댓글쓰기</span>
			                </div>
			            </div>
			            <div class="comment-area">
			            	<div class="comment-read"></div>
							<div class="comment-write" contentEditable=true>
							</div>
			            </div>
			
			       </article>
				`;
				})
				
			if(page == 1)	$('#list_board').html(text);
			else			$('#list_board').append(text);
			
			// 목록 생성 후 이미지 삽입
			for(i = 0; i < numbers.length; i++){
				profile = $('#read-pageNum-' + numbers[i]).children('.read-profile-img');
				profile.css({'background':'url('+ imgs[i] +')', 'background-size':'contain'});
			}
		},
		error: function(e){
				alert("데이터를 가져오기 실패");
			
		}
	});
}

// 게시물 메뉴창 생성
function menubtn(data){
	window.onclick = (event) => {
		var list = document.getElementById('list_board').querySelectorAll('article');
		let html = '';
		var pageNum
		
		// 메뉴바 외 영역 선택 시 삭제
		if(!event.target.matches('.read-menu-icon')){
			data.removeChild(data.children[1]);
			window.onclick = null;
		}
		// 메뉴 아이콘 클릭 시 메뉴바 생성
		// 새로운 메뉴 아이콘 클릭 시 초기화
		else{
			for(i = 0; i < list.length; i++){
				if(list[i].children[2].children.length == 2){
					list[i].children[2].removeChild(list[i].children[2].children[1]);
				}
			}
			
			data.innerHTML += `<span class="read-menu-list"></span>`;
			
			// 그룹별 기능 생성		
			if(event.toElement.className === 'read-menu-icon'){
				pageNum = event.path[2].id.split('-')[2];
				boardCheck(pageNum, data);
			}
		}
	}
}

//게시물 댓글
function commentbtn(pn){
	var comment = pn.children[5];
	
	if(comment.style.display === ''){
		comment.style.display = 'inherit';
	}
	else{
		comment.style.display = '';
	}
}
// 그룹원 확인
// 작성권한 확인
$(function(){
	teamData = { 'teamid' : scllCheck.teamid };
	
	$.ajax({
		url: "../teamCheck/",
		type: "POST",
		data: teamData,
		dataType: "text",
		success: function(check){
			if(check === "허용"){
				console.log("Success: 그룹" + check);
				$(".write-article").show();
			}
			else{
				console.log("Fail: 그룹" + check);
				$(".write-article").hide();
			}
		},
		error: function(check){
			console.log("ERROR:" + check);
			$(".write-article").hide();
		}
	})
})

// 게시물 사용자 확인
// 그룹별 메뉴 리스트
function boardCheck(pageNum, node){
	data = { 'page' : pageNum, 'teamid' : scllCheck.teamid };
	
	$.ajax({
		url: "../boardCheck/",
		type: "POST",
		data: data,
		dataType: "text",
		success: function(e){
			// 아래순으로 메뉴 순서
			
			if(e === '허용'){
				node.children[1].innerHTML += `<div>상단 고정</div>`;
				node.children[1].innerHTML += `<div id="board_update">게시물 수정</div>`;	// 게시물 수정
				node.children[1].innerHTML += `<div id="board_delete">게시물 삭제</div>`;	// 게시물 삭제
				deleteBoard(pageNum);
				updateBoard(pageNum, node);
			}
			
			node.children[1].innerHTML += `<div>신고</div>`;
		},
	})
}

// 게시물 삭제
function deleteBoard(pageNum){
	endCheck = scllCheck.scrollMax === $(document).height() ? -1 : 0;

	$(function(){
		$('#board_delete').one('click', function() {
		var boardId = $('#board_delete').parents('article')[0].id;
		var data = { 
				'page' : pageNum,
				'teamid' : scllCheck.teamid,
		} ;
		
		$.ajax({
			url: "../deleteBoard/",
			type: "POST",
			data: data,
			dataType: "text",
			success: function(e){
				if(e === '삭제성공'){
					alert('게시물이 삭제되었습니다.');
					
					// 서버응답 전에 코드가 실행이 되므로 0.1초 지연
					function loadingPage(aa, i){
						setTimeout(function (){
							if(i <= aa){
								getList(i);
								loadingPage(aa, ++i)
							}
						}, 100)}	// 지연속도 100 = 0.1초
					
					loadingPage(page + endCheck, 1);
				}
				console.log(e);
			},
		})
		});
	})
}

// 게시물 수정
function updateBoard(pageNum, node){
	id = node.parentNode.id;
	scllCheck.scrollSave = $(window).scrollTop();
	
	$(function() {
		$('#board_update').one('click', function() {
			modal('board_update');
			if($('#write-textarea') != ''){
				console.log($('#write-textarea'));
			}
			else{
				$('#write-textarea').text($('#' + id + " .read-box .read-context").text());
			}
			
			$('#update-btn').one('click', function(){
				data = { 
						'num' : pageNum, 
						'teamid' : scllCheck.teamid,
						'context': $('#write-textarea').val().replace(/(?:\r\n|\r|\n)/g, '<br/>'),
						};
				
				$.ajax({
					url: "../updateBoard/",
					type: "POST",
					data: data,
					dataType: "text",
					success:function(e){
						page = 1;
						getList(page);
						$('#write-textarea').val('');
						$('#myModal').trigger('click');
					}
				})
			})
			
		})
	})
}

// 게시물 등록	
$(function(){
	$('#write-btn').on('click', function() {
		console.log('insert실행')
		var data = {
				'context': $('#write-textarea').val().replace(/(?:\r\n|\r|\n)/g, '<br/>'),
				'teamid' : scllCheck.teamid,
		}
		
		$.ajax({
			url: "../insertBoard/",
			type: "POST",
			data: data,
			dataType: "text",
			success: function(e){	// 게시물 등록 성공 시 초기화 및 목록 새로고침
				if(e === '등록 성공'){
					console.log("Success: "+ e);
					page = 1;
					getList(page);
					$('#write-textarea').val('');
					$('#myModal').trigger('click');
				}
				else{
					alert("등록 실패 : " + e);
				}
				
			},
			error: function(e){
			console.log('등록 실패  : 통신 오류');
			}
		})	
	});
})

//	Modal
function modal(id){
	$('#myModal').show();
	$('#write-article').css('z-index', '2');
	$('#write-textarea').css('height', '200px');
	$('#write-btn').show();
	
	switch(id){
	case 'board_update':
		$(window).bind('scroll.update',function() {
			if($(window).scrollTop() >= 300) 	$('#write-article').css({position: 'sticky', top: '25vh' });
			else								$('#write-article').css({position: 'sticky', top: '10vh' });
		})
		$('#write-btn').off('click');
		$('#write-btn').text('수정');
		$('#write-btn').attr('id', 'update-btn');
		break;
	
	}
	
	$('#myModal').one('click', function() {
		switch(id){
		case 'board_update':
			console.log('실행확인');
			$('#write-textarea').text('');
			$('#update-btn').text('게시');
			$('#update-btn').attr('id', 'write-btn');
			$(window).unbind('.update');
			break;
		}
		
		$('#myModal').hide();
		$('#write-article').removeAttr('style');
		$('#write-textarea').removeAttr('style');
		$('#write-btn').removeAttr('style');
		$('#write-btn').hide();
		
	})
}

	
	
