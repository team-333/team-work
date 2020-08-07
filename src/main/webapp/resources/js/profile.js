let click = false;
let click2 = false;

function change_title(cpath) {
	if (click === false) {
		const original = document.getElementById('title');
		const changetext = document.createElement("textarea");
		changetext.type = "text";
		changetext.id = "ch_text";
		
		console.log("original : " + original);
		text = original.innerHTML;
		console.log("text : " + text);
		original.innerHTML = "";
		original.appendChild(changetext);
		changetext.value = text;

		return click = true;
	} else {
		text = document.getElementById("ch_text").value;
		console.log("변경 후 : " + text);
		changeTitle(text, cpath);
		return click = false;
	}
}

function changeTitle(text, cpath) {
	$.ajax({
		url : cpath + "/updatetitle/",
		method : "POST",
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

function change_context(cpath) {

	if (click2 === false) {
		const original = document.getElementById('context');
		const changeContext = document.createElement("textarea");
		changeContext.type = "text";
		changeContext.id = "ch_context";
        changeContext.style="resize:none;overflow:hidden;width:50vw;hieght:30vh;max-width:";
       


		text = original.innerHTML;
		text=text.replace(/(?:\r\n|\r|\n)/g, '<br/>');
		console.log("text replace후 : "+text);
		original.innerHTML = "";
		original.appendChild(changeContext);
		changeContext.value = text;
		console.log("changeConxte : "+changeContext.value);

		return click2 = true;
	} else {
		text = document.getElementById("ch_context").value;
	    text=text.replace(/(?:\r\n|\r|\n)/g, '<br/>');
		console.log(text);
		changeContext(text,cpath);
		return click2 = false;
	}
}

function changeContext(text,cpath) {
	$.ajax({
		url : cpath+"/updatecontext/",
		method : "POST",
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