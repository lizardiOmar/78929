// Get the modal alergia
var modalAlergia = document.getElementById("modalAlergia");
// Get the button that opens the modal
var btnAlergia = document.getElementById("btnAlergia");
// Get the <span> element that closes the modal
var spanAlergia = document.getElementsByClassName("close")[0];
// When the user clicks on the button, open the modal
btnAlergia.onclick = function() {
    modalAlergia.style.display = "block";
    msgAlergia.innerHTML="-";
}
// When the user clicks on <span> (x), close the modal
spanAlergia.onclick = function() {
    modalAlergia.style.display = "none";
}
//Cerrar modal en caso de hacer click fuera de el
window.onclick = function(event) {
    if (event.target == modalAlergia) {
      modalAlergia.style.display = "none";
    }
}
//mensaje de error e input para la descripción de la alergia
var msgAlergia=document.getElementById("msgAlergia");
var descAlergia=document.getElementById("descAlergia");
//Arreglo que contendrá los valores de las alergias registradas
var alergias = []
//boton para guardar
var guardarAlergia=document.getElementById("guardarAlergia");
//Función que agrega la alergia deseada al arreglo de alergias
guardarAlergia.addEventListener("click", guardarUnaAlergia);
function guardarUnaAlergia() {
    if (descAlergia.value) {
        alergias.push(descAlergia.value);     
        descAlergia.value="";
        msgAlergia.innerHTML="-";
        modalAlergia.style.display = "none";
        alergias_listadas();
    } else {
        msgAlergia.innerHTML="La descripción es necesaria."
        descAlergia.focus();
    }
    
}
function eliminarAlergia(e) {
    //alert(alergias[e.srcElement.value]);
    alergias.splice(e.srcElement.value, 1);
    alergias_listadas();
}
function alergias_listadas(){
    document.getElementById('alergias_step_list').innerHTML="";
    let c=0;
    alergias.forEach(alergia => {
        let tr_alergia=document.createElement("tr");
        let tc_alergia=document.createElement("td");

        let tc_btn=document.createElement("td");
        let btn_quitarAlergia=document.createElement("button");
        btn_quitarAlergia.innerHTML="Eliminar";
        btn_quitarAlergia.value=c;
        btn_quitarAlergia.addEventListener("click", eliminarAlergia);

        tc_alergia.innerHTML=alergia;

        tc_btn.appendChild(btn_quitarAlergia);

        tr_alergia.appendChild(tc_alergia);
        tr_alergia.appendChild(tc_btn);
        document.getElementById('alergias_step_list').appendChild(tr_alergia);
        c+=1;
    });
}