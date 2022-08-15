function verPaciente(idPaciente) {
    //alert("Editar paciente con id "+e.srcElement.value);
    window.location.href="/paciente/"+idPaciente;
}

function navegarHC(idPaciente) {
    //alert("Editar paciente con id "+e.srcElement.value);
    window.location.href="/historiaClinica/"+idPaciente;
}

function navegarEF(idPaciente) {
    //alert("Editar paciente con id "+e.srcElement.value);
    window.location.href="/agregarExploracionFisica/"+idPaciente;
}


function agregarPaciente(e) {
    window.location.href="/nuevoPaciente"
}
