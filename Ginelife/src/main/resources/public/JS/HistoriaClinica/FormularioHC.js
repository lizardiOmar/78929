// Get the modal información
var modalInfo = document.getElementById("modalInfo");
var modalLoad = document.getElementById("modalLoading");
// Get the <span> element that closes the modal
var spanInfo = document.getElementsByClassName("close")[3];
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
//mensaje de error
var msgInfo=document.getElementById("msgInfo");
//Botones prev y next
var btnPrev=document.getElementById('btnPrev');
var btnNext=document.getElementById('btnNext');
//progress bar del registro
var progress=document.getElementById('registroBar');
//Containers de los pasos del formulario
var step_1=document.getElementById('step_1');
var step_2=document.getElementById('step_2');
var step_3=document.getElementById('step_3');
var step_4=document.getElementById('step_4');
var step_5=document.getElementById('step_5');
var step_6=document.getElementById('step_6');
//Visibilidad de los steps
step_1.style.display = "block";
step_2.style.display = "none";
step_3.style.display = "none";
step_4.style.display = "none";
step_5.style.display = "none";
step_6.style.display = "none";
//Función para el boton Next
btnNext.addEventListener('click', nextStep);
btnPrev.addEventListener('click', prevStep);

var diabetesHF, cardiopatiasHF, htaHF, tiroidesHF, neoplasticosHF, especificacionesHF;
var diabetesPP, cardiopatiasPP, htaPP, epilepsiaPP, nefropatiasPP;
var alcoholismoPNP, tabaquismoPNP, drogasPNP;
var rubeolaPNP, influenzaPNP, tetanosPNP, covidPNP;
var menarca, ritmo, ivs, comSex, gestaciones, partos, abortos, cesareas, ectopico, molar, fup, fum, fpp, padecimientoActual="Ninguno.";
var idPaciente=btnNext.value;

function nextStep() {
    if (progress.value==100) {
        modalLoad.style.display = "flex";
        modalLoad.style.alignItems = "center";
        modalLoad.style.justifyContent = "center";
        document.getElementById('btnNext').disabled="true";
        let msgLoadingHC=document.getElementById("msgLoadingHC");
        axios.post('/agregarHistoriaClinica', {
            idHistoriaClinica: 0,
            fecha: dateHoy,
            hora: horaImprimible,
            idPaciente: idPaciente,
            completed: false
        }).then(function (response) {
            if(response.data==='SI'){
                msgLoadingHC.innerHTML="Expediente creado.";
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
                }).then(function (response) {
                    if(response.data==='SI'){
                        msgLoadingHC.innerHTML="Antecedentes heredo familiares agregados.";
                        axios.post('/agregarAPP', {
                            idAntecedentesPersonalesPatologicos: 0,
                            diabetes: diabetesPP,
                            hta: htaPP,
                            nefropatias: nefropatiasPP,
                            cardiopatias: cardiopatiasPP,
                            epilepsia: epilepsiaPP,
                            idHistoriaClinica: 0,
                            completed: false
                        }).then(function (response) {
                            if(response.data==='SI'){
                                msgLoadingHC.innerHTML="Antecedentes personales patológicos agregados.";
                                axios.post('/agregarAPNP', {
                                    idAntecedentesPersonalesNoPatologicos: 0,
                                    tabaquismo: tabaquismoPNP,
                                    alcoholismo: alcoholismoPNP,
                                    drogas: drogasPNP,
                                    rubeola: rubeolaPNP,
                                    influenza: influenzaPNP,
                                    tetanos: tetanosPNP,
                                    covid19: covidPNP,
                                    idHistoriaClinica: 0,
                                    completed: false
                                }).then(function (response) {
                                    if(response.data==='SI'){
                                        msgLoadingHC.innerHTML="Antecedentes personales no patológicos agregados.";
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
                                            padecimientoActual:padecimientoActual,
                                            idHistoriaClinica: 0,
                                            completed: false
                                        }).then(function (response) {
                                            if(response.data==='SI'){
                                                msgLoadingHC.innerHTML="Antecedentes gineco obstetricos agregados.";
                                                if(traumas.length==0){
                                                    msgLoadingHC.innerHTML="No hay traumatismos que agregar.";
                                                }else{
                                                    let c=1;
                                                    traumas.forEach(trauma => {
                                                        axios.post('/agregarAT', {
                                                            idAntecedenteTraumatico: 0,
                                                            descripcion: trauma.descripcion,
                                                            fecha: trauma.fecha,
                                                            idHistoriaClinica: 0,
                                                            completed: false
                                                        })
                                                        .then(function (response) {
                                                            if(response.data==='SI'){
                                                                msgLoadingHC.innerHTML="Traumatismo "+c+' de '+traumas.length;
                                                            }else{
                                                                msgLoadingHC.innerHTML=response.data;
                                                            }
                                                        })
                                                        c+=1;
                                                    })
                                                    if(cirugias.length==0){
                                                        msgLoadingHC.innerHTML="No hay cirugias que agregar.";
                                                    }else{
                                                        let c=1;
                                                        cirugias.forEach(cirugia => {
                                                            axios.post('/agregarAQ', {
                                                                idAntecedenteQuirurgico: 0,
                                                                descripcion: cirugia.descripcion,
                                                                fecha: cirugia.fecha,
                                                                idHistoriaClinica: 0,
                                                                completed: false
                                                            })
                                                            .then(function (response) {
                                                                if(response.data==='SI'){
                                                                    msgLoadingHC.innerHTML="Cirugia "+c+' de '+cirugias.length;
                                                                }else{
                                                                    msgLoadingHC.innerHTML=response.data;
                                                                }
                                                            })
                                                            c+=1;
                                                        })
                                                    }
                                                    if(alergias.length==0){
                                                        msgLoadingHC.innerHTML="No hay alergias que agregar.";
                                                    }else{
                                                        let c=1;
                                                        alergias.forEach(alergia => {
                                                            axios.post('/agregarAlergia', {
                                                                idAlergia: 0,
                                                                nombre: alergia,
                                                                idHistoriaClinica: 0,
                                                                completed: false
                                                            })
                                                            .then(function (response) {
                                                                if(response.data==='SI'){
                                                                    msgLoadingHC.innerHTML="Alergia "+c+' de '+alergias.length;
                                                                }else{
                                                                    msgLoadingHC.innerHTML=response.data;
                                                                }
                                                            })
                                                            c+=1;
                                                        })
                                                    }
                                                    msgLoadingHC.innerHTML="Registro de hitoria clínica finalizado con éxito.";

                                                    document.location.href="https://ginelife-mx.herokuapp.com/historiaClinica/"+idPaciente;
                                                }
                                            }else{
                                                msgLoadingHC.innerHTML=response.data;
                                            }
                                        })
                                    }else{
                                        msgLoadingHC.innerHTML=response.data;
                                    }
                        
                                })
                            }else{
                                msgLoadingHC.innerHTML=response.data;
                            }
                        })
                    }else{
                        msgLoadingHC.innerHTML=response.data;
                    }
                })
            }else{
                msgLoadingHC.innerHTML="Esta paciente ya tiene un expediente creado.";
                if (msgLoadingHC.innerHTML=="Esta paciente ya tiene un expediente creado.") {
                    document.location.href="https://ginelife-mx.herokuapp.com/historiaClinica/"+idPaciente;
                }
            }
        });
        
        
        
        
        
        
       
       
    }else{
        btnNext.innerHTML="Siguiente";
        btnPrev.innerHTML="Anterior";
        if (progress.value!=80) {
            progress.value+=20;
        } else {
            //Validar menarca
            if (document.getElementById('menarca').value) {
                document.getElementById('menarca').style.boxShadow="none";
                menarca=document.getElementById('menarca').value;
                //Validar ritmo
                if (document.getElementById('ritmo').value) {
                    document.getElementById('ritmo').style.boxShadow="none";
                    ritmo=document.getElementById('ritmo').value;
                    //Validar ivs
                    if (document.getElementById('ivs').value) {
                        document.getElementById('ivs').style.boxShadow="none";
                        ivs=document.getElementById('ivs').value;
                        //Validar compañeros sexuales
                        if (document.getElementById('comSex').value) {
                            document.getElementById('comSex').style.boxShadow="none";
                            comSex=document.getElementById('comSex').value;
                            //Validar gestaciones
                            if (document.getElementById('gestaciones').value) {
                                document.getElementById('gestaciones').style.boxShadow="none";
                                gestaciones=document.getElementById('gestaciones').value;
                                //Validar partos
                                if (document.getElementById('partos').value) {
                                    document.getElementById('partos').style.boxShadow="none";
                                    partos=document.getElementById('partos').value;
                                    //Validar abortos
                                    if (document.getElementById('abortos').value) {
                                        document.getElementById('abortos').style.boxShadow="none";
                                        abortos=document.getElementById('abortos').value;
                                        //Validar cesareas
                                        if (document.getElementById('cesareas').value) {
                                            document.getElementById('cesareas').style.boxShadow="none";
                                            cesareas=document.getElementById('cesareas').value;
                                            //Validar ectopico
                                            if (document.getElementById('ectopico').value) {
                                                document.getElementById('ectopico').style.boxShadow="none";
                                                ectopico=document.getElementById('ectopico').value;
                                                //Validar molar
                                                if (document.getElementById('molar').value) {
                                                    document.getElementById('molar').style.boxShadow="none";
                                                    molar=document.getElementById('molar').value;
                                                    //Validar fup
                                                    if (document.getElementById('fup').value) {
                                                        document.getElementById('fup').style.boxShadow="none";
                                                        fup=document.getElementById('fup').value;
                                                        //Validar fum
                                                        if (document.getElementById('fum').value) {
                                                            document.getElementById('fum').style.boxShadow="none";
                                                            fum=document.getElementById('fum').value;
                                                            //Validar fum
                                                            if (document.getElementById('fpp').value) {
                                                                document.getElementById('fpp').style.boxShadow="none";
                                                                fpp=document.getElementById('fpp').value;
                                                                if (document.getElementById('padecimientoActual').value) {
                                                                    padecimientoActual=document.getElementById('padecimientoActual').value;
                                                                }
                                                                progress.value+=20;
                                                            } else {
                                                                msgInfo.innerHTML="FPP es un campo necesario.";
                                                                modalInfo.style.display = "block";
                                                                document.getElementById('fpp').style.boxShadow="2px 2px 5px red";
                                                            }
                                                        } else {
                                                            msgInfo.innerHTML="FUM es un campo necesario.";
                                                            modalInfo.style.display = "block";
                                                            document.getElementById('fum').style.boxShadow="2px 2px 5px red";
                                                        }
                                                    } else {
                                                        msgInfo.innerHTML="FUP es un campo necesario.";
                                                        modalInfo.style.display = "block";
                                                        document.getElementById('fup').style.boxShadow="2px 2px 5px red";
                                                    }
                                                } else {
                                                    msgInfo.innerHTML="Molar es un campo necesario.";
                                                    modalInfo.style.display = "block";
                                                    document.getElementById('molar').style.boxShadow="2px 2px 5px red";
                                                }
                                            } else {
                                                msgInfo.innerHTML="Ectópico es un campo necesario.";
                                                modalInfo.style.display = "block";
                                                document.getElementById('ectopico').style.boxShadow="2px 2px 5px red";
                                            }
                                        } else {
                                            msgInfo.innerHTML="El número de cesareas es un campo necesario.";
                                            modalInfo.style.display = "block";
                                            document.getElementById('cesareas').style.boxShadow="2px 2px 5px red";
                                        }
                                    } else {
                                        msgInfo.innerHTML="El número de abortos es un campo necesario.";
                                        modalInfo.style.display = "block";
                                        document.getElementById('abortos').style.boxShadow="2px 2px 5px red";
                                    }
                                } else {
                                    msgInfo.innerHTML="El número de partos es un campo necesario.";
                                    modalInfo.style.display = "block";
                                    document.getElementById('partos').style.boxShadow="2px 2px 5px red";
                                }
                            } else {
                                msgInfo.innerHTML="El número de gestaciones es un campo necesario.";
                                modalInfo.style.display = "block";
                                document.getElementById('gestaciones').style.boxShadow="2px 2px 5px red";
                            }
                        } else {
                            msgInfo.innerHTML="El número de compañeros sexuales es un campo necesario.";
                            modalInfo.style.display = "block";
                            document.getElementById('comSex').style.boxShadow="2px 2px 5px red";
                        }
                    } else {
                        msgInfo.innerHTML="El I.V.S. es un campo necesario.";
                        modalInfo.style.display = "block";
                        document.getElementById('ivs').style.boxShadow="2px 2px 5px red";
                    }
                } else {
                    msgInfo.innerHTML="El ritmo es un campo necesario.";
                    modalInfo.style.display = "block";
                    document.getElementById('ritmo').style.boxShadow="2px 2px 5px red";
                }
            } else {
                msgInfo.innerHTML="La menarca es un campo necesario.";
                modalInfo.style.display = "block";
                document.getElementById('menarca').style.boxShadow="2px 2px 5px red";
            }
        }
        if (progress.value==20) {
            btnNext.style.backgroundColor="gray";
            step_1.style.display = "none";
            step_2.style.display = "block";
            step_3.style.display = "none";
            step_4.style.display = "none";
            step_5.style.display = "none";
            step_6.style.display = "none";
            
        }
        if (progress.value==40) {
            step_1.style.display = "none";
            step_2.style.display = "none";
            step_3.style.display = "block";
            step_4.style.display = "none";
            step_5.style.display = "none";
            step_6.style.display = "none";

        }
        if (progress.value==60) {
            step_1.style.display = "none";
            step_2.style.display = "none";
            step_3.style.display = "none";
            step_4.style.display = "block";
            step_5.style.display = "none";
            step_6.style.display = "none";

        }
        if (progress.value==80) {
            step_1.style.display = "none";
            step_2.style.display = "none";
            step_3.style.display = "none";
            step_4.style.display = "none";
            step_5.style.display = "block";
            step_6.style.display = "none";

        }
        if (progress.value==100) {
            step_1.style.display = "none";
            step_2.style.display = "none";
            step_3.style.display = "none";
            step_4.style.display = "none";
            step_5.style.display = "none";
            step_6.style.display = "block";
            step_1_vars();
            step_2_vars();
            step_3_vars();
            step_4_vars();
            step_5_vars();
            btnNext.innerHTML="Guardar";
            btnNext.style.backgroundColor="#20FC8F";
            
        }
    }
}
function prevStep() {
    if (progress.value==0) {
        btnNext.innerHTML="Siguiente";
        btnPrev.innerHTML="Cancelar";
    }else{
        progress.value-=20;
        btnNext.innerHTML="Siguiente";
        btnPrev.innerHTML="Anterior";
        btnNext.style.backgroundColor="gray";
        if (progress.value==0) {
            btnPrev.innerHTML="Cancelar";
            step_1.style.display = "block";
            step_2.style.display = "none";
            step_3.style.display = "none";
            step_4.style.display = "none";
            step_5.style.display = "none";
            step_6.style.display = "none";
        }
        if (progress.value==20) {
            step_1.style.display = "none";
            step_2.style.display = "block";
            step_3.style.display = "none";
            step_4.style.display = "none";
            step_5.style.display = "none";
            step_6.style.display = "none";
        }
        if (progress.value==40) {
            step_1.style.display = "none";
            step_2.style.display = "none";
            step_3.style.display = "block";
            step_4.style.display = "none";
            step_5.style.display = "none";
            step_6.style.display = "none";
        }
        if (progress.value==60) {
            step_1.style.display = "none";
            step_2.style.display = "none";
            step_3.style.display = "none";
            step_4.style.display = "block";
            step_5.style.display = "none";
        }
        if (progress.value==80) {
            
            step_1.style.display = "none";
            step_2.style.display = "none";
            step_3.style.display = "none";
            step_4.style.display = "none";
            step_5.style.display = "block";
            step_6.style.display = "none";
        }
        if (progress.value==100) {
            step_1.style.display = "none";
            step_2.style.display = "none";
            step_3.style.display = "none";
            step_4.style.display = "none";
            step_5.style.display = "none";
            step_6.style.display = "block";
        }
    }
}
function step_1_vars() {
    especificacionesHF="Ninguna.";
    diabetesHF=0;
    cardiopatiasHF=0;
    tiroidesHF=0;
    htaHF=0;
    neoplasticosHF=0;

    document.getElementById("diabetes__HF").innerHTML="No";
    document.getElementById("cardio__HF").innerHTML="No";
    document.getElementById("hta__HF").innerHTML="No";
    document.getElementById("tiro__HF").innerHTML="No";
    document.getElementById("neop__HF").innerHTML="No";
    

    if(document.getElementById("diabetes_hf").checked == true){
        diabetesHF=1;
        document.getElementById("diabetes__HF").innerHTML="Si";
    }
    if(document.getElementById("cardiopatias_hf").checked == true){
        cardiopatiasHF=1;
        document.getElementById("cardio__HF").innerHTML="Si";
    }
    if(document.getElementById("HTA_hf").checked == true){
        htaHF=1;
        document.getElementById("hta__HF").innerHTML="Si";
    }
    if(document.getElementById("tiroides_hf").checked == true){
        tiroidesHF=1;
        document.getElementById("tiro__HF").innerHTML="Si";
    }
    if(document.getElementById("neoplasticos_hf").checked == true){
        neoplasticosHF=1;
        document.getElementById("neop__HF").innerHTML="Si";
    }
    
    if(document.getElementById("especificaciones_hf").value){
        especificacionesHF=document.getElementById("especificaciones_hf").value;
    }
    
    document.getElementById("espec__HF").innerHTML=especificacionesHF;
}
function step_2_vars() {
    diabetesPP=0;
    cardiopatiasPP=0;
    htaPP=0;
    epilepsiaPP=0;
    nefropatiasPP=0;

    document.getElementById("diabetes__PP").innerHTML="No";
    document.getElementById("cardio__PP").innerHTML="No";
    document.getElementById("hta__PP").innerHTML="No";
    document.getElementById("epil__PP").innerHTML="No";
    document.getElementById("nefro__PP").innerHTML="No";
    

    if(document.getElementById("diabetes_pp").checked == true){
        diabetesPP=1;
        document.getElementById("diabetes__PP").innerHTML="Si";
    }
    if(document.getElementById("cardiopatias_pp").checked == true){
        cardiopatiasPP=1;
        document.getElementById("cardio__PP").innerHTML="Si";
    }
    if(document.getElementById("HTA_pp").checked == true){
        htaPP=1;
        document.getElementById("hta__PP").innerHTML="Si";
    }
    if(document.getElementById("epilepsia_pp").checked == true){
        epilepsiaPP=1;
        document.getElementById("epil__PP").innerHTML="Si";
    }
    if(document.getElementById("nefropatias_pp").checked == true){
        nefropatiasPP=1;
        document.getElementById("nefro__PP").innerHTML="Si";
    }
}
function step_3_vars(){
    //Alergias
    document.getElementById('alergias_step_list_1').innerHTML="";
    if (alergias.length>0) {
        alergias.forEach(alergia => {
            let tr_alergia=document.createElement("tr");
            let tc_alergia=document.createElement("td");
            tc_alergia.innerHTML=alergia;
            tr_alergia.appendChild(tc_alergia);
            document.getElementById('alergias_step_list_1').appendChild(tr_alergia);
        });
    } else {
        let tr_alergia=document.createElement("tr");
        let tc_alergia=document.createElement("td");
        tc_alergia.innerHTML="Sin alergias registradas.";
        tr_alergia.appendChild(tc_alergia);
        document.getElementById('alergias_step_list_1').appendChild(tr_alergia);
    }
    //Traumatismos
    document.getElementById('traumas_step_list_1').innerHTML="";
    if (traumas.length>0) {
        traumas.forEach(trauma => {
            let tr_trauma=document.createElement("tr");
            let tc_traumaDesc=document.createElement("td");
            let tc_traumaFecha=document.createElement("td");
    
            tc_traumaDesc.innerHTML=trauma.descripcion;
            tc_traumaFecha.innerHTML=trauma.fecha;
    
            tr_trauma.appendChild(tc_traumaDesc);
            tr_trauma.appendChild(tc_traumaFecha);
            document.getElementById('traumas_step_list_1').appendChild(tr_trauma);
        });
    } else {
        let tr_trauma=document.createElement("tr");
        let tc_traumaDesc=document.createElement("td");
    
        tc_traumaDesc.innerHTML="Sin antecedentes traumaticos registrados."
        tr_trauma.appendChild(tc_traumaDesc);
        document.getElementById('traumas_step_list_1').appendChild(tr_trauma);

    }
    //Cirugias
    document.getElementById('cirugias_step_list_1').innerHTML="";
    if (cirugias.length>0) {
        cirugias.forEach(cirugia => {
            let tr_cirugia=document.createElement("tr");
            let tc_CirugiaDesc=document.createElement("td");
            let tc_CirugiaFecha=document.createElement("td");
    
            tc_CirugiaDesc.innerHTML=cirugia.descripcion;
            tc_CirugiaFecha.innerHTML=cirugia.fecha;
    
            tr_cirugia.appendChild(tc_CirugiaDesc);
            tr_cirugia.appendChild(tc_CirugiaFecha);
            document.getElementById('cirugias_step_list_1').appendChild(tr_cirugia);
        });
    } else {
        let tr_cirugia=document.createElement("tr");
            let tc_CirugiaDesc=document.createElement("td");
    
            tc_CirugiaDesc.innerHTML="Sin antecedentes quirurgicos registrados.";
    
            tr_cirugia.appendChild(tc_CirugiaDesc);
            document.getElementById('cirugias_step_list_1').appendChild(tr_cirugia);
    }
}
function step_4_vars() {
    alcoholismoPNP=0, tabaquismoPNP=0, drogasPNP=0;
    rubeolaPNP=0, influenzaPNP=0, tetanosPNP=0, covidPNP=0;

    document.getElementById("tabaquismo__PNP").innerHTML="No";
    document.getElementById("alcoholismo__PNP").innerHTML="No";
    document.getElementById("drogas__PNP").innerHTML="No";

    document.getElementById("rubeola__PNP").innerHTML="No";
    document.getElementById("influenza__PNP").innerHTML="No";
    document.getElementById("tetanos__PNP").innerHTML="No";
    document.getElementById("covid__PNP").innerHTML="No";

    if(document.getElementById("tabaquismo").checked == true){
        tabaquismoPNP=1;
        document.getElementById("tabaquismo__PNP").innerHTML="Si";
    }
    if(document.getElementById("alcoholismo").checked == true){
        alcoholismoPNP=1;
        document.getElementById("alcoholismo__PNP").innerHTML="Si";
    }
    if(document.getElementById("drogas").checked == true){
        drogasPNP=1;
        document.getElementById("drogas__PNP").innerHTML="Si";
    }

    if(document.getElementById("rubeola").checked == true){
        rubeolaPNP=1;
        document.getElementById("rubeola__PNP").innerHTML="Si";
    }
    if(document.getElementById("influenza").checked == true){
        influenzaPNP=1;
        document.getElementById("influenza__PNP").innerHTML="Si";
    }
    if(document.getElementById("tetanos").checked == true){
        tetanosPNP=1;
        document.getElementById("tetanos__PNP").innerHTML="Si";
    }
    if(document.getElementById("covid19").checked == true){
        covidPNP=1;
        document.getElementById("covid__PNP").innerHTML="Si";
    }
}
function step_5_vars() {
    //Row 1
    document.getElementById("menarca__").innerHTML=menarca;
    document.getElementById("ritmo__").innerHTML=ritmo;
    document.getElementById("ivs__").innerHTML=ivs;
    document.getElementById("comSex__").innerHTML=comSex;
    //Row 2
    document.getElementById("gestaciones__").innerHTML=gestaciones;
    document.getElementById("partos__").innerHTML=partos;
    document.getElementById("abortos__").innerHTML=abortos;
    document.getElementById("cesareas__").innerHTML=cesareas;
    //Row 3
    document.getElementById("ectopico__").innerHTML=ectopico;
    document.getElementById("molar__").innerHTML=molar;
    document.getElementById("fum__").innerHTML=fum;
    document.getElementById("fup__").innerHTML=fup;
    document.getElementById("fpp__").innerHTML=fpp;
    //Row 4
    document.getElementById("padecimientoActual__").innerHTML=padecimientoActual;
    
}