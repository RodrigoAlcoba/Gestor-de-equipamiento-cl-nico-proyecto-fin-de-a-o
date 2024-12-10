package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.entidades.Intervencion;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.time.LocalDate;
import java.util.List;

@LocalBean
public interface IIntervencionBean {
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
