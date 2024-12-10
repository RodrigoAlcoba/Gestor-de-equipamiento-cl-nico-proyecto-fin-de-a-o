package uy.edu.utec.servers.beans.remote.interfaces;

import jakarta.ejb.Remote;
import uy.edu.utec.servers.dtos.SectorDTO;

import java.util.List;
@Remote
public interface SectorRemote {
    SectorDTO buscarPorId(Integer id);
    List<SectorDTO> obtenerTodosLosSectores();
    SectorDTO buscarPorNombre(String nombre);
}
