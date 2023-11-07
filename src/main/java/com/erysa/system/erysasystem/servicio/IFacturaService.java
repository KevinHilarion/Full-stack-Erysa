package com.erysa.system.erysasystem.servicio;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.erysa.system.erysasystem.controlador.dto.UsuarioRegistroDTO;
import com.erysa.system.erysasystem.modelo.Factura;
import com.erysa.system.erysasystem.modelo.Producto;
import com.erysa.system.erysasystem.modelo.Usuario;


@Service
public interface IFacturaService {

	public List<Usuario> findAll();

	public Page<Usuario> findAll(Pageable pageable);

	//public Usuario guardar(UsuarioRegistroDTO clienteRegistroDTO);

	public Usuario buscarPorId(Integer id);

	//Optional<Usuario> findById(Integer id);

	public void delete(Integer id);

	public List<Producto> findByNombre(String term);

	public void saveFactura(Factura factura);

	public Producto findProductoByid(Integer id);

	public Factura findFacturaById(Integer id);

	public void deleteFactura(Integer id);

	public Factura fetchByIdWithItemFacturaWithProducto(Integer id);

	public Usuario fetchByIdWithFacturas(Integer id);

}
