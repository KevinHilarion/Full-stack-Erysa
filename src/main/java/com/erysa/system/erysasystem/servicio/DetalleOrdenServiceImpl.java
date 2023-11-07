package com.erysa.system.erysasystem.servicio;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erysa.system.erysasystem.modelo.DetalleOrden;
import com.erysa.system.erysasystem.repositorio.IDetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService {

	@Autowired
	private IDetalleOrdenRepository detalleOrdenRepository;

	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}

	@Override
	public Optional<DetalleOrden> findById(Integer id) {
		return detalleOrdenRepository.findById(id);
	}
}