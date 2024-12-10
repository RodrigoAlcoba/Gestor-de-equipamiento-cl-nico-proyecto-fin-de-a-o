package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import uy.edu.utec.servers.beans.local.interfaces.ITipoIntervencionBean;
import uy.edu.utec.servers.beans.remote.interfaces.TipoIntervencionRemote;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;
import java.util.Set;

@Stateless
public class TipoIntervencionImpl implements TipoIntervencionRemote {
    @EJB
    private ITipoIntervencionBean tipoIntervencionRemote;
    @Override
    public void agregarTipoIntervencion(TipoIntervencionDTO tipoIntervencion) throws ServiciosException {

        tipoIntervencionRemote.agregarTipoIntervencion(tipoIntervencion);
    }

    @Override
    public TipoIntervencionDTO buscarPorId(Integer id) {
        return tipoIntervencionRemote.buscarPorId(id);
    }

    @Override
    public List<TipoIntervencionDTO> obtenerTodosLosTiposIntervencion() {
        return tipoIntervencionRemote.obtenerTodosLosTiposIntervencion();
    }

    @Override
    public List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionActivos() {
        return tipoIntervencionRemote.obtenerTodosLosTipoIntervencionActivos();
    }

    @Override
    public List<TipoIntervencionDTO> obtenerTodosLoTipoIntervencionEliminados() {
        return tipoIntervencionRemote.obtenerTodosLoTipoIntervencionEliminados();
    }

    @Override
    public List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionPorTipo(String inputTipo) {
        return tipoIntervencionRemote.obtenerTodosLosTipoIntervencionPorTipo(inputTipo);
    }

    @Override
    public TipoIntervencionDTO obtenerTodosLosTipoIntervencionPorNombre(String nombre) {
        return tipoIntervencionRemote.obtenerTodosLosTipoIntervencionPorNombre(nombre);
    }
    @Override
    public void eliminarTipoIntervencion(Integer id) throws ServiciosException {
tipoIntervencionRemote.eliminarTipoIntervencion(id);
    }

    @Override
    public void activarTipoIntervencion(Integer id) throws ServiciosException {
tipoIntervencionRemote.activarTipoIntervencion(id);
    }

    @Override
    public TipoIntervencionDTO obtenerPorNombre(String nombre) {
        return tipoIntervencionRemote.obtenerPorNombre(nombre);
    }
}
