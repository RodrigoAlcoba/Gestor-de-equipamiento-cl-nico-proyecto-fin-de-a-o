package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.InstitucionRemote;
import uy.edu.utec.servers.dtos.InstitucionDTO;

import javax.naming.NamingException;
import java.util.List;

public class InstitucionDAOCliNEW {
    public static InstitucionRemote institucionRemote;
    static {
        EJBClientUtility.init();
        try {
            institucionRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "InstitucionBeanImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.InstitucionRemote",
                    InstitucionRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static List<InstitucionDTO> obtenerInstituciones(){
        if (institucionRemote != null) {
            return institucionRemote.obtenerTodasLasInstituciones();
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar la institucion.");
        }
        return null;
    }

    public static InstitucionDTO obtenerInstitucionPorNombre(String nombre){
        if (institucionRemote != null) {
            return institucionRemote.buscarPorNombre(nombre);
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar la institucion.");
        }
        return null;
    }

    public static InstitucionDTO obtenerInstitucionPorId(Integer id){
        if (institucionRemote != null) {
            return institucionRemote.buscarPorId(id);
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar la institucion.");
        }
        return null;
    }

}
