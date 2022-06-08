// Get the modal trauma
var modalTrauma = document.getElementById("modalTrauma");
// Get the button that opens the modal
var btnTrauma = document.getElementById("btnTrauma");
// Get the <span> element that closes the modal
var spanTrauma = document.getElementsByClassName("close")[1];
// When the user clicks on the button, open the modal
btnTrauma.onclick = function() {
    modalTrauma.style.display = "block";
    msgTrauma.innerHTML="-";
}
// When the user clicks on <span> (x), close the modal
spanTrauma.onclick = function() {
    modalTrauma.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modalTrauma) {
      modalTrauma.style.display = "none";
    }
}

let descTrauma=document.getElementById("descTrauma");
let ddTrauma=document.getElementById("ddTrauma");
let mmTrauma=document.getElementById("mmTrauma");
let aaaaTrauma=document.getElementById("aaaaTrauma");
let msgTrauma=document.getElementById("msgTrauma");

for (let index = 1922; index < YYYY+1; index++){
    let optionY=document.createElement("option");
    optionY.value=index;
    optionY.innerHTML=index;
    aaaaTrauma.appendChild(optionY);
}

var traumas = [];

var guardarTrauma=document.getElementById("guardarTrauma");
guardarTrauma.addEventListener("click", guardarTraumatismo);
function guardarTraumatismo() {
    if(descTrauma.value){
        if (ddTrauma.value) {
            if(ddTrauma.value>31){
                ddTrauma.value="";
                ddTrauma.focus();
                msgTrauma.innerHTML="El día del traumatismo debe ser menor a 31.";
            }else{
                if (ddTrauma.value<1) {
                    ddTrauma.value="";
                    ddTrauma.focus();
                    msgTrauma.innerHTML="El día del traumatismo debe ser mayor a 0.";
                }else{
                    if (ddTrauma.value<10) {
                        ddTrauma.value="0"+ddTrauma.value;
                    }
                    let descripcion=descTrauma.value;
                    let fecha=aaaaTrauma.options[aaaaTrauma.selectedIndex].value+'-'+mmTrauma.options[mmTrauma.selectedIndex].value+'-'+ddTrauma.value;
                    let trauma=new Antecedente(descripcion, fecha);
                    traumas.push(trauma); 
                    console.log(traumas);
                    ddTrauma.value="";
                    descTrauma.value="";
                    modalTrauma.style.display = "none";
                    traumas_listados();
                }
            }
        }else{
            ddTrauma.focus();
            msgTrauma.innerHTML="El día del traumatismo es un campo obligatorio.";
        }
    }else{
        descTrauma.focus();
        msgTrauma.innerHTML="La descripción es un campo obligatorio.";
    }
}
function traumas_listados(){
    document.getElementById('traumas_step_list').innerHTML="";
    let c=0;
    traumas.forEach(trauma => {
        let tr_trauma=document.createElement("tr");
        let tc_traumaDesc=document.createElement("td");
        let tc_traumaFecha=document.createElement("td");

        let tc_btn=document.createElement("td");
        let btn_quitarTrauma=document.createElement("button");
        btn_quitarTrauma.innerHTML="Eliminar";
        btn_quitarTrauma.value=c;
        btn_quitarTrauma.addEventListener("click", eliminarTrauma);

        tc_traumaDesc.innerHTML=trauma.descripcion;
        tc_traumaFecha.innerHTML=trauma.fecha;

        tc_btn.appendChild(btn_quitarTrauma);

        tr_trauma.appendChild(tc_traumaDesc);
        tr_trauma.appendChild(tc_traumaFecha);
        tr_trauma.appendChild(tc_btn);
        document.getElementById('traumas_step_list').appendChild(tr_trauma);
        c+=1;
    });
    
}
function eliminarTrauma(e) {
    traumas.splice(e.srcElement.value, 1);
    traumas_listados();
}
