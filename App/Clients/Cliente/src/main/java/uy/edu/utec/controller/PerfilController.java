package uy.edu.utec.controller;

import uy.edu.utec.daos.PerfilDAOCliNEW;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearPerfilDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

public class PerfilController {
    private ControllerGeneral controllerGeneral;

    public PerfilController(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
    }

    public void agregarPerfil(CrearPerfilDTO perfilDTO) throws ServiciosException {
        PerfilDAOCliNEW.agregarPerfil(perfilDTO);
    }

    public List<PerfilDTO> obtenerPerfilesActivos(){
        return PerfilDAOCliNEW.obtenerPerfilesActivos();
    }

    public List<PerfilDTO> obtenerTodosPerfiles(){
        return PerfilDAOCliNEW.obtenerTodosPerfiles();
    }

    public PerfilDTO obtenerPerfilPorNombre(String nombre){
       return PerfilDAOCliNEW.obtenerPerfilPorNombre(nombre);
    }

    public void bajaPerfil(Integer id) throws ServiciosException {
        PerfilDAOCliNEW.bajaPerfil(id);
    }

    public List<PerfilDTO> obtenerTodosLosPerfilesPorNombre(String nombre){
       return PerfilDAOCliNEW.obtenerPerfilesPorNombre(nombre);
    }

    public void activarPerfil(Integer id) throws ServiciosException {
        PerfilDAOCliNEW.reactivarPerfil(id);
    }

    public List<PerfilDTO> obtenerPerfilesEliminados(){
        return PerfilDAOCliNEW.obtenerPerfilesEliminados();
    }

    public PerfilDTO obtenerPerfilPorId(Integer idPerfil) throws ServiciosException{
        return PerfilDAOCliNEW.obtenerPerfilPorId(idPerfil);
    }


}
