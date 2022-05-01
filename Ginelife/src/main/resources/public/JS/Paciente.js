const btnEditarApellidoP=document.getElementById('btnApellidoP');
const apellidoP=document.getElementById('apellidoP');
btnEditarApellidoP.addEventListener('click', editarApellidoP);
function editarApellidoP() {
    if (apellidoP.disabled==true) {
        btnEditarApellidoP.src="/public/IMG/7.png";
        apellidoP.disabled=false;
        apellidoP.focus();
    }else{
        btnEditarApellidoP.src="/public/IMG/6.png";
        apellidoP.disabled=true;
    }
}
const btnEditarApellidoM=document.getElementById('btnApellidoM');
const apellidoM=document.getElementById('apellidoM');
btnEditarApellidoM.addEventListener('click', editarApellidoM);
function editarApellidoM() {
    if (apellidoM.disabled==true) {
        btnEditarApellidoM.src="/public/IMG/7.png";
        apellidoM.disabled=false;
        apellidoM.focus();
    }else{
        btnEditarApellidoM.src="/public/IMG/6.png";
        apellidoM.disabled=true;
    }
}
const btnNombres=document.getElementById('btnNombres');
const nombres=document.getElementById('nombres');
btnNombres.addEventListener('click', editarNombres);
function editarNombres() {
    if (nombres.disabled==true) {
        btnNombres.src="/public/IMG/7.png";
        nombres.disabled=false;
        nombres.focus();
    }else{
        btnNombres.src="/public/IMG/6.png";
        nombres.disabled=true;
    }
}