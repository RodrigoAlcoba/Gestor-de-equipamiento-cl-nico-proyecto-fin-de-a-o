package uy.edu.utec.controller;

import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.MenuInterfaz;
import uy.edu.utec.view.Interfaces.Principales.PanelPerfil;
import uy.edu.utec.view.Interfaces.Principales.PanelUsuario;

import java.sql.SQLException;


public class MenuController {

    private MenuInterfaz menuInterfaz;

    private PanelPerfil panelPerfil;

    private PanelUsuario panelUsuario;


    public MenuController(ControllerGeneral controllerGeneral) throws SQLException, ServiciosException {
        panelPerfil = new PanelPerfil(controllerGeneral);
        this.menuInterfaz = new MenuInterfaz(controllerGeneral);

    }

    public void mostrarMenu(boolean visible){
        menuInterfaz.setVisible(visible);

    }



}
