package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.IntervencionRemote;
import uy.edu.utec.servers.beans.remote.interfaces.UbicacionRemote;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.entidades.Intervencion;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntervencionDAOCli {
    public static IntervencionRemote intervencionRemote;
        static {
            EJBClientUtility.init();
            try {
                intervencionRemote = EJBClientUtility.lookupEJB(
                        "ejb:/server-1.0-SNAPSHOT/" +
                                "IntervencionBEANImpl!" +
                                "uy.edu.utec.servers.beans.remote.interfaces.IntervencionRemote",
                        IntervencionRemote.class
                );
            } catch (NamingException e) {
                e.printStackTrace();
            }
    }

    public static void agregarIntervencion(IntervencionDTO intervencion) throws ServiciosException {

        if (intervencionRemote != null) {
            intervencionRemote.agregarIntervencion(intervencion);
            System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar la intervencion.");
        }
    }
    public static List<IntervencionDTO> obtenerIntervencionesPorTipoYFechas(Integer tipoIntervencion, LocalDate fechaDesde, LocalDate fechaHasta){
            if (intervencionRemote != null){
                return intervencionRemote.obtenerIntervencionesPorTipoYFechas(tipoIntervencion, fechaDesde, fechaHasta);
            }
            return new ArrayList<>();
    }

    public static List<IntervencionDTO> obtenerTodasLasIntervenciones(){
            if (intervencionRemote != null){
                return intervencionRemote.obtenerTodasLasIntervenciones();
            }else {
                System.out.println("El EJB remoto no está disponible. No se puede seleccionar Intervención.");
            }
            return null;
    }

    public static List<IntervencionDTO> obtenerIntervencionesPorEquipoID(Integer equipoID, LocalDate fechaDesde, LocalDate fechaHasta) {
        if (intervencionRemote != null) {
            return intervencionRemote.obtenerIntervencionesPorEquipoID(equipoID, fechaDesde, fechaHasta);
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede seleccionar equipo.");
        }
        return new ArrayList<>();
    }

    public static IntervencionDTO buscarPorId(Integer id){
            if (intervencionRemote != null){
                return intervencionRemote.buscarPorId(id);
            }else {
                System.out.println("El EJB remoto no está disponible. No se puede seleccionar intervencion.");
            }
            return null;
    }

    public static List<IntervencionDTO> obtenerIntervencionesPorTipo(String tipoIntervencion){
        if (intervencionRemote != null){
            return intervencionRemote.obtenerIntervencionesPorTipo(tipoIntervencion);
        }else {
            System.out.println("El EJB remoto no está disponible. No se puede seleccionar intervencion.");
        }
        return null;
    }
}
