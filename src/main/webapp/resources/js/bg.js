const body = document.querySelector("body");

const IMG_NUM = 6;

function paintImg(imgNumber) {
  const video = document.getElementById('videoP')
  const source = document.createElement("source");
  source.src = `/yeol-gong/video/bg_${imgNumber}.mp4`;
  source.type = "video/mp4";
  video.appendChild(source);
}

function genRandom() {
  const number = Math.ceil(Math.random() * IMG_NUM);
  return number;
}

function init() {
  const randomNumber = genRandom();
  paintImg(randomNumber);
}
init();
