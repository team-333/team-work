let currentTitle = document.getElementById('current_month_board'); // 달력 년월
let calendarDateBD = document.getElementById('calendar_date_board');

let today = new Date(); // 현재 날짜의 모든 정보
let firstDate = new Date(today.getFullYear(), today.getMonth(), 1);// 이번 달 첫 번째 날짜의 모든 정보
const monthList = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']; // 월(달)List
const leapYear = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // 윤년
const Year = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // 평년
let pageFirst = firstDate;
let pageYear;
let clickedDate = document.getElementById(today.getDate());	// 오늘 날짜
let getMonth = (firstDate.getMonth()+1) < 10 ? ('0' + (firstDate.getMonth()+1)) : (firstDate.getMonth()+1);
let getDate = today.getDate() < 10 ? '0' + today.getDate() : today.getDate();
let getTime = (new Date().getHours() < 10 ? ('0' + new Date().getHours()) : new Date().getHours()) + ':' + 
(new Date().getMinutes() < 10 ? ('0' + new Date().getMinutes()) : new Date().getMinutes());
let checkToday = firstDate.getFullYear() + '-' + getMonth + '-' + getDate;	// 현재 년, 월, 일 

let path = document.location.href;
let localpath = "http://localhost:8080";
//let localpath = "http://localhost:7070";
let usepath = path.substring(localpath.length);
let upath = usepath = usepath.replace('/study', '/study/calenda');

checkLeap();

// 윤년 계산
function checkLeap() {
	if(firstDate.getFullYear() % 4 === 0) { // 윤년 계산
		pageYear = leapYear;	// 윤년
	} 
	else {
		pageYear = Year; 		// 평년
	}
}

// 일정 DB에서 가져오기
const selectList = async () => {
	
	await axios.get(upath + 'select/' + checkToday.substring(0, checkToday.length-3) + "/")
	.then( (response) => {
		jsonData = JSON.stringify(response.data);
		listData = JSON.parse(jsonData);

		let monthDB = [];
		for(i = 1; i <= pageYear[firstDate.getMonth()]; i++){
			monthDB[i < 10 ? '0' + i : i] = [];
		}
		
		keys = Object.keys(monthDB);
		
		for(i = 0; i < listData.length; i++){
			valueDate = listData[i].registDate.substring(listData[i].registDate.length-2, listData[i].registDate.length);
			for(j = 0; j < keys.length; j++){
				if(keys[j] === valueDate){
					monthDB[keys[j]].push(listData[i]);
				}
			}
		}
		
		for(i = 1; i < monthDB.length; i++){
			cnt = i < 10 ? '0' + i : i;
			if(monthDB[cnt].length != 0){
				cntTd = document.getElementById(cnt);
				cntTd.style.backgroundColor = '#B2CCFF';
			}
		}	
		
	})
	.catch( (ex) => {
		alert('0 exception : ' + ex);
	})
}

function currentCal(){
	today = new Date();
	console.log(checkToday);
	firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
	getMonth = (firstDate.getMonth()+1) < 10 ? ('0' + (firstDate.getMonth()+1)) : (firstDate.getMonth()+1);
	checkToday = firstDate.getFullYear() + '-' + getMonth + '-' + getDate;	// 현재 년, 월, 일 
//	showDayList("none");
	removeCalendar();
	showCalendar();
	
}
// 달력 출력
function showCalendar() {
	let monthCnt = 100; // 임의 id값 지정 
	let cnt = 1;		// 날짜 <td> id값으로 사용
	for(let week = 0; week < 6; week++) {	// 최대 6주
		let tr = document.createElement('tr');
		tr.setAttribute('id', monthCnt);
		for(let date = 0; date < 7; date++){
			if((week === 0 && date < firstDate.getDay()) || cnt > pageYear[firstDate.getMonth()]){
				let td = document.createElement('td');
				tr.appendChild(td);
			}
			else {
				let td = document.createElement('td');
				let cntdiv = document.createElement('div');
				let cntspan = document.createElement('span');
				
				cntdiv.setAttribute('id', 'cntdiv_board');
				cntspan.setAttribute('id', 'cntspan_board');
				
				cntspan.textContent = cnt;
				cntdiv.appendChild(cntspan);
				td.appendChild(cntdiv);
				td.setAttribute('id', cnt < 10 ? '0' + cnt : cnt);
				td.setAttribute('onclick', 'calendaMove()');
				tr.appendChild(td);
				cnt++;
			} // else end
		}	// for(date) end
		monthCnt++;
		calendarDateBD.appendChild(tr);
	}	// for(week) end
	currentTitle.innerHTML = monthList[firstDate.getMonth()];
	clickedDate = document.getElementById(today.getDate() < 10 ? '0' + today.getDate() : today.getDate());	// 오늘 날짜
	clickedDate.classList.add('active_board');
	selectList();
}	// method end

showCalendar();

//달력 초기화
function removeCalendar() {
	for(let trId = 100; trId < 106; trId++) {	// 100 ~ 105 : id값 + 6주
		let tr = document.getElementById(trId);
		tr.remove();
	}
}

// 이전 달
function prev() {
	if(pageFirst.getMonth() === 1) {
		pageFirst = new Date(firstDate.getFullYear()-1, 12, 1);
		firstDate = pageFirst;
		checkLeap();	// 윤년 체크
	}
	else {
		pageFirst = new Date(firstDate.getFullYear(), firstDate.getMonth() -1, 1);
		firstDate = pageFirst;
	}
	today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());	// 인덱스 기준
	getMonth =  (firstDate.getMonth()+1) < 10 ? '0' + ((getMonth*1)-1) : (getMonth*1)-1;
	checkToday = firstDate.getFullYear() + '-' + getMonth + '-' + getDate;
	currentTitle.innerHTML = monthList[firstDate.getMonth()];
	// &nbsp; : 웹 사이트 공백(space var)표시 
	removeCalendar();
	showCalendar();
	clickedDate = document.getElementById(today.getDate() < 10 ? '0' + today.getDate() : today.getDate());	// 오늘 날짜
	clickedDate.classList.add('active');
}

// 다음 달
function next() {
	if(pageFirst.getMonth() === 12) {
		pageFirst = new Date(firstDate.getFullYear()+1, 1, 1);
		firstDate = pageFirst;
		checkLeap();
	}
	else{
		pageFirst = new Date(firstDate.getFullYear(), firstDate.getMonth()+1, 1);
		firstDate = pageFirst;
	}
	today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());
	getMonth =  (firstDate.getMonth()+1) < 10 ? '0' + ((getMonth*1)+1) : (getMonth*1)+1;
	checkToday = firstDate.getFullYear() + '-' + getMonth + '-' + getDate;
	currentTitle.innerHTML = monthList[firstDate.getMonth()];
	removeCalendar();
	showCalendar();
	clickedDate = document.getElementById(today.getDate() < 10 ? '0' + today.getDate() : today.getDate());	// 오늘 날짜
	clickedDate.classList.add('active');		
}

let prevBtn = document.getElementById('prev_board');
let nextBtn = document.getElementById('next_board');
let todayBtn = document.getElementById('today_board');
let XBtn = document.getElementById('fa-ti-cancel');
prevBtn.addEventListener('click', prev);
nextBtn.addEventListener('click', next);
todayBtn.addEventListener('click', currentCal);
XBtn.addEventListener('click', cancelPlan);


function calendaMove(){
	path = location.href.replace('/study','/study/calenda');
	location.href = path;
}

function checkPlan(){		
	document.getElementById('plan-simple-title').innerText = document.getElementById('title').value;
	document.getElementById('plan-simple-date').innerText = document.getElementById('registDate').value;
	document.getElementById('write-planCheck').style.display = 'flex';
	document.getElementById('write-function-area').style.display = 'none';
}

function cancelPlan(){
	document.getElementById('addForm_board').reset();
	document.getElementById('write-planCheck').style.display = 'none';
}

function cancelAddPlan(){
	document.getElementById('addForm_board').reset();
	document.getElementById('write-function-area').style.display = 'none';
}

// 게시물 일정등록
const insertList = async (inherence) => {
	formData = new FormData(document.getElementById('addForm_board'));
	ent = formData.entries();
	ob = {};
	
	while(true) {
		next = ent.next();
		if(next.done == true) break;
		ob[next.value[0]] = next.value[1];
	}
	
	ob["inherence"] =  inherence;
		
	await axios.post(upath + 'insert/', ob)
	.then( (response) => {
		jsonData = JSON.stringify(response.data);
		listData = JSON.parse(jsonData);
		
		document.getElementById('addForm_board').reset();
		
		removeCalendar();
		showCalendar();
	})
	.catch( (ex) => {
		alert('1 exception : ' + ex);
	})
}

const selectOneBoard = async (param) => {
	result = {};
	
	await axios.get(upath + 'selectOneBoard/' + param + '/')
	.then((response) => {
		result = response.data;
	})
	.catch( (ex) => {
		alert('7 exception : ' 	+ ex);
	})
	
	return result;
}

const deleteList = async ( checkedList ) => {
	await axios.post(usepath + 'delete/', checkedList)
	.then( (response) => {
		jsonData = response.data;
		console.log(jsonData);
		console.log(response);
		if(jsonData === '성공'){
			alert('삭제완료');
			removeCalendar();
			removeList();
			selectList('click');
			removeMiniList();
			showCalendar();
		}
		else{
			alert('삭제실패');
		}
		
	})
	.catch( (ex) => {
		alert('4 exception : ' + ex);
	})
}

//datepicker
function datepicker() {
	document.getElementById('regTime').value = getTime;

	$( ".datepicker1" ).datepicker({
		closeText: '닫기',
		changeMonth: true,
		changeYear: true,
		nextText: '다음달',
		prevText: '이전달',
		dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dateFormat: "yy-mm-dd",
		showButtonPanel: true,
		minDate: 0, // 선택할 수 있는 최소 날짜, (0 : 오늘 이전 날짜 선택 불가)
		showAnim: "fadeIn", // show(기본), slideDown, fadeIn, slide
	});

	$('.datepicker1').datepicker('setDate', 'today');
	$('.ui-datepicker').addClass('notranslate');	// Nan/Nan/Nan 오류 수정코드
}