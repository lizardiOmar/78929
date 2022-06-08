//Boton para mostrar el modal de estudiso de laboratorio
var btnEstudios=document.getElementById('btnEstudios');
//Modal de estudios de laboratorio
var modalEstudios = document.getElementById("modalEstudios");
//Boton para cerrar el modal estudios de laboratorio
var spanEstudios = document.getElementsByClassName("close")[1];
//Boton para guardar los estudios de laboratorio
var btnGuardarEstudios =  document.getElementById("guardarEstudios");
//Asociar funion al boton Guardar esudios
btnGuardarEstudios.addEventListener("click", guardarEstudios);
//Mensaje de feedback del modal de los estudios de laboratorio
var msgEstudios=document.getElementById("msgEstudios");
//Mostrar modal
btnEstudios.onclick = function() {
    modalEstudios.style.display = "block";
    msgEstudios.innerHTML="-";
}
//Cerrar modal mediante el boton
spanEstudios.onclick = function() {
    modalEstudios.style.display = "none";
}
//Cerrar modal en caso de hacer click fuera de él
window.onclick = function(event) {
    if (event.target == modalEstudios) {
        modalEstudios.style.display = "none";
    }
}
//Datos del estudio de laboratorio
//Selects
var grupoSanguineoSelect=document.getElementById("grupoSanguineo");
var rhSelect=document.getElementById("rh");
var vdrlSelect=document.getElementById("vdrl");
var hivSelect=document.getElementById("hiv");
//Variables del estudio del lab. 
var gpoSanguineo, rh, hemoglobina, hematocrito, leucocitos, plaquetas, tp, tpt, glucosa, vdrl, hiv;
//Función para guardar datos del estudio
function guardarEstudios(e){
    gpoSanguineo=grupoSanguineoSelect.options[grupoSanguineoSelect.selectedIndex].value;
    rh=rhSelect.options[rhSelect.selectedIndex].value;
    vdrl=vdrlSelect.options[vdrlSelect.selectedIndex].value;
    hiv=hivSelect.options[hivSelect.selectedIndex].value;
    //Flag para validar el formulario
    let guardado=false;
    //hemoglobina
    if (document.getElementById("hemoglobina").value) {
        hemoglobina=document.getElementById("hemoglobina").value;
        document.getElementById('hemoglobina').style.boxShadow="none";
        //hematocrito
        if (document.getElementById("hematocrito").value) {
            hematocrito=document.getElementById("hematocrito").value;
            document.getElementById('hematocrito').style.boxShadow="none";
            //leucocitos
            if (document.getElementById("leucocitos").value) {
                leucocitos=document.getElementById("leucocitos").value;
                document.getElementById('leucocitos').style.boxShadow="none";
                //plaquetas
                if (document.getElementById("plaquetas").value) {
                    plaquetas=document.getElementById("plaquetas").value;
                    document.getElementById('plaquetas').style.boxShadow="none";
                    //tp
                    if (document.getElementById("tp").value) {
                        tp=document.getElementById("tp").value;
                        document.getElementById('tp').style.boxShadow="none";
                        //tpt
                        if (document.getElementById("tpt").value) {
                            tpt=document.getElementById("tpt").value;
                            document.getElementById('tpt').style.boxShadow="none";
                            //glucosa
                            if (document.getElementById("glucosa").value) {
                                glucosa=document.getElementById("glucosa").value;
                                guardado=true;
                                document.getElementById('glucosa').style.boxShadow="none";
                            }else{
                                guardado=false;
                                document.getElementById('glucosa').style.boxShadow="2px 2px 5px red";
                                document.getElementById('glucosa').focus();
                            }
                        }else{
                            guardado=false;
                            document.getElementById('tpt').style.boxShadow="2px 2px 5px red";
                            document.getElementById('tpt').focus();
                        }
                    }else{
                        guardado=false;
                        document.getElementById('tp').style.boxShadow="2px 2px 5px red";
                        document.getElementById('tp').focus();
                    }
                }else{
                    guardado=false;
                    document.getElementById('plaquetas').style.boxShadow="2px 2px 5px red";
                    document.getElementById('plaquetas').focus();
                }
            }else{
                guardado=false;
                document.getElementById('leucocitos').style.boxShadow="2px 2px 5px red";
                document.getElementById('leucocitos').focus();
            }
        }else{
            guardado=false;
            document.getElementById('hematocrito').style.boxShadow="2px 2px 5px red";
            document.getElementById('hematocrito').focus();
        }
    }else{
        guardado=false;
        document.getElementById('hemoglobina').style.boxShadow="2px 2px 5px red";
        document.getElementById('hemoglobina').focus();
    }
    
    if (guardado==true){
        //msgEstudios.innerHTML="Estudios de laboratorio guardados.";
        estudiosLab=new EstudiosLab(gpoSanguineo, rh, hemoglobina, hematocrito, leucocitos, plaquetas, tp, tpt, glucosa, vdrl, hiv);
        //alert(estudiosLab.gpoSanguineo+estudiosLab.rh);
        modalEstudios.style.display = "none";
    }else{
        msgEstudios.innerHTML="Los campos resaltados en color rojo son obligatorios.";
        
    }


}