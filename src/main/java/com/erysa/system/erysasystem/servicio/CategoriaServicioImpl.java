package com.erysa.system.erysasystem.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.erysa.system.erysasystem.modelo.Categoria;
import com.erysa.system.erysasystem.repositorio.CategoriaRepositorio;

@Service
public class CategoriaServicioImpl implements CategoriaServicio{
	/**
	 * Aqui se determina la estructura para realizar los metodos de eliminar,
	 * actualizar y listar
	 */
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	
	/** Aqui se determina la estructura para listar el registro de productos */
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return (List<Categoria>) categoriaRepositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Categoria> findAll(Pageable pageable) {
		return categoriaRepositorio.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public void save(Categoria categoria) {
		categoriaRepositorio.save(categoria);
	}

	@Override
	public Categoria findOne(Integer id) {
		return categoriaRepositorio.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		categoriaRepositorio.deleteById(id);
		
	}

}
