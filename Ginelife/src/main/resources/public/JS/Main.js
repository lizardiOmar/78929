var btnCitasHoy=document.getElementById("btnCitasHoy");
btnCitasHoy.addEventListener("click", mostrarCitasHoy);
var btnCitasSemana=document.getElementById("btnCitasSemana");
btnCitasSemana.addEventListener("click", mostrarCitasSemana);

var containerHoy=document.getElementById("containerHoy");
var containerSemana=document.getElementById("containerSemana");

function mostrarCitasHoy() {
    btnCitasHoy.style.backgroundColor="#ffcccc";
    btnCitasSemana.style.backgroundColor="#ffe6e6";
    btnCitasHoy.style.color="#262322";
    btnCitasSemana.style.color="#996600";
    containerHoy.style.display="block";
    containerSemana.style.display="none";
}
function mostrarCitasSemana() {
    btnCitasHoy.style.backgroundColor="#ffe6e6";
    btnCitasSemana.style.backgroundColor="#ffcccc";
    btnCitasHoy.style.color="#996600";
    btnCitasSemana.style.color="#262322";
    containerHoy.style.display="none";
    containerSemana.style.display="block";
}

containerHoy.style.display="block";
containerSemana.style.display="none";
btnCitasHoy.style.backgroundColor="#ffcccc";
btnCitasSemana.style.backgroundColor="#ffe6e6";
btnCitasHoy.style.color="#262322";
btnCitasSemana.style.color="#996600";

var btnAgregarPaciente=document.getElementById('agregarPaciente');
var citasCard=document.getElementById('citasCard');
btnAgregarPaciente.addEventListener('click', agregarPaciente)
function agregarPaciente() {
    window.location.href="/nuevoPaciente"
}

citasCard.addEventListener('click', function() {
    window.location.href="/citas"
})