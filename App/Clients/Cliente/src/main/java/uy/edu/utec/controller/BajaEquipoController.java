package uy.edu.utec.controller;

import uy.edu.utec.daos.BajaEquipoDAOCli;
import uy.edu.utec.daos.EquipoDAOCli;
import uy.edu.utec.servers.dtos.BajaUsuarioEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.sql.SQLException;

public class BajaEquipoController {

    private ControllerGeneral controllerGeneral;

    public BajaEquipoController(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
    }

    public void crearBajaEquipo(BajaUsuarioEquipoDTO bajaUsuarioEquipoDTO) throws ServiciosException, SQLException { //
        BajaEquipoDAOCli.crearBajaEquipo(bajaUsuarioEquipoDTO);
    }
}
