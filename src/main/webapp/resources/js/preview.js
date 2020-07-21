const upload = document.getElementById('profile-pic');
const preview = document.getElementById('preview');

upload.addEventListener('change', (e) => {
	const get_file = e.target.files;
	
	const image = document.createElement('img');
	
	const reader = new FileReader();
	
	reader.onload = ((aImg) => {
		return (e) => {
			aImg.src = e.target.result
		}
	})(image)

	if(get_file) {
		reader.readAsDataURL(get_file[0]);
	}
	
	console.log(preview.childNodes);
	if (preview.childNodes[2]) {
		preview.removeChild(preview.childNodes[2]);
	}
	preview.removeChild(preview.childNodes[1]);
	preview.appendChild(image);



})