//https://www.gob.mx/vun/estados?filter[order]=nombre%20ASC
axios.get('https://www.gob.mx/vun/estados?filter[order]=nombre%20ASC').then(function (response) {
    var estados=JSON.parse(JSON.stringify(response.data));
    var selectEstado=document.getElementById('estadoNacimiento');
    estados.forEach(e=>{
        //console.log(e.nombre); 
        var option = document.createElement('option');
        option.value=e.nombre;
        option.innerHTML=e.nombre;
        selectEstado.appendChild(option);
    });
});
const btnRegistrar=document.getElementById('btnRegistrar');
const msg=document.getElementById('msg');

const inputNombres=document.getElementById('nombres');
const inputApellidoP=document.getElementById('apellidoPaterno');
const inputApellidoM=document.getElementById('apellidoMaterno');
var txtNombres, txtApellidoP, txtApellidoM;
function getNombres() {
    txtNombres=inputNombres.value;
}
function getApellidoP() {
    txtApellidoP=inputApellidoP.value;
}
function getApellidoM() {
    txtApellidoM=inputApellidoM.value;
}
const inputEdad=document.getElementById('edad');
const inputDN=document.getElementById('dN');
const inputMN=document.getElementById('mN');
const inputAN=document.getElementById('aN');
var txtEdad, txtDN, txtMN, txtAN, txtFechaN;
function getEdad() {
    if(inputEdad.value<110 & inputEdad.value>15){
        txtEdad=inputEdad.value;
    }
}
function getDN() {
    if(inputDN.value<32 & inputDN.value>0){
        if(inputDN.value<10){
            txtDN='0'+inputDN.value;
        }else{
            txtDN=inputDN.value;
        }
    }
}
function getAN() {
    if(inputAN.value<2006 & inputAN.value>1922){
        txtAN=inputAN.value;
    }
}
const inputEstadoCivil=document.getElementById('estadoCivil');
const inputOcupacion=document.getElementById('ocupacion');
const inputEscolaridad=document.getElementById('escolaridad');
var txtEstadoCivil, txtOcupacion, txtEscolaridad;
function getOcupacion() {
    txtOcupacion=inputOcupacion.value;
}
const inputCiudadN=document.getElementById('ciudadN');
const inputEntidadN=document.getElementById('estadoNacimiento');
var txtEntidad, txtCiudad;
function getCiudadN(){
    txtCiudad=inputCiudadN.value;
}
const inputTelefono=document.getElementById('telefono');
const inputCorreo=document.getElementById('correo');
var txtCorreo, txtTelefono;
function getCorreo() {
    txtCorreo=inputCorreo.value;
}
function getTelefono() {
    txtTelefono=inputTelefono.value;
}
const inputMunicipio=document.getElementById('municipio');
const inputColonia=document.getElementById('colonia');
var txtMunicipio, txtColonia;
function getMunicipio() {
    txtMunicipio=inputMunicipio.value;
}
function getColonia() {
    txtColonia=inputColonia.value;
}
const inputCalle=document.getElementById('calle');
const inputNumero=document.getElementById('numero');
const inputCP=document.getElementById('cp');
var txtCalle, txtNumero, txtCP;
function getCalle() {
    txtCalle=inputCalle.value;
}
function getNumero() {
    txtNumero=inputNumero.value;
}
function getCP() {
    txtCP=inputCP.value;
}
btnRegistrar.addEventListener('click', registrar);
function registrar(e) {
    txtMN = inputMN.options[inputMN.selectedIndex].value;
    txtEstadoCivil = inputEstadoCivil.options[inputEstadoCivil.selectedIndex].value;
    txtEscolaridad = inputEscolaridad.options[inputEscolaridad.selectedIndex].value;
    txtEntidad = inputEntidadN.options[inputEntidadN.selectedIndex].value;
    
    msg.innerHTML="-";
    if(!txtNombres){
        msg.innerHTML="El (los) nombre (s) del paciente son necesarios.";
        inputNombres.focus();
    }else{
        if(!txtApellidoP){
            msg.innerHTML="El apellido paterno del paciente es necesario.";
            inputApellidoP.focus();
        }else{
            if(!txtApellidoM){
                msg.innerHTML="El apellido materno del paciente es necesario.";
                inputApellidoM.focus();
            }else{
                if(!txtEdad){
                    msg.innerHTML="La edad del paciente es necesaria.";
                    inputEdad.focus();
                }else{
                    if(!txtDN){
                        msg.innerHTML="El d??a de nacimiento del paciente es necesario.";
                        inputDN.focus();
                    }else{
                        if(!txtAN){
                            msg.innerHTML="El a??o de nacimiento del paciente es necesario.";
                            inputAN.focus();
                        }else{
                            txtFechaN=txtAN+'-'+txtMN+'-'+txtDN;
                            if(!txtOcupacion){
                                msg.innerHTML="La ocupaci??n del paciente es necesario.";
                                inputOcupacion.focus();
                            }else{
                                if(!txtCiudad){
                                    msg.innerHTML="La ciudad de nacimiento del paciente es necesaria.";
                                    inputCiudadN.focus();
                                }else{
                                    if(!txtTelefono){
                                        msg.innerHTML="El n??mero tel??fono del paciente es necesario.";
                                        inputTelefono.focus();
                                    }else{
                                        if(!txtCorreo){
                                            msg.innerHTML="El correo electr??nico del paciente es necesario.";
                                            inputCorreo.focus();
                                        }else{
                                            if(!txtMunicipio){
                                                msg.innerHTML="El municipio del domicilio del paciente es necesario.";
                                                inputMunicipio.focus();
                                            }else{
                                                if(!txtColonia){
                                                    msg.innerHTML="La colonia del domicilio del paciente es necesaria.";
                                                    inputColonia.focus();
                                                }else{
                                                    if(!txtCalle){
                                                        msg.innerHTML="La calle del domicilio del paciente es necesaria.";
                                                        inputCalle.focus();
                                                    }else{
                                                        if(!txtNumero){
                                                            msg.innerHTML="El n??mero del domicilio del paciente es necesario.";
                                                            inputNumero.focus();
                                                        }else{
                                                            if(!txtCP){
                                                                msg.innerHTML="El c??digo postal del domicilio del paciente es necesaria.";
                                                                inputCP.focus();
                                                            }else{
                                                                msg.innerHTML="Registrando paciente.";
                                                                btnRegistrar.disabled = true;
                                                                axios.post('/crearPaciente', {
                                                                    idPaciente: 0,
                                                                    nombres: txtNombres,
                                                                    apellidoPaterno: txtApellidoP,
                                                                    apellidoMaterno: txtApellidoM,
                                                                    edad: txtEdad,
                                                                    estadoCivil: txtEstadoCivil,
                                                                    ocupacion: txtOcupacion,
                                                                    escolaridad: txtEscolaridad,
                                                                    ciudadDeNacimiento: txtCiudad,
                                                                    entidadDeNacimiento: txtEntidad,
                                                                    telefono: txtTelefono,
                                                                    correo: txtCorreo,
                                                                    idGinecologa: 123, 
                                                                    fechaDeNacimiento: txtFechaN,
                                                                    completed: false
                                                                })
                                                                .then(function (response) {
                                                                    console.log(response.data);
                                                                    if(response.data==='SI'){
                                                                        msg.innerHTML="Paciente agregada. Registrando domicilio.";
                                                                        axios.post('/agregarDomicilio', {
                                                                            idDomicilio:0,
                                                                            municipio:txtMunicipio,
                                                                            colonia:txtColonia,
                                                                            calle:txtCalle,
                                                                            numero:txtNumero,
                                                                            cp:txtCP,
                                                                            idPaciente:0,
                                                                            completed: false
                                                                        })
                                                                        .then(function (response) {
                                                                            console.log(response.data);
                                                                            if(response.data==='SI'){
                                                                                alert('Domicilio registrado en la base de datos.');
                                                                                window.location.href='/pacientes';
                                                                            }else{
                                                                                alert('Paciente no registrada en la base de datos. Contacte al administrador.');
                                                                                window.location.href='/nuevoPaciente';
                                                                            }
                                                                        })
                                                                        .catch(
                                                                            function (error) {
                                                                              console.log('Show error notification!')
                                                                              return Promise.reject(error)
                                                                            }
                                                                        )
                                                                        //window.location.href='/ginelife';
                                                                    }else{
                                                                        alert('Paciente no registrada en la base de datos. Contacte al administrador.');
                                                                        window.location.href='/nuevoPaciente';
                                                                    }
                                                                })
                                                                .catch(
                                                                    function (error) {
                                                                      console.log('Show error notification!')
                                                                      return Promise.reject(error)
                                                                    }
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
window.addEventListener("beforeunload", function (e) {
    if(btnRegistrar.disabled != true){
        var confirmationMessage = "\o/";
        msg.innerHTML="El paciente a??n no ha sido guardado.";
        e.returnValue = confirmationMessage;     // Gecko, Trident, Chrome 34+
        return confirmationMessage;              // Gecko, WebKit, Chrome <34
    }
    
});