package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.IBajaUbicacionUsuarioRemote;
import uy.edu.utec.servers.beans.remote.interfaces.InstitucionRemote;
import uy.edu.utec.servers.beans.remote.interfaces.UbicacionRemote;
import uy.edu.utec.servers.dtos.BajaUsuarioUbicacionDTO;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.entidades.BajaUsuarioUbicacion;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.NamingException;

public class BajaUbicacionUsuarioDAOCli {

    public static IBajaUbicacionUsuarioRemote bajaUbicacionRemote;
    static {
        EJBClientUtility.init();
        try {
            bajaUbicacionRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/BajaUbicacionUsuarioRemote!uy.edu.utec.servers.beans.remote.interfaces.IBajaUbicacionUsuarioRemote",
                    IBajaUbicacionUsuarioRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void crearBajaUbicacion(BajaUsuarioUbicacionDTO bajaUsuarioUbicacion) throws ServiciosException {

        if (bajaUbicacionRemote != null) {
            bajaUbicacionRemote.usuarioBajaUbicacion(bajaUsuarioUbicacion);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
    }
}
