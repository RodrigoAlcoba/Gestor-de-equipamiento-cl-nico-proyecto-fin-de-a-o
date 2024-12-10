package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.SectorDTO;

import java.util.List;
@LocalBean
public interface ISectorBean {
    SectorDTO buscarPorId(Integer id);
    List<SectorDTO> obtenerTodosLosSectores();
    SectorDTO buscarPorNombre(String nombre);
}
