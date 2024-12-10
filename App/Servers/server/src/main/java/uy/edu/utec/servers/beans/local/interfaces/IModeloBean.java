package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.ModeloDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@LocalBean
public interface IModeloBean {
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
