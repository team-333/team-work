$(document).ready(function(){
	
		$("#sub").click(function(){
			const email = $("#email").val();
			const password = $("#password").val();
			console.log(email + password);
			if(email === ''){
				alert("이메일을 입력하세요");		
				return;
			}
			if(password === ''){
				alert("비밀번호를 입력하세요")
				return;
			}
			
			$.ajax({
				url: "../login/",	
				method: "POST",				
				data: {email: email,
					   password : password},		
				dataType: "text",			
				success: function(data) {	
					
					if(data === "login") {
						alert("로그인 성공");
//						location.history.go(-1);
						$(location).attr('href',"../main/");
//						location.href("${cpath}/main/");
					}
					else {
						alert("정보를 확인해주세요");
						location.history.go(-1);
					}
				},
				error: function(data) {		
					alert("Error")
				}
			});
		});
		
	});