package com.erysa.system.erysasystem.servicio;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.erysa.system.erysasystem.UsuarioNoEncontrado;
import com.erysa.system.erysasystem.controlador.dto.UsuarioRegistroDTO;
import com.erysa.system.erysasystem.modelo.Rol;
import com.erysa.system.erysasystem.modelo.Usuario;
import com.erysa.system.erysasystem.repositorio.RolRepositorio;
import com.erysa.system.erysasystem.repositorio.UsuarioRepositorio;


@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {
	
	@Autowired
    private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private RolRepositorio Rrepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        super();
        this.usuarioRepositorio = usuarioRepositorio;
    }
    
    @Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getNombre(), 
				registroDTO.getApellido(),registroDTO.getNumero_celular(),registroDTO.getDireccion(),
				registroDTO.getUsername(),registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()),Rrepositorio.asignarRol());
		return usuarioRepositorio.save(usuario);
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(usuario.getUsername(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre_Rol()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }
    
    
    //ResetPassword
    @Autowired
    private UsuarioRepositorio usuarioRepositorio2;
    
    public void updateResetPasswordToken(String token, String email) throws com.erysa.system.erysasystem.UsuarioNoEncontrado {
		Usuario usuario = usuarioRepositorio2.findByEmail(email);
		
		if(usuario != null) {
		   usuario.setResetPasswordToken(token);
		   usuarioRepositorio2.save(usuario);
		   
		}else {
			throw new UsuarioNoEncontrado ("No se encontró el usuario con el correo: " + email);
		}

	}

	public Usuario get(String token) {
	
		return usuarioRepositorio2.findByResetPasswordToken(token);
	}

	
	public void updatePassword(Usuario usuario, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(newPassword);
		
		usuario.setPassword(encodePassword);
		usuario.setResetPasswordToken(null);
		
		usuarioRepositorio2.save(usuario);
		
	}
	
    
    
}
