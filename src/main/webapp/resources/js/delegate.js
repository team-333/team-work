
   	if(publicChk == 1){
		  document.getElementById("toggleChk").setAttribute('checked','checked');
		   document.getElementById('off').setAttribute('class', 'Pk');
		  document.getElementById('on').setAttribute('class', '');
}

	// 공개 비공개
	document.getElementById("toggleChk").addEventListener("click", (e) => {
	    if(document.getElementById("off").getAttribute('class') == ''){
	        document.getElementById('off').setAttribute('class', 'Pk');
	        document.getElementById('on').setAttribute('class', '');
	    }
	    else{
	        document.getElementById('off').setAttribute('class', '');
	        document.getElementById('on').setAttribute('class', 'Pk');
	    }
	});

	var slideIndex = 1;
	showSlides(slideIndex);

	function plusSlides(n) {
	    showSlides(slideIndex += n);
	}

	function currentSlide(n) {
	    showSlides(slideIndex = n);
	}

	function showSlides(n) {
	    var i;
	    var slides = document.getElementsByClassName("mySlides");
	    var dots = document.getElementsByClassName("dot");
	    if (n > slides.length) { slideIndex = 1 }    
	    if (n < 1) {slideIndex = slides.length}
	    for (i = 0; i < slides.length; i++) {
	        slides[i].style.display = "none";  
	    }
	    for (i = 0; i < dots.length; i++) {
	        dots[i].className = dots[i].className.replace(" active", "");
	    }
	    slides[slideIndex-1].style.display = "block";  
	    dots[slideIndex-1].className += " active";
	}

	const openButton = document.getElementById("open");
	const modal = document.querySelector(".modal");
	const overlay = modal.querySelector(".md_overlay");
	const closeButton = modal.querySelector("button");

	const openModal = () => {
	    modal.classList.remove("hidden");
	}
	const closeModal = () => {
	    modal.classList.add("hidden");
	}

	openButton.addEventListener("click", openModal);
	overlay.addEventListener("click", closeModal);
	closeButton.addEventListener("click", closeModal);

	// enter + 추가 (전송) + 시간
	const contents = document.querySelector('.text-basic');

	function today() {
	    var writeTime = new Date();
	    var hours = writeTime.getHours();
	    if(hours > 12) {
	        hours -= 12;
	        hours = '오후 ' + hours;
	    }
	    else{
	        hours = '오전 ' + hours;
	    }
	    var minu = writeTime.getMinutes();
	    if(minu < 10) { minu = '0' + minu; }
	    var wrtime = hours + ':' + minu + '&nbsp';
	    return wrtime;
	}

	function addList() {
	    if (!contents.value) {
	        alert('내용을 입력해주세요.');
	        contents.focus();
	        return false;
	    }

	    var tr = document.createElement('tr');
	    var td02 = document.createElement('td');

	    td02.innerHTML = '<span id="wtime">'+ today() + ' </span>' + contents.value;

	    tr.appendChild(td02);
	    document.getElementById('listBody').appendChild(tr);     
	    contents.value='';
	    listBody.lastChild.scrollIntoView({ behavior: 'smooth' });
	    
	    var arr = new Array();
	    chk = document.querySelectorAll('#memberListChk');
	    for(i=1; i<chk.length; i++){
            if(chk[i].className =='cdchk'){
                console.log("ㅇㅇㅇ" + chk[i].innerText);
                arr[i] = chk[i].innerText;
            }
	    }
	    
	    var writeTime = new Date();
	  
	    console.log("받는명단" + arr);
	    console.log("내용" + contents.value);
	    console.log("시간" + writeTime);
	    console.log("보내는사람" + receiver);
	    console.log("팀아이디" + teamId);
	    ob = {
				senderList : arr,
				context : contents.value,
				time : writeTime,
				receiver : receiver,
				teamId : teamId
		}

		data = JSON.stringify(ob);
		console.log(data);
		
		console.log(cpath);
		
		const request = new XMLHttpRequest();
		request.open("POST", cpath+"/message/", true);
		request.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
		
		request.onreadystatechange = function(){
			if (request.readyState == 4 ) {
				if(request.status == 200) {
					alert('전송완료');
				}
			
			}
		}
		request.send(data);	
	    
	}

	document.getElementById('btnAdd').addEventListener('click', addList);

	contents.addEventListener('keydown', function() {
	    if(window.event.keyCode == 13) {
	        addList();
	    }
	})

	// 전체 선택
	chk = document.querySelectorAll('#memberListChk');

	function allCheckedBox() {
	    if(chk[0].className == 'cd') {
	        for(i=0; i < chk.length; i++){
	            chk[i].className = 'cdchk'; 
	        }
	    }
	    else{
	        for(i=0; i < chk.length; i++){
	            chk[i].className = 'cd'; 
	        }
	    }
	}

	// 개별 선택
	function eachCheckedBox(obj) {
	    chk[0].className = 'cd';
	    num = 0;
	    if(obj.className == 'cd'){
	        obj.className = 'cdchk';
	    }
	    else {
	        obj.className = 'cd';
	    }

	    for(i=1; i<chk.length; i++){
	        if(chk[i].className == 'cdchk'){
	            num++;
	        }
	    }
	    if(num == chk.length-1){
	        chk[0].className = 'cdchk';
	    }
	}

	// OK 버튼
	function chkOK(){
	    var arr = new Array();
	    var arr2 = new Array();
	    var look = '';
	    var chkNum = 0;
	    var unchkNum = 0;
	    var chkarr = 0;
	    span =  document.getElementById('open');

	    if(chk[0].className != 'cdchk'){

	        for(i=0; i<chk.length; i++){
	            if(chk[i].className =='cdchk'){
	                console.log(chk[i].innerText);
	                arr[i] = chk[i].innerText;
	                chkNum ++;
	            }
	            else if(chk[i].className =='cd'){
	                unchkNum ++;
	            }
	        }

	        if(unchkNum == chk.length){
	            look='전체';
	        }

	        if(chkNum >= 3){
	            for( i=0; i<arr.length; i++){
	                if(arr[i] != null){
	                    chkarr++;

	                    if(chkarr > 3){
	                        look += ' 외 ' + (chkNum - 3) + '명...';
	                        break;
	                    }
	                    look += arr[i];
	                    look += ' ';
	                }
	            }
	        }

	        else{
	            for( i=0; i<arr.length; i++){
	                if(arr[i] != null){
	                    look += arr[i] + ' ' ;
	                }
	            }
	        }
	    }

	    else {
	        look = '전체';
	    }

	    span.innerText = look;

	}
