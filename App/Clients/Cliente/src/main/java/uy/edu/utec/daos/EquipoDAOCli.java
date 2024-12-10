package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.EquipoRemote;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EquipoDAOCli {
    public static EquipoRemote equipoRemote;

    static {
        EJBClientUtility.init();
        try {
            equipoRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "EquipoBeanImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.EquipoRemote",
                    EquipoRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void crearEquipo(CrearEquipoDTO equipo) throws ServiciosException, SQLException {
        if (equipoRemote != null) {
            System.out.println("EquipoDTO{" +
                    ", idInterna=" + equipo.getIdInterna() +
                    ", nomEquipo='" + equipo.getNomEquipo() + '\'' +
                    ", numSerie=" + equipo.getNumSerie() +
                    ", fecAdquisicion=" + equipo.getFecAdquisicion() +
                    ", imagen='" + equipo.getImagen() + '\'' +
                    ", garantia=" + equipo.getGarantia() +
                    ", paisId=" + equipo.getPaisId() +
                    ", tipoEquipoId=" + equipo.getTipoEquipoId() +
                    ", proveedorId=" + equipo.getProveedorId() +
                    ", modeloId=" +equipo.getModeloId() +
                    "marca" +equipo.getMarcaId()
                    +'}');
            equipoRemote.agregarEquipo(equipo);
            // System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. ");
        }
    }

    public static List<EquipoDTO> obtenerTodosLosEquipos() throws SQLException {
        if (equipoRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return equipoRemote.obtenerTodosLosEquipos();
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;

    }
    public static EquipoDTO obtenerEquipoPorNombre(String nombre) throws SQLException {
        return equipoRemote.obtenerPorNombre(nombre);
    }

    public static void modificarEquipo(EquipoDTO equipoDTO) throws ServiciosException, SQLException {
        if (equipoRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            System.out.println("EquipoDTO{" +
                    ", idInterna=" + equipoDTO.getIdInterna() +
                    ", nomEquipo='" + equipoDTO.getNomEquipo() + '\'' +
                    ", numSerie=" + equipoDTO.getNumSerie() +
                    ", fecAdquisicion=" + equipoDTO.getFecAdquisicion() +
                    ", imagen='" + equipoDTO.getImagen() + '\'' +
                    ", garantia=" + equipoDTO.getGarantia() +
                    ", paisId=" + equipoDTO.getPaisId() +
                    ", tipoEquipoId=" + equipoDTO.getTipoEquipoId() +
                    ", proveedorId=" + equipoDTO.getProveedorId() +
                    ", modeloId=" + equipoDTO.getModeloId() +
                    ", marcaId=" + equipoDTO.getMarcaId() +
                    '}');

            equipoRemote.actualizarEquipo(equipoDTO);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible. ");
        }
    }

    public static EquipoDTO obtenerEquipoPorIdInteno(Integer idInterna) throws SQLException {
        if (equipoRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return equipoRemote.buscarEquipoPoridInterna(idInterna);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static EquipoDTO obtenerEquipoPorId(Integer id) throws SQLException {
        if (equipoRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return equipoRemote.buscarPorId(id);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static void bajarEquipo(Integer id) throws ServiciosException, SQLException {
        equipoRemote.bajaEquipo(id);
    }

    public static void reactivarEquipo(Integer id) throws ServiciosException {
        equipoRemote.activarEquipo(id);
    }

    //LISTADO------------------------------------
    public static List<EquipoDTO> obtenerEquiposPorNombre(String nombre) throws SQLException {
        if (equipoRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return equipoRemote.buscarTodosLosEquiposPorNombre(nombre);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<EquipoDTO> obtenerEquiposTipoEquipo(Integer tipoEquipo) throws SQLException {
        if (equipoRemote != null) {
            //System.out.println(perfil.getID_Perfil() + perfil.getNom_Perfil() + perfil.getEstado());
            return equipoRemote.obtenerEquiposPorTipoEquipo(tipoEquipo);
            //System.out.println("llegué aquí");
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<EquipoDTO> obtenerEquiposPorMarca(Integer idMarca) throws SQLException{
        if (equipoRemote != null) {
            return equipoRemote.obtenerEquiposPorMarca(idMarca);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<EquipoDTO> obtenerEquiposPorModelo(Integer idModelo) throws SQLException {
        if (equipoRemote != null) {
            return equipoRemote.obtenerEquiposPorModelo(idModelo);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<EquipoDTO> obtenerEquiposPorNumeroSerie(Integer numSerie) throws SQLException {
        if (equipoRemote != null) {
            return equipoRemote.obtenerEquiposPorNumeroSerie(numSerie);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<EquipoDTO> obtenerEquiposPorPais(Integer idPais) throws SQLException {
        if (equipoRemote != null) {
            return equipoRemote.obtenerEquiposPorPais(idPais);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<EquipoDTO> obtenerEquiposPorProveedor(Integer idProveedor) throws SQLException {
        if (equipoRemote != null) {
            return equipoRemote.obtenerEquiposPorProveedor(idProveedor);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<EquipoDTO> obtenerEquiposPorFecAdquisicion(Date fecAdquisicion) throws SQLException {
        if (equipoRemote != null) {
            return equipoRemote.obtenerEquiposPorFecAdquisicion(fecAdquisicion);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<EquipoDTO> obtenerEquiposPorUbicacion(Integer idUbicacion) throws SQLException {
        if (equipoRemote != null) {
            return equipoRemote.obtenerEquiposPorUbicacion(idUbicacion);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }

    public static List<EquipoDTO> buscarEquiposPoridInterna(Integer inputIdInterna) throws SQLException {
        if (equipoRemote != null) {
            return equipoRemote.buscarEquiposPoridInterna(inputIdInterna);
        } else {
            System.out.println("El EJB remoto no está disponible.");
        }
        return null;
    }
}