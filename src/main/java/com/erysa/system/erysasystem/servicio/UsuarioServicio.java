package com.erysa.system.erysasystem.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.erysa.system.erysasystem.controlador.dto.UsuarioRegistroDTO;
import com.erysa.system.erysasystem.modelo.Usuario;

public interface UsuarioServicio extends UserDetailsService {

	public Usuario guardar(UsuarioRegistroDTO registroDTO);

	public List<Usuario> listarUsuarios();

}