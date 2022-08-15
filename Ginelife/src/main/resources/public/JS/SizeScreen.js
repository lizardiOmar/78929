function pantalla(){
    axios.post('/pantalla', {
        height: screen.height,
        width: screen.width,
        completed: false
    }).then(function(response){
        console.log(response.data);
    }).catch(function (error) {
        console.log(error);
    });
}
pantalla();