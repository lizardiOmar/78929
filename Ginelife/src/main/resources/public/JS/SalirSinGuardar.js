var bPreguntar = false;
window.onbeforeunload = preguntarAntesDeSalir;
function preguntarAntesDeSalir () {
	if (document.getElementById('btnNext').disabled=="true") {
		var bPreguntar = false;
	}
	var respuesta;
	if ( bPreguntar ) {
		respuesta = confirm ( '¿Seguro que quieres salir?' );
		if ( respuesta ) {
			window.onunload = function () {
			    return true;
		    }
        } else {
		    return false;
	    }
	}
}
