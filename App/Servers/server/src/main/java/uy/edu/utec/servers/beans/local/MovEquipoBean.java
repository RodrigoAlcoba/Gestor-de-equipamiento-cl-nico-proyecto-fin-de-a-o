package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IMovEquipoBean;
import uy.edu.utec.servers.dao.EquipoDAO;
import uy.edu.utec.servers.dao.MovEquipoDAO;
import uy.edu.utec.servers.dao.UbicacionDAO;
import uy.edu.utec.servers.dao.UsuarioDAOnew;
import uy.edu.utec.servers.dtos.MovEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearMovEquipoDTO;
import uy.edu.utec.servers.entidades.MovEquipo;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.MovEquipoMapper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Stateless
public class MovEquipoBean implements IMovEquipoBean {
    @EJB
    MovEquipoDAO movEquipoDAO;
    @EJB
    UsuarioDAOnew usuarioDao;
    @EJB
    EquipoDAO equipoDAO;
    @EJB
    UbicacionDAO ubicacionDAO;
    @Override
    public void crearMovEquipo(CrearMovEquipoDTO movEquipo) throws ServiciosException {
        MovEquipo newMovEquipo = MovEquipoMapper.toEntity(movEquipo);

        newMovEquipo.setEquipoMovEquipo(equipoDAO.buscarPorId(movEquipo.getEquipoId()));
        newMovEquipo.setUbicacionMovEquipo(ubicacionDAO.buscarPorId(movEquipo.getUbicacionId()));
        newMovEquipo.setUsuarioMovEquipo(usuarioDao.buscarPorId(movEquipo.getUsuarioId()));

        //Actualiza la fecha de salida del mov equipo anterior.
        MovEquipo movEquipoAnterior = movEquipoDAO.buscarUbicacionActualByEquipoId(movEquipo.getEquipoId());
        if (movEquipoAnterior != null) {
            movEquipoAnterior.setFecSalida(movEquipo.getFecEntrada());
            movEquipoDAO.actualizarMovEquipo(movEquipoAnterior);
        }

        movEquipoDAO.crearMovEquipo(newMovEquipo);
    }

    @Override
    public void actualizarMovEquipo(MovEquipoDTO movEquipo) throws ServiciosException {
        MovEquipo updateMovEquipo = movEquipoDAO.buscarPorId(movEquipo.getIdMovEquipo());

        //De momento solo actualiza observaciones y la fecha de salida
        updateMovEquipo.setObservaciones(movEquipo.getObservaciones());
        updateMovEquipo.setFecSalida(movEquipo.getFecSalida());

        movEquipoDAO.actualizarMovEquipo(updateMovEquipo);
    }

    @Override
    public MovEquipoDTO buscarPorId(Integer id) {
        return MovEquipoMapper.toDTO(movEquipoDAO.buscarPorId(id));
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipo() {
        return MovEquipoMapper.toDTO(movEquipoDAO.obtenerTodosMovDeEquipo());
    }

    @Override
    public MovEquipoDTO buscarUbicacionActualByEquipoId(Integer equipoId) {
        return MovEquipoMapper.toDTO(movEquipoDAO.buscarUbicacionActualByEquipoId(equipoId));
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoByEuipoId(Integer equipoId) {
        return MovEquipoMapper.toDTO(movEquipoDAO.obtenerTodosMovDeEquipoByEuipoId(equipoId));
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoPorInstitucion(Integer InstitucionId) {
         return MovEquipoMapper.toDTO(movEquipoDAO.obtenerTodosMovDeEquipoPorInstitucion(InstitucionId));
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoPorSector(Integer idSector) {
        return MovEquipoMapper.toDTO(movEquipoDAO.obtenerTodosMovDeEquipoPorSector(idSector));
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoPorUsuario(Integer idUsuario) {
        return MovEquipoMapper.toDTO(movEquipoDAO.obtenerTodosMovDeEquipoPorUsuario(idUsuario));
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoPorFecDesde(LocalDate fecEntrada) {
        return MovEquipoMapper.toDTO(movEquipoDAO.obtenerTodosMovDeEquipoPorFecDesde(fecEntrada));
    }

    @Override
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoPorFechaHasta(LocalDate fecHasta) {
        return MovEquipoMapper.toDTO(movEquipoDAO.obtenerTodosMovDeEquipoPorFechaHasta(fecHasta));
    }
}
