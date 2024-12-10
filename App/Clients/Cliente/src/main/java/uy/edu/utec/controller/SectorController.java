package uy.edu.utec.controller;

import uy.edu.utec.daos.SectorDAOCli;
import uy.edu.utec.daos.UbicacionDAOCli;
import uy.edu.utec.servers.dtos.SectorDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;


public class SectorController {
        private ControllerGeneral controllerGeneral;

        public SectorController(ControllerGeneral controllerGeneral){
            this.controllerGeneral = controllerGeneral;
        }
    public SectorDTO buscarPorId(Integer id) {
        return SectorDAOCli.buscarPorId(id);
    }


    public List<SectorDTO> obtenerTodosLosSectores() {
        return SectorDAOCli.obtenerTodosLosSectores();
    }
public SectorDTO obtenerSectorPorNombre(String nombre){
            return SectorDAOCli.buscarPorNombre(nombre);
}


    }

