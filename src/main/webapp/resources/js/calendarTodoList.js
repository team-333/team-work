let mini = document.getElementById('mini');
let plusBtn = document.getElementById('plus');
let selBtn = document.getElementById('selectBtn');
let uptBtn = document.getElementById('updateBtn');
let listBtn = document.getElementById('listBtn');
let listModal = document.getElementById('listModal');

let getTime = (new Date().getHours() < 10 ? ('0' + new Date().getHours()) : new Date().getHours()) + ':' + 
(new Date().getMinutes() < 10 ? ('0' + new Date().getMinutes()) : new Date().getMinutes());

let BtnState = false;

let title = document.getElementById('uTitle');
let registDate = document.getElementById('uDate');
let regTime = document.getElementById('uTime');
let context = document.getElementById('uContext');

plusBtn.addEventListener('click', changeList);
selBtn.addEventListener('click', BtnCheck);
uptBtn.addEventListener('click', updateCheck);
listBtn.addEventListener('click', cancelList);

// 모달 창 회색 화면 클릭 시
window.onclick = function(event) {
    if (event.target == listModal) {
		listModal.style.display = 'none';
		selBtn.style.display = 'none';
		plusBtn.style.display = 'block';
		uptBtn.style.display = 'none';
    }
}

// 선택 버튼 클릭 확인
function BtnCheck(){
	if(BtnState == true){
		closeCheckbox();
		BtnState = false;
	}
	else{
		showCheckbox();
		BtnState = true;
	}
}

// 수정버튼 클릭 시 readonly를 지운다
function updateCheck(){
	document.getElementById('uptBtnBoxes').style.display = 'flex';
	title.removeAttribute('readonly', 'readonly');
	registDate.removeAttribute('readonly', 'readonly');
	regTime.removeAttribute('readonly', 'readonly');
	context.removeAttribute('readonly', 'readonly');
}

// 체크박스 보이기
function showCheckbox(){
	document.querySelectorAll('#checkList').forEach( (ob) => {
		ob.style.visibility = 'visible';
	});
	document.getElementById('deleteBtn').style.display = 'inline-block';
}

//체크박스 숨기기
function closeCheckbox(){
	document.querySelectorAll('#checkList').forEach( (ob) => {
		ob.checked = false;
		ob.style.visibility = 'hidden';
	});
	document.getElementById('deleteBtn').style.display = 'none';
}

// 추가 상태에서 취소버튼 클릭시
function cancelList(){
	let loadListDiv = document.querySelectorAll('#loadList > div');
	for(i = 0; i < loadListDiv.length; i++){
		console.log(loadListDiv[i].id);
		if(loadListDiv[i].id === 'doList'){
			selBtn.style.display = 'inline-block';
		}
		else{
			selBtn.style.display = 'none';
		}
	}
	document.getElementById('addForm').reset();
	document.getElementById('addList').style.display = 'none';
	document.getElementById('updateList').style.display = 'none';
	document.getElementById('loadList').style.display = 'block';
	closeCheckbox();
	cancelUpdate();
	uptBtn.style.display = 'none';
	plusBtn.style.display = 'block';
	listBtn.style.display = 'none';
}

// 수정 상태에서 취소 클릭 시
function cancelUpdate() {
	document.getElementById('uptBtnBoxes').style.display = 'none';
	title.setAttribute('readonly', 'readonly');
	registDate.setAttribute('readonly', 'readonly');
	regTime.setAttribute('readonly', 'readonly');
	context.setAttribute('readonly', 'readonly');
}

//추가상태로 변경
function changeList(){
    document.getElementById('addList').style.display = 'block';
	document.getElementById('regTime').value = getTime;
	document.getElementById('registDate').value = checkToday;
	document.getElementById('loadList').style.display = 'none';
	document.getElementById('deleteBtn').style.display = 'none';
	selBtn.style.display = 'none';
	plusBtn.style.display = 'none';
}

	
