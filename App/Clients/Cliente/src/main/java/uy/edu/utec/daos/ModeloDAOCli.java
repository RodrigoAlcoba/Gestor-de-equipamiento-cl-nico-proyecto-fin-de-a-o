package uy.edu.utec.daos;


import uy.edu.utec.servers.beans.remote.interfaces.ModeloRemote;
import uy.edu.utec.servers.dtos.ModeloDTO;

import javax.naming.NamingException;
import java.util.List;

public class ModeloDAOCli {
    public static ModeloRemote modeloRemote;
    static {
        EJBClientUtility.init();
        try {
            modeloRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "ModeloBeanRemoteImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.ModeloRemote",
                    ModeloRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public static List<ModeloDTO> obtenerTodosLosModelos(){
        if (modeloRemote != null) {
            return  modeloRemote.obtenerTodosLosModelos();
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static ModeloDTO obtenerModeloPorNombre(String nombre){
        if (modeloRemote != null) {
            return  modeloRemote.buscarModeloPorNombre(nombre);
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static ModeloDTO obtenerModeloPorId(Integer id){
        if (modeloRemote != null) {
            return  modeloRemote.buscarPorId(id);
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<ModeloDTO> obtenerModelosPorNombre(String nombre){
        if (modeloRemote != null) {
            return modeloRemote.obtenerTodosLosModelosPorNombre(nombre);
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<ModeloDTO> obtenerTodosLosModelosActivos(){
        if (modeloRemote != null) {
            return  modeloRemote.obtenerTodosLosModelosActivos();
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }
}
