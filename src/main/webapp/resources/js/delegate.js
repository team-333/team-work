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
		       listBody.lastChild.scrollIntoView({ behavior: 'smooth' });
		       
		       var arr = new Array();
		       var arr2 = new Array();
		       
		       chk = document.querySelectorAll('#memberListChk');
		       idchk = document.querySelectorAll('#idChk');
		       console.log(idchk);
		       
		       for(i=1; i<chk.length; i++){
		            if(chk[i].className =='cdchk'){
		                arr[i] = chk[i].innerText;
		                console.log("id"+i+ ":" + idchk[i].className);
		   
		                arr2[i] = idchk[i].className;
		            }
		       }
		       
		       console.log(arr2);
		       var writeTime = new Date();
		       var msg = arr2;
		     
		       
		       
		       ob = {
		            sender : sender,
		            context : contents.value,
		            time : writeTime,
		            receiverList : arr2,
		            teamId : teamId
		      }

		      data = JSON.stringify(ob);
		      console.log(data);
		      
		      console.log(cpath);
		      contents.value='';
		      
		      const request = new XMLHttpRequest();
		      request.open("POST", cpath+"/message/", true);
		      request.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
		      
		      request.onreadystatechange = function(){
		         if (request.readyState == 4 ) {
		            if(request.status == 200) {
		               alert('전송완료');
		               webSocket(msg);
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
		   });

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






toggle();

function toggle() {
			result = document.getElementById("listMembers");
			const request = new XMLHttpRequest();
			request.open("GET", cpath + "/toggle/" + cnt + "/" + teamId + "/",
					true);
			request.setRequestHeader('Content-type',
					'application/json; charset=UTF-8');
				
		
		
			
			
			request.onreadystatechange = function() {
				if (request.readyState == 4) {
					if (request.status == 200) {
						
						
						jsonChk = request.response;
						console.log(jsonChk)
						if(jsonChk == "NullString"){
							
							var query = document.querySelectorAll('#memberListReMove');
							
							for(i=0; i<query.length; i++){
								query[i].remove();
							}
		
							document.getElementById('slideRemove').remove();
							
							var mySlides = document.createElement('div');
							mySlides.className = 'mySlides fade';
							mySlides.id = 'memberListReMove';
							mySlides.style.display = "block";
						
							var profile1 = document.createElement('div');
							profile1.className = 'profile1';
							
							var profile2 = document.createElement('div');
							profile2.className = 'profile2';
							
							var pname = document.createElement('div');
							pname.id = 'pname';
//							pname.innerText = "내용이 없습니다";
							profile1.appendChild(pname);
							profile2.appendChild(pname);
							
							mySlides.appendChild(profile1);
							mySlides.appendChild(profile2);
							
							var memberListAjax = document.getElementById('memberListAjax');
							memberListAjax.appendChild(mySlides);
							
							var slideRemove = document.createElement('div');
							slideRemove.id = "slideRemove";
							
							var span = document.createElement('span');
							span.className = "dot";
							span.onclick = "currentSlide(1)";
							
							
							slideRemove.appendChild(span);
							document.getElementById('slideChk').appendChild(
									slideRemove);
							
							cnt++;
						}
						else{
							
						
						var list = new Array();
						
						
						list = JSON.parse(request.response);
						
						
						var query = document.querySelectorAll('#memberListReMove');
						
						for(i=0; i<query.length; i++){
							query[i].remove();
						}
	
						document.getElementById('slideRemove').remove();
						
						var slideRemove = document.createElement('div');
						slideRemove.id = "slideRemove";
						
						
						if(cnt % 2 == 0){
							for (i = 0; i < list.length; i++) {
								var mySlides = document.createElement('div');
								mySlides.className = 'mySlides fade';
								mySlides.id = 'memberListReMove';
								console.log(i);
								
								if(i == 0){
									mySlides.style.display = "block";
								}
								
								var profile1 = document.createElement('div');
								profile1.className = 'profile1';
								
								var img = document.createElement('img');
								img.className = 'profileIcon';
								img.src = list[i].pictureUrl;
								
								var pname = document.createElement('div');
								pname.id = 'pname';
								pname.innerText = list[i].username;
								
								var join = document.createElement('a');
								join.href = cpath+"/MemberTeam/"+list[i].memberId+"/"+teamId+"/1/"
							
								var nope = document.createElement('a');
								nope.href = cpath+"/MemberTeam/"+list[i].memberId+"/"+teamId+"/0/"
								
								var joinbutton = document.createElement('button');
								joinbutton.id ='joins';
								joinbutton.innerText ='승인'	;
								
								var nopebutton = document.createElement('button');
								nopebutton.id ='nope';
								nopebutton.innerText ='거절'	;
								
								var profile2 = document.createElement('div');
								profile2.className = 'profile2';
								
								var pemail = document.createElement('div');
								pemail.id = 'pemail';
								pemail.innerText = list[i].email;

								var pintro = document.createElement('div');
								pintro.id = 'pintro';
								pintro.innerText = list[i].introduce;

								var ptexts = document.createElement('div');
								ptexts.id = 'ptexts';
								ptexts.innerText = list[i].introduceContext;
								
								
								
					
								
								join.appendChild(joinbutton);
								nope.appendChild(nopebutton);
								
								profile1.appendChild(img);
								profile1.appendChild(pname);
								profile1.appendChild(join);
								profile1.appendChild(nope);
								profile2.appendChild(pemail);
								profile2.appendChild(pintro);
								profile2.appendChild(ptexts);
							
								
								
								mySlides.appendChild(profile1);
								mySlides.appendChild(profile2);
								
								
								var memberListAjax = document.getElementById('memberListAjax');
								memberListAjax.appendChild(mySlides);
								
								
								
								var slideRemove = document.createElement('div');
								slideRemove.id = "slideRemove";
								
								var span = document.createElement('span');
								span.className = "dot";
								span.onclick = "currentSlide("+i+")";
								
								
								slideRemove.appendChild(span);
								document.getElementById('slideChk').appendChild(
								slideRemove);
								
								
								
								
						}
						}
						
						
						else{
							
						for (i = 0; i < list.length; i++) {
							var mySlides = document.createElement('div');
							mySlides.className = 'mySlides fade';
							mySlides.id = 'memberListReMove';
							console.log(i);
							
							if(i == 0){
								mySlides.style.display = "block";
							}
							
							var profile1 = document.createElement('div');
							profile1.className = 'profile1';
							var profile2 = document.createElement('div');
							profile2.className = 'profile2';
							
							var img = document.createElement('img');
							img.className = 'profileIcon';
							img.src = list[i].pictureUrl;
							
							var pname = document.createElement('div');
							pname.id = 'pname';
							pname.innerText = list[i].username;

							var pemail = document.createElement('div');
							pemail.id = 'pemail';
							pemail.innerText = list[i].email;

							var pintro = document.createElement('div');
							pintro.id = 'pintro';
							pintro.innerText = list[i].introduce;

							var ptexts = document.createElement('div');
							ptexts.id = 'ptexts';
							ptexts.innerText = list[i].introduceContext;
						
							
							if(list[i].memberId == delegateChk){
								var outsbutton = document.createElement('button');
								outsbutton.id ='outs';
								outsbutton.innerText ='캡틴'	;
								var outs = document.createElement('strong');
															
							}
							else {
								var outsbutton = document.createElement('button');
								outsbutton.id ='outs';
								outsbutton.innerText ='추방'	;
								var outs = document.createElement('a');
								outs.href = cpath+"/MemberTeam/"+list[i].memberId+"/"+teamId+"/3/"
							}
							
						
							
							
						
							outs.appendChild(outsbutton);
	
							profile1.appendChild(img);
							profile1.appendChild(pname);
							profile1.appendChild(outs);
							profile2.appendChild(pemail);
							profile2.appendChild(pintro);
							profile2.appendChild(ptexts);
							
			
							mySlides.appendChild(profile1);
							mySlides.appendChild(profile2);
	
							var memberListAjax = document.getElementById('memberListAjax');
							memberListAjax.appendChild(mySlides);
							
							
							
							var span = document.createElement('span');
							span.className = "dot";
							span.onclick =function currentSlide(i) {
							       showSlides(slideIndex = i);
							   }
							
							
							slideRemove.appendChild(span);
							document.getElementById('slideChk').appendChild(
							slideRemove);
						}
					   }
						cnt++;
					}
					}
				}
				
				
			}
				
				
				
			request.send();
			
			}
			
		



      if(publicChk == 1){
        document.getElementById("toggleChk").setAttribute('checked','checked');
         document.getElementById('off').setAttribute('class', 'Pk');
        document.getElementById('on').setAttribute('class', '');
      }

   // 공개 비공개
   document.getElementById("toggleChk").addEventListener("click", (e) => {
      
	   var toggleDB = 0;
	   
	   if(document.getElementById("off").getAttribute('class') == ''){
           document.getElementById('off').setAttribute('class', 'Pk');
           document.getElementById('on').setAttribute('class', '');
           toggleDB = 1;
       }
       else{
           document.getElementById('off').setAttribute('class', '');
           document.getElementById('on').setAttribute('class', 'Pk');
           toggleDB = 0;
       }
       
	   
	   const request = new XMLHttpRequest();
	      request.open("GET", cpath+"/toggleChange/"+toggleDB+"/"+teamId+"/", true);
	      request.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
	      
	      request.onreadystatechange = function(){
	         if (request.readyState == 4 ) {
	            if(request.status == 200) {
	               alert('변경완료');
	            }
	         
	         }
	      }
	     request.send();   
       
       
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

   