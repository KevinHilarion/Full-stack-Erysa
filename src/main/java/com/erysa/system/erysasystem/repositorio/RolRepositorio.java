package com.erysa.system.erysasystem.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erysa.system.erysasystem.modelo.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long>{
	
	@Query(value= "SELECT * FROM rol WHERE rol.id_rol= 1", nativeQuery = true )
	 List<Rol> asignarRol();
}
