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
//Dias de la semana
const intlForWeeks = new Intl.DateTimeFormat(locale, { weekday: 'long' });
const weekDays = [...Array(7).keys()]
const today=intlForWeeks.format(new Date(YYYY, MM, DD));
//dia 1
const startsOn = new Date(YYYY, MM, 1).getDay();
const firstday=intlForWeeks.format(new Date(YYYY, MM, 1));
//dias del mes
const daysOfMonth = new Date(YYYY, MM+1, 0).getDate();
const days = [...Array(daysOfMonth).keys()];

const fecha=today+' '+DD+' de '+monthName+' del '+YYYY;
document.getElementById('fecha').innerHTML=fecha;
//Cerrar sesión
document.getElementById('salir').addEventListener('click', salir);
function salir(e){
    axios.post('/logout', {
        completed: false
    })
    .then(function (response) {
        console.log(response.data);
        if(response.data=='EXIT'){
            alert('Saliendo.');
            window.location.href="/";
        }else{
            alert('Error.');
        }
    })
    .catch(function (error) {
        console.log(error);
    });
}

if(document.getElementById('pacientes')!=null){
    document.getElementById('pacientes').addEventListener('click', pacientes);
}
function pacientes(e){
    window.location.href="/pacientes";
}

document.getElementById('citas').addEventListener('click', citas);
function citas(e){
    window.location.href="/citas";
}