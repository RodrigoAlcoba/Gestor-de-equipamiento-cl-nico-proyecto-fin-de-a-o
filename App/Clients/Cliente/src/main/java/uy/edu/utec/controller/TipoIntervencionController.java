package uy.edu.utec.controller;

import uy.edu.utec.daos.TipoIntervencionDAOCli;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

public class TipoIntervencionController {

    private ControllerGeneral controllerGeneral;

    public TipoIntervencionController(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
    }

    public void agregarTipoIntervencion(TipoIntervencionDTO intervencion) throws ServiciosException {
        TipoIntervencionDAOCli.agregarTipoIntervencion(intervencion);
    }
    public List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionActivos(){
        return TipoIntervencionDAOCli.obtenerTodosLosTiposIntervencionActivos();
    }

    public TipoIntervencionDTO obtenerTodosLosTipoIntervencionPorNombre(String nombre){
        return TipoIntervencionDAOCli.obtenerTodosLosTipoIntervencionPorNombre(nombre);
    }
    public TipoIntervencionDTO buscarPorId(Integer id){
        return TipoIntervencionDAOCli.buscarPorId(id);
    }

    public void eliminarTipoIntervencion(Integer id) throws ServiciosException {
        TipoIntervencionDAOCli.eliminarTipoIntervencion(id);
    }
    public void activarTipoIntervencion(Integer id) throws ServiciosException {
        TipoIntervencionDAOCli.activarTipoIntervencion(id);
    }
    public List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionPorTipo(String inputTipo){
        return TipoIntervencionDAOCli.obtenerTodosLosTipoIntervencionPorTipo(inputTipo);
    }
    public List<TipoIntervencionDTO> obtenerTodosLoTipoIntervencionEliminados(){
        return TipoIntervencionDAOCli.obtenerTodosLoTipoIntervencionEliminados();
    }
    public List<TipoIntervencionDTO> obtenerTodosLosTiposIntervencion(){
        return TipoIntervencionDAOCli.obtenerTodosLosTiposIntervencion();
    }
}
