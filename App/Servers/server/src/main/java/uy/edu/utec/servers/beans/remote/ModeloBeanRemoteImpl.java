package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IModeloBean;
import uy.edu.utec.servers.beans.remote.interfaces.ModeloRemote;
import uy.edu.utec.servers.dtos.ModeloDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class ModeloBeanRemoteImpl implements ModeloRemote {
    @EJB
    IModeloBean modeloBean;


    @Override
    public void agregarModelo(ModeloDTO modelo) throws ServiciosException {
        modeloBean.agregarModelo(modelo);
    }

    @Override
    public void activarModelo(Integer id) throws ServiciosException {
        modeloBean.activarModelo(id);
    }

    @Override
    public void bajaModelo(Integer id) throws ServiciosException {
        modeloBean.bajaModelo(id);
    }

    @Override
    public ModeloDTO buscarPorId(Integer id) {
        return modeloBean.buscarPorId(id);
    }

    @Override
    public ModeloDTO buscarModeloPorNombre(String nombre) {
        return modeloBean.buscarModeloPorNombre(nombre);
    }

    @Override
    public List<ModeloDTO> obtenerTodosLosModelos() {
        return modeloBean.obtenerTodosLosModelos();
    }

    @Override
    public List<ModeloDTO> obtenerTodosLosModelosPorNombre(String inputNombre) {
        return modeloBean.obtenerTodosLosModelosPorNombre(inputNombre);
    }

    @Override
    public List<ModeloDTO> obtenerTodosLosModelosActivos() {
        return modeloBean.obtenerTodosLosModelosActivos();
    }

    @Override
    public List<ModeloDTO> obtenerTodosLosModelosEliminados() {
        return modeloBean.obtenerTodosLosModelosEliminados();
    }
}
