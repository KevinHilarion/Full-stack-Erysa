package com.erysa.system.erysasystem.servicio;

import java.util.Optional;
import com.erysa.system.erysasystem.modelo.DetalleOrden;


public interface IDetalleOrdenService {

	DetalleOrden save(DetalleOrden detalleOrden);

	Optional<DetalleOrden> findById(Integer id);
}
