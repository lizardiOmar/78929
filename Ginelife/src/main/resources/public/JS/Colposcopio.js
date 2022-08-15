const capturarNormal = document.getElementById("btnCapturarNormal");
const guardarNormal = document.getElementById("btnGuardarNormal");
const video =document.getElementById("video");
const canvasNormal = document.getElementById('canvasNormal');
const normal = document.getElementById("normal");
const nombres=["Normal", "Luz verde", "Ácido acético", "Lugol"];
const selectCamara=document.getElementById('selectCamara');
var devices, videoDevices;
async function getDevices() {
  devices = await navigator.mediaDevices.enumerateDevices();
  console.log(devices);
  videoDevices = devices.filter(device => device.kind === 'videoinput');
  console.log(videoDevices);
  const options = videoDevices.map(videoDevice => {
    return `<option value="${videoDevice.deviceId}">${videoDevice.label}</option>`;
  });
  selectCamara.innerHTML = options.join('');
}

getDevices();
navigator.mediaDevices.getUserMedia({video: true});
var constraints = {
    audio: false,
    video: {
      width: 720, height: 480
    }
};  
  async function init() {
    try {
      const stream = await navigator.mediaDevices.getUserMedia(constraints);
      handleSuccess(stream);
    } catch (e) {
      console.log("navigator.getUserMedia error: "+e.toString());
    }
}
// Success
function handleSuccess(stream) {
  window.stream = stream;
  video.srcObject = stream;
}

// Load init
init();
var contextNormal = canvasNormal.getContext('2d');
capturarNormal.addEventListener('click', function(event){
    contextNormal.drawImage(video, 0, 0);
    
} ); 
alert("La resolución de tu pantalla es: " + screen.width + " x " + screen.height);
