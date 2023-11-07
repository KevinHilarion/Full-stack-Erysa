package com.erysa.system.erysasystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.erysa.system.erysasystem.modelo.Usuario;

public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Integer> {

	@Query("select c from Usuario c left join fetch c.facturas f where c.id = ?1")
	public Usuario fetchByIdWithFacturas(Integer id);

}
