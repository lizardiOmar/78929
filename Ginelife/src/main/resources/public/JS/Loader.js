const loaderLabel=document.getElementById("loaderLabel");
let height=screen.height;
let width=screen.width;
function pantalla(){
    axios.post('/pantalla', {
        height: screen.height,
        width: screen.width,
        completed: false
    }).then(function(response){
        setTimeout(function() {
            loaderLabel.innerHTML="Conectado.";
            setTimeout(function() {
                loaderLabel.innerHTML="Cargando contenido.";
                location.reload(true);
            }, 2000);
        }, 2000);
    }).catch(function (error) {
        setTimeout(function() {
            loaderLabel.innerHTML=error;
        }, 2000);
    });
}
pantalla();
