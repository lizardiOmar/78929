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
                if(response.data!='NO'){
                    alert('Acceso correcto.');
                    window.location.href="/ginelife";
                }else{
                    alert('Error de correo y/o clave.');
                }
            })
            .catch(function (error) {
                console.log(error);
            });
        }else{
            alert("Por favor, escriba  su clave.");
        }
    }else{
        alert("Por favor, escriba  un correo electrónico.");
    }
}