package uy.edu.utec.servers.beans.local;


import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.ISeguimientoIntervencionBean;
import uy.edu.utec.servers.dao.IntervencionDAO;
import uy.edu.utec.servers.dao.TipoIntervencionDAO;
import uy.edu.utec.servers.dao.interfaces.ISeguimientoIntervencionDAO;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.entidades.Intervencion;
import uy.edu.utec.servers.entidades.SeguimientoIntervencion;
import uy.edu.utec.servers.entidades.TipoIntervencion;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.IntervencionMapper;
import uy.edu.utec.servers.mappers.SeguimientoIntervencionMapper;

import java.util.List;

@Stateless
public class SeguimientoIntervencionBean implements ISeguimientoIntervencionBean {
    @EJB
    ISeguimientoIntervencionDAO seguimientoIntervencionDAO;
    @EJB
    TipoIntervencionDAO tipoIntervencionDAO;

    @EJB
    IntervencionDAO intervencionDAO;

    @Override
    public void altaSeguimiento(SeguimientoIntervencionDTO seguimientoIntervencion) throws ServiciosException {
        TipoIntervencion tipoIntervencion = tipoIntervencionDAO.obtenerTodosLosTipoIntervencionPorNombre(seguimientoIntervencion.getTipoIntervencion());

       Intervencion intervencion = intervencionDAO.buscarPorId(seguimientoIntervencion.getIntervencionId());

       List<SeguimientoIntervencion> seguimientoIntervencionList = seguimientoIntervencionDAO.obtenerTodosLosSeguimientosDeUnaIntervencionPorID(seguimientoIntervencion.getIntervencionId());
       if (isResolucion(seguimientoIntervencionList)){
          throw new ServiciosException("No se puede agregar mas seguimientos a esta intervención.");
       }
       seguimientoIntervencionDAO.altaSeguimiento(SeguimientoIntervencionMapper.toEntity(seguimientoIntervencion, tipoIntervencion, intervencion));
    }
    private boolean isResolucion(List<SeguimientoIntervencion> seguimientoIntervencionList){
        for (SeguimientoIntervencion seguimientoIntervencion : seguimientoIntervencionList){
            if ("RESOLUCION".equals(seguimientoIntervencion.getTipoIntervencion().getNomTipo())){
                return true;
            }
        }
        return false;
    }

    @Override
    public  SeguimientoIntervencionDTO buscarPorId(Integer id) {
        return SeguimientoIntervencionMapper.toDTO(seguimientoIntervencionDAO.buscarPorId(id));
    }

    @Override
    public List<SeguimientoIntervencionDTO> obtenerTodosLosSeguimientosDeUnaIntervencionPorID(Integer intervencionId) {
        return SeguimientoIntervencionMapper.toDTOList(seguimientoIntervencionDAO.obtenerTodosLosSeguimientosDeUnaIntervencionPorID(intervencionId));
    }
}
