package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Remote
public interface TipoIntervencionRemote {
    void agregarTipoIntervencion(TipoIntervencionDTO tipoIntervencion) throws ServiciosException;
    TipoIntervencionDTO buscarPorId(Integer id);
    List<TipoIntervencionDTO> obtenerTodosLosTiposIntervencion();
    List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionActivos();
    List<TipoIntervencionDTO> obtenerTodosLoTipoIntervencionEliminados();
    List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionPorTipo(String inputTipo);
    TipoIntervencionDTO obtenerTodosLosTipoIntervencionPorNombre(String nombre);
    void eliminarTipoIntervencion(Integer id) throws ServiciosException;
    void activarTipoIntervencion(Integer id) throws ServiciosException;

    TipoIntervencionDTO obtenerPorNombre(String nombre);
}
