package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.EquipoRemote;
import uy.edu.utec.servers.beans.remote.interfaces.IBajaUsuarioEquipoRemote;
import uy.edu.utec.servers.dtos.BajaUsuarioEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.NamingException;
import java.sql.SQLException;

public class BajaEquipoDAOCli {

    public static IBajaUsuarioEquipoRemote iBajaUsuarioEquipoRemote;

    static {
        EJBClientUtility.init();
        try {
            iBajaUsuarioEquipoRemote = (IBajaUsuarioEquipoRemote) EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "BajaUsuarioEquipoBeanImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.IBajaUsuarioEquipoRemote",
                    EquipoRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
        }
        public static void crearBajaEquipo(BajaUsuarioEquipoDTO bajaUsuarioEquipoDTO) throws ServiciosException, SQLException {
        if (iBajaUsuarioEquipoRemote != null){
            iBajaUsuarioEquipoRemote.crearBajaEquipo(bajaUsuarioEquipoDTO);
        }else {
            System.out.println("El EJB remoto no está disponible.");
        }
    }
}
