var txtMsg=document.getElementById('msg');
var inputCorreo=document.getElementById('correo');
var inputClave=document.getElementById('clave');

document.getElementById('acceder').addEventListener('click', acceder);
function acceder(e){
    if(inputCorreo.value){
        if(inputClave.value){
            axios.post('/login', {
                correo: inputCorreo.value,
                clave: inputClave.value,
                completed: false
            })
            .then(function (response) {
                console.log(response.data);
                if(response.data==='SI'){
                    txtMsg.innerHTML="Acceso correcto.";
                    alert('Redirigiendo.');
                    window.location.href="/ginelife?c="+btoa(inputCorreo.value);
                }else{
                    txtMsg.innerHTML="Acceso incorrecto.";
                }
            })
            .catch(function (error) {
                console.log(error);
            });
        }else{
            txtMsg.innerText="Por favor, escriba  su clave.";
        }
    }else{
        txtMsg.innerText="Por favor, escriba  un correo electrónico.";
    }
}