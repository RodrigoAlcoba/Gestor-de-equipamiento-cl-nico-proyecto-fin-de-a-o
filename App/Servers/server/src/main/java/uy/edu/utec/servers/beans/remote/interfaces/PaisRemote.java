package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import uy.edu.utec.servers.dtos.PaisDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Remote
public interface PaisRemote {
    void agregarPais(PaisDTO pais) throws ServiciosException;
    void activarPais(Integer id) throws ServiciosException;
    void bajaPais(Integer id) throws ServiciosException;
    PaisDTO buscarPorId(Integer id);
    List<PaisDTO> obtenerTodosLosPaises();
    List<PaisDTO> obtenerTodosLosPaisActivos();
    List<PaisDTO> obtenerTodosLosPaisesEliminados();
    List<PaisDTO> obtenerTodosLosPaisesPorNombre(String inputNombre);
    PaisDTO obtenerPaisPorNombre(String nombre);

}
