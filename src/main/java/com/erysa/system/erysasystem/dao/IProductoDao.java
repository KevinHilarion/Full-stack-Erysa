package com.erysa.system.erysasystem.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.erysa.system.erysasystem.modelo.Producto;

public interface IProductoDao extends CrudRepository<Producto, Integer> {
	
	@Query("SELECT p FROM Producto p WHERE p.nombre like %?1%")
	public List<Producto> findByNombre(String term);

}
