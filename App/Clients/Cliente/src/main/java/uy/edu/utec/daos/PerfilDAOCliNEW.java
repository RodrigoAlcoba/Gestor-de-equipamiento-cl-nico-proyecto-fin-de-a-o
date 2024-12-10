package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.PerfilRemote;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearPerfilDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.NamingException;
import java.util.List;

public class PerfilDAOCliNEW {
    public static PerfilRemote perfilRemote;
    static {
        EJBClientUtility.init();
        try {
            perfilRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "PerfilBeanImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.PerfilRemote",
                    PerfilRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

     //AGREGAR-------------------
    public static void agregarPerfil(CrearPerfilDTO perfil) throws ServiciosException {
        if (perfilRemote != null) {
            System.out.println(perfil.getNomPerfil() + perfil.getNomPerfil());
            perfilRemote.agregarPerfil(perfil);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
        }
    }

    //LISTAR----------------
    public static List<PerfilDTO> obtenerPerfilesActivos() {
        if (perfilRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return perfilRemote.obtenerTodosLosPerfilesActivos();
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
        }
        return null;
    }

    public static List<PerfilDTO> obtenerTodosPerfiles() {
        if (perfilRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return perfilRemote.obtenerTodosLosPerfiles();
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
        }
        return null;
    }


    //OBTENER UN SOLO PERFIL POR NOMBRE--------------
    public static PerfilDTO obtenerPerfilPorNombre(String nombre){
        if (perfilRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return perfilRemote.obtenerPerfilPorNombre(nombre);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
        }
        return null;
    }

    public static List<PerfilDTO> obtenerPerfilesPorNombre(String nombre){
        if (perfilRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return perfilRemote.obtenerTodosLosPerfilesPorNombre(nombre);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
        }
        return null;

    }

    public static List<PerfilDTO> obtenerPerfilesEliminados(){
             if (perfilRemote != null) {
        //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
        return perfilRemote.obtenerTodosLosPerfilesEliminados();
        //System.out.println("llegué aquí");
    } else {
        System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
    }
        return null;
}

    //BAJA------------
    public static void bajaPerfil(Integer id) throws ServiciosException {
        if (perfilRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
             perfilRemote.eliminarPerfilLogico(id);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
        }
    }

    //REACTIVAR--------
    public static void reactivarPerfil(Integer id) throws ServiciosException {
        if (perfilRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            perfilRemote.activarPerfil(id);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
        }
    }
    public static PerfilDTO obtenerPerfilPorId(Integer idPerfil) throws ServiciosException {
        if (perfilRemote != null) {
            return perfilRemote.buscarPorId(idPerfil);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }
}
