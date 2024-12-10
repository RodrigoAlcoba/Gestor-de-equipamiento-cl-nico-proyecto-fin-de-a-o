package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.MarcaRemote;
import uy.edu.utec.servers.beans.remote.interfaces.PaisRemote;
import uy.edu.utec.servers.dtos.PaisDTO;

import javax.naming.NamingException;
import java.util.List;

public class PaisDAOCliente {

    public static PaisRemote paisRemote;

    static {
        EJBClientUtility.init();
        try {
            paisRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "PaisRemoteImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.PaisRemote",
                    PaisRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static List<PaisDTO> obtenerTodosLosPaisesActivos() {
        if (paisRemote != null) {
            return paisRemote.obtenerTodosLosPaisActivos();
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static PaisDTO obtenerPaisPorNombre(String nombre){
        if (paisRemote != null) {
            return paisRemote.obtenerPaisPorNombre(nombre);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static PaisDTO obtenerPaisPorId(Integer id){
        if (paisRemote != null) {
            return paisRemote.buscarPorId(id);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }
}
