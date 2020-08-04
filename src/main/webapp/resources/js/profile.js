let click = false;
let click2 = false;

function change_title() {

	if (click === false) {
		const original = document.getElementById('title');
		const changetext = document.createElement("textarea");
		changetext.type = "text";
		changetext.id = "ch_text";

		text = original.innerHTML;
		console.log(text);
		original.innerHTML = "";
		original.appendChild(changetext);
		changetext.value = text;

		return click = true;
	} else {
		text = document.getElementById("ch_text").value;
		console.log(text);
		changeTitle(text);
		return click = false;
	}
}

function changeTitle(text) {
	$.ajax({
		url : "../updatetitle/",
		method : "GET",
		data : {
			text : text
		},
		dataType : "text",
		success : function(result) {

			const original = document.getElementById('title');
			document.getElementById("ch_text").remove();
			original.innerHTML = result;

		}
	})
}

function change_context() {

	if (click2 === false) {
		const original = document.getElementById('context');
		const changeContext = document.createElement("textarea");
		changeContext.type = "text";
		changeContext.id = "ch_context";

		text = original.innerHTML;
		console.log(text);
		original.innerHTML = "";
		original.appendChild(changeContext);
		changeContext.value = text;

		return click2 = true;
	} else {
		text = document.getElementById("ch_context").value;
		console.log(text);
		changeContext(text);
		return click2 = false;
	}
}

function changeContext(text) {
	$.ajax({
		url : "../updatecontext/",
		method : "GET",
		data : {
			text : text
		},
		dataType : "text",
		success : function(result) {

			const original = document.getElementById('context');
			document.getElementById("ch_context").remove();
			original.innerHTML = result;
		}
	})
}