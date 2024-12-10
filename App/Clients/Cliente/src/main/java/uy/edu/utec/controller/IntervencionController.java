package uy.edu.utec.controller;

import uy.edu.utec.daos.IntervencionDAOCli;
import uy.edu.utec.daos.UbicacionDAOCli;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.time.LocalDate;
import java.util.List;

public class IntervencionController {

    private ControllerGeneral controllerGeneral;

    public IntervencionController(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
    }

    public void agregarIntervencion(IntervencionDTO intervencion) throws ServiciosException {
        IntervencionDAOCli.agregarIntervencion(intervencion);
    }
    public List<IntervencionDTO> obtenerIntervencionesPorTipoYFechas(Integer tipoIntervencion, LocalDate fechaDesde, LocalDate fechaHasta) {
        return IntervencionDAOCli.obtenerIntervencionesPorTipoYFechas(tipoIntervencion, fechaDesde, fechaHasta);
    }
    public List<IntervencionDTO> obtenerTodasLasIntervenciones(){
        return IntervencionDAOCli.obtenerTodasLasIntervenciones();
    }
    public List<IntervencionDTO> obtenerIntervencionesPorEquipoID(Integer equipoID, LocalDate fechaDesde, LocalDate fechaHasta){
        return IntervencionDAOCli.obtenerIntervencionesPorEquipoID(equipoID, fechaDesde, fechaHasta);
    }
    public IntervencionDTO buscarPorId(Integer id){
        return IntervencionDAOCli.buscarPorId(id);
    }

    public  List<IntervencionDTO> obtenerIntervencionesPorTipo(String tipoIntervencion){
        return IntervencionDAOCli.obtenerIntervencionesPorTipo(tipoIntervencion);
    }
}
