package com.erysa.system.erysasystem.servicio;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.erysa.system.erysasystem.controlador.dto.UsuarioRegistroDTO;
import com.erysa.system.erysasystem.dao.IFacturaDao;
import com.erysa.system.erysasystem.dao.IProductoDao;
import com.erysa.system.erysasystem.dao.IUsuarioDao;
import com.erysa.system.erysasystem.modelo.Factura;
import com.erysa.system.erysasystem.modelo.Producto;
import com.erysa.system.erysasystem.modelo.Rol;
import com.erysa.system.erysasystem.modelo.Usuario;
import com.erysa.system.erysasystem.repositorio.UsuarioRepositorio;


@Service
public class FacturaServiceImpl implements IFacturaService {

	@Autowired
	private IUsuarioDao usarioteDao;

	@Autowired
	private IProductoDao productoDao;

	@Autowired
	private IFacturaDao facturaDao;

//	@Autowired
//	private UsuarioRepositorio usuarioRepositorio;
//	
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usarioteDao.findAll();
	}
//
//	@Override
//	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
//
//		Usuario usuario = new Usuario(registroDTO.getNombre(), registroDTO.getApellido(),
//				registroDTO.getNumero_celular(), registroDTO.getDireccion(), registroDTO.getUsername(),
//				registroDTO.getEmail(), passwordEncoder.encode(registroDTO.getPassword()),
//				,Rrepositorio.asignarRol());
//				return usuarioRepositorio.save(usuario);;
//	}
//
	@Override
	@Transactional(readOnly = true)
	public Usuario buscarPorId(Integer id) {
		return usarioteDao.findById(id).orElse(null);
	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public Optional<Usuario> findById(Integer id) {
//		return usuarioRepositorio.findById(id);
//
//	}

	@Override
	@Transactional
	public void delete(Integer id) {
		usarioteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usarioteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombre(term);
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findProductoByid(Integer id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Integer id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteFactura(Integer id) {
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura fetchByIdWithItemFacturaWithProducto(Integer id) {
		return facturaDao.fetchByIdWithItemFacturaWithProducto(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario fetchByIdWithFacturas(Integer id) {
		return usarioteDao.fetchByIdWithFacturas(id);
	}

}
