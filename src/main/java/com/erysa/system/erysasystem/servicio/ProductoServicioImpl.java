package com.erysa.system.erysasystem.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.erysa.system.erysasystem.modelo.Producto;
import com.erysa.system.erysasystem.repositorio.ProductoRepositorio;

@Service
public class ProductoServicioImpl implements ProductoServicio {
	/**
	 * Aqui se determina la estructura para realizar los metodos de eliminar,
	 * actualizar y listar
	 */
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	/** Aqui se determina la estructura para listar el registro de productos */
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoRepositorio.findAll();
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pageable) {
		return productoRepositorio.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Producto producto) {
		productoRepositorio.save(producto);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		productoRepositorio.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findOne(Integer id) {
		return productoRepositorio.findById(id).orElse(null);
	}
}
