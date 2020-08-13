

function sendMessage(msg) {
			sock.send(msg);
}

// 서버로부터 메시지를 받았을 때
function onMessage(msg) {
	var data = msg.data;
	console.log("data" + data)
}

// 서버와 연결을 끊었을 때
function onClose(evt) {
	console.log("연결끊김")
} 