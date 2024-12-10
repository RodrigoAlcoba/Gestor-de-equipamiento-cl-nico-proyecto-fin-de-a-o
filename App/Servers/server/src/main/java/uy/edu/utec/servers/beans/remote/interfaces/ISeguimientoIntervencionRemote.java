package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Remote
public interface ISeguimientoIntervencionRemote {
    void altaSeguimiento(SeguimientoIntervencionDTO seguimientoIntervencion) throws ServiciosException;
    SeguimientoIntervencionDTO buscarPorId(Integer id);
    List<SeguimientoIntervencionDTO> obtenerTodosLosSeguimientosDeUnaIntervencionPorID(Integer intervencionId);
}
