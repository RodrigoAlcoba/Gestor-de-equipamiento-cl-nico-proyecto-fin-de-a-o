package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IModeloBean;
import uy.edu.utec.servers.dao.ModeloDAO;
import uy.edu.utec.servers.dtos.ModeloDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.ModeloMapper;

import java.util.List;
@Stateless
public class ModeloBean implements IModeloBean {
    @EJB
    ModeloDAO modeloDAO;

    @Override
    public void agregarModelo(ModeloDTO modelo) throws ServiciosException {
        modeloDAO.agregarModelo(ModeloMapper.toEntity(modelo));
    }

    @Override
    public void activarModelo(Integer id) throws ServiciosException {
        modeloDAO.activarModelo(id);
    }

    @Override
    public void bajaModelo(Integer id) throws ServiciosException {
        modeloDAO.bajaModelo(id);
    }

    @Override
    public ModeloDTO buscarPorId(Integer id) {
        System.out.println("Id: bl" + id);
        return ModeloMapper.toDTO(modeloDAO.buscarPorId(id));
    }

    @Override
    public ModeloDTO buscarModeloPorNombre(String nombre) {
        return ModeloMapper.toDTO(modeloDAO.obtenerModeloPorNombre(nombre));
    }

    @Override
    public List<ModeloDTO> obtenerTodosLosModelos() {
        return ModeloMapper.toDTOList(modeloDAO.obtenerTodosLosModelos());
    }

    @Override
    public List<ModeloDTO> obtenerTodosLosModelosPorNombre(String inputNombre) {
        return ModeloMapper.toDTOList(modeloDAO.obtenerTodosLosModelosPorNombre(inputNombre));
    }

    @Override
    public List<ModeloDTO> obtenerTodosLosModelosActivos() {
        return ModeloMapper.toDTOList(modeloDAO.obtenerTodosLosModelosActivos());
    }

    @Override
    public List<ModeloDTO> obtenerTodosLosModelosEliminados() {
        return ModeloMapper.toDTOList(modeloDAO.obtenerTodosLosModelosEliminados());
    }
}
