package uy.edu.utec.controller;

import uy.edu.utec.daos.ProveedorDAOCli;
import uy.edu.utec.daos.TipoEquipoDAOCli;
import uy.edu.utec.servers.dtos.ProveedorDTO;

import java.util.List;

public class ProveedorController {

    private ControllerGeneral controllerGeneral;

    public ProveedorController(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
    }

    public  List<ProveedorDTO> obtenerTodosLosProveedoresActivos(){
        return ProveedorDAOCli.obtenerTodosLosProveedoresActivos();
    }

    public  ProveedorDTO obtenerProveedorPorNombre(String nombre){
        return ProveedorDAOCli.obtenerProveedorPorNombre(nombre);
    }

    public  ProveedorDTO obtenerProveedorPorId(Integer id) {
        return ProveedorDAOCli.obtenerProveedorPorId(id);
    }
}
