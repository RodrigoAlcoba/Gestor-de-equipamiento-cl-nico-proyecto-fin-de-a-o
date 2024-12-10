package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.entidades.Ubicacion;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.sql.SQLException;
import java.util.List;

@LocalBean
public interface IUbicacionBean {
    void agregarUbicacion(CrearUbicacionDTO ubicacion) throws ServiciosException;
    UbicacionDTO buscarPorId(Integer id);
    List<UbicacionDTO> obtenerTodasLasUbicaciones();
    void actualizarUbicacion(UbicacionDTO ubicacionDTO) throws ServiciosException;

    void bajarUbicacion(Integer id) throws ServiciosException;
    UbicacionDTO buscarUbicacionPorNombre(String nombre);
    List<UbicacionDTO> obtenerTodasLasUbicacionesActivas();

}
