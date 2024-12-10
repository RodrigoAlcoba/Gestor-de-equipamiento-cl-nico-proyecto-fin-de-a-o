package uy.edu.utec.controller;

import uy.edu.utec.daos.EquipoDAOCli;
import uy.edu.utec.daos.TipoEquipoDAOCli;
import uy.edu.utec.servers.dtos.TipoEquipoDTO;

import java.util.List;

public class TipoEquipoController {

    private ControllerGeneral controllerGeneral;

    public TipoEquipoController(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
    }

    public List<TipoEquipoDTO> obtenerTipoEquiposActivos(){
        return TipoEquipoDAOCli.obtenerTipoEquiposActivos();
    };
    public TipoEquipoDTO obtenerTipoEquipoPorNombre(String nombre){
        return TipoEquipoDAOCli.obtenerTipoEquipoPorNombre(nombre);
    }
    public TipoEquipoDTO obtenerTipoEquipoPorId(Integer id) {
        return TipoEquipoDAOCli.obtenerTipoEquipoPorId(id);
    }

}
