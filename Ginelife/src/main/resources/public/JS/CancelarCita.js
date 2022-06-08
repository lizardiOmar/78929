function cancelarCita(idCita) {
    axios.get("/cancelarCitaById?idCita="+idCita,{
        completed: false  
    })
    .then(function(response){
        if(response.data.estado=="SI"){
            alert("Cita cancelada");
            window.location.href='/ginelife';
        }else{
            alert("Cita no cancelada; error desconocido.");
        }
    })
}