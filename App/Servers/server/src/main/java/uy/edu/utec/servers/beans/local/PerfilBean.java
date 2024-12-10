package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IPerfilBean;
import uy.edu.utec.servers.dao.PerfilDAO;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearPerfilDTO;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.enums.EstadoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.PerfilMapper;
import uy.edu.utec.servers.entidades.Perfil;

import java.util.List;
@Stateful
public class PerfilBean implements IPerfilBean {
    @EJB
    private PerfilDAO perfilDAO;

    @Override
    public void agregarPerfil(CrearPerfilDTO perfil) throws ServiciosException {
        Perfil newPerfil = PerfilMapper.toEntityCreate(perfil);
        newPerfil.setEstado(EstadoGeneral.ACTIVO);
        perfilDAO.agregarPerfil(newPerfil);
        //perfilDAO.agregarPerfil(PerfilMapper.toEntity(perfil));
    }

    @Override
    public void activarPerfil(Integer id) throws ServiciosException {
        perfilDAO.activarPerfil(id);
    }

    @Override
    public PerfilDTO buscarPorId(Integer id) {
        return PerfilMapper.toDTO(perfilDAO.buscarPorId(id));
    }

    @Override
    public List<PerfilDTO> obtenerTodosLosPerfiles() {
        return PerfilMapper.toDTOList(perfilDAO.obtenerTodosLosPerfiles());
    }

    @Override
    public List<PerfilDTO> obtenerTodosLosPerfilesActivos() {
        return PerfilMapper.toDTOList(perfilDAO.obtenerTodosLosPerfilesActivos());
    }

    @Override
    public List<PerfilDTO> obtenerTodosLosPerfilesEliminados() {
        return PerfilMapper.toDTOList(perfilDAO.obtenerTodosLosPerfilesEliminados());
    }

    @Override
    public List<PerfilDTO> obtenerTodosLosPerfilesPorNombre(String nombre) {
        return PerfilMapper.toDTOList(perfilDAO.obtenerTodosLosPerfilesPorNombre(nombre));
    }

    @Override
    public void eliminarPerfilLogico(Integer id) throws ServiciosException {
        perfilDAO.eliminarPerfilLogico(id);
    }

    @Override
    public PerfilDTO obtenerPerfilPorNombre(String inputNombre) {
        return PerfilMapper.toDTO(perfilDAO.obtenerPerfilPorNombre(inputNombre));
    }


}
