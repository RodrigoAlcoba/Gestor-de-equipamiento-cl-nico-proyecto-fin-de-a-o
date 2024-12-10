package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.MarcaRemote;
import uy.edu.utec.servers.dtos.MarcaDTO;
import uy.edu.utec.servers.entidades.Marca;

import javax.naming.NamingException;
import java.util.List;

public class MarcaDAOCli {
    public static MarcaRemote marcaRemote;

    static {
        EJBClientUtility.init();
        try {
            marcaRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "MarcaBeanImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.MarcaRemote",
                    MarcaRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    public static List<MarcaDTO> obtenerTodasLasMarcas(){
        if (marcaRemote != null) {
            return  marcaRemote.obtenerTodasLasMarcas();
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static MarcaDTO obtenerMarcaPorNombre(String nombre){
        if (marcaRemote != null) {
            return marcaRemote.obtenerMarcaPorNombre(nombre);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static MarcaDTO obtenerMarcaPorId(Integer id){
        if (marcaRemote != null) {
            return marcaRemote.buscarPorId(id);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<MarcaDTO> obtenerTodasLasMarcasActivas(){
        if (marcaRemote != null) {
            return  marcaRemote.obtenerTodasLasMarcasActivas();
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

}
