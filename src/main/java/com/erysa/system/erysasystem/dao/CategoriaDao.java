package com.erysa.system.erysasystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erysa.system.erysasystem.modelo.Categoria;



//Se toma la id de la entidad y su tipo que en este caso es Integer
public interface CategoriaDao extends JpaRepository<Categoria, Integer> {

}
