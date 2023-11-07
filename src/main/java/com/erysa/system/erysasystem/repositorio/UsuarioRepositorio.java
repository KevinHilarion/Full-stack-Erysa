package com.erysa.system.erysasystem.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erysa.system.erysasystem.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    public Usuario findByEmail(String username);
    
    public Usuario findByResetPasswordToken(String token);
}
