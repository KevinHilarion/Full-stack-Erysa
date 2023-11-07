function recuperar(){

	swal({
		  text: "Se ha enviado exitosamente el mensaje de restablecimiento!",
		  icon: "success",
	
		  
		})
		.then((OK) => {
		  if (OK) {
			  location.href="/login";
				}		
			  });
		   
		
}
