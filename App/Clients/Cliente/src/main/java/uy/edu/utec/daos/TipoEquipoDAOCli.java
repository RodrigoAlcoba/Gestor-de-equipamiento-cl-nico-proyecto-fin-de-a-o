package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.ITipoEquipoRemote;
import uy.edu.utec.servers.beans.remote.interfaces.ProveedorRemote;
import uy.edu.utec.servers.dao.TipoEquipoDAO;
import uy.edu.utec.servers.dtos.TipoEquipoDTO;

import javax.naming.NamingException;
import java.util.List;

public class TipoEquipoDAOCli {

    public static ITipoEquipoRemote iTipoEquipoRemote;
    static {
        EJBClientUtility.init();
        try {
            iTipoEquipoRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "TipoEquipoBeanImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.ITipoEquipoRemote",
                    ITipoEquipoRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static List<TipoEquipoDTO> obtenerTipoEquiposActivos(){
        if (iTipoEquipoRemote != null) {
           return  iTipoEquipoRemote.obtenerTodosLosTipoEquipoActivos();
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static TipoEquipoDTO obtenerTipoEquipoPorNombre(String nombre){
        if (iTipoEquipoRemote != null) {
            return  iTipoEquipoRemote.buscarPorNombre(nombre);
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static TipoEquipoDTO obtenerTipoEquipoPorId(Integer id){
        if (iTipoEquipoRemote != null) {
            return  iTipoEquipoRemote.buscarPorId(id);
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

}
