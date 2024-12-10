package uy.edu.utec.controller;

import uy.edu.utec.daos.UsuarioDAOCliNEW;
import uy.edu.utec.view.Interfaces.Usuario.LoginInterfaz;

import javax.naming.NamingException;

public class LoginController {
    private UsuarioDAOCliNEW usuarioDAOCli;
    private String email;
    private  String contra;

    private LoginInterfaz loginInterfazView;
    public LoginController(ControllerGeneral controllerGeneral) throws NamingException {
        usuarioDAOCli = new UsuarioDAOCliNEW();
        this.loginInterfazView = new LoginInterfaz(controllerGeneral);
    }
    public void mostrarLogin(boolean visible){
        loginInterfazView.setVisible(visible);
    }




}
