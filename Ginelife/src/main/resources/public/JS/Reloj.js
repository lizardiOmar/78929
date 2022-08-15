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
