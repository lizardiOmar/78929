var selectFiltro=document.getElementById("citas_select");
selectFiltro.addEventListener('change', seleccionarFiltro);
function seleccionarFiltro() {
    var filtro=selectFiltro.options[selectFiltro.selectedIndex].value;
    console.log(filtro)
    switch (filtro) {
        case "hoy":
            document.getElementById("citasHoyC").style.display="block";
            document.getElementById("citasSemanaC").style.display="none";
            document.getElementById("citasMesC").style.display="none";
            document.getElementById("citasYearC").style.display="none";
            document.getElementById("canceladasC").style.display="none";
        break;
        case "semana":
            document.getElementById("citasHoyC").style.display="none";
            document.getElementById("citasSemanaC").style.display="block";
            document.getElementById("citasMesC").style.display="none";
            document.getElementById("citasYearC").style.display="none";
            document.getElementById("canceladasC").style.display="none";
        break;
        case "mes":
            document.getElementById("citasHoyC").style.display="none";
            document.getElementById("citasSemanaC").style.display="none";
            document.getElementById("citasMesC").style.display="block";
            document.getElementById("citasYearC").style.display="none";
            document.getElementById("canceladasC").style.display="none";
        break;
        case "año":
            document.getElementById("citasHoyC").style.display="none";
            document.getElementById("citasSemanaC").style.display="none";
            document.getElementById("citasMesC").style.display="none";
            document.getElementById("citasYearC").style.display="block";
            document.getElementById("canceladasC").style.display="none";
        break;
        case "canceladas":
            document.getElementById("citasHoyC").style.display="none";
            document.getElementById("citasSemanaC").style.display="none";
            document.getElementById("citasMesC").style.display="none";
            document.getElementById("citasYearC").style.display="none";
            document.getElementById("canceladasC").style.display="block";
        break;
    }
}