package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import jakarta.validation.Valid;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import java.util.List;

@Remote
public interface UbicacionRemote {

    void agregarUbicacion(@Valid CrearUbicacionDTO ubicacion) throws ServiciosException;
    UbicacionDTO buscarPorId(Integer id);
    List<UbicacionDTO> obtenerTodasLasUbicaciones();
    void actualizarUbicacion(UbicacionDTO ubicacionDTO) throws ServiciosException;
    void bajarUbicacion(Integer id) throws ServiciosException;
    UbicacionDTO buscarUbicacionPorNombre(String nombre);
    List<UbicacionDTO> obtenerTodasLasUbicacionesActivas();
}