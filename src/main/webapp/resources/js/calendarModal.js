let mini = document.getElementById('mini');
let plusBtn = document.getElementById('plus');
let selBtn = document.getElementById('selectBtn');
let uptBtn = document.getElementById('updateBtn');
let listBtn = document.getElementById('listBtn');
let BtnSta = false;
//let getTime = (new Date().getHours() < 10 ? ('0' + new Date().getHours()) : new Date().getHours()) + ':' + 
//	(new Date().getMinutes() < 10 ? ('0' + new Date().getMinutes()) : new Date().getMinutes());
//console.log(getTime);

let title = document.getElementById('uTitle');
let registDate = document.getElementById('uDate');
let regTime = document.getElementById('uTime');
let context = document.getElementById('uContext');

plusBtn.addEventListener('click', changeList);
selBtn.addEventListener('click', BtnCheck);
uptBtn.addEventListener('click', updateCheck);
listBtn.addEventListener('click', cancelList);

let listModal = document.getElementById('listModal');
window.onclick = function(event) {
    if (event.target == listModal) {
		listModal.style.display = 'none';
		selBtn.style.display = 'none';
		plusBtn.style.display = 'block';
		uptBtn.style.display = 'none';
    }
}

function BtnCheck(){
	if(BtnSta == true){
		closeCheckbox();
		BtnSta = false;
	}
	else{
		showCheckbox();
		BtnSta = true;
	}
}

function updateCheck(){
	document.getElementById('uptBtnBoxes').style.display = 'flex';
	title.removeAttribute('readonly', 'readonly');
	registDate.removeAttribute('readonly', 'readonly');
	regTime.removeAttribute('readonly', 'readonly');
	context.removeAttribute('readonly', 'readonly');
}

//function changeList(){
//    document.getElementById('addList').style.display = 'block';
//	document.getElementById('regTime').value = getTime;
//	document.getElementById('loadList').style.display = 'none';
//	document.getElementById('deleteBtn').style.display = 'none';
//	selBtn.style.display = 'none';
//	plusBtn.style.display = 'none';
//}

function showCheckbox(){
	document.querySelectorAll('#checkList').forEach( (ob) => {
		ob.style.visibility = 'visible';
	});
	document.getElementById('deleteBtn').style.display = 'inline-block';
}

function closeCheckbox(){
	document.querySelectorAll('#checkList').forEach( (ob) => {
		ob.style.visibility = 'hidden';
	});
	document.getElementById('deleteBtn').style.display = 'none';
}

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

function cancelUpdate() {
	document.getElementById('uptBtnBoxes').style.display = 'none';
	title.setAttribute('readonly', 'readonly');
	registDate.setAttribute('readonly', 'readonly');
	regTime.setAttribute('readonly', 'readonly');
	context.setAttribute('readonly', 'readonly');
}
