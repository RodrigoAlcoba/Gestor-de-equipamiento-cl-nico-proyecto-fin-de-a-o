package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IPaisBean;
import uy.edu.utec.servers.dao.PaisDAO;
import uy.edu.utec.servers.dtos.PaisDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.PaisMapper;

import java.util.List;

@Stateless
public class PaisBean implements IPaisBean {
    @EJB
    private PaisDAO paisDAO;

    @Override
    public void agregarPais(PaisDTO pais) throws ServiciosException {
        paisDAO.agregarPais(PaisMapper.toPais(pais));
    }

    @Override
    public void activarPais(Integer id) throws ServiciosException {
        paisDAO.activarPais(id);
    }

    @Override
    public void bajaPais(Integer id) throws ServiciosException {
        paisDAO.bajaPais(id);
    }

    @Override
    public PaisDTO buscarPorId(Integer id) {
        return PaisMapper.toDTO(paisDAO.buscarPorId(id));
    }

    @Override
    public List<PaisDTO> obtenerTodosLosPaises() {
        return PaisMapper.toDTOList(paisDAO.obtenerTodosLosPaises());
    }

    @Override
    public List<PaisDTO> obtenerTodosLosPaisActivos() {
        return PaisMapper.toDTOList(paisDAO.obtenerTodosLosPaisActivos());
    }

    @Override
    public List<PaisDTO> obtenerTodosLosPaisesEliminados() {
        return PaisMapper.toDTOList(paisDAO.obtenerTodosLosPaisesEliminados());
    }

    @Override
    public List<PaisDTO> obtenerTodosLosPaisesPorNombre(String inputNombre) {
        return PaisMapper.toDTOList(paisDAO.obtenerTodosLosPaisesPorNombre(inputNombre));
    }

    @Override
    public PaisDTO obtenerPaisPorNombre(String nombre) {
        return PaisMapper.toDTO(paisDAO.obtenerPaisPorNombre(nombre));
    }


}
