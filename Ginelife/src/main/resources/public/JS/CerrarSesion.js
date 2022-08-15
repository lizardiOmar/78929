//Cerrar sesión
document.getElementById('salir').addEventListener('click', salir);
function salir(e){
    axios.post('/logout', {
        completed: false
    })
    .then(function (response) {
        console.log(response.data);
        if(response.data=='EXIT'){
            alert('Saliendo.');
            window.location.href="/";
        }else{
            alert('Error.');
        }
    })
    .catch(function (error) {
        console.log(error);
    });
}
