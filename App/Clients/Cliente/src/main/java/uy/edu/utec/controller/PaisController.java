package uy.edu.utec.controller;

import uy.edu.utec.daos.PaisDAOCliente;
import uy.edu.utec.servers.dtos.PaisDTO;
import uy.edu.utec.servers.entidades.Pais;

import java.util.List;

public class PaisController {

    ControllerGeneral controllerGeneral;

    public PaisController(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
    }

    public List<PaisDTO> obtenerTodosLosPaisesActivos(){
        return PaisDAOCliente.obtenerTodosLosPaisesActivos();
    }

    public PaisDTO obtenerPaisPorNombre(String nombre){
        return PaisDAOCliente.obtenerPaisPorNombre(nombre);
    }

    public  PaisDTO obtenerPaisPorId(Integer id) {
        return PaisDAOCliente.obtenerPaisPorId(id);
    }

}
