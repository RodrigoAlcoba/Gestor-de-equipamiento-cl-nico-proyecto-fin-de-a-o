package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.IMovEquipoRemote;
import uy.edu.utec.servers.dtos.MovEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearMovEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.DateMapper;

import javax.naming.NamingException;
import java.util.Date;
import java.util.List;

public class MovEquipoDAOCli {
    public static IMovEquipoRemote movEquipoRemote;


    static {
        EJBClientUtility.init();
        try {
            movEquipoRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "MovEquipoRemoteImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.IMovEquipoRemote",
                    IMovEquipoRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static MovEquipoDTO obtenerUbicacionPorIdEquipo(Integer id){
        if (movEquipoRemote != null) {
            return movEquipoRemote.buscarUbicacionActualByEquipoId(id);
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }
    public static List<MovEquipoDTO> obtenerTodosMovDeEquipoByEuipoId(Integer equipoId){
        if (movEquipoRemote != null){
            return movEquipoRemote.obtenerTodosMovDeEquipoByEuipoId(equipoId);
        }else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static MovEquipoDTO obtenerMovEquipoPorId(Integer id){
        if (movEquipoRemote != null) {
            return movEquipoRemote.buscarPorId(id);
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static void crearMovEquipo(CrearMovEquipoDTO movEquipo) throws ServiciosException{
        if (movEquipoRemote != null){
            movEquipoRemote.crearMovEquipo(movEquipo);
        }else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar movimiento.");
        }
    }
    public static List<MovEquipoDTO> obtenerTodosMovDeEquipo(){
        if (movEquipoRemote != null){
            return movEquipoRemote.obtenerTodosMovDeEquipo();
        }else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar movimiento.");
        }
        return null;
    }

    public static MovEquipoDTO obtenerUbicacionActualByEquipoId(Integer idEquipo){
        if (movEquipoRemote != null){
            return movEquipoRemote.buscarUbicacionActualByEquipoId(idEquipo);
        }else {
            System.out.println("El EJB remoto no está disponible. No se puede agregar movimiento.");
        }
        return null;
    }

    public static List<MovEquipoDTO> obtenerMovEquiposPorInstitucion(Integer institucionId){
        if (movEquipoRemote != null) {
            return movEquipoRemote.obtenerTodosMovDeEquipoPorInstitucion(institucionId);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<MovEquipoDTO> obtenerMovEquiposPorSector(Integer sectorId){
        if (movEquipoRemote != null) {
            return movEquipoRemote.obtenerTodosMovDeEquipoPorSector(sectorId);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<MovEquipoDTO> obtenerMovEquiposPorUsuario(Integer usuarioId){
        if (movEquipoRemote != null) {
            return movEquipoRemote.obtenerTodosMovDeEquipoPorUsuario(usuarioId);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<MovEquipoDTO> obtenerMovEquiposPorFecDesde(Date fecDesde){
        if (movEquipoRemote != null) {
            return movEquipoRemote.obtenerTodosMovDeEquipoPorFecDesde(DateMapper.convertDateToLocalDate(fecDesde));
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<MovEquipoDTO> obtenerMovEquiposPorFecHasta(Date fecHasta){
        if (movEquipoRemote != null) {
            return movEquipoRemote.obtenerTodosMovDeEquipoPorFechaHasta(DateMapper.convertDateToLocalDate(fecHasta));
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }
}
