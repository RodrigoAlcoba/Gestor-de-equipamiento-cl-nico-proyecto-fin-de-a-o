package uy.edu.utec.controller;

import uy.edu.utec.daos.SeguimientoIntervencionDAOCli;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.Principales.PanelUsuario;

import java.util.List;

public class SeguimientoIntervencionController {
    private ControllerGeneral controllerGeneral;

    public SeguimientoIntervencionController(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
    }

    public void agregarSeguimientoIntervencion(SeguimientoIntervencionDTO seguimiento) throws ServiciosException {
        SeguimientoIntervencionDAOCli.agregarSeguimientoIntervencion(seguimiento);
    }

    public List<SeguimientoIntervencionDTO> obtenerTodosLosSeguimientosDeUnaIntervencionPorID(Integer intervencionId){
       return SeguimientoIntervencionDAOCli.obtenerTodosLosSeguimientosDeUnaIntervencionPorID(intervencionId);
    }
}
