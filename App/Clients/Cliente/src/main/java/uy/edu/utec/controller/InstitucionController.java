package uy.edu.utec.controller;


import uy.edu.utec.daos.InstitucionDAOCliNEW;
import uy.edu.utec.servers.dtos.InstitucionDTO;

import java.util.List;

public class InstitucionController {
    private ControllerGeneral controllerGeneral;

    public InstitucionController(ControllerGeneral controllerGeneral){
        this.controllerGeneral = controllerGeneral;
    }

   public List<InstitucionDTO> mostrarTodasInstituciones(){
      return InstitucionDAOCliNEW.obtenerInstituciones();
   }

   public  InstitucionDTO obtenerInstitucionPorNombre(String nombre){
        return InstitucionDAOCliNEW.obtenerInstitucionPorNombre(nombre);
   }

    public InstitucionDTO obtenerInstitucionPorId(Integer id){
        return  InstitucionDAOCliNEW.obtenerInstitucionPorId(id);
    }
}
