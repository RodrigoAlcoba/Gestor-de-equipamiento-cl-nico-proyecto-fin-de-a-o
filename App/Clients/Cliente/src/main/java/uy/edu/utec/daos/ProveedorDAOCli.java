package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.ProveedorRemote;
import uy.edu.utec.servers.dtos.ProveedorDTO;

import javax.naming.NamingException;
import java.util.List;

public class ProveedorDAOCli {
    public static ProveedorRemote proveedorRemote;
    static {
        EJBClientUtility.init();
        try {
            proveedorRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "ProveedorRemoteImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.ProveedorRemote",
                    ProveedorRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static List<ProveedorDTO> obtenerTodosLosProveedoresActivos(){
        if (proveedorRemote != null) {

            return proveedorRemote.obtenerTodosLosProveedoresActivos();
        } else {
            System.out.println("El EJB remoto no está disponible. ");
        }
        return null;
    }

    public static ProveedorDTO obtenerProveedorPorNombre(String nombre){
        if (proveedorRemote != null) {
            return proveedorRemote.buscarPorNombre(nombre);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static ProveedorDTO obtenerProveedorPorId(Integer id){
        if (proveedorRemote != null) {
            return proveedorRemote.buscarPorId(id);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }



}
