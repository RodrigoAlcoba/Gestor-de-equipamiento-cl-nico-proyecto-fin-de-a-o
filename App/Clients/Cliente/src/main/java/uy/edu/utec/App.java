package uy.edu.utec;


import uy.edu.utec.controller.ControllerGeneral;
import uy.edu.utec.servers.dao.UsuarioDAOnew;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.NamingException;

public class App {
    public static void main(String[] args) throws NamingException, ServiciosException {
        //LoginConsoleController loginConsole = new LoginConsoleController();
        //loginConsole.login();
       UsuarioDAOnew daoCli = new UsuarioDAOnew();
       // InstitucionDAOCli institucionDAOCli =new InstitucionDAOCli();
       // EquipoDAOCli equipoDAOCli = new EquipoDAOCli();
        //TipoIntervencionDAOCli tipoIntervencionDAOCli = new TipoIntervencionDAOCli();

        ControllerGeneral controllerGeneral = ControllerGeneral.getInstance();
        controllerGeneral.mostrarLogin(true);
    }

    //Administrador
    //Auxiliar administrador
    //Ingeniero Biomédico
    //Técnico / Tecnólogo
    public static void initFunction() throws ServiciosException {

        //PerfilDAOCli.agregarPerfil(new Perfil());
    }
}
