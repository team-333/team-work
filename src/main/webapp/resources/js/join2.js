document.getElementById('email').addEventListener('blur', checkEmail);
document.getElementById('password').addEventListener('keyup', passwordComplexity);
document.getElementById('password2').addEventListener('keyup', checkPassword);

function enter(event) {
	if(event.keyCode == 13) submit();
}

document.querySelectorAll('input.signupForm').forEach( (input) => {
	input.addEventListener('keypress', enter);
})

document.getElementById('joinSubmit').addEventListener('click',submit);