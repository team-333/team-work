var idcheck=false;
var pscheck=false;
var pscheck2=false;
var lists=[idcheck,pscheck,pscheck2];
// 각 항목의 boolean을 리스트에 넣어서 체크함
		function checkEmail() {
			const email = $("#email").val();	
			if(email === '') {
				$("#ediv").css('color','red');
				$("#eicons").attr("src",'../img/x.png');
		         lists[0]=false;
				return;
			}
			else {
				$("#eicons").attr("src",'');
				lists[0]=true;
			}
		
			$.ajax({
				url: "../checkemail/",	
				method: "GET",				
				data: {email: email},		
				dataType: "text",			
				success: function(data) {	
	
					if(data === '생성가능') {
						$("#emailCheckText").text("");
						$("#eicons").attr("src",'../img/o.png');
						lists[0] =true;
					}
					else {
						$("#emailCheckText").text("사용중인 이메일 입니다.");
						$("#eicons").attr("src",'../img/x.png');
						$("#ediv").focus();
						lists[0]=false;
					}
				},
				error: function(data) {		
					
				}
			})
		}	
		
		
		function checkPassword(event) {
 		if(event.keyCode != 13 || event.keyCode != 9) {
		// 		13 : Enter, 9 : Tab 			
	 		pass1 = document.getElementById('password').value;
	 		pass2 = document.getElementById('password2').value;

	 		if(pass1 === pass2) {
	 			document.getElementById('p2icons').src = '../img/o.png';
			    lists[2]=true;
	 		}
	 		else {
	 			document.getElementById('p2icons').src = '../img/x.png';
	 		    lists[2]=false;
		   }
 		} 
 	}


function passwordComplexity(event) {
		
		const regExp = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		
		const userpw = document.getElementById('password').value;
		const pwmsg = document.getElementById('picons');
		
		if(regExp.test(userpw) == false) {
			pwmsg.src = '../img/x.png';
			lists[1]=false;
			return false;
		}
		else {
			pwmsg.src = '../img/o.png';
			lists[1]=true;
			return true;
		}
	}
	
	
	function submit(event){
		event.preventDefault();
        

	
        // 각 항목 true인지 체크하기	
	    // 리스트를 for문을 돌려서 각인자 값이 true이면 count를 ++ 해서  count가 3이면 submit 아니면 default   
        count=0;
		for(i=0;i<lists.length;i++){
			if(lists[i]===true) count++;
		}
		
	    // form의 각 항목이 빈 값인지 확인하기
		inputs = document.querySelectorAll('input.signupForm');
		const check =$(serviceCheck).prop("checked");
		cnt = 0;

		for(i = 0; i < inputs.length; i++) {
			if(inputs[i].value === '') {
				inputs[i].style.border = '1px solid red';
			}
			else {
				cnt++;	
			}
		}
		if(cnt !== inputs.length || check !== true || count!==3){
			alert('항목을 확인하세요');
			return false;
			}
		if(cnt ===inputs.length && check === true && count===3){
			alert('회원가입 성공');
			var form =document.getElementById('joinForm');
			form.submit();
			
		}
		
		
	}