//Fecha actual
const hoy = new Date();
const mes_hoy=hoy.getMonth();
const dia_hoy=hoy.getDate();
const anio_hoy=hoy.getFullYear();
const ubicacion = 'es';
var diaS=0;
var diaS_id=0;
var horaS=0;
var horaS_id=0;
const r_cita=document.getElementById('reg_cita');
r_cita.addEventListener('click', reg_cita);
//Mes actual
const intlForMonths = new Intl.DateTimeFormat(ubicacion, { month: 'long' });
const meses_array = [...Array(12).keys()];
//Arreglo para el nombre de los meses
let meses=[];
//Select HTML para garegar opciones
const select_mes=document.getElementById('mes');
//Mes actual
let aux = document.createElement("option");
aux.innerHTML = intlForMonths.format(new Date(anio_hoy, mes_hoy)).toUpperCase();
aux.value=mes_hoy;
select_mes.appendChild(aux);
let prueba=document.createElement("p");


//Cargar días del mes actual
meses_array.forEach(element => {
    meses.push(intlForMonths.format(new Date(anio_hoy, element)));
	if (element > mes_hoy) {
		let aux = document.createElement("option");
		aux.innerHTML = meses[element].toUpperCase();
		aux.value=element;
		select_mes.appendChild(aux);
	}
});

select_mes.addEventListener("change", mes_select);
var mes_id=mes_hoy;
function mes_select(e){
	//console.log(select_mes[select_mes.selectedIndex].value);
	//console.log(meses[select_mes[select_mes.selectedIndex].value])
	let index_mes=select_mes[select_mes.selectedIndex].value;
	mes_id=index_mes;
    let text_mes=meses[select_mes[select_mes.selectedIndex].value];
	llenar_calendario(index_mes);
	
}
//Días de la semana
const intlForWeeks = new Intl.DateTimeFormat(ubicacion, { weekday: 'long' });
const weekDays = [...Array(7).keys()]
const today=intlForWeeks.format(new Date(anio_hoy, mes_hoy, dia_hoy));
console.log(today);
llenar_calendario(mes_hoy)
//Llenar calendario de acuerdo al mes
function llenar_calendario(mes){
	
	let dia_1_index = new Date(anio_hoy, mes, 1).getDay();
	let dia_1_text=intlForWeeks.format(new Date(anio_hoy, mes, 1));
	let dia_ultimo = new Date(anio_hoy, mes+1, 0).getDate();
	let dias = [...Array(dia_ultimo).keys()];
	for(let i=1;i<43;i++){
		document.getElementById(i).innerHTML='-';
		document.getElementById(i).value='0';
	}
	console.log(dia_1_text)
	switch (dia_1_text) {
		
		case 'domingo':
			document.getElementById('1').innerHTML='1';
			document.getElementById('1').value='1';
			for(let i=2;i<43;i++){
				if(i<dias.length){
					document.getElementById(i).innerHTML=i;
					document.getElementById(i).value=i;
				}else if(i==dias.length){
					document.getElementById(i).innerHTML=i;
					document.getElementById(i).value=i;
					break;
				}
			}
		break;
		case 'lunes':
			document.getElementById('2').innerHTML='1';
			document.getElementById('2').value='1';
			var aux=2;
			for(let i=3;i<43;i++){
				if(aux<dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					aux++;
				}else if(aux==dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					break;
				}
			}
        break;
		case 'martes':
			document.getElementById('3').innerHTML='1';
			document.getElementById('3').value='1';
			var aux=2;
			for(let i=4;i<43;i++){
				if(aux<dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					aux++;
				}else if(aux==dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					break;
				}
			}
        break;
		case 'miércoles':
			document.getElementById('4').innerHTML='1';
			document.getElementById('4').value='1';
			var aux=2;
			for(let i=5;i<43;i++){
				if(aux<dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					aux++;
				}else if(aux==dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					break;
				}
			}
        break;
		case 'jueves':
			document.getElementById('5').innerHTML='1';
			document.getElementById('5').value='1';
			var aux=2;
			for(let i=6;i<43;i++){
				if(aux<dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					aux++;
				}else if(aux==dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					break;
				}
			}
        break;
		case 'viernes':
			document.getElementById('6').innerHTML='1';
			document.getElementById('6').value='1';
			var aux=2;
			for(let i=7;i<43;i++){
				if(aux<dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					aux++;
				}else if(aux==dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					break;
				}
			}
		break;
		case 'sábado':
			document.getElementById('7').innerHTML='1';
			var aux=2;
			for(let i=8;i<43;i++){
				if(aux<dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					aux++;
				}else if(aux==dias.length){
					document.getElementById(i).innerHTML=aux;
					document.getElementById(i).value=aux;
					break;
				}
			}
		break;
		default:
        break;
	}
    for(let i=1;i<43;i++){
        document.getElementById(i).style.justifyContent="center";
		if(document.getElementById(i).innerHTML!='-'){
			document.getElementById(i).addEventListener('click', getDia);
		}
    }
	diaS=0;
	diaS_id=0;
	horaS=0;
	horaS_id=0;
}

function getDia(e){
	if(horaS!=0){
		if(document.getElementById(horaS_id)!=null){
			document.getElementById(horaS_id).style.backgroundColor="#ffcccc";
		}
		horaS_id=0;
		horaS=0;
	}
	if(diaS==0){
		diaS_id=e.srcElement.id;
		document.getElementById(diaS_id).style.backgroundColor="#ff8080";
	}else{
		//Itinerario por dia y mes
		document.getElementById(diaS_id).style.backgroundColor="#ffcccc";
		diaS_id=e.srcElement.id;
		document.getElementById(diaS_id).style.backgroundColor="#ff8080";
	}
	diaS=e.srcElement.innerHTML;
	axios.get('/itinerario?dia='+diaS+'&mes='+meses[mes_id]).then(function (response) {
		var citasDia=JSON.parse(JSON.stringify(response.data));
		console.log(citasDia);
	});
}

function getHora(e){
	if(diaS==0){
		alert("primero seleccione un día del calendario.");
	}else{
		if(horaS_id==0){
			horaS_id=e.srcElement.id;
			document.getElementById(horaS_id).style.backgroundColor="#ff8080";
		}else{
			document.getElementById(horaS_id).style.backgroundColor="#ffcccc";
			horaS_id=e.srcElement.id;
			document.getElementById(horaS_id).style.backgroundColor="#ff8080";
		}
		horaS=e.srcElement.innerHTML;
		//alert(diaS+'-'+mes_id+', '+horaS);
	}
	
}
for(let i=1;i<11;i++){
	let v=i+'v';
	let m=i+'m';
	document.getElementById(m).addEventListener('click', getHora);
	document.getElementById(v).addEventListener('click', getHora);
}
var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];
function reg_cita(e) {
	if(diaS==0){
		alert("primero seleccione un día del calendario")
	}else{
		if(horaS==0){
			alert("primero seleccione una hora del itinerario del "+diaS+" de "+meses[mes_id]);
		}else{
			//alert("Puede crear una cita el día "+diaS+" de "+meses[mes_id]+" a las "+horaS);
			document.getElementById('fecha').innerHTML="Fecha: "+diaS+" de "+meses[mes_id]+" a las "+horaS
			//Datos seleccionados, se puede enviar la solicitud post al servidor para crear una cita
			modal.style.display = "block";
		}
	}
}
span.onclick = function() {
	modal.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == modal) {
	  modal.style.display = "none";
	}
}
btnCrear=document.getElementById("crearCita");
btnCrear.addEventListener("click", crearCita);
function crearCita(e) {
	let txtNombre=document.getElementById('nombre').value;
	let txtDescripcion=document.getElementById('descripcion').value;
	let msgModal=document.getElementById('msgModal');
	if(txtNombre){
		if(txtDescripcion){
			//Post registro.
			let nombre=txtNombre;
			let hora=horaS;
			let dia=diaS;
			let mes=meses[mes_id];
			let descripcion=txtDescripcion;
			let idGinecologa=document.getElementById('g').innerHTML;
			let estado = 0;
			axios.post('/crearCita', {
                idCita: 0,
                nombre: nombre,
                hora: hora,
                dia: dia,
                mes: mes,
                descripcion: descripcion,
                idGinecologa: idGinecologa,
                estado: estado,
                completed: false
            })
            .then(function (response) {
                console.log(response.data);
                if(response.data==='SI'){
                    alert('Cita creada.');
                    window.location.href='/citas';
                }else{
                    alert('Cita no creada. Contacte al administrador.');
                    window.location.href='/citas';
                }
            })
		}else{
			msgModal.innerHTML="La descripción es un campo obligatorio para registrar una cita."
		}
	}else{
		msgModal.innerHTML="El nombre es un campo obligatorio para registrar una cita."
	}
}