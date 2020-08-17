history.scrollRestoration = "manual";	// 새로 고침 시 스크롤 위치 복원(AUTO), 복원안함(manual)

// 게시물 목록
var page = 1;	// 목록 페이지 초기값
var groupCheck;
var scllCheck = {
		page: page,		// 목록 페이지
		scrollMax: '', 	// 이전 목록의 문서길이
		scrollSave: '',	// 현재 스크롤 위치
		teamid: window.location.href.split("/")[5],	// 그룹 아이디
}		

// 그룹원 확인
// 동기식 사용중
function groupCheck(){
	
	
	return result;
};

$(function() {
	// 문서 로딩 후 1페이지 출력	
	board_getList(page); 
	$(".write-article").hide();
	
	// 그룹원 확인
	// 함수도 만들었으나 호출이 안되노!!
	teamData = { 'teamid' : scllCheck.teamid };
	
	$.ajax({
		url: "../teamCheck/",
		type: "POST",
		data: teamData,
		dataType: "text",
		async: false,
		success: function(check){
			groupCheck = check * 1;
		},
		error: function(){
			console.log("그룹 : 통신불가");
		}
	})
})	

// 스크롤 조회
// 스크롤 버그 : 일부 브라우저는 소수점으로 표현하여 올림 처리 : 브라우저 문제 ㅡㅡ;
$(window).scroll(function(){
	scrollCheck = Math.ceil($(window).scrollTop()) >= $(document).height() - $(window).height();
	endCheck = scllCheck.scrollMax !== $(document).height();

	if(scrollCheck && endCheck){
		page++;
		board_getList(page);
		
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
function board_getList(page){
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
			                    <span class="read-comment-text">댓글보기</span>
			                </div>
			            </div>
			            <div class="comment-area">
			            	<div class="comment-read"></div>
							<div class="comment-write"
								contentEditable=true placeholder="댓글을 입력하세요.">
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
			
			if(groupCheck > 0)	$(".comment-write").show(); 
			else 				$(".comment-write").hide();
		},
		error: function(e){
				alert("목록 불어오기 실패 : 통신오류");
			
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


// 게시물 작성권한 확인
$(function(){
	if(groupCheck === 0)		console.log('로그아웃');
	else if(groupCheck > 0) {
		console.log('그룹 허용');
		$(".write-article").show();
	}
	else 				console.log('그룹 거부');
})

// 그룹장, 그룹원 메뉴 리스트
function boardCheck(pageNum, node){
	data = { 'page' : pageNum, 'teamid' : scllCheck.teamid };
		
	$.ajax({
		url: "../boardCheck/",
		type: "POST",
		data: data,
		dataType: "text",
		success: function(check){
			// 아래순으로 메뉴 순서
			check *= 1;
			
			node.children[1].innerHTML += `<div id="board_notice">공지 등록</div>`;
			node.children[1].innerHTML += `<div id="board_topFix">상단 고정</div>`;	
			node.children[1].innerHTML += `<div id="board_update">게시물 수정</div>`;	// 게시물 수정
			node.children[1].innerHTML += `<div id="board_delete">게시물 삭제</div>`;	// 게시물 삭제
			node.children[1].innerHTML += `<div id="board_report">신고</div>`;
			
			// 나중에 수정.. 메뉴를 전부 생성하고 일부만 삭제하여 기능 활성화
			switch(check){
			case 1:	// 그룹원 메뉴
				$('#board_notice').remove();
				break;
			case 2:	// 그룹장 메뉴
				break;
			case -2: // 그룹원의 그룹장 메뉴
				$('#board_update').remove();
				break;
			default:
				$('#board_notice').remove();
				$('#board_update').remove();
				$('#board_topFix').remove();
				$('#board_delete').remove();
				$('#board_notice').remove();
			}
			
			deleteBoard(pageNum);
			updateBoard(pageNum, node);
		},
	})
}

// 게시물 삭제
function deleteBoard(pageNum){
	endCheck = scllCheck.scrollMax === $(document).height() ? -1 : 0;

	$(function(){
		$('#board_delete').one('click', function() {
		var boardId = $('#board_delete').parents('article')[0].id;
		var data = 
		{ 
				'page' : pageNum,
				'teamid' : scllCheck.teamid,
		} ;
		
		$.ajax({
			url: "../deleteBoard/",
			type: "POST",
			data: data,
			dataType: "text",
			success: function(check){
				check *= 1;
				
				switch(check){
				case -1 :
					alert('게시물 삭제 오류 : 게시물이 존재하지 않습니다.')
					break;
				case 0 :
					alert('게시물 삭제 오류 : 그룹원 또는 작성자가 아닙니다.')
					break;
				case 1 :
					alert('게시물이 삭제되었습니다.');

					// 서버응답 전에 코드가 실행이 되므로 0.1초 지연
					function loadingPage(page, i){
						setTimeout(function (){
							if(i <= page){
								board_getList(i);
								loadingPage(page, ++i)
							}
						}, 100)}	// 지연속도 100 = 0.1초
					
					loadingPage(page + endCheck, 1);
					break;
				}
			},
			error: function(){
				alert("통신오류 : 삭제")
			}
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
			textarea = $('#' + id + " .read-box .read-context")[0].innerHTML.replace('<br>', '\n');
			modal('board_update');
			
			if($('#write-textarea').val() !== '')	$('#write-textarea').val('');
			
			$('#write-textarea').val(textarea);
				
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
					success:function(check){
						check *= 1;
						
						switch(check){
						case -1:
							alert('수정 실패: 게시물 존재하지 않거나 수정되지 않았습니다');
							break;
						case 0:
							alert('수정 실패: 로그인 상태 또는 작성자가 아닙니다.')
							break;
						case 1:
							page = 1;
							board_getList(page);
							$('#write-textarea').val('');
							$('#myModal').trigger('click');
							break;
						}
					}
				})
			})
			
		})
	})
}

// 게시물 등록	
$(function(){
	$('#write-textarea').on('click', function(){
		modal('board_insert');
		
		$('#write-btn').one('click', function() {
			var data = {
					'context': $('#write-textarea').val().replace(/(?:\r\n|\r|\n)/g, '<br/>'),
					'teamid' : scllCheck.teamid,
			}
			
			$.ajax({
				url: "../insertBoard/",
				type: "POST",
				data: data,
				dataType: "text",
				success	: function(check){	// 게시물 등록 성공 시 초기화 및 목록 새로고침
					check *= 1;
					switch(check){
					case -1:
						alert('등록 실패 : 그룹원이 아닙니다.')
						break;
					case 0:
						alert('등록 실패 : 로그아웃')
						break;
					case 1:
						page = 1;
						board_getList(page);
						$('#write-textarea').val('');
						$('#myModal').trigger('click');
						break;
					}
				},
				error: function(e){
					alert('등록 실패  : 통신 오류');
				}
			})	
		});
	})
})

//게시물 댓글 버튼
function commentbtn(node){
	var comment = node.children[5];
	var pageNum = node.id.split('-')[2];
	
	if(comment.style.display === ''){
		comment.style.display = 'inherit';
		comment_getList(1, pageNum, node);
		
		$(node).find('.comment-write').keydown(function(key) {
			if (key.keyCode == 13) {
				insertComment(pageNum, node);
			}
		});
	}
	else{
		comment.style.display = '';
		node.children[5].children[0].innerHTML = '';
		$(node).find('.comment-write').off();
	}	
}

// 게시물 댓글 리스트
function comment_getList(commentPage ,pageNum, node){
	data = {
			'page'		: commentPage,
			'pageNum'	: pageNum,
			'teamid'	: scllCheck.teamid,
	}
	
	$.ajax({
		url: "../selectComment/",
		type: "POST",
		data: data,
		dataType: "json",
		success: function(list){
			cmtPage = node.id.split('-')[2];
			cmt = node.children[5].children[0];
			
			$(cmt).find('.comment-more').remove();
			
			let text = cmt.innerHTML;
			let numbers = [];
			let imgs = [];
			
			cmt.innerHTML = '';
			
			if(commentPage == 1)	text = '';
			
			$.each(list[0], function(i, j){
				var time = getFormatDate(new Date(j.time));
				var parent = "parentNode.parentNode";

				imgs[i] = list[1][i];	// 이미지 주소 저장
				numbers[i] = j.cmtnum;		// 페이지 주소 저장
				
				text += `
					<div id="comment-list-${cmtPage}-${j.cmtnum}" class="comment-list">
						<div class="comment-box">
							<div class="comment-img"></div>
							<div class="comment-context">
								<span class="comment-writer">${j.writer}</span>
								${j.context}
							</div>
							<div class="commentMenu-img" onclick="cmtmenubtn(${parent})"></div>
						</div>
						<div class="comment-time">${time}</div>
					</div>
				`;
			})
			
			cmt.innerHTML += text;

			commentSize = node.getElementsByClassName('comment-list').length
			if(list[2] > commentSize) {
				cmt.innerHTML += `<div class="comment-more">댓글 더보기(${list[2] - commentSize})</div>`;
			}
			
			moreComment(list[2] ,pageNum, node);
			
			// 목록 생성 후 이미지 삽입
			for	(i = 0; i < numbers.length; i++){
				profile = $('#comment-list-' + cmtPage + '-' + numbers[i] + ' .comment-img');
				profile.css({'background':'url('+ imgs[i] +')', 'background-size':'contain'});
			}
			
			
		},
	})
}

// 댓글 더보기
function moreComment(count, pageNum, node){
	$(node).find('.comment-more').one('click', function() {
		commentCount = 	node.getElementsByClassName('comment-list').length;
		
		if(count > commentCount){
			commentPage = (commentCount / 3) + 1;
			comment_getList(commentPage ,pageNum, node);
		}
	})
}

// 댓글 등록		
function insertComment(pageNum, node){
	data = {
			'teamid' : scllCheck.teamid,
			'num': pageNum,
			'context': $(node).find('.comment-write').text(),
	}
	
	$.ajax({
		url: "../insertComment/",
		type: "POST",
		data: data,
		dataType: "json",
		success: function(check){
			comment_getList(1, 	pageNum, node);
			$(node).find('.comment-write').text('');
		}
	})
}

// 댓글 메뉴창
function cmtmenubtn(node){
	window.onclick = (event) => {
		parent = $(node).parent()[0];	// 최상위 부모(comment-read)
		child = $(node).find('.commentMenu-img');	// 댓글 버튼
		
		var list = $(parent).find('.comment-list');
		let html = '';
		var pageNum

		// 메뉴바 외 영역 선택 시 삭제
		if(!event.target.matches('.commentMenu-img')){
			child.find('.comment-menu-list').remove();
			window.onclick = null;
		}
		
		// 메뉴 아이콘 클릭 시 메뉴바 생성
		// 새로운 메뉴 아이콘 클릭 시 초기화
		else{
			$(parent).find('.comment-menu-list').remove();
			child[0].innerHTML = `<span class="comment-menu-list"></span>`;
			
			// 그룹별 기능 생성		
			if(event.toElement.className === 'commentMenu-img'){
				pageNum = event.path[2].id.split('-')[2];
				commentCheck(node);
			}
		}
	}
}

function commentCheck(node) {
	data = { 
			'page' : node.id.split('-')[2],
			'cmtPage' : node.id.split('-')[3],
			'teamid' : scllCheck.teamid, 
			};
	
	$.ajax({
		url: "../commentCheck/",
		type: "POST",
		data: data,
		dataType: "text",
		success: function(check){
			// 아래순으로 메뉴 순서
			check *= 1;
			
			$(node).find('.comment-menu-list')[0].innerHTML += `<div id="comment_delete">삭제</div>`;
			$(node).find('.comment-menu-list')[0].innerHTML += `<div id="comment_update">수정</div>`;
			$(node).find('.comment-menu-list')[0].innerHTML += `<div id="comment_notice">신고</div>`;
			
			// 나중에 수정.. 메뉴를 전부 생성하고 일부만 삭제하여 기능 활성화
			switch(check){
			case 1:	// 그룹원 메뉴
				$('#board_notice').remove();
				break;
			case 2:	// 그룹장 메뉴
				break;
			case -2: // 그룹원의 그룹장 메뉴
				$('#comment_update').remove();
				break;
			default:
				$('#comment_delete').remove();
				$('#comment_update').remove();
			}
		
			deleteComment(node);
			updateComment(node);
		},
	})
}

function deleteComment(node){
	$('#comment_delete').one('click', function() {
		var data = 
			{ 
				'page' : node.id.split('-')[2],
				'cmtPage' : node.id.split('-')[3],
				'teamid' : scllCheck.teamid, 
			};
		
		$.ajax({
			url: "../deleteComment/",
			type: "POST",
			data: data,
			dataType: "text",
			success: function(check){
				check *= 1;
				
				switch(check){
				case -1 :
					alert('게시물 삭제 오류 : 게시물이 존재하지 않습니다.')
					break;
				case 0 :
					alert('게시물 삭제 오류 : 그룹원 또는 작성자가 아닙니다.')
					break;
				case 1 :
					alert('게시물이 삭제되었습니다.');
					
					parent = $(node).parent()[0];
					cmtList = $(parent).find('.comment-list').length;
					cmtPage = cmtList / 3;
					node = $(node).parent().parent().parent()[0];
					
					if(cmtList % 3 !== 0)	cmtPage += 1;
					
					// 서버응답 전에 코드가 실행이 되므로 0.1초 지연
					function loadingPage(cmtPage, pageNum, node, i){
						setTimeout(function (){
							if(i <= cmtPage){
								comment_getList(i, pageNum, node);
								loadingPage(cmtPage, pageNum, node, ++i);
							}
						}, 100)}	// 지연속도 100 = 0.1초
					
					loadingPage(cmtPage, data.page, node, 1);
					break;
				}
			},
			error: function(){
				alert("통신오류 : 삭제")
			}
		})
		});
}
function updateComment(node){
	$('#comment_update').one('click', function() {
		$(node).find('.comment-context').hide();
		$(node).find('.commentMenu-img').hide();
		$(node).find('.comment-time').hide();
		cmtBox = $(node).find('.comment-box');
		
		// 수정 내용 가져옴
		writer = $(node).find('.comment-writer').text();
		text = $(node).find('.comment-context').text().replace(writer, '')	;
		
		$('<div />', {
			class : 'comment-update-write',
			contenteditable : 'true',
			text: text,
			keydown : function(key){
				if (key.keyCode == 13) {
					data = {
						'teamid' : scllCheck.teamid,
						'num': node.id.split('-')[2],
						'cmtnum': node.id.split('-')[3],
						'context': $(node).find('.comment-update-write').text(),
					};
					
					$.ajax({
						url: "../updateComment/",
						type: "POST",
						data: data,
						dataType: "text",
						success: function(check){
							check *= 1;
							
							switch(check){
							case -1:
								alert('수정 실패: 게시물 존재하지 않거나 수정되지 않았습니다');
								break;
							case 0:
								alert('수정 실패: 로그인 상태 또는 작성자가 아닙니다.')
								break;
							case 1:
								page = 1;
								node = $(node).parent().parent().parent()[0];
								comment_getList(page, data.num, node);
								break;
							}
							
						}
					});
					
					$(node).find('.comment-update-write').remove();
					$(node).find('.comment-context').show();
					$(node).find('.commentMenu-img').show();
					$(node).find('.comment-time').show();
				}
				if(key.keyCode == 27){
					$(node).find('.comment-update-write').remove();
					$(node).find('.comment-context').show();
					$(node).find('.commentMenu-img').show();
					$(node).find('.comment-time').show();
				}
			}
		}).appendTo(cmtBox);
		
		
	})
}
// 게시물 작성 기능
// 캘린더
function calenda(){
	$('#write-function-area').show();
	myModal = document.getElementById('write-function-area');
	$('#write-planCheck').css('display', 'none');
	$('#addList_board').css('display', 'block');

	// jquery css 불러오기에 강제맞춤
	$('#registDate').on('click',function(){
		$('#ui-datepicker-div').css('z-index', '2');
		$('#ui-datepicker-div').css('box-shadow', '0px 6px 6px rgba(0, 0, 0, 0.25)');
		
	})
}

//	Modal
function modal(id){
	$('#myModal').show();
	$('#write-article').css('z-index', '2');
	$('#write-textarea').css('height', '200px');
	$('#write-function-icon').show();
	$('#write-function-icon').css('display', 'flex');
	$('#write-btn').show();
	
	
	switch(id){
	case 'board_update':
		$(window).bind('scroll.update',function() {
			if($(window).scrollTop() >= 300) 	$('#write-article').css({position: 'sticky', top: '25vh' });
			else								$('#write-article').css({position: 'sticky', top: '10vh' });
		})
		$('#write-btn').text('수정');
		$('#write-btn').attr('id', 'update-btn');
		break;
	
	}
	
	$('#myModal').one('click', function() {
		switch(id){
		case 'board_insert':
			$('#write-btn').off();
			break;
		case 'board_update':
			$('#write-textarea').val('');
			$('#update-btn').off();
			$('#update-btn').text('게시');
			$('#update-btn').attr('id', 'write-btn');
			$(window).unbind('.update');
			break;
		}
		
		$('#myModal').hide();
		$('#myModal').text('');
		$('#write-article').removeAttr('style');
		$('#write-textarea').removeAttr('style');
		$('#write-function-icon').hide();
		$('#write-btn').removeAttr('style');
		$('#write-btn').hide();
		$('#write-function-area').hide();
		$('#write-calenda').remove();
		
	})
}