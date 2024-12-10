package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearPerfilDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Remote
public interface PerfilRemote {

    void agregarPerfil(CrearPerfilDTO perfil) throws ServiciosException;

    void activarPerfil(Integer id)throws ServiciosException;

    PerfilDTO buscarPorId(Integer id);

    List<PerfilDTO> obtenerTodosLosPerfiles();

    List<PerfilDTO> obtenerTodosLosPerfilesActivos();

    List<PerfilDTO> obtenerTodosLosPerfilesEliminados();

    List<PerfilDTO> obtenerTodosLosPerfilesPorNombre(String nombre);
    void eliminarPerfilLogico(Integer id) throws ServiciosException;

    PerfilDTO obtenerPerfilPorNombre(String nombre);
}
