package com.erysa.system.erysasystem.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.erysa.system.erysasystem.modelo.Curso;
import com.erysa.system.erysasystem.repositorio.CursoRepositorio;

@Service
public class CursoServicioImpl implements CursoServicio{
	/**
	 * Aqui se determina la estructura para realizar los metodos de eliminar,
	 * actualizar y listar
	 */
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	
	/** Aqui se determina la estructura para listar el registro de productos */
	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		return (List<Curso>) cursoRepositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Curso> findAll(Pageable pageable) {
		return cursoRepositorio.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Curso curso) {
		cursoRepositorio.save(curso);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Curso findOne(Long id) {
		return cursoRepositorio.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		cursoRepositorio.deleteById(id);
	}

}
