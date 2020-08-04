document.getElementById('title_btn').addEventListener('click', change_username);
document.getElementById('title_btn2').addEventListener('click', change_email);
document.getElementById('password').addEventListener('keyup',
		check_basic_password);
document.getElementById('newpassword').addEventListener('keyup',
		passwordComplexity);
document.getElementById('newpassword2')
		.addEventListener('keyup', checkPassword);
document.getElementById('submit').addEventListener('click', submit);