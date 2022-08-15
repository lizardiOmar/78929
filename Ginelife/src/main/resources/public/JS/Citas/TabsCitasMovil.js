var tabMisCitas=document.getElementById("tabMisCitas");
tabMisCitas.addEventListener("click", mostrarMisCitas);
var tabNewCita=document.getElementById("tabNewCita");
tabNewCita.addEventListener("click", mostrarNewCita);

var containerMisCitas=document.getElementById("containerMisCitas");
var containerNewCita=document.getElementById("containerNewCita");
function mostrarMisCitas() {
    tabMisCitas.style.backgroundColor="#ffcccc";
    tabNewCita.style.backgroundColor="#ffe6e6";
    tabMisCitas.style.color="#262322";
    tabNewCita.style.color="#996600";
    containerMisCitas.style.display="block";
    containerNewCita.style.display="none";
}
function mostrarNewCita() {
    tabNewCita.style.backgroundColor="#ffcccc";
    tabMisCitas.style.backgroundColor="#ffe6e6";
    tabNewCita.style.color="#262322";
    tabMisCitas.style.color="#996600";
    containerNewCita.style.display="block";
    containerMisCitas.style.display="none";
}