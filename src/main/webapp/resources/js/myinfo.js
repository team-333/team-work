let click = false;
let click2 = false;
let check1, check2, check3;
let check4 = false;
let check5 = false;

function getContextPath() {
  var hostIndex = location.href.indexOf( location.host ) + location.host.length;
  return location.href.substring( hostIndex, location.href.indexOf("/", hostIndex + 1) );
}

function change_username(cpath) {
	if (click === false) {
		const original = document.getElementById('title_username');
		const changetext = document.createElement("input");
		changetext.type = "text";
		changetext.id = "ch_username";

		text = original.innerHTML;
		console.log(text);
		original.innerHTML = "";
		original.appendChild(changetext);
		changetext.value = text;

		return click = true;
	} else {
		text = document.getElementById("ch_username").value;
		console.log(text);
		changeusername(text,cpath);
		return click = false;
	}
}

function changeusername(text,cpath) {
	$.ajax({
		url : cpath + "/updateusername/",
		method : "POST",
		data : {
			text : text
		},
		dataType : "text",
		success : function(result) {

			const original = document.getElementById('title_username');
			const mainname = document.getElementById('mainUsername');
			mainname.innerHTML=result;
			document.getElementById("ch_username").remove();
			console.log(result);
			original.innerHTML = result;
		}
	})
}

function change_email() {

	if (click2 === false) {
		const original = document.getElementById('title_email');
		const changeEmail = document.createElement("input");
		changeEmail.type = "text";
		changeEmail.id = "ch_email";

		text = original.innerHTML;
		console.log(text);
		original.innerHTML = "";
		original.appendChild(changeEmail);
		changeEmail.value = text;

		return click2 = true;
	} else {
		text = document.getElementById("ch_email").value;
		console.log(text);
		changeemail(text);
		return click2 = false;
	}
}


function check_basic_password() {

	const password = $("#password").val();
	if (password === '') {
		$("#picons").attr("src", '../img/x.png');

	} else {
		$("#picons").attr("src", "");
	}

	$.ajax({
		url : getContextPath()+"/checkpassword/",
		method : "POST",
		data : {
			password : password
		},
		dataType : "text",
		success : function(data) {
			if (data === '성공') {
				$("#picons").attr("src", getContextPath()+'/img/o.png');
				console.log("비밀번호 체크 성공");
				check1 = true;
			} else {
				$("#picons").attr("src", getContextPath()+'/img/x.png');
				check2 = false;
			}
		}
	})
}



function checkPassword(event) {
	if (event.keyCode != 13 || event.keyCode != 9) {

		pass1 = document.getElementById('newpassword').value;
		pass2 = document.getElementById('newpassword2').value;

		if (pass1 === pass2) {
			document.getElementById('p3icons').src = getContextPath()+'/img/o.png';
			check3 = true;
		} else {
			document.getElementById('p3icons').src = getContextPath()+'/img/x.png';
			check3 = false;
		}
	}
}

function passwordComplexity(event) {

	const regExp = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;

	const userpw = document.getElementById('newpassword').value;
	const pwmsg = document.getElementById('p2icons');

	if (regExp.test(userpw) == false) {
		pwmsg.src = getContextPath()+'/img/x.png';
		check2 = false;
		return false;
	} else {
		pwmsg.src = getContextPath()+'/img/o.png';
		check2 = true;
		return true;
	}
}

function submit() {

	if (check1 === true && check2 === true && check3 === true) {
		const password = document.getElementById('password').value;
		console.log("password");
		const newpassword = document.getElementById('newpassword').value;

		$.ajax({
			url : getContextPath()+"/updatepassword/",
			contentType : "application/json",
			type : "POST",
			data : JSON.stringify({
				"password" : password,
				"newpassword" : newpassword
			}),
			dataType : "text",
			success : function(data) {
				console.log("비밀번호 변경완료 : " + data);
				if (data === 'ok') {

					alert("비밀번호가 변경되었습니다.");
					location.href = getContextPath()+"/main/"
				} else {
					alert("다시한번 시도해주세요.");
				}
			}
		})
	} else {
		alert("정확하게 입력하세요.");

	}
}

function check_deletepassword() {

	const password = $("#dpassword").val();
	if (password === '') {
		$("#dicons").attr("src", '../img/x.png');

	} else {
		$("#dicons").attr("src", "");
	}

	$.ajax({
		url : getContextPath()+"/checkpassword/",
		method : "POST",
		data : {
			password : password
		},
		dataType : "text",
		success : function(data) {
			if (data === '성공') {
				$("#dicons").attr("src", getContextPath()+'/img/o.png');
				
				check4 = true;
			} else {
				$("#dicons").attr("src", getContextPath()+'/img/x.png');
				check4 = false;
			}
		}
	})
}

function check_delete_Password(event) {
	if (event.keyCode != 13 || event.keyCode != 9) {

		pass1 = document.getElementById('dpassword').value;
		pass2 = document.getElementById('d2password').value;

		if (pass1 === pass2) {
			document.getElementById('d2icons').src = getContextPath()+'/img/o.png';
			check5 = true;
		} else {
			document.getElementById('d2icons').src = getContextPath()+'/img/x.png';
			check5 = false;
		}
	}
}

function delete_submit(event){
	event.preventDefault();
	if(check4 ===true && check5===true){
		var form =document.getElementById('deleteform');
			form.submit();
			return true;
	}
	console.log('delete 실패');
	alert('비밀번호를 확인해주세요');
	return false;
}