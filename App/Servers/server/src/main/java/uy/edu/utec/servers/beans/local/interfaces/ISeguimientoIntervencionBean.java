package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@LocalBean
public interface ISeguimientoIntervencionBean {
    void altaSeguimiento(SeguimientoIntervencionDTO seguimientoIntervencion) throws ServiciosException;
    SeguimientoIntervencionDTO buscarPorId(Integer id);
    List<SeguimientoIntervencionDTO> obtenerTodosLosSeguimientosDeUnaIntervencionPorID(Integer intervencionId);
}
