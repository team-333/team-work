

let ajaxRet;
		function checkEmail() {
			const email = $("#email").val();	
			if(email === '') {
				$("#ediv").css('color','red');
				$("#eicons").attr("src",'../img/x.png');
				$("#email").focus();
				return;
			}
			else {
				$("#eicons").attr("src",'');
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
						ajaxRet =true;
					}
					else {
						$("#emailCheckText").text("사용중인 이메일 입니다.");
						$("#eicons").attr("src",'../img/x.png');
						$("#ediv").focus();
						ajaxRet=false;
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
	 		}
	 		else {
	 			document.getElementById('p2icons').src = '../img/x.png';
	 		}
 		} 
 	}


function passwordComplexity(event) {
		
		const regExp = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		
		const userpw = document.getElementById('password').value;
		const pwmsg = document.getElementById('picons');
		
		if(regExp.test(userpw) == false) {
			pwmsg.src = '../img/x.png';
			return false;
		}
		else {
			pwmsg.src = '../img/o.png';
			return true;
		}
	}
	
	
	function submit(event){
		event.preventDefault();

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
		if(cnt !== inputs.length || check !== true ){
			alert('항목을 확인하세요');
			return false;
			}
		
	}