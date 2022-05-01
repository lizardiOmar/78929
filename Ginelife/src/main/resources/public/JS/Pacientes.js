var pacientesT=document.getElementById('pacientes');
axios.get("/pacientesPorDoctora", {
    completed: false
})
.then(function (response) {
    var pacientes=JSON.parse(JSON.stringify(response.data));
    pacientes.forEach(p => {
        let rowPaciente=document.createElement('tr');

        let nombrePaciente=document.createElement('th');
        nombrePaciente.scope="row";
        nombrePaciente.innerHTML=p.apellidoPaterno+' '+p.apellidoMaterno+', '+p.nombres;
        rowPaciente.appendChild(nombrePaciente);

        let edadPaciente=document.createElement('td');
        edadPaciente.innerHTML=p.fechaDeNacimiento+' ('+p.edad+' años)';
        rowPaciente.appendChild(edadPaciente);

        let estadoCivilPaciente=document.createElement('td');
        estadoCivilPaciente.innerHTML=p.estadoCivil;
        rowPaciente.appendChild(estadoCivilPaciente);

        let telefonoPaciente=document.createElement('td');
        telefonoPaciente.innerHTML=p.telefono;
        rowPaciente.appendChild(telefonoPaciente);

        
        let buttonVer=document.createElement('button');
        buttonVer.innerHTML="Ver"
        buttonVer.value=p.idPaciente;
        buttonVer.addEventListener('click', verPaciente);

        let buttons=document.createElement('td');
        buttons.appendChild(buttonVer);
        rowPaciente.appendChild(buttons);

        pacientesT.appendChild(rowPaciente);
    });
    
})
.catch(function (error) {
    console.log(error);
});

function verPaciente(e) {
    //alert("Editar paciente con id "+e.srcElement.value);
    window.location.href="/paciente/"+e.srcElement.value;
}
var btnAgregarPaciente=document.getElementById('agregarPaciente');
btnAgregarPaciente.addEventListener('click', agregarPaciente)
function agregarPaciente(e) {
    window.location.href="/nuevoPaciente"
}