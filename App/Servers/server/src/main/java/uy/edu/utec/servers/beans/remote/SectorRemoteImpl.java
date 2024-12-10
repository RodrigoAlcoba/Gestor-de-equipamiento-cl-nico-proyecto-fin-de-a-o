package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.ISectorBean;
import uy.edu.utec.servers.beans.remote.interfaces.SectorRemote;
import uy.edu.utec.servers.dtos.SectorDTO;

import java.util.List;
@Stateless
public class SectorRemoteImpl implements SectorRemote {
    @EJB
    ISectorBean bean;

    @Override
    public SectorDTO buscarPorId(Integer id) {
        return bean.buscarPorId(id);
    }

    @Override
    public List<SectorDTO> obtenerTodosLosSectores() {
        return bean.obtenerTodosLosSectores();
    }

    @Override
    public SectorDTO buscarPorNombre(String nombre) {
        return bean.buscarPorNombre(nombre);
    }

}
