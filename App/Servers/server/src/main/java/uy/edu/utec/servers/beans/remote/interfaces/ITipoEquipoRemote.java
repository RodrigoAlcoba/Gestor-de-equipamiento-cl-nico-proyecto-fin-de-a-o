package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import uy.edu.utec.servers.dtos.TipoEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Remote
public interface ITipoEquipoRemote {
    void agregarTipoEquipo(TipoEquipoDTO tipoEquipo) throws ServiciosException;
    TipoEquipoDTO buscarPorId(Integer id);
    TipoEquipoDTO buscarPorNombre(String string);
    List<TipoEquipoDTO> obtenerTodosLosTiposEquipo();
    List<TipoEquipoDTO> obtenerTodosLosTipoEquipoActivos();
    List<TipoEquipoDTO> obtenerTodosLoTipoEquipoEliminados();
    List<TipoEquipoDTO> obtenerTodosLosTipoEquipoPorTipo(String inputTipo);
    void eliminarTipoIntervencion(Integer id) throws ServiciosException;
    void activarTipoEquipo(Integer id) throws ServiciosException;

}
