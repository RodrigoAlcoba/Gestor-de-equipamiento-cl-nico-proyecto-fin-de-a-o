package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.TipoEquipoBean;
import uy.edu.utec.servers.beans.local.interfaces.ITipoEquipoBean;
import uy.edu.utec.servers.beans.remote.interfaces.ITipoEquipoRemote;
import uy.edu.utec.servers.dao.TipoEquipoDAO;
import uy.edu.utec.servers.dtos.TipoEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class TipoEquipoBeanImpl implements ITipoEquipoRemote {
    @EJB
    ITipoEquipoBean tipoEquipoBean;
    @Override
    public void agregarTipoEquipo(TipoEquipoDTO tipoEquipo) throws ServiciosException {
        tipoEquipoBean.agregarTipoEquipo(tipoEquipo);
    }

    @Override
    public TipoEquipoDTO buscarPorId(Integer id) {
        return tipoEquipoBean.buscarPorId(id);
    }

    @Override
    public TipoEquipoDTO buscarPorNombre(String string) {
        return tipoEquipoBean.buscarPorNombre(string);
    }

    @Override
    public List<TipoEquipoDTO> obtenerTodosLosTiposEquipo() {
        return tipoEquipoBean.obtenerTodosLosTiposEquipo();
    }

    @Override
    public List<TipoEquipoDTO> obtenerTodosLosTipoEquipoActivos() {
        return tipoEquipoBean.obtenerTodosLosTipoEquipoActivos();
    }

    @Override
    public List<TipoEquipoDTO> obtenerTodosLoTipoEquipoEliminados() {
        return tipoEquipoBean.obtenerTodosLoTipoEquipoEliminados();
    }

    @Override
    public List<TipoEquipoDTO> obtenerTodosLosTipoEquipoPorTipo(String inputTipo) {
        return tipoEquipoBean.obtenerTodosLosTipoEquipoPorTipo(inputTipo);
    }

    @Override
    public void eliminarTipoIntervencion(Integer id) throws ServiciosException {
        tipoEquipoBean.eliminarTipoIntervencion(id);
    }

    @Override
    public void activarTipoEquipo(Integer id) throws ServiciosException {
        tipoEquipoBean.activarTipoEquipo(id);
    }
}
