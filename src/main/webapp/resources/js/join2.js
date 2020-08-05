	function getFocus() {
		switch(this.id) {
		case 'email':
			if(ajaxRet !== true)
				this.focus(); break;
		
		case 'password':
			if(passwordComplexity() === false)
				this.focus(); break;
		}
	}
	document.getElementById('email').addEventListener('blur', checkEmail);
	document.getElementById('password').addEventListener('keyup', passwordComplexity);
	document.getElementById('email').addEventListener('blur', getFocus);
	document.getElementById('password').addEventListener('blur', getFocus);
	document.getElementById('password2').addEventListener('keyup', checkPassword);
	
	function enter(event) {
		if(event.keyCode == 13) submit();
	}
	document.querySelectorAll('input.signupForm').forEach( (input) => {
		input.addEventListener('keypress', enter);
	})
	