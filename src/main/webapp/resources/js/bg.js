const body = document.querySelector("body");

const IMG_NUM = 3;

function paintImg(imgNumber) {
  const video = document.createElement("video");
  video.setAttribute("muted", "muted");
  video.setAttribute("autoplay", "autoplay");
  video.setAttribute("loop", "loop");
  const source = document.createElement("source");
  source.src = `/yeol-gong/video/bg_${imgNumber}.mp4`;
  source.type = "video/mp4";
  video.appendChild(source);
  body.prepend(video);
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
