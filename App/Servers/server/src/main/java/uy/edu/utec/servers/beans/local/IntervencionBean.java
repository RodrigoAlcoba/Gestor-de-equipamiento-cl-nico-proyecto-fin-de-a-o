package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IIntervencionBean;
import uy.edu.utec.servers.dao.EquipoDAO;
import uy.edu.utec.servers.dao.IntervencionDAO;
import uy.edu.utec.servers.dao.TipoIntervencionDAO;
import uy.edu.utec.servers.dao.UsuarioDAOnew;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.entidades.Intervencion;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.IntervencionMapper;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class IntervencionBean implements IIntervencionBean {
    @EJB
    IntervencionDAO intervencionDAO;
    @EJB
    TipoIntervencionDAO tipoIntervencionDAO;
    @EJB
    EquipoDAO equipoDAO;
    @EJB
    UsuarioDAOnew usuarioDAO;
    @Override
    public void agregarIntervencion(IntervencionDTO intervencion) throws ServiciosException {
        Intervencion intervencion1 = IntervencionMapper.toEntity(intervencion);
        intervencion1.setTipoIntervencion(tipoIntervencionDAO.buscarPorId(intervencion.getTipoIntervencion().getIdTipointervencion()));
        intervencion1.setEquipo(equipoDAO.buscarPorId(intervencion.getEquipoId()));
        intervencion1.setUsuario(usuarioDAO.buscarPorEmail(intervencion.getEmailUsuario()));
        intervencionDAO.agregarIntervencion(intervencion1);
    }

    @Override
    public IntervencionDTO buscarPorId(Integer id) {
        return IntervencionMapper.toDTO(intervencionDAO.buscarPorId(id));
    }

    @Override
    public List<IntervencionDTO> obtenerTodasLasIntervenciones() {
        return IntervencionMapper.toDTOList(intervencionDAO.obtenerTodasLasIntervenciones());
    }
    @Override
    public List<IntervencionDTO> obtenerIntervencionesPorTipoYFechas(
            Integer tipoIntervencionID,
            LocalDate fechaDesde,
            LocalDate fechaHasta) {
        return IntervencionMapper.toDTOList(intervencionDAO.obtenerIntervencionesPorTipoYFechas(
                tipoIntervencionID,
                fechaDesde,
                fechaHasta)
        );
    }

    @Override
    public List<IntervencionDTO> obtenerIntervencionesPorEquipoID(
            Integer equipoId,
            LocalDate fechaDesde,
            LocalDate fechaHasta) {
        return IntervencionMapper.toDTOList(intervencionDAO.obtenerIntervencionesPorEquipoID(
                 equipoId,
                 fechaDesde,
                 fechaHasta));
    }

    @Override
    public List<IntervencionDTO> obtenerIntervencionesPorTipo(String tipoIntervencion) {
        return IntervencionMapper.toDTOList(intervencionDAO.obtenerIntervencionesPorTipo(tipoIntervencion));
    }
}
