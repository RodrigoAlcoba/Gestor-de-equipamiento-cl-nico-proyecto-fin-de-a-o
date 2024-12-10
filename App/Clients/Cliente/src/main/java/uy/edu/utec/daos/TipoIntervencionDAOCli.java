package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.TipoIntervencionRemote;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;


public class TipoIntervencionDAOCli {
    public static TipoIntervencionRemote tipoIntervencionRemote;

    static {
        EJBClientUtility.init();
            try {
                tipoIntervencionRemote = EJBClientUtility.lookupEJB(
                        "ejb:/server-1.0-SNAPSHOT/" +
                                "TipoIntervencionImpl!" +
                                "uy.edu.utec.servers.beans.remote.interfaces.TipoIntervencionRemote",
                        TipoIntervencionRemote.class
                );
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    public static void agregarTipoIntervencion(TipoIntervencionDTO tipoIntervencion) throws ServiciosException {

        if (tipoIntervencionRemote != null) {
            tipoIntervencionRemote.agregarTipoIntervencion(tipoIntervencion);
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar tipo intervencion.");
        }
    }

    public static List<TipoIntervencionDTO> obtenerTodosLosTiposIntervencionActivos(){
        if (tipoIntervencionRemote != null){
            return tipoIntervencionRemote.obtenerTodosLosTipoIntervencionActivos();
        }else {
            System.out.println("El EJB remoto no está disponible. No se puede seleccionar Tipo Intervención.");
        }
        return null;
    }

    public static TipoIntervencionDTO obtenerTodosLosTipoIntervencionPorNombre(String nombre){
        if (tipoIntervencionRemote != null){
            return tipoIntervencionRemote.obtenerTodosLosTipoIntervencionPorNombre(nombre);
        }else{
            System.out.println("El EJB remoto no esta disponible. No se puede seleccionar Tipo Intervención.");
        }
        return null;
    }
    public static  TipoIntervencionDTO buscarPorId(Integer id){
        if (tipoIntervencionRemote != null){
            return tipoIntervencionRemote.buscarPorId(id);
        }else {
            System.out.println("El EJB remoto no esta disponible. No se puede seleccionar el id de Tipo Intervención.");
        }
        return null;
    }

    public static void eliminarTipoIntervencion(Integer id) throws ServiciosException {
        if (tipoIntervencionRemote != null){
            tipoIntervencionRemote.eliminarTipoIntervencion(id);
        }else {
            System.out.println("\"El EJB remoto no esta disponible. No se puede seleccionar el id de Tipo Intervención.\"");
        }
    }

    public static void activarTipoIntervencion(Integer id) throws ServiciosException {
        if (tipoIntervencionRemote != null){
            tipoIntervencionRemote.activarTipoIntervencion(id);
        }
    }

    public static List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionPorTipo(String inputTipo){
        if (tipoIntervencionRemote != null){
            return tipoIntervencionRemote.obtenerTodosLosTipoIntervencionPorTipo(inputTipo);
        }else {
            System.out.println("\"El EJB remoto no esta disponible. No se puede seleccionar el id de Tipo Intervención.\"");
        }
        return new ArrayList<>();
    }
    public static List<TipoIntervencionDTO> obtenerTodosLoTipoIntervencionEliminados(){
        if (tipoIntervencionRemote != null){
            return tipoIntervencionRemote.obtenerTodosLoTipoIntervencionEliminados();
        }else {
            System.out.println("\"El EJB remoto no esta disponible. No se puede seleccionar el id de Tipo Intervención.\"");
        }
        return null;
    }
    public static List<TipoIntervencionDTO> obtenerTodosLosTiposIntervencion(){
        if (tipoIntervencionRemote != null){
            return tipoIntervencionRemote.obtenerTodosLosTiposIntervencion();
        }else {
            System.out.println("El EJB remoto no esta disponible. No se puede seleccionar el id de Tipo Intervención.");
        }
        return null;
    }

}
