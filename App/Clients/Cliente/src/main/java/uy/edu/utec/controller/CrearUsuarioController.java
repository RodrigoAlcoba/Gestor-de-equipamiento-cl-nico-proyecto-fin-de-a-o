package uy.edu.utec.controller;


import uy.edu.utec.daos.UsuarioDAOCliNEW;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.Usuario.InterfazCrearUsuario;

public class CrearUsuarioController {

    private InterfazCrearUsuario interfazCrearUsuario;

    public CrearUsuarioController(ControllerGeneral controllerGeneral) {
        this.interfazCrearUsuario = new InterfazCrearUsuario(controllerGeneral);
    }

    //cambiar visibilidad interfaz
    public void mostrarCrearUsuario(boolean visible){
        interfazCrearUsuario.setVisible(visible);
    }

    //metodos
    public void crearUsuario(UsuarioDTO usuario) throws ServiciosException {
        UsuarioDAOCliNEW.agregarUsuario(usuario);
    }

}
