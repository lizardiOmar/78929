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
const dateHoy=YYYY+'-'+mmAux+'-'+ddAux;
