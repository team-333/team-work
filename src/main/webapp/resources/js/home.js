function wi(event){
		event.preventDefault();
		var url="${url}";
		window.open(url,"","width =400,height=400,left=600");
	}

	function setCookie(cookieName,value,exdays){
		let exdate = new Date();
		exdate.setDate(exdate.getDate()+exdays);
		let cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	function deleteCookie(cookieName){
		let expireDate = new Date();
		expireDate.setDate(expireDate.getDate() -1);
		document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
	function getCookie(cookieName){
		cookieName = cookieName + '=';
		let cookieData = document.cookie;
		let start = cookieData.indexOf(cookieName);
		let cookieValue ='';

		if(start != -1){
			start += cookieName.length;
			let end = cookieData.indexOf(';',start);
			if(end == -1){
				end = cookieData.length;}
				cookieValue = cookieData.substring(start,end);
			}
			return unescape(cookieValue);
		}

		$(document).ready(function(){
			let userInputId = getCookie("userInputId");

			$("input[name='email']").val(userInputId);

			if($("input[name='email']").val()!=""){
				$("#cookiecheck").attr("checked",true);

			}

			$("#cookiecheck").change(function(){
				if($("#cookiecheck").is(":checked")){
					 let userInputId = $("input[name='email']").val();
		             setCookie("userInputId", userInputId, 365);

				}else{
					deleteCookie("userInputId");

				}
			});

			$("input[name='email']").keyup(function(){ 
	            if($("#cookiecheck").is(":checked")){ 
	                let userInputId = $("input[name='email']").val();
	                setCookie("userInputId", userInputId, 365);
	            }
	        });
	  });