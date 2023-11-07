package com.erysa.system.erysasystem.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erysa.system.erysasystem.modelo.Categoria;

// Se toma la id de la entidad y su tipo que en este caso es integer 
@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {

}
