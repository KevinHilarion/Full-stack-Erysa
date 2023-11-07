package com.erysa.system.erysasystem.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.erysa.system.erysasystem.modelo.Producto;
import java.util.List;


// Se toma la id de la entidad y su tipo que en este caso es integer 
@Repository
public interface ProductoRepositorio extends PagingAndSortingRepository<Producto, Integer> {
	
	@Query("SELECT p FROM Producto p WHERE p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
}
