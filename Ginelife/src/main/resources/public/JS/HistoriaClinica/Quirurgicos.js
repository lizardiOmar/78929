// Get the modal cirugia
var modalCirugia = document.getElementById("modalCirugia");
// Get the button that opens the modal
var btnCirugia = document.getElementById("btnCirugia");
// Get the <span> element that closes the modal
var spanCirugia = document.getElementsByClassName("close")[2];
// When the user clicks on the button, open the modal
btnCirugia.onclick = function() {
    modalCirugia.style.display = "block";
    msgCirug.innerHTML="-";
}
// When the user clicks on <span> (x), close the modal
spanCirugia.onclick = function() {
    modalCirugia.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modalCirugia) {
    modalCirugia.style.display = "none";
  }
}

let descCirug=document.getElementById("descCirug");
let ddCirug=document.getElementById("ddCirug");
let mmCirug=document.getElementById("mmCirug");
let aaaaCirug=document.getElementById("aaaaCirug");
let msgCirug=document.getElementById("msgCirug");

for (let index = 1922; index < YYYY+1; index++){
    let optionYc=document.createElement("option");
    optionYc.value=index;
    optionYc.innerHTML=index;
    aaaaCirug.appendChild(optionYc);
}

var cirugias = [];
var guardarCirug=document.getElementById("guardarCirug");
guardarCirug.addEventListener("click", guardarCirugia);

function guardarCirugia() {
    if(descCirug.value){
        if (ddCirug.value) {
            if(ddCirug.value>31){
                ddCirug.value="";
                ddCirug.focus();
                msgCirug.innerHTML="El día de la cirugía debe ser menor a 31.";
            }else{
                if (ddCirug.value<1) {
                    ddCirug.value="";
                    ddCirug.focus();
                    msgCirug.innerHTML="El día de la cirugía debe ser mayor a 0.";
                }else{
                    if (ddCirug.value<10) {
                        ddCirug.value="0"+ddCirug.value;
                    }
                    let descripcion=descCirug.value;
                    let fecha=aaaaCirug.options[aaaaCirug.selectedIndex].value+'-'+mmCirug.options[mmCirug.selectedIndex].value+'-'+ddCirug.value;
                    let cirugia=new Antecedente(descripcion, fecha);
                    cirugias.push(cirugia); 
                    console.log(cirugias);
                    ddCirug.value="";
                    descCirug.value="";
                    modalCirugia.style.display = "none";
                    cirugias_listadas();
                }
            }
        }else{
            ddCirug.focus();
            msgCirug.innerHTML="El día de la cirugía es un campo obligatorio.";
        }
    }else{
        descCirug.focus();
        msgCirug.innerHTML="La descripción es un campo obligatorio.";
    }
}
function eliminarCirugia(e) {
    cirugias.splice(e.srcElement.value, 1);
    cirugias_listadas();
}
function cirugias_listadas(){
    document.getElementById('cirugias_step_list').innerHTML="";
    let c=0;
    cirugias.forEach(cirugia => {
        let tr_cirugia=document.createElement("tr");
        let tc_CirugiaDesc=document.createElement("td");
        let tc_CirugiaFecha=document.createElement("td");

        let tc_btn=document.createElement("td");
        let btn_quitarCirugia=document.createElement("button");
        btn_quitarCirugia.innerHTML="Eliminar";
        btn_quitarCirugia.value=c;
        btn_quitarCirugia.addEventListener("click", eliminarCirugia);

        tc_CirugiaDesc.innerHTML=cirugia.descripcion;
        tc_CirugiaFecha.innerHTML=cirugia.fecha;

        tc_btn.appendChild(btn_quitarCirugia);

        tr_cirugia.appendChild(tc_CirugiaDesc);
        tr_cirugia.appendChild(tc_CirugiaFecha);
        tr_cirugia.appendChild(tc_btn);
        document.getElementById('cirugias_step_list').appendChild(tr_cirugia);
        c+=1;
    });
    
}