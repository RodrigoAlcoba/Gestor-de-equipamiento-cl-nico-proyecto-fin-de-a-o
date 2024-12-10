package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.ISeguimientoIntervencionBean;
import uy.edu.utec.servers.beans.remote.interfaces.ISeguimientoIntervencionRemote;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class SeguimientoIntervencionBeanImpl implements ISeguimientoIntervencionRemote {
    @EJB
    ISeguimientoIntervencionBean seguimientoIntervencionBean;
    @Override
    public void altaSeguimiento(SeguimientoIntervencionDTO seguimientoIntervencion) throws ServiciosException {
        seguimientoIntervencionBean.altaSeguimiento(seguimientoIntervencion);
    }

    @Override
    public SeguimientoIntervencionDTO buscarPorId(Integer id) {
        return seguimientoIntervencionBean.buscarPorId(id);
    }

    @Override
    public List<SeguimientoIntervencionDTO> obtenerTodosLosSeguimientosDeUnaIntervencionPorID(Integer intervencionId) {
        return seguimientoIntervencionBean.obtenerTodosLosSeguimientosDeUnaIntervencionPorID(intervencionId);
    }
}
