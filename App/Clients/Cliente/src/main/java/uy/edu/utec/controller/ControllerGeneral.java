package uy.edu.utec.controller;

//import uy.edu.utec.controller.LoginController;

import uy.edu.utec.servers.excepciones.ServiciosException;

import uy.edu.utec.daos.TipoEquipoDAOCli;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.view.Interfaces.MenuInterfaz;

import javax.naming.NamingException;
import java.sql.SQLException;

public class ControllerGeneral {

    //Controladores
    private LoginController loginController;

    private CrearUsuarioController crearUsuarioController;

    private MenuController menuController;

    private EquipoController equipoController;

    private InstitucionController institucionController;

    private PerfilController perfilController;

    private UsuarioController usuarioController = new UsuarioController(this);

    private UbicacionController ubicacionController;
    private SectorController sectorController;
    private IntervencionController intervencionController;

    private TipoIntervencionController tipoIntervencionController;

    private TipoEquipoController tipoEquipoController;
    private ProveedorController proveedorController;

    private ModeloController modeloController;

    private MarcaController marcaController;

    private PaisController paisController;

    private MenuInterfaz menuInterfaz;

    private BajaEquipoController bajaEquipoController;

    private MovEquipoController movEquipoController;


    //Singleton
    private static ControllerGeneral instance;

    static {
        try {
            instance = new ControllerGeneral();
        } catch (NamingException | SQLException | ServiciosException e) {
            throw new RuntimeException(e);
        }
    }

    public static ControllerGeneral getInstance(){
        return instance;
    }
    private ControllerGeneral() throws NamingException, SQLException, ServiciosException {
        this.loginController = new LoginController(this);
        this.crearUsuarioController = new CrearUsuarioController(this);
        this.menuController = new MenuController(this);
        this.equipoController = new EquipoController(this);
        this.institucionController = new InstitucionController(this);
        this.perfilController = new PerfilController(this);
        this.usuarioController = new UsuarioController(this);
        this.ubicacionController = new UbicacionController(this);
        this.sectorController = new SectorController(this);
        this.tipoEquipoController = new TipoEquipoController(this);
        this.proveedorController = new ProveedorController(this);
        this.proveedorController = new ProveedorController(this);
        this.marcaController = new MarcaController(this);
        this.intervencionController = new IntervencionController(this);
        this.tipoIntervencionController = new TipoIntervencionController(this);
        this.paisController = new PaisController(this);
        this.bajaEquipoController = new BajaEquipoController(this);
        this.menuInterfaz = new MenuInterfaz(this);
        this.movEquipoController = new MovEquipoController(this);
    }


    //obtener controladores

    public LoginController getLoginController() {
        return loginController;
    }

    public CrearUsuarioController getCrearUsuarioController() {
        return crearUsuarioController;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public EquipoController getControllerEquipo(){
        return equipoController;
    }

    public InstitucionController getInstitucionController(){
    return institucionController;
    }

    public  PerfilController getPerfilController(){
        return perfilController;
    }
    public UsuarioController getUsuarioController(){
        return usuarioController;
    }

    public UbicacionController getUbicacionController(){
        return ubicacionController;
    }
    public SectorController getSectorController() {return sectorController;}

    public TipoEquipoController tipoEquipoController(){
        return tipoEquipoController;
    }
    public ProveedorController getProveedorController(){
        return proveedorController;
    }

    public ModeloController getModeloController() {
        return modeloController;
    }

    public MarcaController getMarcaController() {
        return marcaController;
    }
    public TipoIntervencionController getTipoIntervencionController() {
        return tipoIntervencionController;
    }

    public EquipoController getEquipoController() {
        return equipoController;
    }

    public IntervencionController getIntervencionController() {
        return intervencionController;
    }

    public PaisController getPaisController(){
        return paisController;
    }
    public BajaEquipoController getBajaEquipoController(){return bajaEquipoController;}

    public MenuInterfaz getMenuInterfaz(){
        return menuInterfaz;
    }
    public MovEquipoController movEquipoController(){
        return movEquipoController;
    }

    //Mostrar vistas

    public void mostrarLogin(boolean visible){
        loginController.mostrarLogin(visible);
    }

    public void mostrarCrearUsuario(boolean visible){
        crearUsuarioController.mostrarCrearUsuario(visible);
    }

    public void mostrarMenuInterfaz(boolean visible){
        menuController.mostrarMenu(visible);
    }
}

