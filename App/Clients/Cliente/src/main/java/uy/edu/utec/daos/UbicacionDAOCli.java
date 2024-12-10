package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.UbicacionRemote;
import uy.edu.utec.servers.dtos.MovEquipoDTO;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.dtos.*;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.entidades.Ubicacion;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.NamingException;
import java.util.List;
import java.util.Objects;

public class UbicacionDAOCli {

    public static UbicacionRemote ubicacionRemote;

    static {
        EJBClientUtility.init();
        try {
            ubicacionRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "UbicacionRemoteImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.UbicacionRemote",
                    UbicacionRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void agregarUbicacion(CrearUbicacionDTO ubicacion) throws ServiciosException {
        if (ubicacionRemote != null) {
            ubicacionRemote.agregarUbicacion(ubicacion);
            System.out.println("Ubicación agregada con éxito");
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar la ubicacion.");
        }
    }

    public static UbicacionDTO buscarUbicacionPorId(Integer id) throws ServiciosException {

        if (ubicacionRemote != null) {
            return ubicacionRemote.buscarPorId(id);
        } else {
            System.out.println("El EJB remoto no está disponible. No se puede buscar la ubicacion.");
        }
        return null;
    }

    public static List<UbicacionDTO> obtenerTodasLasUbicaciones() throws ServiciosException {
        if (ubicacionRemote != null) {
            return ubicacionRemote.obtenerTodasLasUbicaciones();
        } else {
            System.out.println("El EJB remoto no está disponible. No se pueden obtener todas las ubicaciones.");
        }
        return null;
    }

    public static void bajaUbicacion(BajaUsuarioUbicacionDTO bajaUsuarioUbicacion) throws ServiciosException {
        if (ubicacionRemote != null) {
            //ubicacionRemote.bajarUbicacion(bajaUsuarioUbicacion.getUbicacionId());
            BajaUbicacionUsuarioDAOCli.crearBajaUbicacion(bajaUsuarioUbicacion);
            System.out.println("Ubicacion dada de baja");

        } else {
            System.out.println("El EJB remoto no está disponible. No se puede bajar ubicacion.");
        }
    }

    public static UbicacionDTO obtenerUbicacionPorNombre(String ubicacion) {
        if (ubicacionRemote != null) {
            return ubicacionRemote.buscarUbicacionPorNombre(ubicacion);

        } else {
            System.out.println("El EJB remoto no está disponible");
        }
        return null;
    }

    public static UbicacionDTO obtenerUbicacionPorIdEquipo(Integer id) {
        if (ubicacionRemote != null) {
            return ubicacionRemote.buscarPorId(Objects.requireNonNull(MovEquipoDAOCli.obtenerUbicacionPorIdEquipo(id)).getUbicacionId());

        } else {
            System.out.println("El EJB remoto no está disponible");
        }
        return null;
    }

    public static List<UbicacionDTO> obtenerTodasLasUbicacionesActivas() throws ServiciosException {
        if (ubicacionRemote != null) {
            return ubicacionRemote.obtenerTodasLasUbicacionesActivas();
        } else {
            System.out.println("El EJB remoto no está disponible. No se pueden obtener todas las ubicaciones.");
        }
        return null;
    }

    public static void modificarUbicacion(UbicacionDTO ubicacionDTO) throws ServiciosException {
        if (ubicacionRemote != null) {
            ubicacionRemote.actualizarUbicacion(ubicacionDTO);
        }
    }

}
