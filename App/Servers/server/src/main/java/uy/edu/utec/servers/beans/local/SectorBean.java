package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.ISectorBean;
import uy.edu.utec.servers.dao.SectorDAO;
import uy.edu.utec.servers.dtos.SectorDTO;
import uy.edu.utec.servers.mappers.SectorMapper;

import java.util.List;
@Stateless
public class SectorBean implements ISectorBean {
    @EJB
    SectorDAO sectorDAO;

    @Override
    public SectorDTO buscarPorId(Integer id) {
        return SectorMapper.toDTO(sectorDAO.buscarPorId(id));
    }

    @Override
    public List<SectorDTO> obtenerTodosLosSectores() {
        return SectorMapper.toDTO(sectorDAO.obtenerTodosLosSectores());
    }

    @Override
    public SectorDTO buscarPorNombre(String nombre) {
        return SectorMapper.toDTO(sectorDAO.buscarPorNombre(nombre));
    }

}
