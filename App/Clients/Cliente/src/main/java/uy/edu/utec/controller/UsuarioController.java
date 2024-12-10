package uy.edu.utec.controller;

import uy.edu.utec.daos.UsuarioDAOCliNEW;
import uy.edu.utec.servers.dtos.LoginDTO;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;
import java.util.Objects;

public class UsuarioController {
    public static String nombreUsuarioActivo;

    public static String perfilUsuarioActivo;
    public static UsuarioDTO usuarioActivo;

    private ControllerGeneral controllerGeneral;

    public UsuarioController(ControllerGeneral controllerGeneral) {
        this.controllerGeneral = controllerGeneral;
    }

    //baja------
    public void bajaUsuarioLogica(Integer id) throws ServiciosException {
        UsuarioDAOCliNEW.bajaUsuarioLogica(id);
    }

    //activar------
    public void activarUsuario(Integer id) throws ServiciosException {
        UsuarioDAOCliNEW.activarUsuario(id);
    }
    //listados---------
    public List<UsuarioDTO> obtenerTodosLosUsuarios(){
        return  UsuarioDAOCliNEW.obtenerTodosLosUsuarios();
    }

    public List<UsuarioDTO> obtenerUsuariosActivos(){
        return UsuarioDAOCliNEW.obtenerTodosLosUsuariosActivos();
    }

    public List<UsuarioDTO> obtenerUsuariosEliminados(){
        return UsuarioDAOCliNEW.obtenerTodosLosUsuariosEliminados();
    }

    public List<UsuarioDTO> obtenerUsuariosSinValidar(){
        return UsuarioDAOCliNEW.obtenerTodosLosUsuariosSinValidar();
    }

    public  List<UsuarioDTO> getUsuariosSinValidarYEliminados(){
        return UsuarioDAOCliNEW.getUsuariosSinValidarYEliminados();
    }


    //buscar----------
    public  UsuarioDTO buscarPorEmail(String email){
        return UsuarioDAOCliNEW.buscarPorEmail(email);
    }

    //actualizar------
    public void actualizarUsuario(UsuarioDTO usuario) throws ServiciosException {
         UsuarioDAOCliNEW.actualizarUsuario(usuario);
    }

    //login------
    public boolean verificarLogIn(LoginDTO loginDTO) throws ServiciosException {
        if (UsuarioDAOCliNEW.verificarLogin(loginDTO) == null){
            return false;
        }else{
            System.out.println(loginDTO.getEmail());
            setNombreUsuarioActivo(buscarPorEmail(loginDTO.getEmail()).getNom_usuario());
            return true;
        }
    }

    //obtenerUsuarioActivo


    public static String getnombreUsuarioActivo() {
        //System.out.println(emailUsuarioActivo);
        return nombreUsuarioActivo;
    }

    public UsuarioDTO getUsuarioActivo(){
        return obtenerPorNombreUsuario(nombreUsuarioActivo);
    }

    public void setPerfilUsuarioActivo() throws ServiciosException {
        this.perfilUsuarioActivo =
                controllerGeneral.getPerfilController().obtenerPerfilPorId(obtenerPorNombreUsuario(UsuarioController.getnombreUsuarioActivo()).getPerfilId()).getNomPerfil();
        System.out.println(perfilUsuarioActivo + "seteo");
    }


    public void setNombreUsuarioActivo(String nombreUsuarioActivo) {
        this.nombreUsuarioActivo = nombreUsuarioActivo;
    }

    //para los listados por filtro

    public List<UsuarioDTO> obtenerTodosLosUsuariosPorNombre1(String inputNombre1){
       return  UsuarioDAOCliNEW.obtenerTodosLosUsuariosPorNombre1(inputNombre1);
    }

    public List<UsuarioDTO> obtenerTodosLosUsuariosPorApellido1(String inputApellido1){
        return  UsuarioDAOCliNEW.obtenerTodosLosUsuariosPorApellido1(inputApellido1);
    }

    public List<UsuarioDTO> obtenerTodosLosUsuariosPorEmails(String inputEmail){
        return  UsuarioDAOCliNEW.obtenerTodosLosUsuariosPorEmails(inputEmail);
    }

    public  List<UsuarioDTO> obtenerTodosLosUsuariosPorNombreUsuario(String inputNombreUsuario){
        return UsuarioDAOCliNEW.obtenerTodosLosUsuariosPorNombreUsuario(inputNombreUsuario);
    }

    public UsuarioDTO obtenerPorNombreUsuario(String inputUsuario){
        return UsuarioDAOCliNEW.buscarPorNombreUsuario(inputUsuario);
    }

    public  List<UsuarioDTO> buscarPorPerfi(Integer idPerfil){
        return UsuarioDAOCliNEW.buscarPorPerfi(idPerfil);
    }


   public UsuarioDTO buscarPorId(Integer id){
        return UsuarioDAOCliNEW.buscarPorId(id);
   }

    public UsuarioDTO obtenerUsuarioPorCedula(String cedula){
        return UsuarioDAOCliNEW.obtenerUsuarioPorCedula(cedula);
    }
}
