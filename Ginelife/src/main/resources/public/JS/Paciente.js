function nuevaEF(idPaciente) {
    //alert("Editar paciente con id "+e.srcElement.value);
    window.location.href="/agregarExploracionFisica/"+idPaciente;
}

function verEF(idEF, idPaciente) {
    //alert("Editar paciente con id "+e.srcElement.value);
    window.location.href="/exploracionFisica/"+idEF+"/"+idPaciente;
}