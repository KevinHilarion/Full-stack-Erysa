package com.erysa.system.erysasystem.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erysa.system.erysasystem.modelo.Producto;

// Aqui se determina la estructura para realizar los metodos de eliminar, actualizar y listar 
public interface ProductoServicio {

	public List<Producto> findAll();

	public Page<Producto> findAll(Pageable pageable);

	public void save(Producto producto);

	public Producto findOne(Integer id);

	public void delete(Integer id);
}
