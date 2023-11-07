package com.erysa.system.erysasystem.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.erysa.system.erysasystem.modelo.Curso;


//Se toma la id de la entidad y su tipo que en este caso es integer 
@Repository
public interface CursoRepositorio extends PagingAndSortingRepository<Curso, Long> {

}
