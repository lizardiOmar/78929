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
let mesHoy=MM+1;
if(mesHoy<10){
    mesHoy='0'+mesHoy;
}
axios.get("/itinerario?fecha="+YYYY+'-'+mesHoy+'-'+DD, {
    completed: false
})
.then(function (response) {
    var citasDia=JSON.parse(JSON.stringify(response.data.itinerario));
    let citasSpace=document.getElementById("citasHoy");
    if(citasDia!="No hay citas registradas."){
        citasDia.forEach(cita=>{
            //Fila de datos
            let row_1 = document.createElement('tr');
            row_1.style.borderTop="2px solid white";
            let row_2 = document.createElement('tr');
            row_2.style.borderBottom="2px solid white";

            citasSpace.appendChild(row_1);
            citasSpace.appendChild(row_2);
            //Fecha y hora en columna
            let tdFecha = document.createElement('td');
            //Fecha y hora en columna
            let tdHora = document.createElement('td');
            row_1.appendChild(tdFecha);
            row_2.appendChild(tdHora);
            //Nombre en columna
            let tdNombre = document.createElement('td');
            tdNombre.rowSpan=2;
            tdNombre.colSpan=2;
            row_1.appendChild(tdNombre);
            //Botones en columna
            let tdEditar = document.createElement('td');
            let tdCancelar = document.createElement('td');
            row_1.appendChild(tdEditar);
            row_2.appendChild(tdCancelar);
            //Fecha
            let fecha = document.createElement('p');
            fecha.innerHTML=cita.fecha;
            fecha.style.fontWeight="bold";
            tdFecha.appendChild(fecha);
            //Hora
            let hora = document.createElement('p');
            hora.innerHTML=cita.hora;
            tdHora.appendChild(hora);
            //Nombre
            let nombre = document.createElement('h3');
            nombre.innerHTML=cita.nombre;
            nombre.style.fontWeight="bold";
            tdNombre.appendChild(nombre);
            //Boton editar cita
            let editar = document.createElement('button');
            editar.innerHTML="Editar";
            editar.style.width="100%";
            editar.style.border="none";
            editar.style.backgroundColor="#20FC8F";
            editar.style.color="#262322";
            editar.style.borderRadius="15px";
            editar.style.fontWeight="bold";
            editar.value=cita.idCitas;
            editar.addEventListener('click', editarrCita);
            tdEditar.appendChild(editar);
            //Boton cancelar cita
            let cancelar = document.createElement('button');
            cancelar.innerHTML="Cancelar";
            cancelar.style.width="100%";
            cancelar.style.border="none";
            cancelar.style.backgroundColor="red";
            cancelar.style.color="white";
            cancelar.style.borderRadius="15px";
            cancelar.style.fontWeight="bold";
            cancelar.value=cita.idCitas;
            cancelar.addEventListener('click', cancelarCita);
            tdCancelar.appendChild(cancelar);

            citasSpace.style.textAlign="center";
            citasSpace.style.justifyContent="center";
            
        });
    }else{
        let row_1 = document.createElement('tr');
        row_1.style.borderTop="2px solid white";
        row_1.innerHTML=citasDia;
        citasSpace.appendChild(row_1);
    }
})
.catch(function (error) {
    console.log(error);
});

var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];
span.onclick = function() {
	modal.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == modal) {
	    modal.style.display = "none";
	}
}
document.getElementById('cancelarCita').addEventListener('click', cancelarCitaM);
function cancelarCitaM(e) {
    axios.get("/cancelarCitaById?idCita="+e.srcElement.value,{
        completed: false  
    })
    .then(function(response){
        if(response.data.estado=="SI"){
            alert("Cita cancelada");
            window.location.href='/ginelife';
        }else{
            alert("Cita no cancelada; error desconocido.");
        }
    })
}
function cancelarCita(e) {
    //alert("Cancelar cita con id "+e.srcElement.value);
    axios.get("/citaById?idCita="+e.srcElement.value, {
        completed: false
    })
    .then(function (response) {
        if(response.data!="No hay ninguna cita que coincida con el registro solicitado."){
            var citaS=JSON.parse(JSON.stringify(response.data.cita));
			document.getElementById('fecha').innerHTML=citaS.fecha;
            document.getElementById('hora').innerHTML=citaS.hora;
            document.getElementById('nombre').innerHTML=citaS.nombre;
            document.getElementById('motivo').innerHTML=citaS.descripcion;
            document.getElementById('cancelarCita').value=citaS.idCitas;
			modal.style.display = "block";
        }else{
            console.log(response.data);
        }
    })
    .catch(function (error) {
        console.log(error);
    });
}
function editarrCita(e) {
    alert("Editar cita con id "+e.srcElement.value);
}
var btnCitasHoy=document.getElementById('btnCitasHoy');
btnCitasHoy.addEventListener('click', mostrarCitasHoy);
var btnCitasSemana=document.getElementById('btnCitasSemana');
btnCitasSemana.addEventListener('click', mostrarCitasSemana);
var containerHoy=document.getElementById('citasHoy');
var containerSemana=document.getElementById('citasSemana');

function mostrarCitasHoy(e) {
    btnCitasHoy.style.backgroundColor="#ffcccc";
    btnCitasSemana.style.backgroundColor="#ffe6e6";

    btnCitasHoy.style.color="#262322";
    btnCitasSemana.style.color="#996600";

    containerHoy.style.visibility="visible";
    containerSemana.style.visibility="collapse";
}
function mostrarCitasSemana(e) {
    btnCitasHoy.style.backgroundColor="#ffe6e6";
    btnCitasSemana.style.backgroundColor="#ffcccc";

    btnCitasHoy.style.color="#996600";
    btnCitasSemana.style.color="#262322";

    containerHoy.style.visibility="collapse";
    containerSemana.style.visibility="visible";
}

//Ejemplo fecha
/*
axios.get("/itinerarioRange?fecha1=2022-04-12&fecha2=2022-04-30", {
    completed: false
})
.then(function (response) {
    var citasRango=JSON.parse(JSON.stringify(response.data.citas));
    console.log(citasRango);
})
.catch(function (error) {
    console.log(error);
});
*/
var fecha1=YYYY+'-'+mesHoy+'-'+DD;
var dia2=DD+7;
var mes2=mesHoy;
if(dia2>daysOfMonth){
    dia2=dia2-daysOfMonth;
    mes2=MM+2;
    if(mes2<10){
        mes2='0'+mes2;
    }
}
if(dia2<10){
    dia2='0'+dia2;
}
var fecha2=YYYY+'-'+mes2+'-'+dia2;
console.log("fecha1="+fecha1+"fecha2"+fecha2);

axios.get("/itinerarioRange?fecha1="+fecha1+"&fecha2="+fecha2, {
    completed: false
})
.then(function (response) {
    var citasRango=JSON.parse(JSON.stringify(response.data.citas));
    let citasSpace=document.getElementById("citasSemana");
    if(citasRango!="No hay citas registradas."){
        citasRango.forEach(cita=>{
            //Fila de datos
            let row_1 = document.createElement('tr');
            row_1.style.borderTop="2px solid white";
            let row_2 = document.createElement('tr');
            row_2.style.borderBottom="2px solid white";

            citasSpace.appendChild(row_1);
            citasSpace.appendChild(row_2);
            //Fecha y hora en columna
            let tdFecha = document.createElement('td');
            //Fecha y hora en columna
            let tdHora = document.createElement('td');
            row_1.appendChild(tdFecha);
            row_2.appendChild(tdHora);
            //Nombre en columna
            let tdNombre = document.createElement('td');
            tdNombre.rowSpan=2;
            tdNombre.colSpan=2;
            row_1.appendChild(tdNombre);
            //Botones en columna
            let tdEditar = document.createElement('td');
            let tdCancelar = document.createElement('td');
            row_1.appendChild(tdEditar);
            row_2.appendChild(tdCancelar);
            //Fecha
            let fecha = document.createElement('p');
            fecha.innerHTML=cita.fecha;
            fecha.style.fontWeight="bold";
            tdFecha.appendChild(fecha);
            //Hora
            let hora = document.createElement('p');
            hora.innerHTML=cita.hora;
            tdHora.appendChild(hora);
            //Nombre
            let nombre = document.createElement('h3');
            nombre.innerHTML=cita.nombre;
            nombre.style.fontWeight="bold";
            tdNombre.appendChild(nombre);
            //Boton editar cita
            let editar = document.createElement('button');
            editar.innerHTML="Editar";
            editar.style.width="100%";
            editar.style.border="none";
            editar.style.backgroundColor="#20FC8F";
            editar.style.color="#262322";
            editar.style.borderRadius="15px";
            editar.style.fontWeight="bold";
            editar.value=cita.idCitas;
            editar.addEventListener('click', editarrCita);
            tdEditar.appendChild(editar);
            //Boton cancelar cita
            let cancelar = document.createElement('button');
            cancelar.innerHTML="Cancelar";
            cancelar.style.width="100%";
            cancelar.style.border="none";
            cancelar.style.backgroundColor="red";
            cancelar.style.color="white";
            cancelar.style.borderRadius="15px";
            cancelar.style.fontWeight="bold";
            cancelar.value=cita.idCitas;
            cancelar.addEventListener('click', cancelarCita);
            tdCancelar.appendChild(cancelar);

            citasSpace.style.textAlign="center";
            citasSpace.style.justifyContent="center";
            
        }); 
    }
})
.catch(function (error) {
    console.log(error);
});
containerHoy.style.visibility="visible";
containerSemana.style.visibility="collapse";
var btnAgregarPaciente=document.getElementById('agregarPaciente');
btnAgregarPaciente.addEventListener('click', agregarPaciente)
function agregarPaciente(e) {
    window.location.href="/nuevoPaciente"
}