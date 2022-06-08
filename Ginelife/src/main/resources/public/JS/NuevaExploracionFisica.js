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
fechaHoy.value=DD+' de '+monthName+' del '+YYYY;
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
//Historia Clinica
const dateHoy=YYYY+'-'+mmAux+'-'+ddAux;

var btn_back=document.getElementById('btn_back');
var btn_next=document.getElementById('btn_next');
btn_next.addEventListener('click', btnNext);
//btn_back.addEventListener('click', btnBack);
btn_next.innerHTML="Crear";
btn_back.innerHTML="Cancelar";
console.log(btn_next.value)
var consciente, habitus, peso, estatura, ta, fc, fr, temperatura;
var pulmones, pulmones_nota, corazon, corazon_nota, cabeza, cabeza_nota, cuello, cuello_nota, idPaciente;
var pronostico, plan;
var ele = document.getElementsByName('consciente');

var pulmonesRadio = document.getElementsByName('pulmones');
var corazonRadio = document.getElementsByName('corazon');
var cabezaRadio = document.getElementsByName('cabeza');
var cuelloRadio = document.getElementsByName('cuello');
const msg=document.getElementById('msg');
function btnNext(){
    for(i = 0; i < ele.length; i++) {
        if(ele[i].checked){
            consciente=ele[i].value;
        }
    }
    habitus=document.getElementById('habitus').value;
    peso=document.getElementById('peso').value;
    estatura=document.getElementById('estatura').value;
    ta=document.getElementById('ta').value;
    fc=document.getElementById('fc').value;
    fr=document.getElementById('fr').value;
    temperatura=document.getElementById('temperatura').value;
    
    for(i = 0; i < pulmonesRadio.length; i++) {
        if(pulmonesRadio[i].checked){
            pulmones=pulmonesRadio[i].value;
        }
    }
    pulmones_nota=document.getElementById('pulmones_nota').value;

    for(i = 0; i < corazonRadio.length; i++) {
        if(corazonRadio[i].checked){
            corazon=corazonRadio[i].value;
        }
    }

    corazon_nota=document.getElementById('corazon_nota').value;
    
    for(i = 0; i < cabezaRadio.length; i++) {
        if(cabezaRadio[i].checked){
            cabeza=cabezaRadio[i].value;
        }
    }

    cabeza_nota=document.getElementById('cabeza_nota').value;
    
    for(i = 0; i < cuelloRadio.length; i++) {
        if(cuelloRadio[i].checked){
            cuello=cuelloRadio[i].value;
        }
    }

    cuello_nota=document.getElementById('cuello_nota').value;
    pronostico=document.getElementById('pronostico').value;
    plan=document.getElementById('plan').value;
    idPacienten=btn_next.value;
    if(!habitus){
        document.getElementById('habitus').focus;
        msg.innerHTML="Escriba el Habitus del paciente."
    }else{
        if(!peso){
            document.getElementById('peso').focus;
            msg.innerHTML="Escriba el peso del paciente."
        }else{
            if(!estatura){
                document.getElementById('estatura').focus;
                msg.innerHTML="Escriba la estatura del paciente."
            }else{
                if(!ta){
                    document.getElementById('ta').focus;
                    msg.innerHTML="Escriba la tensón arterial del paciente."
                }else{
                    if(!ta){
                        document.getElementById('ta').focus;
                        msg.innerHTML="Escriba la tensón arterial del paciente."
                    }else{
                        if(!fc){
                            document.getElementById('fc').focus;
                            msg.innerHTML="Escriba la frecuencia cardiaca del paciente."
                        }else{
                            if(!fr){
                                document.getElementById('fr').focus;
                                msg.innerHTML="Escriba la frecuencia respiratoria del paciente."
                            }else{
                                if(!temperatura){
                                    document.getElementById('temperatura').focus;
                                    msg.innerHTML="Escriba la temperatura del paciente."
                                }else{
                                    if(!temperatura){
                                        document.getElementById('temperatura').focus;
                                        msg.innerHTML="Escriba la temperatura del paciente."
                                    }else{
                                        msg.innerHTML="-"
                                        document.getElementById('registroBar').style.visibility="visible";
                                        document.getElementById('registroBar').value=50;
                                        //Envio de registro de exploración fisica
                                        axios.post('/agregarExploracionFisica', {
                                            idExploracionFisica:0,
                                            fecha:dateHoy,
                                            hora:horaImprimible,
                                            consciente:consciente,
                                            habitus: habitus,
                                            peso:peso,
                                            estatura: estatura,
                                            ta:ta,
                                            fc:fc,
                                            fr:fr, 
                                            temperatura:temperatura,
                                            pulmones:pulmones,
                                            pulmones_nota: pulmones_nota,
                                            corazon:corazon,
                                            corazon_nota:corazon_nota,
                                            cabeza:cabeza, 
                                            cabeza_nota:cabeza_nota,
                                            cuello:cuello,
                                            cuello_nota:cuello_nota,
                                            pronostico: pronostico,
                                            plan: plan,
                                            idPaciente: btn_next.value,
                                            completed: false
                                        })
                                        .then(function (response) {
                                            console.log(response.data);
                                            if(response.data==='SI'){
                                                msg.innerHTML="";
                                                document.getElementById('registroBar').value=100;
                                                
                                                if(document.getElementById('registroBar').value==100){
                                                    alert("Exploración física registrada con éxito.");
                                                    window.location.href="/paciente/"+btn_next.value;
                                                }
                                            }else{
                                                alert('Exploración física no creada. Contacte al administrador.');
                                            }
                                        })
                                        .catch(
                                            function (error) {
                                              console.log('Show error notification!')
                                              return Promise.reject(error)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}