class Trauma {
    constructor(descripcion, fecha) {
      this.descripcion = descripcion;
      this.fecha = fecha;
    }
}
const fechaHoy=document.getElementById("fechaHoy");
const hoy = new Date();
const MM=hoy.getMonth();
const DD=hoy.getDate();
const YYYY=hoy.getFullYear();
const locale = 'es';
const intlForMonths = new Intl.DateTimeFormat(locale, { month: 'long' });
const months = [...Array(12).keys()];
const monthName = intlForMonths.format(new Date(YYYY, MM));
var ddAux=DD;
if(ddAux<10){
    ddAux='0'+DD;
}
var mmAux=MM+1;
if(mmAux<10){
    mmAux='0'+mmAux;
}

const dateHoy=YYYY+'-'+mmAux+'-'+ddAux;
// Get the modal alergia
var modalAlergia = document.getElementById("modalAlergia");
// Get the button that opens the modal
var btnAlergia = document.getElementById("btnAlergia");
// Get the <span> element that closes the modal
var spanAlergia = document.getElementsByClassName("close")[0];
// When the user clicks on the button, open the modal
btnAlergia.onclick = function() {
    modalAlergia.style.display = "block";
}
// When the user clicks on <span> (x), close the modal
spanAlergia.onclick = function() {
    modalAlergia.style.display = "none";
}

// Get the modal trauma
var modalTrauma = document.getElementById("modalTrauma");
// Get the button that opens the modal
var btnTrauma = document.getElementById("btnTrauma");
// Get the <span> element that closes the modal
var spanTrauma = document.getElementById("btnCerrarTra");
// When the user clicks on the button, open the modal
btnTrauma.onclick = function() {
    modalTrauma.style.display = "block";
}
// When the user clicks on <span> (x), close the modal
spanTrauma.onclick = function() {
    modalTrauma.style.display = "none";
}

// Get the modal cirugia
var modalCirugia = document.getElementById("modalCirugia");
// Get the button that opens the modal
var btnCirugia = document.getElementById("btnCirugia");
// Get the <span> element that closes the modal
var spanCirugia = document.getElementById("btnCerrarCir");
// When the user clicks on the button, open the modal
btnCirugia.onclick = function() {
    modalCirugia.style.display = "block";
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
  if (event.target == modalAlergia) {
    modalAlergia.style.display = "none";
  }
  if (event.target == modalTrauma) {
    modalTrauma.style.display = "none";
  }
}

var descTrauma=document.getElementById("descTrauma");
var ddTrauma=document.getElementById("ddTrauma");
var mmTrauma=document.getElementById("mmTrauma");
var aaaaTrauma=document.getElementById("aaaaTrauma");
var msgTrauma=document.getElementById("msgTrauma");

var descCirug=document.getElementById("descCirug");
var ddCirug=document.getElementById("ddCirug");
var mmCirug=document.getElementById("mmCirug");
var aaaaCirug=document.getElementById("aaaaCirug");
var msgCirug=document.getElementById("msgCirug");

for (let index = 1922; index < YYYY+1; index++){
    let optionY=document.createElement("option");
    let optionYc=document.createElement("option");
    optionY.value=index;
    optionY.innerHTML=index;
    optionYc.value=index;
    optionYc.innerHTML=index;
    aaaaTrauma.appendChild(optionY);
    aaaaCirug.appendChild(optionYc);
}

let traumas  = [];
let cirugias = [];
let alergias = [];

var guardarTrauma=document.getElementById("guardarTrauma");
guardarTrauma.addEventListener("click", guardarTraumatismo);

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
                    let cirugia=new Trauma(descripcion, fecha);
                    cirugias.push(cirugia); 
                    console.log(cirugias);
                    ddCirug.value="";
                    descCirug.value="";
                    modalCirugia.style.display = "none";
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
                    let trauma=new Trauma(descripcion, fecha);
                    traumas.push(trauma); 
                    console.log(traumas);
                    ddTrauma.value="";
                    descTrauma.value="";
                    modalTrauma.style.display = "none";
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

fechaHoy.innerHTML=DD+' de '+monthName+' del '+YYYY;
function mueveReloj(){
    momentoActual = new Date();
    hora = momentoActual.getHours();
    minuto = momentoActual.getMinutes();
    segundo = momentoActual.getSeconds();
    if(minuto<10){
        minuto='0'+minuto;
    }
    if(segundo<10){
        segundo='0'+segundo;
    }
    if(hora<10){
        hora='0'+hora;
    }
    horaImprimible = hora + ":" + minuto + ":" + segundo;
    document.form_reloj.reloj.value = horaImprimible;
    setTimeout("mueveReloj()", 1000);
}
var btn_back=document.getElementById('btn_back');
var btn_next=document.getElementById('btn_next');
const idPaciente=btn_next.value;
btn_next.addEventListener('click', btnNext);
btn_back.addEventListener('click', btnBack);
btn_next.innerHTML="Crear";
btn_back.innerHTML="Cancelar";
function btnBack() {
    
}

const msg=document.getElementById("msg");
function btnNext() {
    //alert();
    horaActual= document.form_reloj.reloj.value;
    let menarca, ritmo, ivs, comSex;
    let gestaciones, partos, abortos, cesareas;
    let ectopico, molar, fup, fum, fpp;
    let padecimiento_actual;
    if(document.getElementById("menarca").value){
        menarca=document.getElementById("menarca").value;
        if(document.getElementById("ritmo").value){
            ritmo=document.getElementById("ritmo").value;
            if(document.getElementById("ivs").value){
                    ivs=document.getElementById("ivs").value;
                    if(document.getElementById("comSex").value){
                        comSex=document.getElementById("comSex").value;
                        if(document.getElementById("gestaciones").value){
                            gestaciones=document.getElementById("gestaciones").value;
                            if(document.getElementById("partos").value){
                                partos=document.getElementById("partos").value;
                                if(document.getElementById("abortos").value){
                                    abortos=document.getElementById("abortos").value;
                                    if(document.getElementById("cesareas").value){
                                        cesareas=document.getElementById("cesareas").value;
                                        if(document.getElementById("ectopico").value){
                                            ectopico=document.getElementById("ectopico").value;
                                            if(document.getElementById("molar").value){
                                                molar=document.getElementById("molar").value;
                                                if(document.getElementById("fup").value){
                                                    fup=document.getElementById("fup").value;
                                                    if(document.getElementById("fum").value){
                                                        fum=document.getElementById("fum").value;
                                                        if(document.getElementById("fpp").value){
                                                            fpp=document.getElementById("fpp").value;
                                                            if(document.getElementById("padecimiento_actual").value){
                                                                padecimiento_actual=document.getElementById("padecimiento_actual").value;
                                                            }else{
                                                                padecimiento_actual="Sin observaciones.";
                                                            }
                                                            btn_next.disabled="true";
                                                                btn_back.disabled="true";
                                                                axios.post('/agregarHistoriaClinica', {
                                                                    idHistoriaClinica: 0,
                                                                    fecha: dateHoy,
                                                                    hora: horaActual,
                                                                    idPaciente: idPaciente,
                                                                    completed: false
                                                                })
                                                                .then(function (response) {
                                                                    console.log(response.data);
                                                                    if(response.data==='SI'){
                                                                        msg.innerHTML="Historia clínica creada.";
                                                                        document.getElementById('registroBar').style.visibility="visible";
                                                                        document.getElementById('registroBar').value=20;
                                                                        let diabetesHF=0;
                                                                        if(document.getElementById("diabetes_hf").checked == true){
                                                                            diabetesHF=1;
                                                                        }
                                                                        let cardiopatiasHF=0;
                                                                        if(document.getElementById("cardiopatias_hf").checked == true){
                                                                            cardiopatiasHF=1;
                                                                        }
                                                                        let htaHF=0;
                                                                        if(document.getElementById("HTA_hf").checked == true){
                                                                            htaHF=1;
                                                                        }
                                                                        let tiroidesHF=0;
                                                                        if(document.getElementById("tiroides_hf").checked == true){
                                                                            tiroidesHF=1;
                                                                        }
                                                                        let neoplasticosHF=0;
                                                                        if(document.getElementById("neoplasticos_hf").checked == true){
                                                                            neoplasticosHF=1;
                                                                        }
                                                                        let especificacionesHF=null;
                                                                        if(document.getElementById("especificaciones_hf").value){
                                                                            especificacionesHF=document.getElementById("especificaciones_hf").value;
                                                                        }
                                                                        axios.post('/agregarAHF', {
                                                                            idAntecedentesHeredoFamiliares: 0,
                                                                            diabetes: diabetesHF,
                                                                            hta: htaHF,
                                                                            neoplasticos: neoplasticosHF,
                                                                            cardiopatias: cardiopatiasHF,
                                                                            tiroides: tiroidesHF,
                                                                            especificaciones: especificacionesHF,
                                                                            idHistoriaClinica: 0,
                                                                            completed: false
                                                                        })
                                                                        .then(function (response) {
                                                                            console.log(response.data);
                                                                            if(response.data==='SI'){
                                                                                msg.innerHTML="Antecedentes heredo familiares agregados.";
                                                                                document.getElementById('registroBar').value=40;
                                                                                let diabetesPP=0;
                                                                                if(document.getElementById("diabetes_pp").checked==true){
                                                                                    diabetesPP=1;
                                                                                }
                                                                                let htaPP=0;
                                                                                if(document.getElementById("HTA_pp").checked==true){
                                                                                    htaPP=1;
                                                                                }
                                                                                let cardiopatiasPP=0;
                                                                                if(document.getElementById("cardiopatias_pp").checked==true){
                                                                                    cardiopatiasPP=1;
                                                                                }
                                                                                let nefropatiasPP=0;
                                                                                if(document.getElementById("nefropatias_pp").checked==true){
                                                                                    nefropatiasPP=1;
                                                                                }
                                                                                let epilepsiaPP=0;
                                                                                if(document.getElementById("epilepsia_pp").checked==true){
                                                                                    epilepsiaPP=1;
                                                                                }
                                                                                axios.post('/agregarAPP', {
                                                                                    idAntecedentesPersonalesPatologicos: 0,
                                                                                    diabetes: diabetesPP,
                                                                                    hta: htaPP,
                                                                                    nefropatias: nefropatiasPP,
                                                                                    cardiopatias: cardiopatiasPP,
                                                                                    epilepsia: epilepsiaPP,
                                                                                    idHistoriaClinica: 0,
                                                                                    completed: false
                                                                                })
                                                                                .then(function (response) {
                                                                                    console.log(response.data);
                                                                                    if(response.data==='SI'){
                                                                                        msg.innerHTML="Antecedentes personales patológicos agregados.";
                                                                                        document.getElementById('registroBar').value=60;
                                                                                        let tabaquismo=0;
                                                                                        if(document.getElementById("tabaquismo").checked==true){
                                                                                            tabaquismo=1;
                                                                                        }
                                                                                        let alcoholismo=0;
                                                                                        if(document.getElementById("alcoholismo").checked==true){
                                                                                            alcoholismo=1;
                                                                                        }
                                                                                        let drogas=0;
                                                                                        if(document.getElementById("drogas").checked==true){
                                                                                            drogas=1;
                                                                                        }
                                                                                        let rubeola=0;
                                                                                        if(document.getElementById("rubeola").checked==true){
                                                                                            rubeola=1;
                                                                                        }
                                                                                        let influenza=0;
                                                                                        if(document.getElementById("influenza").checked==true){
                                                                                            influenza=1;
                                                                                        }
                                                                                        let tetanos=0;
                                                                                        if(document.getElementById("tetanos").checked==true){
                                                                                            tetanos=1;
                                                                                        }
                                                                                        let covid19=0;
                                                                                        if(document.getElementById("covid19").checked==true){
                                                                                            covid19=1;
                                                                                        }
                                                                                        axios.post('/agregarAPNP', {
                                                                                            idAntecedentesPersonalesNoPatologicos: 0,
                                                                                            tabaquismo: tabaquismo,
                                                                                            alcoholismo: alcoholismo,
                                                                                            drogas: drogas,
                                                                                            rubeola: rubeola,
                                                                                            influenza: influenza,
                                                                                            tetanos: tetanos,
                                                                                            covid19: covid19,
                                                                                            idHistoriaClinica: 0,
                                                                                            completed: false
                                                                                        })
                                                                                        .then(function (response) {
                                                                                            console.log(response.data);
                                                                                            if(response.data==='SI'){
                                                                                                msg.innerHTML="Antecedentes personales no patológicos agregados.";
                                                                                                document.getElementById('registroBar').value=80;
                                                                                                axios.post('/agregarAGO', {
                                                                                                    idAntecedentesGinecoObstetricos: 0,
                                                                                                    menarca:menarca,
                                                                                                    ritmo:ritmo,
                                                                                                    ivs:ivs,
                                                                                                    compañerosSexuales:comSex,
                                                                                                    gestaciones:gestaciones,
                                                                                                    partos:partos,
                                                                                                    abortos:abortos,
                                                                                                    ceareas:cesareas,
                                                                                                    ectopico:ectopico,
                                                                                                    molar:molar,
                                                                                                    fup:fup,
                                                                                                    fum:fum,
                                                                                                    fpp:fpp,
                                                                                                    padecimientoActual:padecimiento_actual,
                                                                                                    idHistoriaClinica: 0,
                                                                                                    completed: false
                                                                                                })
                                                                                                .then(function (response) {
                                                                                                    console.log(response.data);
                                                                                                    if(response.data==='SI'){
                                                                                                        msg.innerHTML="Antecedentes gineco obstetricos agregados.";
                                                                                                        //Traumas
                                                                                                        document.getElementById('registroBar').value=85;
                                                                                                        if(traumas.length==0){
                                                                                                            document.getElementById('registroBar').value=90;
                                                                                                        }else{
                                                                                                            traumas.forEach(trauma => {
                                                                                                                //Axios solicitud
                                                                                                                axios.post('/agregarAT', {
                                                                                                                    idAntecedenteTraumatico: 0,
                                                                                                                    descripcion: trauma.descripcion,
                                                                                                                    fecha: trauma.fecha,
                                                                                                                    idHistoriaClinica: 0,
                                                                                                                    completed: false
                                                                                                                })
                                                                                                                .then(function (response) {
                                                                                                                    console.log(response.data);
                                                                                                                    if(response.data==='SI'){
                                                                                                                        
                                                                                                                    }else{
                                                                                                                        alert('Contacte al administrador.');
                                                                                                                    }
                                                                                                                })
                                                                                                                .catch(
                                                                                                                    function (error) {
                                                                                                                      console.log('Show error notification!')
                                                                                                                      return Promise.reject(error)
                                                                                                                    }
                                                                                                                )
                                                                                                            });

                                                                                                        }
                                                                                                        
                                                                                                    }else{
                                                                                                        alert('Antecedentes gineco obstetricos no creados. Contacte al administrador.');
                                                                                                    }
                                                                                                })
                                                                                                .catch(
                                                                                                    function (error) {
                                                                                                      console.log('Show error notification!')
                                                                                                      return Promise.reject(error)
                                                                                                    }
                                                                                                )
                                                                                            }else{
                                                                                                alert('Antecedentes personales no patologicos no creados. Contacte al administrador.');
                                                                                            }
                                                                                        })
                                                                                        .catch(
                                                                                            function (error) {
                                                                                              console.log('Show error notification!')
                                                                                              return Promise.reject(error)
                                                                                            }
                                                                                        )
                                                                                    }else{
                                                                                        alert('Antecedentes personales patologicos no creados. Contacte al administrador.');
                                                                                    }
                                                                                })
                                                                                .catch(
                                                                                    function (error) {
                                                                                      console.log('Show error notification!')
                                                                                      return Promise.reject(error)
                                                                                    }
                                                                                )
                                                                            }else{
                                                                                alert('Antecedentes heredo familiares no creados. Contacte al administrador.');
                                                                            }
                                                                        })
                                                                        .catch(
                                                                            function (error) {
                                                                              console.log('Show error notification!')
                                                                              return Promise.reject(error)
                                                                            }
                                                                        )
                                                                    }else{
                                                                        alert('Historia clínica no registrada en la base de datos. Contacte al administrador.');
                                                                    }
                                                                })
                                                                .catch(
                                                                    function (error) {
                                                                      console.log('Show error notification!')
                                                                      return Promise.reject(error)
                                                                    }
                                                                )                       
                                                        }else{
                                                            msg.innerHTML="El campo FPP es obligatorio.";
                                                            document.getElementById("fpp").focus;
                                                        }
                                                    }else{
                                                        msg.innerHTML="El campo FUM es obligatorio.";
                                                        document.getElementById("fum").focus;
                                                    }
                                                }else{
                                                    msg.innerHTML="El campo FUP es obligatorio.";
                                                    document.getElementById("fup").focus;
                                                }
                                            }else{
                                                msg.innerHTML="El campo molar es obligatorio.";
                                                document.getElementById("molar").focus;
                                            }
                                        }else{
                                            msg.innerHTML="El campo ectópico es obligatorio.";
                                            document.getElementById("ectopico").focus;
                                        }
                                    }else{
                                        msg.innerHTML="El número de cesáreas es un campo obligatorio.";
                                        document.getElementById("cesareas").focus;
                                    }
                                }else{
                                    msg.innerHTML="El número de abortos es un campo obligatorio.";
                                    document.getElementById("abortos").focus;
                                }
                            }else{
                                msg.innerHTML="El número de partos es un campo obligatorio.";
                                document.getElementById("partos").focus;
                            }
                        }else{
                            msg.innerHTML="El número de gestaciones es un campo obligatorio.";
                            document.getElementById("gestaciones").focus;
                        }
                    }else{
                        msg.innerHTML="El número de compañeros sexuales es un campo obligatorio.";
                        document.getElementById("comSex").focus;
                    }
                }else{
                    msg.innerHTML="El I.V.S. es un campo obligatorio.";
                    document.getElementById("ivs").focus;
                }
            }else{
                msg.innerHTML="El ritmo es un campo obligatorio.";
                document.getElementById("ritmo").focus;
            }
        }else{
            msg.innerHTML="La Menarca es un campo obligatorio.";
            document.getElementById("menarca").focus;
        }
}