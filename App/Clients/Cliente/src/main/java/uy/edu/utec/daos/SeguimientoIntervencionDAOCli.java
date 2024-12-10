package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.ISeguimientoIntervencionRemote;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.NamingException;
import java.util.List;

public class SeguimientoIntervencionDAOCli {
    public static ISeguimientoIntervencionRemote seguimientoIntervencionRemote;
    static {
        EJBClientUtility.init();
        try{
            seguimientoIntervencionRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "SeguimientoIntervencionBeanImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.ISeguimientoIntervencionRemote",
                    ISeguimientoIntervencionRemote.class
            );
        }catch (NamingException e){
            e.printStackTrace();
        }
    }
    public static void agregarSeguimientoIntervencion(SeguimientoIntervencionDTO seguimientoIntervencionDTO) throws ServiciosException {
        if (seguimientoIntervencionRemote != null){
            seguimientoIntervencionRemote.altaSeguimiento(seguimientoIntervencionDTO);
            System.out.println("llegué");
        }else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el seguimiento a la intervencion.");
        }
    }

    public static List<SeguimientoIntervencionDTO> obtenerTodosLosSeguimientosDeUnaIntervencionPorID(Integer intervencionId){
        if (seguimientoIntervencionRemote != null){
            return seguimientoIntervencionRemote.obtenerTodosLosSeguimientosDeUnaIntervencionPorID(intervencionId);
        }else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar el seguimiento a la intervencion.");
        }
        return null;
    }
}
