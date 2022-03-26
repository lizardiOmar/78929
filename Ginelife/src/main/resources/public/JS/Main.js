let params = new URLSearchParams(location.search);
const correo = params.get('c');
//Fecha actual
const hoy = new Date();
const MM=hoy.getMonth();
const DD=hoy.getDate();
const YYYY=hoy.getFullYear();
const locale = 'es';
//Mes actual
const intlForMonths = new Intl.DateTimeFormat(locale, { month: 'long' });
const months = [...Array(12).keys()];
const monthName = intlForMonths.format(new Date(YYYY, MM));
//console.log(monthName);
//Dias de la semana
const intlForWeeks = new Intl.DateTimeFormat(locale, { weekday: 'long' });
const weekDays = [...Array(7).keys()]
const today=intlForWeeks.format(new Date(YYYY, MM, DD));
//console.log(DD+'/'+today);
//dia 1
const startsOn = new Date(YYYY, MM, 1).getDay();
const firstday=intlForWeeks.format(new Date(YYYY, MM, 1));
//console.log(1+'/'+firstday+'('+monthName+')');
//dias del mes
const daysOfMonth = new Date(YYYY, MM+1, 0).getDate();
const days = [...Array(daysOfMonth).keys()];
//console.log(days.length);
/*
for(let i=1;i<days.length+1;i++){
    //console.log(i);
    console.log(i+'/'+intlForWeeks.format(new Date(YYYY, MM, i)));
    intlForWeeks.format(new Date(YYYY, MM, i-1));
}
*/
const fecha=today+' '+DD+' de '+monthName+' del '+YYYY;
document.getElementById('fecha').innerHTML=fecha;
document.getElementById('salir').addEventListener('click', salir);
function salir(e){
    window.location.href="/";
}

if(document.getElementById('pacientes')!=null){
    document.getElementById('pacientes').addEventListener('click', pacientes);
}
function pacientes(e){
    window.location.href="/pacientes?c="+correo;
}

document.getElementById('citas').addEventListener('click', citas);
function citas(e){
    window.location.href="/citas?c="+correo;
}