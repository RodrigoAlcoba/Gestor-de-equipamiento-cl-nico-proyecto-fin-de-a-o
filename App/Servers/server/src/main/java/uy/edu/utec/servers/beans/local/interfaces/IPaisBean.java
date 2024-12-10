package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.PaisDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@LocalBean
public interface IPaisBean {
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
