package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IPaisBean;
import uy.edu.utec.servers.beans.remote.interfaces.PaisRemote;
import uy.edu.utec.servers.dtos.PaisDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class PaisRemoteImpl implements PaisRemote {

    @EJB
    IPaisBean paisBean;
    @Override
    public void agregarPais(PaisDTO pais) throws ServiciosException {
        paisBean.agregarPais(pais);
    }

    @Override
    public void activarPais(Integer id) throws ServiciosException {
        paisBean.activarPais(id);
    }

    @Override
    public void bajaPais(Integer id) throws ServiciosException {
        paisBean.bajaPais(id);
    }

    @Override
    public PaisDTO buscarPorId(Integer id) {
        return paisBean.buscarPorId(id);
    }

    @Override
    public List<PaisDTO> obtenerTodosLosPaises() {
        return paisBean.obtenerTodosLosPaises();
    }

    @Override
    public List<PaisDTO> obtenerTodosLosPaisActivos() {
        return paisBean.obtenerTodosLosPaisActivos();
    }

    @Override
    public List<PaisDTO> obtenerTodosLosPaisesEliminados() {
        return paisBean.obtenerTodosLosPaisesEliminados();
    }

    @Override
    public List<PaisDTO> obtenerTodosLosPaisesPorNombre(String inputNombre) {
        return paisBean.obtenerTodosLosPaisesPorNombre(inputNombre);
    }

    @Override
    public PaisDTO obtenerPaisPorNombre(String nombre) {
        return paisBean.obtenerPaisPorNombre(nombre);
    }
}
