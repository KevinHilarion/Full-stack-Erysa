package com.erysa.system.erysasystem.servicio;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.erysa.system.erysasystem.modelo.Categoria;

public interface CategoriaServicio {
	

	public List<Categoria> findAll();

	public Page<Categoria> findAll(Pageable pageable);

	public void save(Categoria categoria);

	public Categoria findOne(Integer id);

	public void delete(Integer id);
}
