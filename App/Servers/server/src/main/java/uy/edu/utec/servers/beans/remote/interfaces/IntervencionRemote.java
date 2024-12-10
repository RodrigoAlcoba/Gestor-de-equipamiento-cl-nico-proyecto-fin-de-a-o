package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.time.LocalDate;
import java.util.List;

@Remote
public interface IntervencionRemote {
    void agregarIntervencion(IntervencionDTO intervencion) throws ServiciosException;
    IntervencionDTO buscarPorId(Integer id);
    List<IntervencionDTO> obtenerTodasLasIntervenciones();
    List<IntervencionDTO> obtenerIntervencionesPorTipoYFechas(
            Integer tipoIntervencionID,
            LocalDate fechaDesde,
            LocalDate fechaHasta);
    List<IntervencionDTO> obtenerIntervencionesPorEquipoID(
            Integer equipoId,
            LocalDate fechaDesde,
            LocalDate fechaHasta);

    List<IntervencionDTO> obtenerIntervencionesPorTipo(String tipoIntervencion);
}
