package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.ITipoEquipoBean;
import uy.edu.utec.servers.dao.TipoEquipoDAO;
import uy.edu.utec.servers.dtos.TipoEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.TipoEquipoMapper;

import java.util.List;

@Stateless
public class TipoEquipoBean implements ITipoEquipoBean {

    @EJB
    private TipoEquipoDAO tipoEquipoDAO;
    @Override
    public void agregarTipoEquipo(TipoEquipoDTO tipoEquipo) throws ServiciosException {
        tipoEquipoDAO.agregarTipoEquipo(TipoEquipoMapper.toEntity(tipoEquipo));
    }

    @Override
    public TipoEquipoDTO buscarPorId(Integer id) {
        return TipoEquipoMapper.toDTO(tipoEquipoDAO.buscarPorId(id));
    }

    @Override
    public TipoEquipoDTO buscarPorNombre(String nombre) {
        return TipoEquipoMapper.toDTO(tipoEquipoDAO.buscarPorNombre(nombre));
    }

    @Override
    public List<TipoEquipoDTO> obtenerTodosLosTiposEquipo() {
        return TipoEquipoMapper.toDTOList(tipoEquipoDAO.obtenerTodosLosTiposEquipo());
    }

    @Override
    public List<TipoEquipoDTO> obtenerTodosLosTipoEquipoActivos() {
        return TipoEquipoMapper.toDTOList(tipoEquipoDAO.obtenerTodosLosTipoEquipoActivos());
    }

    @Override
    public List<TipoEquipoDTO> obtenerTodosLoTipoEquipoEliminados() {
        return TipoEquipoMapper.toDTOList(tipoEquipoDAO.obtenerTodosLoTipoEquipoEliminados());
    }

    @Override
    public List<TipoEquipoDTO> obtenerTodosLosTipoEquipoPorTipo(String inputTipo) {
        return TipoEquipoMapper.toDTOList(tipoEquipoDAO.obtenerTodosLosTipoEquipoPorTipo(inputTipo));
    }

    @Override
    public void eliminarTipoIntervencion(Integer id) throws ServiciosException {
        tipoEquipoDAO.eliminarTipoIntervencion(id);
    }

    @Override
    public void activarTipoEquipo(Integer id) throws ServiciosException {
        tipoEquipoDAO.activarTipoEquipo(id);
    }
}
