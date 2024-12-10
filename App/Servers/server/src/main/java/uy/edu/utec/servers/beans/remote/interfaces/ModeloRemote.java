package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import uy.edu.utec.servers.dtos.ModeloDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Remote
public interface ModeloRemote {
    void agregarModelo(ModeloDTO modelo) throws ServiciosException;
    void activarModelo(Integer id) throws ServiciosException;
    void bajaModelo(Integer id) throws ServiciosException;
    ModeloDTO buscarPorId(Integer id);
    ModeloDTO buscarModeloPorNombre(String nombre);
    List<ModeloDTO> obtenerTodosLosModelos();
    List<ModeloDTO> obtenerTodosLosModelosPorNombre(String inputNombre);
    List<ModeloDTO> obtenerTodosLosModelosActivos();
    List<ModeloDTO> obtenerTodosLosModelosEliminados();
}
