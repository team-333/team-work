let currentTitle = document.getElementById('current_year_month'); // 달력 년월
let calendarDate = document.getElementById('calendar_date');
let calendarDateBD = document.getElementById('calendar_date_board');

let today = new Date(); // 현재 날짜의 모든 정보
let firstDate = new Date(today.getFullYear(), today.getMonth(), 1);// 이번 달 첫 번째 날짜의 모든 정보
const dayList = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일']; // 요일List
const monthList = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']; // 월(달)List
const leapYear = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // 윤년
const Year = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // 평년
let pageFirst = firstDate;
let pageYear;
let miniDate = document.getElementById('mini_date');
let miniDay = document.getElementById('mini_day');
let clickedDate = document.getElementById(today.getDate());	// 오늘 날짜
let getMonth = (firstDate.getMonth()+1) < 10 ? ('0' + (firstDate.getMonth()+1)) : (firstDate.getMonth()+1);
let getDate = today.getDate() < 10 ? '0' + today.getDate() : today.getDate();
let checkToday = firstDate.getFullYear() + '-' + getMonth + '-' + getDate;	// 현재 년, 월, 일 

let getTime = (new Date().getHours() < 10 ? ('0' + new Date().getHours()) : new Date().getHours()) + ':' + 
(new Date().getMinutes() < 10 ? ('0' + new Date().getMinutes()) : new Date().getMinutes());
console.log(getTime);

let path = document.location.href;
let localpath = "http://localhost:8080";
let usepath = path.substring(localpath.length);
let upath = [];
upath = usepath.split('/');
console.log(usepath);
console.log(upath);

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
const selectList = async (select) => {
	await axios.get(usepath + 'select/' + checkToday.substring(0, checkToday.length-3) + "/")
	.then( (response) => {
		jsonData = JSON.stringify(response.data);
		listData = JSON.parse(jsonData);
		loadList = document.getElementById('loadList');

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

		switch(select){
		case "click":
			active = document.getElementsByClassName('active')[0].id;

			if(monthDB[active].length === 0){
				document.getElementById('selectBtn').style.display = 'none';
				document.getElementById('deleteBtn').style.display = 'none';
				document.getElementById('updateBtn').style.display = 'none';
				noList = document.createElement('div');
				noList.setAttribute('id', 'noList');
				todo = document.createElement('span');
				todo.innerText = '일정 없음';
				noList.appendChild(todo);
				loadList.appendChild(noList);
			}
			else{
				document.getElementById('selectBtn').style.display = 'inline-block';
				document.getElementById('deleteBtn').style.display = 'none';
				document.getElementById('updateBtn').style.display = 'none';
				for(i = 0; i < monthDB[active].length; i++){
					doList = document.createElement('div');
					doList.setAttribute('id', 'doList');
					console.log(monthDB);

					checkbox = document.createElement('input');
					checkbox.type = 'checkbox';
					checkbox.setAttribute('id', 'checkList');
					checkbox.setAttribute('name', 'checkList');
					checkbox.setAttribute('class', monthDB[active][i].inherence);
					checkbox.style.visibility = 'hidden';

					doList.appendChild(checkbox);

					todo = document.createElement('span');
					todo.innerText = monthDB[active][i].title;
					todo.setAttribute('class', monthDB[active][i].inherence);
					todo.setAttribute('id', 'todo');
					todo.setAttribute('onclick',"updateEv(this.className)");
					doList.appendChild(todo);
					loadList.appendChild(doList);
				}
			}
			break;
		default:
			for(i = 1; i < monthDB.length; i++){
				cnt = i < 10 ? '0' + i : i;

				if(monthDB[cnt].length != 0){
					cntTd = document.getElementById(cnt);
					if(monthDB[cnt].length > 2){
						for(j = 0; j < 2; j++){
							miniList = document.createElement('div');
							miniList.setAttribute('id', 'miniList');
							miniList.innerText = monthDB[cnt][j].title;
							cntTd.appendChild(miniList);
						}
						another = document.createElement('div');
						another.setAttribute('id', 'another');
						another.innerText = '. . .';
						cntTd.appendChild(another);
					}
					else{
						for(j = 0; j < monthDB[cnt].length; j++){
							miniList = document.createElement('div');
							miniList.setAttribute('id', 'miniList');
							miniList.innerText = monthDB[cnt][j].title;
							cntTd.appendChild(miniList);
						}
					}
				}	
			}
		}

	})
	.catch( (ex) => {
		alert('0 exception : ' + ex);
	})
}

// 현재 날짜로 이동
function currentCal(){
	today = new Date();
	console.log(checkToday);
	firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
	getMonth = (firstDate.getMonth()+1) < 10 ? ('0' + (firstDate.getMonth()+1)) : (firstDate.getMonth()+1);
	checkToday = firstDate.getFullYear() + '-' + getMonth + '-' + getDate;	// 현재 년, 월, 일 
	removeList();
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

				cntdiv.setAttribute('id', 'cntdiv');
				cntspan.setAttribute('id', 'cntspan');

				cntspan.textContent = cnt;
				cntdiv.appendChild(cntspan);
				td.appendChild(cntdiv);
				td.setAttribute('id', cnt < 10 ? '0' + cnt : cnt);
				tr.appendChild(td);
				cnt++;
			} // else end
		}	// for(date) end
		monthCnt++;
		calendarDate.appendChild(tr);
	}	// for(week) end
	currentTitle.innerHTML = firstDate.getFullYear() + '년 &nbsp;' + monthList[firstDate.getMonth()];
	clickedDate = document.getElementById(today.getDate() < 10 ? '0' + today.getDate() : today.getDate());	// 오늘 날짜
	clickedDate.classList.add('active');
	removeList();
	showDayList("none");
	clickStart();
}	// method end

showCalendar();

// 달력 초기화
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
	currentTitle.innerHTML = firstDate.getFullYear() + '년 &nbsp;' + monthList[firstDate.getMonth()];
	// &nbsp; : 웹 사이트 공백(space var)표시 
	removeList();
	removeCalendar();
	showCalendar();
	showToday();
	clickedDate = document.getElementById(today.getDate() < 10 ? '0' + today.getDate() : today.getDate());	// 오늘 날짜
	clickedDate.classList.add('active');
	clickStart();

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
	currentTitle.innerHTML = firstDate.getFullYear() + '년 &nbsp;' + monthList[firstDate.getMonth()];	
	removeList();
	removeCalendar();
	showCalendar();
//	showToday();
	showDayList('none');
	clickedDate = document.getElementById(today.getDate() < 10 ? '0' + today.getDate() : today.getDate());	// 오늘 날짜
	clickedDate.classList.add('active');	
	clickStart();

}

//현재 요일, 날짜 출력
function showDayList(select){
	document.getElementById('loadList').style.display = 'block';
	document.getElementById('addList').style.display = 'none';
	document.getElementById('updateList').style.display = 'none';
	miniDay.innerHTML = dayList[today.getDay()];
	miniDate.innerHTML = monthList[firstDate.getMonth()] + " " + today.getDate() + '일';
	removeList();
	selectList(select);
	document.getElementById('registDate').value = checkToday;
	console.log('ch : ' + document.getElementById('registDate'));
	console.log("showDayList : " + checkToday);
}

function showToday(){
	miniDate.innerText = monthList[firstDate.getMonth()] + " " + today.getDate() + '일';
	miniDay.innerText = dayList[today.getDay()];
	removeList();
	document.getElementById('registDate').value = checkToday;
	console.log('ch : ' + document.getElementById('registDate'));
	console.log("showToday : " + checkToday);
}

let prevBtn = document.getElementById('prev');
let nextBtn = document.getElementById('next');
let todayBtn = document.getElementById('today');
prevBtn.addEventListener('click', prev);
nextBtn.addEventListener('click', next);
todayBtn.addEventListener('click', currentCal);

//클릭 이벤트
function clickStart(){
	tdGroup = [];
	for(let i = 1; i <= pageYear[firstDate.getMonth()]; i++){
		tdGroup[i] = document.getElementById(i < 10 ? '0' + i : i);
		tdGroup[i].addEventListener('click', changeToday);
	}
}

function changeToday(e){
	for(let i = 1; i <= pageYear[firstDate.getMonth()]; i++){
		if(tdGroup[i].classList.contains('active')){
			tdGroup[i].classList.remove('active');
		}
	}
	clickedDate = e.currentTarget; // 이벤트 발생 위치
	clickedDate.classList.add('active');
	today = new Date(today.getFullYear(), today.getMonth(), clickedDate.id);
	checkToday = firstDate.getFullYear() + '-' + getMonth + '-' + clickedDate.id;

	let listModal = document.getElementById('listModal');
	listModal.style.display = 'block';

	removeList();
	showDayList("click");
}

function removeList(){
	list = document.querySelectorAll('#loadList > div');
	list.forEach(function(e){
		e.remove();
	});
}

function removeMiniList(){
	list = document.querySelectorAll('#miniList');
	list.forEach(function(e){
		e.remove();
	});
}

function updateEv(classname) {
	document.getElementById('loadList').style.display = 'none';
	document.getElementById('updateList').style.display = 'block';
	document.getElementById('updateBtn').style.display = 'inline-block';
	document.getElementById('listBtn').style.display = 'inline-block';
	document.getElementById('selectBtn').style.display = 'none';
	document.getElementById('plus').style.display = 'none';
	if(classname != null ){
		updateForm(classname);
	}	
}

const updateForm = async (classname) => {
	await axios.get(usepath + 'updateForm/' + classname + "/")
	.then( (response) => {
			jsonData = JSON.stringify(response.data);
			listData = JSON.parse(jsonData);

			console.log(response.data);

			title = document.getElementById('uTitle');
			registDate = document.getElementById('uDate');
			regTime = document.getElementById('uTime');
			context = document.getElementById('uContext');
			inherence = document.getElementById('uInc');

			title.value = listData[0].title;
			registDate.value = listData[0].registDate;
			regTime.value = listData[0].regTime;
			context.value = listData[0].context;
			inherence.value = listData[0].inherence;

			title.setAttribute('readonly', 'readonly');
			registDate.setAttribute('readonly', 'readonly');
			regTime.setAttribute('readonly', 'readonly');
			context.setAttribute('readonly', 'readonly');
	})
	.catch( (ex) => {
		alert('2 exception : ' + ex);
	})
}

let delBtn = document.getElementById('deleteBtn');
delBtn.addEventListener('click', checkedBoxs);

function checkedBoxs(){
	checkedList = []
	checked = document.querySelectorAll('input[name=checkList]');
	checked.forEach( (ck) => {
		if(ck.checked == true){
			checkedList.push(ck.className);
		}
	});
	deleteList(checkedList);
}

const deleteList = async ( checkedList ) => {
	await axios.post(usepath + 'delete/', checkedList)
	.then( (response) => {
		alert('삭제완료');
		removeCalendar();
		removeList();
		selectList('click');
		removeMiniList();
		showCalendar();
	})
	.catch( (ex) => {
		alert('4 exception : ' + ex);
	})
}

function checkValue(){
	const title = document.querySelector('#title').value;
	let registDate = document.querySelector('#registDate').value;
	let regTime = document.querySelector('#regTime').value;
	let context = document.querySelector('#context').value;
	if(title === ''){
		console.log('checkValue');
		title.focus();
		return false;
	}
	else{
		insertList();
	}

}

const insertList = async () => {
//	formData = new FormData(document.forms[0]);
	formData = new FormData(document.getElementById('addForm'));
	ent = formData.entries();
	ob = {};
	while(true) {
		next = ent.next();
		if(next.done == true) break;
		ob[next.value[0]] = next.value[1];
	}
	await axios.post(usepath + 'insert/', ob)
	.then( (response) => {
		jsonData = JSON.stringify(response.data);
		listData = JSON.parse(jsonData);
		loadDiv = document.getElementById('loadList');
		document.getElementById('addForm').reset();
		removeList();
		showDayList("click");
		removeCalendar();
		showCalendar();
		document.getElementById('addList').style.display = 'none';
		document.getElementById('loadList').style.display = 'block';
		document.getElementById('plus').style.display = 'block';
		document.getElementById('selectBtn').style.display = 'inline-block';
	})
	.catch( (ex) => {
		alert('1 exception : ' + ex);
	})
}

const updateList = async () => {
	formData = new FormData(document.getElementById('updateForm'));
	ent = formData.entries();
	ob = {};
	while(true) {
		next = ent.next();
		if(next.done == true) break;
		ob[next.value[0]] = next.value[1];
	}

	await axios.post(usepath + 'update/', ob)
	.then( (response) => {
		jsonData = JSON.stringify(response.data);
		listData = JSON.parse(jsonData);
		loadDiv = document.getElementById('loadList');
		removeList();
		showDayList("click");
		removeCalendar();
		showCalendar();
		document.getElementById('updateList').style.display = 'none';
		document.getElementById('plus').style.display = 'block';
		document.getElementById('selectBtn').style.display = 'inline-block';
		document.getElementById('listBtn').style.display = 'none';
	    document.getElementById('loadList').style.display = 'block';
	})
	.catch( (ex) => {
		alert('1 exception : ' + ex);
	})
}

function changeList(){
    document.getElementById('addList').style.display = 'block';
	document.getElementById('regTime').value = getTime;
	document.getElementById('registDate').value = checkToday;
	console.log('ch : ' + document.getElementById('registDate').value);
	document.getElementById('loadList').style.display = 'none';
	document.getElementById('deleteBtn').style.display = 'none';
	selBtn.style.display = 'none';
	plusBtn.style.display = 'none';
}
