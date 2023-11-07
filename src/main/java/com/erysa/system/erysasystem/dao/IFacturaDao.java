package com.erysa.system.erysasystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.erysa.system.erysasystem.modelo.Factura;




public interface IFacturaDao extends CrudRepository<Factura, Integer> {
	@Query("SELECT f FROM Factura f join fetch f.usuario c join fetch f.items l join fetch l.producto p WHERE f.id=?1")
	public Factura fetchByIdWithItemFacturaWithProducto(Integer id);
}
