package uy.edu.utec.controller;

import uy.edu.utec.daos.MovEquipoDAOCli;
import uy.edu.utec.servers.dtos.MovEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearMovEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.Date;
import java.util.List;

public class MovEquipoController {
    private ControllerGeneral controllerGeneral;

    public MovEquipoController(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
    }

    public  MovEquipoDTO obtenerUbicacionPorIdEquipo(Integer id){
        return MovEquipoDAOCli.obtenerUbicacionPorIdEquipo(id);
    }

    public  MovEquipoDTO obtenerMovEquipoPorId(Integer id){
        return MovEquipoDAOCli.obtenerMovEquipoPorId(id);
    }
    public void crearMovEquipo(CrearMovEquipoDTO movEquipo) throws ServiciosException {
        MovEquipoDAOCli.crearMovEquipo(movEquipo);
    }
    public List<MovEquipoDTO> obtenerTodosMovDeEquipo(){
        return MovEquipoDAOCli.obtenerTodosMovDeEquipo();
    }
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoByEuipoId(Integer equipoId){
        return MovEquipoDAOCli.obtenerTodosMovDeEquipoByEuipoId(equipoId);
    }

    public MovEquipoDTO obtenerUbicacionActualByEquipoId(Integer idEquipo) {
        return MovEquipoDAOCli.obtenerUbicacionActualByEquipoId(idEquipo);
    }

    public  List<MovEquipoDTO> obtenerMovEquiposPorInstitucion(Integer institucionId){
        return MovEquipoDAOCli.obtenerMovEquiposPorInstitucion(institucionId);
    }

    public List<MovEquipoDTO> obtenerMovEquiposPorSector(Integer sectorId){
        return MovEquipoDAOCli.obtenerMovEquiposPorSector(sectorId);
    }

    public  List<MovEquipoDTO> obtenerMovEquiposPorUsuario(Integer usuarioId){
        return MovEquipoDAOCli.obtenerMovEquiposPorUsuario(usuarioId);
    }

    public List<MovEquipoDTO> obtenerMovEquiposPorFecDesde(Date fecDesde){
        return MovEquipoDAOCli.obtenerMovEquiposPorFecDesde(fecDesde);
    }

    public List<MovEquipoDTO> obtenerMovEquiposPorFecHasta(Date fecHasta){
        return MovEquipoDAOCli.obtenerMovEquiposPorFecHasta(fecHasta);
    }



}
