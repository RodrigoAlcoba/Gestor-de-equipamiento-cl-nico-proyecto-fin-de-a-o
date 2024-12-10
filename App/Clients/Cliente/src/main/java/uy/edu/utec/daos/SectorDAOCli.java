package uy.edu.utec.daos;

import uy.edu.utec.servers.beans.remote.interfaces.SectorRemote;
import uy.edu.utec.servers.dtos.SectorDTO;

import javax.naming.NamingException;
import java.util.List;

public class SectorDAOCli {
    public static SectorRemote sectorRemote;

    static {
        EJBClientUtility.init();
        try {
            sectorRemote = EJBClientUtility.lookupEJB(
                    "ejb:/server-1.0-SNAPSHOT/" +
                            "SectorRemoteImpl!" +
                            "uy.edu.utec.servers.beans.remote.interfaces.SectorRemote",
                    SectorRemote.class
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
        public static SectorDTO buscarPorId(Integer id) {
            if (sectorRemote != null) {
                return sectorRemote.buscarPorId(id);
            } else {
                System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
            }
            return null;
        }

        public static List<SectorDTO> obtenerTodosLosSectores() {
            return sectorRemote.obtenerTodosLosSectores();
        }

        public static SectorDTO buscarPorNombre(String nombre){
            if (sectorRemote != null) {
                return sectorRemote.buscarPorNombre(nombre);
            } else {
                System.out.println("El EJB remoto no está disponible. No se puede agregar el perfil.");
            }
            return null;
        }

    }

