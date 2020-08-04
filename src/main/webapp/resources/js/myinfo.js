let click = false;
let click2 = false;
let check1, check2, check3;

function change_username() {
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
		changeusername(text);
		return click = false;
	}
}

function changeusername(text) {
	$.ajax({
		url : "../updateusername/",
		method : "GET",
		data : {
			text : text
		},
		dataType : "text",
		success : function(result) {

			const original = document.getElementById('title_username');
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

function changeemail(text) {
	$.ajax({
		url : "../updateemail/",
		method : "GET",
		data : {
			text : text
		},
		dataType : "text",
		success : function(result) {

			const original = document.getElementById('title_email');
			document.getElementById("ch_email").remove();
			original.innerHTML = result;
		}
	})
}

function check_basic_password() {

	const password = $("#password").val();
	if (password === '') {
		$("#picons").attr("src", '../img/x.png');

	} else {
		$("#picons").attr("src", "");
	}

	$.ajax({
		url : "../checkpassword/",
		method : "GET",
		data : {
			password : password
		},
		dataType : "text",
		success : function(data) {
			if (data === '성공') {
				$("#picons").attr("src", '../img/o.png');
				console.log("비밀번호 체크 성공");
				check1 = true;
			} else {
				$("#picons").attr("src", '../img/x.png');
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
			document.getElementById('p3icons').src = '../img/o.png';
			check3 = true;
		} else {
			document.getElementById('p3icons').src = '../img/x.png';
			check3 = false;
		}
	}
}
function passwordComplexity(event) {

	const regExp = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;

	const userpw = document.getElementById('newpassword').value;
	const pwmsg = document.getElementById('p2icons');

	if (regExp.test(userpw) == false) {
		pwmsg.src = '../img/x.png';
		check2 = false;
		return false;
	} else {
		pwmsg.src = '../img/o.png';
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
			url : "../updatepassword/",
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
					location.href = "../myinfo/"
				} else {
					alert("다시한번 시도해주세요.");
				}
			}
		})
	} else {
		alert("정확하게 입력하세요.");

	}
}