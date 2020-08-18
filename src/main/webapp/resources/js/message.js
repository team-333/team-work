 const openButton = document.getElementById("ans");
        const modal = document.querySelector(".modals");
        const overlay = modal.querySelector(".md_overlays");
        const closeButton = modal.querySelector("button");
        const openModal = () => {
            modal.classList.remove("hiddens");
        }
        const closeModal = () => {
            modal.classList.add("hiddens");
        }
        openButton.addEventListener("click", openModal);
        overlay.addEventListener("click", closeModal);
        closeButton.addEventListener("click", closeModal);
        
        
        
        var paging = document.querySelectorAll("#paging");
      
    
        
        var totalData = paging.length;    // 총 데이터 수
        var dataPerPage = 7;    // 한 페이지에 나타낼 데이터 수
        var pageCount = 5;        // 한 화면에 나타낼 페이지 수
        
        var viewPage = Math.ceil(totalData / dataPerPage); // 총 페이지 수
       
        function moveStartPage() {
			
        	var allNum = document.querySelectorAll("#pageNum");
			  var ChkNum =  Math.ceil(allNum.length / pageCount);
			 
			  for(i=0; i<allNum.length; i++){
			  var allChk = allNum[i].className.split(" ");
				 allNum[i].className = allChk[0] +" "+"NonePagingView";
				 
				 if(allChk[0] == "Clc1"){
					 allNum[i].className = allChk[0] +" OnPagingView";
					 
					 if(i == 0){
						
						 allNum[i].className = allChk[0] +" OnPagingView thisClick";
							
							
					 }
				 }
				 
			  }
			  
			  
			  var view1 = document.querySelectorAll(".view1");
			  for(i=0;i<paging.length;i++){
			
				  var chkClass1 = paging[i].className.split(" ");
				  
				  paging[i].className = chkClass1[0] + " noneview " + chkClass1[2] + " " + chkClass1[3];
			  }
			  
			  for(i=0;i<view1.length;i++){
					
				  var chkClass2 = view1[i].className.split(" ");
				  
				  view1[i].className = chkClass2[0] + " onview " + chkClass2[2] + " " + chkClass2[3];
			  }
			  
			  
		}
       
        function moveEndPage() {
        	var allNum = document.querySelectorAll("#pageNum");
		var ChkNum =  Math.ceil(allNum.length / pageCount);
			 
			  for(i=0; i<allNum.length; i++){
			  var allChk = allNum[i].className.split(" ");
				 allNum[i].className = allChk[0] +" "+"NonePagingView";
				 
				 if(allChk[0] == "Clc"+ChkNum){
					 allNum[i].className = allChk[0] +" OnPagingView";
					 
					 if(i == allNum.length-1){
						 allNum[i].className = allChk[0] +" OnPagingView thisClick";
					 }
				 }
				 
			  }
				
			  
			  var paginglength =  Math.ceil(paging.length / dataPerPage);
			  
			  var next = document.querySelectorAll(".view" + paginglength);
			  for(i=0;i<paging.length;i++){
			
				  var chkClass1 = paging[i].className.split(" ");
				  
				  paging[i].className = chkClass1[0] + " noneview " + chkClass1[2] + " " + chkClass1[3];
			  }
			  
			  for(i=0;i<next.length;i++){
					
				  var chkClass2 = next[i].className.split(" ");
				  
				  next[i].className = chkClass2[0] + " onview " + chkClass2[2] + " " + chkClass2[3];
			  }
			  
	}
        
        function view() {
			  var pageSize = document.getElementById("pageSize");
			
			  const span1 =  document.createElement("span");
			  const span2 =  document.createElement("span");
			  const span3 =  document.createElement("span");
			  const span4 =  document.createElement("span");
				
			  span1.innerText = "<< ";
			  span1.onclick = function (){
				  moveStartPage();
			  };
			  pageSize.appendChild(span1);
			  
			  
			  if(viewPage > pageCount){
				  span2.innerText = "◀  ";
				  span2.onclick = function (){
					  var thisClickChk = document.querySelector(".thisClick");
					  var pageNum = document.querySelectorAll("#pageNum");
					  var pgC =  Math.ceil(pageNum.length / pageCount);
					  console.log(pgC);
					  console.log(thisClickChk.className);
					  var Chk1 = thisClickChk.className.split(" ");
					  var Chk2 = Chk1[0].split("Clc");
					  var ChkResult = Chk2[1];
						
					  if(ChkResult != 1){
						  thisClickChk.className = Chk1[0] + " " + Chk1[1];
						  var classNameCh = document.querySelectorAll("."+Chk1[0]);
						  
						  for(i=0;i<classNameCh.length;i++){
							  classNameCh[i].className = Chk1[0] + " NonePagingView";
						  }
						  
						  ChkResult = ChkResult*1 - 1;
						  var ChkResult2 = document.querySelectorAll(".Clc"+ChkResult);
						  for(i=0;i<ChkResult2.length;i++){
							  ChkResult2[i].className = "Clc"+ChkResult + " OnPagingView";
						  }
						  ChkResult2[0].className += " thisClick";
						 
				         
						  
					  }
					  
					  
				  };
				  
				  pageSize.appendChild(span2);
			  }
			  
			  for(i=0; i<viewPage; i++){
			  const span =  document.createElement("span");
			
			  span.innerText = i + 1 +"  ";
			  span.id = "pageNum"
			  
			  var ccc =  Math.ceil(viewPage / pageCount);
			  span.className = "Clc"+ccc + " NonePagingView";
			 
			  span.onclick = function (){
				  var viewPageChk = span.innerText;
				  var thisPage = document.querySelectorAll(".view" + viewPageChk);
				  var pageNum = document.querySelectorAll("#pageNum");
				 
				  
				  for(i=0; i<pageNum.length; i++){
				 		var pg = pageNum[i].innerText;

						var pgC =  Math.ceil(pg / pageCount);
					  pageNum[i].className = "Clc"+pgC + " NonePagingView";
					  
				  }
		
				  var arrSpan = span.className.split(" ");
				 
				  var samNum = document.querySelectorAll("."+arrSpan[0]);
				  
				  for(i=0; i<samNum.length; i++){
					  
					  samNum[i].className = arrSpan[0] +" OnPaginView"
				  }
				  
				
				 span.className += " thisClick";
				  
				  
				  
				  for(i=0; i<paging.length; i++){
					  var chkClass = paging[i].className.split(" ");
					  paging[i].className = chkClass[0] + " noneview " + chkClass[2] + " " + chkClass[3];
				  }
				  
				  
				  for(i=0; i<thisPage.length; i++){
					
						var chkClass = thisPage[i].className.split(" ");
					  
						thisPage[i].className = chkClass[0] + " onview " + chkClass[2] + " " + chkClass[3];
				  }
				  
			  };
			  pageSize.appendChild(span);
			  
		 }
			  
			  
			  if(viewPage > pageCount){
				  span3.innerText = " ▶";
				  span3.onclick = function (){
					  var thisClickChk = document.querySelector(".thisClick");
					  var pageNum = document.querySelectorAll("#pageNum");
					  var pgC =  Math.ceil(pageNum.length / pageCount);
					  console.log(pgC);
					  console.log(thisClickChk.className);
					  var Chk1 = thisClickChk.className.split(" ");
					  var Chk2 = Chk1[0].split("Clc");
					  var ChkResult = Chk2[1];
						
					  if(ChkResult != pgC){
						  thisClickChk.className = Chk1[0] + " " + Chk1[1];
						  var classNameCh = document.querySelectorAll("."+Chk1[0]);
						
						  for(i=0;i<classNameCh.length;i++){
							  classNameCh[i].className = Chk1[0] + " NonePagingView";
						  }
						  
						  ChkResult = ChkResult*1 + 1;
						  var ChkResult2 = document.querySelectorAll(".Clc"+ChkResult);
						  for(i=0;i<ChkResult2.length;i++){
							  ChkResult2[i].className = "Clc"+ChkResult + " OnPagingView";
						  }
						  console.log(ChkResult2)
						  ChkResult2[0].className += " thisClick";
					  }
					  
					  
				  };
				  
				  
				  pageSize.appendChild(span3);
				  
				  
			  }
			  
			  span4.innerText = " >>";
                                       span4.onclick= function() {
				moveEndPage();
				};
			  pageSize.appendChild(span4);
		}
       
        
        // 보여줄 페이징 className 설정
       function messageView() {
		for(i=0; i<paging.length; i++){
			var pageingclass = paging[i].className.split(" ");
			var cN = pageingclass[0] * 1;
			
			var cNC =  Math.ceil(cN / dataPerPage);
			paging[i].className += " view"+cNC ;
			
		}
		}
       
      
       
       view();
       messageView();
        
        
        function startPage() {
        	 var thisPage = document.querySelectorAll(".view1");
        	 var pageNum = document.querySelectorAll("#pageNum");
        	 
        	 
        	 if(pageNum.length > 5){
        		 for(i =0; i<5; i++){
        			 pageNum[i].className = "Clc1 OnPagingView";
        		 }
        	 }
        	 
        	 else{
        		 for(i =0; i<pageNum.length; i++){
        			 pageNum[i].className = "Clc1 OnPagingView";
        		 }
        	 }
        	 pageNum[0].className += " thisClick";
        	 
        	 
			  for(i=0; i<thisPage.length; i++){
				 
				  var chkClass = thisPage[i].className.split(" ");
				  
				  thisPage[i].className = chkClass[0] + " onview " + chkClass[2] + " " + chkClass[3];
			  }
		}
      	
        startPage();     
           


        
        
        const checkAllbox = document.getElementById("checkAllbox");
        const checkOnebox = document.querySelectorAll("#checkOnebox");
      
        
        
        const allBox = () => {
	
        	
       		if(checkAllbox.checked == true){
        	
        		for(i=0; i<checkOnebox.length; i++){
        			checkOnebox[i].checked = true;
        		}
        		
        	}
        	else{
        		for(i=0; i<checkOnebox.length; i++){
        			checkOnebox[i].checked = false;
        		}
        	}
        }
        
        const NotAllBox = () => {
        	checkAllbox.checked = false;
        }
        
      
        checkAllbox.addEventListener("click", allBox);
        for(i=0; i<checkOnebox.length; i++){
        checkOnebox[i].addEventListener("click", NotAllBox);
        }
       
        
     
        
        const ansClick = () => {
        	const addressSelect = document.getElementById("addressSelect");
        	 const receiverList = document.querySelectorAll("#receiverList");
        	
        	 for(i=0; i<receiverList.length; i++){
     			receiverList[i].remove();
				}
        	 for(i=0; i<checkOnebox.length; i++){
        		if(checkOnebox[i].checked == true){
        		 	const option =  document.createElement("option");
        		 	option.id = "receiverList";
        			option.innerText = checkOnebox[i].parentNode.nextSibling.nextSibling.innerText;
        			addressSelect.appendChild(option);
        		}
    		}
        }
        
        
        const ans = document.getElementById("ans");
        ans.addEventListener("click", ansClick);
        	
        
   
        
     
		
        
        
        const deleteClick = () => {
        	   
        	var deleteArr = new Array();
        	for(i=0; i<checkOnebox.length; i++){
            	if(checkOnebox[i].checked == true){
      				
            		deleteArr[i] =  checkOnebox[i].className;
            	}
            }
        	
        		console.log(deleteArr);
        		ob = {
		          
		            receiverList : deleteArr,
		           
		      }

		      data = JSON.stringify(ob);
		   
		      const request = new XMLHttpRequest();
		      request.open("POST", cpath+"/deleteMessage/", true);
		      request.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
		      
		      request.onreadystatechange = function(){
		         if (request.readyState == 4 ) {
		            if(request.status == 200) {
		
		               location.reload(true);
		            }
		         
		         }
		      }
		      request.send(data);   
        }
        
        
        
        
        const deleteMessage = document.getElementById("deleteMessage");
        deleteMessage.addEventListener("click", deleteClick);
        
        const sendClick = () => {
        	var searchMember = document.getElementById("receive").className;
        	console.log(searchMember);
        	
        	
        	
        	var teamId = 999999;
     	  	var receiverArr = new Array();
     	   	const textContext = document.getElementById("textContext");
            for(i=0; i<checkOnebox.length; i++){
            	if(checkOnebox[i].checked == true){
      
            	receiverArr[i] =  checkOnebox[i].parentNode.nextSibling.nextSibling.childNodes[1].value;
            	}
            }
				if(searchMember != ""){
					receiverArr[checkOnebox.length + 1] = searchMember;
        		}
        
            
            ob = {
		            sender : userId,
		            context : textContext.value,
		            receiverList : receiverArr,
		            teamId : teamId
		      }

		      data = JSON.stringify(ob);
		      console.log(data);
		      
		      console.log(cpath);
		      textContext.value='';
		      
		      const request = new XMLHttpRequest();
		      request.open("POST", cpath+"/message/", true);
		      request.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
		      
		      request.onreadystatechange = function(){
		         if (request.readyState == 4 ) {
		            if(request.status == 200) {
		               alert('전송완료');
		               webSocket(receiverArr);
		               location.reload(true);
		            }
		         
		         }
		      }
		      request.send(data);   
     
        }
        
        const messageSend = document.getElementById("messageSend");
        messageSend.addEventListener("click", sendClick);
        
     
        
        function reading(my) {
        	
        
        	var msgId = my.childNodes[1].childNodes[0].className;
        	const request = new XMLHttpRequest();
		      request.open("GET", cpath+"/message/"+msgId+"/", true);
		      request.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
		      
		      request.onreadystatechange = function(){
		         if (request.readyState == 4 ) {
		            if(request.status == 200) {
		               
		            	var read = new Array();
		            	read = my.className.split(" ");
		            	my.className =read[0] + " " + read[1] +  " tureRead " + read[3];
		            	
		               var msg = new Array();
		               msg[0] = "읽기완료"
		               webSocket(msg);
		            }
		         
		         }
		      }
		      request.send();   
		} 
        
        
        const receiveKeyUP = () => {
        	var searchMember = document.getElementById("receive");
        	while ( searchResult.hasChildNodes() ) { searchResult.removeChild( searchResult.firstChild ); }
        	var userE = new Array();
			var userI = new Array();
        
        	const request = new XMLHttpRequest();
		      request.open("GET", cpath+"/message/search/" + searchMember.value+"/", true);
		      request.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
		    
		      request.onreadystatechange = function(){
		         if (request.readyState == 4 ) {
		            if(request.status == 200) {
		            	var json = new Array();
		            	
		            	
		            	json = JSON.parse(request.response);
		            	var searchResult = document.getElementById("searchResult");
	
		            	if(json.length != 0){
		            		for(i=0; i<json.length; i++){
		            			const div1 =  document.createElement("div");
		            			div1.innerText = json[i].username +"["+json[i].email+"]"
								div1.id = i;
		            			
		            				userE[i] = json[i].email;
		            				userI[i] = json[i].memberId;
		            			div1.onclick = function() {
		            		
		            				searchMember.value = userE[div1.id];
		            				searchMember.className = userI[div1.id];
		            		
		            				while ( searchResult.hasChildNodes() ) { searchResult.removeChild( searchResult.firstChild ); }
								}
		            			
		            			searchResult.appendChild(div1);
		            		}
		            		
		            		
		            	}
		            	
		            	else{
		            		const div1 =  document.createElement("div");
	            			div1.innerText = "존재하지 않는 이메일입니다."
	            			searchResult.appendChild(div1);
	            			
		            	}
		            }
		         
		         }
		      }
		      request.send();
		   
        }
       
        const receive = document.getElementById("receive");
        receive.addEventListener("keyup", receiveKeyUP);
  