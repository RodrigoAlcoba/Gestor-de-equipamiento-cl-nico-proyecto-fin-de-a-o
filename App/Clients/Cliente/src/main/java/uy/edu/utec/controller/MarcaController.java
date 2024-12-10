package uy.edu.utec.controller;

import uy.edu.utec.daos.MarcaDAOCli;
import uy.edu.utec.servers.dao.MarcaDAO;
import uy.edu.utec.servers.dtos.MarcaDTO;
import uy.edu.utec.servers.entidades.Marca;

import java.util.List;

public class MarcaController {

    private  ControllerGeneral controllerGeneral;

    public MarcaController(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
    }


    public  List<MarcaDTO> obtenerTodasLasMarcas(){
        return MarcaDAOCli.obtenerTodasLasMarcas();
    }

    public MarcaDTO obtenerMarcaPorNombre(String nombre){
        return MarcaDAOCli.obtenerMarcaPorNombre(nombre);
    }

    public  MarcaDTO obtenerMarcaPorId(Integer id){
        return MarcaDAOCli.obtenerMarcaPorId(id);
    }

    public List<MarcaDTO> obtenerTodasLasMarcasActivas(){return MarcaDAOCli.obtenerTodasLasMarcasActivas();}

}
