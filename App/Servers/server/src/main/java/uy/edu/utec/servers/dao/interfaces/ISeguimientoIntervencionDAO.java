package uy.edu.utec.servers.dao.interfaces;

import uy.edu.utec.servers.entidades.SeguimientoIntervencion;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

public interface ISeguimientoIntervencionDAO {
    void altaSeguimiento(SeguimientoIntervencion seguimientoIntervencion) throws ServiciosException;
    SeguimientoIntervencion buscarPorId(Integer id);
    List<SeguimientoIntervencion> obtenerTodosLosSeguimientosDeUnaIntervencionPorID(Integer intervencionId);

}
