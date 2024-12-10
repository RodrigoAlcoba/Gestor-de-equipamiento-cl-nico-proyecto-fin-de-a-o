package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.TipoEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@LocalBean
public interface ITipoEquipoBean {
    void agregarTipoEquipo(TipoEquipoDTO tipoEquipo) throws ServiciosException;
    TipoEquipoDTO buscarPorId(Integer id);
    TipoEquipoDTO buscarPorNombre(String nombre);
    List<TipoEquipoDTO> obtenerTodosLosTiposEquipo();
    List<TipoEquipoDTO> obtenerTodosLosTipoEquipoActivos();
    List<TipoEquipoDTO> obtenerTodosLoTipoEquipoEliminados();
    List<TipoEquipoDTO> obtenerTodosLosTipoEquipoPorTipo(String inputTipo);
    void eliminarTipoIntervencion(Integer id) throws ServiciosException;
    void activarTipoEquipo(Integer id) throws ServiciosException;

}
