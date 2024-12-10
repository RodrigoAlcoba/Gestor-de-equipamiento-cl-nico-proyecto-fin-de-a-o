package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.IUsuarioRemoteNew;
import uy.edu.utec.servers.dtos.LoginDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.dtos.creacionales.CreateUsuarioDTO;
import uy.edu.utec.servers.entidades.Usuario;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.NamingException;
import java.util.List;

public class UsuarioDAOCliNEW {
    public static IUsuarioRemoteNew usuarioRemote;


    static {
        EJBClientUtility.init();
        try {
            usuarioRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "UsuarioRemoteNew!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.IUsuarioRemoteNew",
                    IUsuarioRemoteNew.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    //AGREGAR USUARIO-----------------

    public static void agregarUsuario(UsuarioDTO usuario) throws ServiciosException {

        if (usuarioRemote != null) {
            System.out.println("Nombre de usuario: " + usuario.getNom_usuario() +
                    ", Correo electrónico: " + usuario.getEmail() +
                    ", Cédula: " + usuario.getCedula() +
                    ", Nombre: " + usuario.getNombre() +
                    ", Segundo nombre: " + usuario.getNombre2() +
                    ", Apellido: " + usuario.getApellido() +
                    ", Segundo apellido: " + usuario.getApellido2() +
                    ", Fecha de nacimiento: " + usuario.getFec_nac() +
                    ", Estado de usuario: " + usuario.getEstado() +
                    ", Contraseña: " + usuario.getContrasenia() +
                   "tele: " +  usuario.getTelefonos().get(0)+
                    ", Perfil ID: " + usuario.getPerfilId() +
                    ", Institución ID: " + usuario.getInstitucionId());
            usuarioRemote.agregarUsuario(usuario);
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el usuario.");
        }
    }

    //OBTENER TODOS----------
    public static List<UsuarioDTO> obtenerTodosLosUsuarios(){
        if (usuarioRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return usuarioRemote.obtenerTodosLosUsuarios();
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. ");
        }
        return null;
    }

    public static List<UsuarioDTO> obtenerTodosLosUsuariosActivos(){
        if (usuarioRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return usuarioRemote.obtenerTodosLosUsuariosActivos();
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<UsuarioDTO> obtenerTodosLosUsuariosEliminados(){
        if (usuarioRemote != null) {
            return usuarioRemote.obtenerTodosLosUsuariosEliminados();
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<UsuarioDTO> obtenerTodosLosUsuariosSinValidar(){
        if (usuarioRemote != null) {
            return usuarioRemote.obtenerTodosLosUsuariosSinValidar();
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<UsuarioDTO> getUsuariosSinValidarYEliminados(){
        if (usuarioRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return usuarioRemote.getUsuariosSinValidarYEliminados();
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }



    //ENCONTRAR---------
    public static UsuarioDTO buscarPorEmail(String inputEmail){
        if (usuarioRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            if (!usuarioRemote.buscarPorEmails(inputEmail).isEmpty()){
                return usuarioRemote.buscarPorEmails(inputEmail).get(0);
            }
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede dar de baja al perfil.");
        }
        return null;
    }


    //BAJA-------------------------
    public static void bajaUsuarioLogica(Integer id) throws ServiciosException{
        if (usuarioRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
             usuarioRemote.bajaUsuarioLogica(id);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede dar de baja al perfil.");
        }
    }

    //ACTIVAR-------------------
    public static void activarUsuario(Integer id) throws ServiciosException {
        if (usuarioRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            usuarioRemote.activarUsuario(id);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede dar de baja al perfil.");
        }
    }

    //MODIFICAR------------------
    public static void actualizarUsuario(UsuarioDTO usuario) throws ServiciosException {
        if (usuarioRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            usuarioRemote.actualizarUsuario(usuario);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede dar de baja al perfil.");
        }
    }
    //LOGIN
    public  static UsuarioDTO verificarLogin(LoginDTO loginDTO)  throws ServiciosException{
        if (usuarioRemote != null) {
            //System.out.println(loginDTO.getPassword());
            //LoginDTO loginDTO1 = new LoginDTO("aaaa@gmail.com", "aaaa1234");
            if (usuarioRemote.login(loginDTO) != null) {
                //JOptionPane.showMessageDialog(null, "Email ingresado incorrecto");
                return usuarioRemote.login(loginDTO);
            }
        //System.out.println("llegué aquí");
    } else {
        System.out.println("El EJB remoto no está disponible. No se puede dar de baja al perfil.");
    }
        return null;
    }

    //PARA LISTADO POR FILTROS--------------

    public static List<UsuarioDTO> obtenerTodosLosUsuariosPorNombre1(String inputNombre1){
        if (usuarioRemote != null) {
           return  usuarioRemote.obtenerTodosLosUsuariosPorNombre1(inputNombre1);

        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<UsuarioDTO> obtenerTodosLosUsuariosPorApellido1(String inputApellido1){
        if (usuarioRemote != null) {
            return  usuarioRemote.obtenerTodosLosUsuariosPorApellido1(inputApellido1);

        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<UsuarioDTO> obtenerTodosLosUsuariosPorEmails(String inputEmail){
        if (usuarioRemote != null) {
            return  usuarioRemote.buscarPorEmails(inputEmail);

        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<UsuarioDTO> obtenerTodosLosUsuariosPorNombreUsuario(String inputNombreUsuario){
        if (usuarioRemote != null) {
            return  usuarioRemote.obtenerTodosLosUsuariosPorNombreUsuario(inputNombreUsuario);

        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }


    public static UsuarioDTO buscarPorNombreUsuario(String inputNombre){
        if (usuarioRemote != null) {
            return  usuarioRemote.buscarPorNombreUsuario(inputNombre);

        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<UsuarioDTO> buscarPorPerfi(Integer idPerfil){
        if (usuarioRemote != null) {
            return  usuarioRemote.obtenerPorPerfil(idPerfil);

        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static UsuarioDTO buscarPorId(Integer id){
        if (usuarioRemote != null) {
            return  usuarioRemote.buscarPorId(id);

        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }


    public static UsuarioDTO obtenerUsuarioPorCedula(String cedula){
        if (usuarioRemote != null) {
            return  usuarioRemote.obtenerUsuarioPorCedula(cedula);

        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

}
