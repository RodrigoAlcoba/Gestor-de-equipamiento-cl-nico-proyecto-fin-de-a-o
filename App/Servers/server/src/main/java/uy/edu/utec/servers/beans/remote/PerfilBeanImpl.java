package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import uy.edu.utec.servers.beans.local.interfaces.IPerfilBean;
import uy.edu.utec.servers.beans.remote.interfaces.PerfilRemote;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearPerfilDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;
import java.util.Set;

@Stateless
public class PerfilBeanImpl implements PerfilRemote {

    @Inject
    private Validator validator;

    @EJB
    private IPerfilBean perfilBean;

    @Override
    public void agregarPerfil(CrearPerfilDTO perfil) throws ServiciosException {
        Set<ConstraintViolation<CrearPerfilDTO>> violations = validator.validate(perfil);
        String errores = "";
        for(ConstraintViolation<CrearPerfilDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);
        perfilBean.agregarPerfil(perfil);
    }

    @Override
    public void activarPerfil(Integer id) throws ServiciosException {
        perfilBean.activarPerfil(id);
    }

    @Override
    public PerfilDTO buscarPorId(Integer id) {
        return perfilBean.buscarPorId(id);
    }

    @Override
    public List<PerfilDTO> obtenerTodosLosPerfiles() {
        return perfilBean.obtenerTodosLosPerfiles();
    }

    @Override
    public List<PerfilDTO> obtenerTodosLosPerfilesActivos() {
        return perfilBean.obtenerTodosLosPerfilesActivos();
    }

    @Override
    public List<PerfilDTO> obtenerTodosLosPerfilesEliminados() {
        return perfilBean.obtenerTodosLosPerfilesEliminados();
    }

    @Override
    public List<PerfilDTO> obtenerTodosLosPerfilesPorNombre(String nombre) {
        return perfilBean.obtenerTodosLosPerfilesPorNombre(nombre);
    }

    @Override
    public void eliminarPerfilLogico(Integer id) throws ServiciosException {
        perfilBean.eliminarPerfilLogico(id);
    }

    @Override
    public PerfilDTO obtenerPerfilPorNombre(String nombre) {
       return perfilBean.obtenerPerfilPorNombre(nombre);
    }
}
