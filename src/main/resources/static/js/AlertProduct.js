function eliminarProducto(id) {
	console.log(id);
	swal({
		  title: "¿Está seguro de eliminar el producto?",
		  text: "Una vez eliminado no se prodrá restablecer!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/eliminar/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("Poof! Producto eliminado!", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/listar";
		    	}
		    });
		  } 
		});
}

function ConfirmarC(){
	
	alert("Se han guardado los cambios de manera correcta")
	
}



   







