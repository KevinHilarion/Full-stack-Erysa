package com.erysa.system.erysasystem.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.erysa.system.erysasystem.modelo.Curso;


//Aqui se determina la estructura para realizar los metodos de eliminar, actualizar y listar 
public interface CursoServicio {
	
	public List<Curso> findAll();

	public Page<Curso> findAll(Pageable pageable);

	public void save(Curso curso);

	public Curso findOne(Long id);

	public void delete(Long id);
}
