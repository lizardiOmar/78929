var btnPrev=document.getElementById('btnPrev');
var btnNext=document.getElementById('btnNext');
//progress bar del registro
var progress=document.getElementById('registroBar');
//Containers de los pasos del formulario
var step_1=document.getElementById('step_1');
var step_2=document.getElementById('step_2');
var step_3=document.getElementById('step_3');
var step_4=document.getElementById('step_4');
//Visibilidad de los steps
step_1.style.display = "block";
step_2.style.display = "none";
step_3.style.display = "none";
step_4.style.display = "none";
//
var modalLoad = document.getElementById("modalLoading");
var estudiosLab=null;
btnPrev.addEventListener('click', prev);
btnNext.addEventListener('click', next);
function next() {
   switch (progress.value) {
        case 0:
            datosGeneralesFunc();
            if (datosGenerales==true) {
                step_1.style.display = "none";
                step_2.style.display = "block";
                step_3.style.display = "none";
                step_4.style.display = "none";
                btnPrev.innerHTML="Anterior";
                progress.value=33;
            } else {
                step_1.style.display = "block";
                step_2.style.display = "none";
                step_3.style.display = "none";
                step_4.style.display = "none";
                btnPrev.innerHTML="Anterior";
                progress.value=0;
            }
        break;
        case 33:
            areasRelevantesFunc();
            if (areasRelevantes==true) {
                step_1.style.display = "none";
                step_2.style.display = "none";
                step_3.style.display = "block";
                step_4.style.display = "none";
                btnPrev.innerHTML="Anterior";
                progress.value=66;
            } else {
                step_1.style.display = "none";
                step_2.style.display = "block";
                step_3.style.display = "none";
                step_4.style.display = "none";
                btnPrev.innerHTML="Anterior";
                progress.value=33;
            }
        break;
        case 66:
            conclusionesFunc();
            if (conclusiones==true) {
                step_1.style.display = "none";
                step_2.style.display = "none";
                step_3.style.display = "none";
                step_4.style.display = "block";
                progress.value=100;
                mostrarDatos();
                btnPrev.innerHTML="Anterior";
                btnNext.innerHTML="Guardar";

            } else {
                step_1.style.display = "none";
                step_2.style.display = "none";
                step_3.style.display = "block";
                step_4.style.display = "none";
                progress.value=66;
            }
        break;
        case 100:
            if (btnNext.innerHTML=="Guardar") {
                document.getElementById('btnNext').disabled="true";
                guardarExploracionFisica();
            }
        break;
   }
}
function prev(){
    switch (progress.value) {
        case 0:
            window.location.href="https://ginelife-mx.herokuapp.com/pacientes"
        break;
        case 33:
            btnNext.innerHTML="Siguiente";
            step_1.style.display = "block";
            step_2.style.display = "none";
            step_3.style.display = "none";
            step_4.style.display = "none";
            progress.value=0;
            btnPrev.innerHTML="Cancelar";
        break;
        case 66:
            btnNext.innerHTML="Siguiente";
            step_1.style.display = "none";
            step_2.style.display = "block";
            step_3.style.display = "none";
            step_4.style.display = "none";
            progress.value=33;
            btnPrev.innerHTML="Anterior";
        break;
        case 100:
            btnNext.innerHTML="Siguiente";
            step_1.style.display = "none";
            step_2.style.display = "none";
            step_3.style.display = "block";
            step_4.style.display = "none";
            progress.value=66;
            btnPrev.innerHTML="Anterior";
        break;
    }
}
//Modal para feedback
var modalInfo = document.getElementById("modalInfo");
// Get the <span> element that closes the modal
var spanInfo = document.getElementsByClassName("close")[0];
// When the user clicks on <span> (x), close the modal
spanInfo.onclick = function() {
    modalInfo.style.display = "none";
}
//Cerrar modal en caso de hacer click fuera de el
window.onclick = function(event) {
    if (event.target == modalInfo) {
        modalInfo.style.display = "none";
    }
}
//Get datos generales
var consciente, habitus, peso, estatura, ta, fc, fr, temperatura;
var radioButonConsciente = document.getElementsByName('consciente');
var datosGenerales=false;
function datosGeneralesFunc() {
    for(i = 0; i < radioButonConsciente.length; i++) {
        if(radioButonConsciente[i].checked){
            consciente=radioButonConsciente[i].value;
        }
    }
    habitus=document.getElementById('habitus').value;
    peso=document.getElementById('peso').value;
    estatura=document.getElementById('estatura').value;
    ta=document.getElementById('ta').value;
    fc=document.getElementById('fc').value;
    fr=document.getElementById('fr').value;
    temperatura=document.getElementById('temperatura').value;
    if(habitus){
        document.getElementById('habitus').style.boxShadow="2px 2px 5px green";
        //Peso
        if(peso){
            document.getElementById('peso').style.boxShadow="2px 2px 5px green";
            //Estatura
            if(estatura){
                document.getElementById('estatura').style.boxShadow="2px 2px 5px green";
                //ta
                if(ta){
                    document.getElementById('ta').style.boxShadow="2px 2px 5px green";
                    //fc
                    if(fc){
                        document.getElementById('fc').style.boxShadow="2px 2px 5px green";
                        //fr
                        if(fr){
                            document.getElementById('fr').style.boxShadow="2px 2px 5px green";
                            //Temperatura
                            if(temperatura){
                                document.getElementById('temperatura').style.boxShadow="2px 2px 5px green";
                                datosGenerales=true;
                            }else{
                                document.getElementById('temperatura').style.boxShadow="2px 2px 5px red";
                                msgInfo.innerHTML="Temperatura es un campo necesario.";
                                modalInfo.style.display = "block";
                                datosGenerales=false;
                            }
                        }else{
                            document.getElementById('fr').style.boxShadow="2px 2px 5px red";
                            msgInfo.innerHTML="F. R. es un campo necesario.";
                            modalInfo.style.display = "block";
                            datosGenerales=false;
                        }
                    }else{
                        document.getElementById('fc').style.boxShadow="2px 2px 5px red";
                        msgInfo.innerHTML="F. C. es un campo necesario.";
                        modalInfo.style.display = "block";
                        datosGenerales=false;
                    }
                }else{
                    document.getElementById('ta').style.boxShadow="2px 2px 5px red";
                    msgInfo.innerHTML="T. A. es un campo necesario.";
                    modalInfo.style.display = "block";
                    datosGenerales=false;
                }
            }else{
                document.getElementById('estatura').style.boxShadow="2px 2px 5px red";
                msgInfo.innerHTML="Estatura es un campo necesario.";
                modalInfo.style.display = "block";
                datosGenerales=false;
            }
        }else{
            document.getElementById('peso').style.boxShadow="2px 2px 5px red";
            msgInfo.innerHTML="Peso es un campo necesario.";
            modalInfo.style.display = "block";
            datosGenerales=false;
        }
    }else{
        document.getElementById('habitus').style.boxShadow="2px 2px 5px red";
        msgInfo.innerHTML="Habitus es un campo necesario.";
        modalInfo.style.display = "block";
        datosGenerales=false;
    }
}
//Get areas relevantes
var pulmones, pulmones_nota, corazon, corazon_nota, cabeza, cabeza_nota, cuello, cuello_nota;
var areasRelevantes=false;
var pulmonesRadio = document.getElementsByName('pulmones');
var corazonRadio = document.getElementsByName('corazon');
var cabezaRadio = document.getElementsByName('cabeza');
var cuelloRadio = document.getElementsByName('cuello');
function areasRelevantesFunc(){
    for(i = 0; i < pulmonesRadio.length; i++) {
        if(pulmonesRadio[i].checked){
            pulmones=pulmonesRadio[i].value;
        }
    }
    pulmones_nota=document.getElementById('pulmones_nota').value;
    if(!pulmones_nota){
        pulmones_nota="Sin observaciones."
    }
    for(i = 0; i < corazonRadio.length; i++) {
        if(corazonRadio[i].checked){
            corazon=corazonRadio[i].value;
        }
    }
    corazon_nota=document.getElementById('corazon_nota').value;
    if(!corazon_nota){
        corazon_nota="Sin observaciones."
    }
    for(i = 0; i < cabezaRadio.length; i++) {
        if(cabezaRadio[i].checked){
            cabeza=cabezaRadio[i].value;
        }
    }
    cabeza_nota=document.getElementById('cabeza_nota').value;
    if(!cabeza_nota){
        cabeza_nota="Sin observaciones."
    }
    for(i = 0; i < cuelloRadio.length; i++) {
        if(cuelloRadio[i].checked){
            cuello=cuelloRadio[i].value;
        }
    }
    cuello_nota=document.getElementById('cuello_nota').value;
    if(!cuello_nota){
        cuello_nota="Sin observaciones."
    }
    areasRelevantes=true;
}
//Get conclusiones
var pronostico, plan;
var conclusiones=false;
function conclusionesFunc() {
    pronostico=document.getElementById('pronostico').value;
    plan=document.getElementById('plan').value;
    if (!pronostico) {
        document.getElementById('pronostico').style.boxShadow="2px 2px 5px red";
        msgInfo.innerHTML="Pronostico es un campo necesario.";
        modalInfo.style.display = "block";
        conclusiones=false;
    }else{
        document.getElementById('pronostico').style.boxShadow="2px 2px 5px green";
        if (!plan) {
            document.getElementById('plan').style.boxShadow="2px 2px 5px red";
            msgInfo.innerHTML="Plan es un campo necesario.";
            modalInfo.style.display = "block";
            conclusiones=false;
        }else{
            document.getElementById('plan').style.boxShadow="2px 2px 5px green";
            conclusiones=true;
        }
    }
}
function mostrarDatos() {
    document.getElementById("consciente__EF").innerHTML=consciente;
    document.getElementById("habitus__EF").innerHTML=habitus;
    document.getElementById("peso__EF").innerHTML=peso+" Kg.";
    document.getElementById("estatura__EF").innerHTML=estatura+" cm.";

    document.getElementById("ta__EF").innerHTML=ta;
    document.getElementById("fc__EF").innerHTML=fc;
    document.getElementById("fr__EF").innerHTML=fr;
    document.getElementById("temperatura__EF").innerHTML=temperatura+" °C";

    document.getElementById("pulmones__EF").innerHTML=pulmones;
    document.getElementById("pulmonesNota__EF").innerHTML=pulmones_nota;
    document.getElementById("corazon__EF").innerHTML=corazon;
    document.getElementById("corazonNota__EF").innerHTML=corazon_nota;
    document.getElementById("cabeza__EF").innerHTML=cabeza;
    document.getElementById("cabezaNota__EF").innerHTML=cabeza_nota;
    document.getElementById("cuello__EF").innerHTML=cuello;
    document.getElementById("cuelloNota__EF").innerHTML=cuello_nota;

    if (estudiosLab!=null) {
        document.getElementById("gpoSanguineo__EF").innerHTML=estudiosLab.gpoSanguineo;
        document.getElementById("rh__EF").innerHTML=estudiosLab.rh;
        document.getElementById("hemoglobina__EF").innerHTML=estudiosLab.hemoglobina+" g/dL";
        document.getElementById("hematocrito__EF").innerHTML=estudiosLab.hematocrito+" %";
        document.getElementById("leucocitos__EF").innerHTML=estudiosLab.leucocitos;
        
        document.getElementById("plaquetas__EF").innerHTML=estudiosLab.plaquetas;
        document.getElementById("tp__EF").innerHTML=estudiosLab.tp;
        document.getElementById("tpt__EF").innerHTML=estudiosLab.tpt;
        document.getElementById("glucosa__EF").innerHTML=estudiosLab.glucosa+" mg/dL";

        document.getElementById("vdrl__EF").innerHTML=estudiosLab.vdrl;
        document.getElementById("hiv__EF").innerHTML=estudiosLab.hiv;

    }
    document.getElementById("pronostico__EF").innerHTML=pronostico;
    document.getElementById("plan__EF").innerHTML=plan;

}
function guardarExploracionFisica(){
    modalLoad.style.display = "flex";
    modalLoad.style.alignItems = "center";
    modalLoad.style.justifyContent = "center";
    let msgLoadingHC=document.getElementById("msgLoadingHC");
    var URLactual = window.location.href;
    document.getElementById('btnNext').disabled="true"
    //Numero de paciente
    var number='';
    for (let index = 0; index < URLactual.length; index++) {
        var aux = URLactual[index];
        
        if (aux >= '0' && aux <= '9') {
            number=number+''+aux;
        }
    }
    //alert(number);
    var conscienteInt=0
    if(consciente="Si"){
        conscienteInt=1;
    }
    var pulmonesInt=0;
    if(pulmones="Normal"){
        pulmonesInt=1;
    }
    var corazonInt=0;
    if(corazon="Normal"){
        corazonInt=1;
    }
    var cabezaInt=0;
    if(cabeza="Normal"){
        cabezaInt=1;
    }
    var cuelloInt=0;
    if(cuello="Normal"){
        cuelloInt=1;
    }
    axios.post('/agregarExploracionFisica', {
        idExploracionFisica: 0,
        fecha: dateHoy,
        hora: horaImprimible,
        consciente: conscienteInt,
        habitus: habitus,
        peso: peso,
        estatura: estatura,
        ta: ta, 
        fc: fc,
        fr: fr,
        temperatura: temperatura,
        pulmones: pulmonesInt,
        pulmones_nota: pulmones_nota,
        corazon: corazonInt,
        corazon_nota: corazon_nota,
        cabeza: cabezaInt,
        cabeza_nota: cabeza_nota,
        cuello: cuelloInt,
        cuello_nota:cuello_nota,
        pronostico:pronostico,
        plan:plan,
        idPaciente: number,
        completed: false
    }).then(function (response) {
        msgLoadingHC.innerHTML="Vinculando expediente."
        if (estudiosLab!=null) {
            axios.post('/agregarEstudiosLaboratorio', {
                idEstudiosLaboratorio:0,
                idExploracionFisica: 0,
                grupoSanguineo: estudiosLab.gpoSanguineo,
                rh: estudiosLab.rh,
                hemoglobina: estudiosLab.hemoglobina,
                hematrocito: estudiosLab.hematocrito,
                leucocitos: estudiosLab.leucocitos,
                plaquetas: estudiosLab.plaquetas,
                tp: estudiosLab.tp,
                tpt: estudiosLab.tpt,
                glucosa: estudiosLab.glucosa,
                vdrl: estudiosLab.vdrl,
                antihiv: estudiosLab.hiv,
                completed: false
            }).then(function(response){
                msgLoadingHC.innerHTML="Vinculando estudios de laboratorio.";
                msgLoadingHC.innerHTML="Listo.";
                window.location.href="https://ginelife-mx.herokuapp.com/paciente/"+number;

            }).catch(function(response){
                msgLoadingHC.innerHTML="Error"+response.body;
            }).finally(function(response){

            })
        }else{
            msgLoadingHC.innerHTML="No hay estudios de laboratorio.";
            msgLoadingHC.innerHTML="Terminando proceso.";
            msgLoadingHC.innerHTML="Listo.";
            window.location.href="https://ginelife-mx.herokuapp.com/paciente/"+number;
        }
    }).catch(function (response){
        msgLoadingHC.innerHTML="Error"+response.body;
    }).finally(function(response){
       
    })
}