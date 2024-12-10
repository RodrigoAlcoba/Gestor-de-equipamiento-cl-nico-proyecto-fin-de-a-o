package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.ITipoIntervencionBean;
import uy.edu.utec.servers.dao.TipoIntervencionDAO;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.entidades.TipoIntervencion;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.TipoIntervencionMapper;

import java.util.List;

@Stateless
public class TipoIntervencionBean implements ITipoIntervencionBean {
    @EJB
    private TipoIntervencionDAO tipoIntervencionDAO;

    @Override
    public void agregarTipoIntervencion(TipoIntervencionDTO tipoIntervencion) throws ServiciosException {
        TipoIntervencion tipoIntervencion1 = TipoIntervencionMapper.toEntity(tipoIntervencion);
        tipoIntervencion1.setEstado(EstadoGeneral.ACTIVO);
        tipoIntervencionDAO.agregarTipoIntervencion(tipoIntervencion1);
    }

    @Override
    public TipoIntervencionDTO buscarPorId(Integer id) {
        return TipoIntervencionMapper.toDTO(tipoIntervencionDAO.buscarPorId(id));
    }

    @Override
    public List<TipoIntervencionDTO> obtenerTodosLosTiposIntervencion() {
        return TipoIntervencionMapper.toDTOList(tipoIntervencionDAO.obtenerTodosLosTiposIntervencion());
    }

    @Override
    public List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionActivos() {
        return TipoIntervencionMapper.toDTOList(tipoIntervencionDAO.obtenerTodosLosTipoIntervencionActivos());
    }

    @Override
    public List<TipoIntervencionDTO> obtenerTodosLoTipoIntervencionEliminados() {
        return TipoIntervencionMapper.toDTOList(tipoIntervencionDAO.obtenerTodosLoTipoIntervencionEliminados());
    }

    @Override
    public List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionPorTipo(String inputTipo) {
        return TipoIntervencionMapper.toDTOList(tipoIntervencionDAO.obtenerTodosLosTipoIntervencionPorTipo(inputTipo));
    }

    @Override
    public TipoIntervencionDTO obtenerTodosLosTipoIntervencionPorNombre(String nombre) {
        return TipoIntervencionMapper.toDTO(tipoIntervencionDAO.obtenerTodosLosTipoIntervencionPorNombre(nombre));
    }

    @Override
    public void eliminarTipoIntervencion(Integer id) throws ServiciosException {
        tipoIntervencionDAO.eliminarTipoIntervencion(id);
    }

    @Override
    public void activarTipoIntervencion(Integer id) throws ServiciosException {
        tipoIntervencionDAO.activarTipoIntervencion(id);
    }

    @Override
    public TipoIntervencionDTO obtenerPorNombre(String nombre) {
        return TipoIntervencionMapper.toDTO(tipoIntervencionDAO.obtenerTodosLosTipoIntervencionPorNombre(nombre));
    }
}
