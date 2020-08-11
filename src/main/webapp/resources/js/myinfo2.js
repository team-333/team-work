document.getElementById('password').addEventListener('keyup',
		check_basic_password);
		
document.getElementById('newpassword').addEventListener('keyup',
		passwordComplexity);
document.getElementById('newpassword2')
		.addEventListener('keyup', checkPassword);
document.getElementById('submit').addEventListener('click', submit);
document.getElementById('dpassword').addEventListener('keyup',
		check_deletepassword);
document.getElementById('d2password').addEventListener('keyup',
		check_delete_Password);		
		
document.getElementById('deleteSubmit').addEventListener('click',delete_submit);