package uy.edu.utec.controller;

import uy.edu.utec.daos.ModeloDAOCli;
import uy.edu.utec.servers.dtos.ModeloDTO;
import uy.edu.utec.servers.entidades.Modelo;

import java.util.List;

public class ModeloController {

    private ControllerGeneral controllerGeneral;

    public ModeloController(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
    }

    public  List<ModeloDTO> obtenerTodosLosModelos(){
        return ModeloDAOCli.obtenerTodosLosModelos();
    }

    public ModeloDTO obtenerModeloPorNombre(String nombre){
        return ModeloDAOCli.obtenerModeloPorNombre(nombre);
    }

    public  ModeloDTO obtenerModeloPorId(Integer id){
        return ModeloDAOCli.obtenerModeloPorId(id);
    }

    public List<ModeloDTO> obtenerModelosPorNombre(String nombre){
        return ModeloDAOCli.obtenerModelosPorNombre(nombre);
    }

    public  List<ModeloDTO> obtenerTodosLosModelosActivos(){
        return ModeloDAOCli.obtenerTodosLosModelosActivos();
    }
}
