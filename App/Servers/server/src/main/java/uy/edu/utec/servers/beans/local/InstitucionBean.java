package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IInstitucionBean;
import uy.edu.utec.servers.dao.InstitucionDAO;
import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearInstitucionDTO;
import uy.edu.utec.servers.entidades.Institucion;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.InstitucionMapper;

import java.util.List;

@Stateless
public class InstitucionBean implements IInstitucionBean {
    @EJB
    private InstitucionDAO institucionDAO;

    @Override
    public void agregarInstitucion(CrearInstitucionDTO institucion) throws ServiciosException {
        Institucion newInstitucion = InstitucionMapper.toEntity(institucion);
        newInstitucion.setEstado(EstadoGeneral.ACTIVO);
        institucionDAO.agregarInstitucion(newInstitucion);
    }

    @Override
    public void bajaInstitucion(Integer id) throws ServiciosException {
        Institucion institucion = institucionDAO.buscarPorId(id);
        institucion.setEstado(EstadoGeneral.ELIMINADO);
        institucionDAO.actualizarInstitucion(institucion);
    }

    @Override
    public void activarInstitucion(Integer id) throws ServiciosException {
        Institucion institucion = institucionDAO.buscarPorId(id);
        institucion.setEstado(EstadoGeneral.ACTIVO);
        institucionDAO.actualizarInstitucion(institucion);
    }

    @Override
    public InstitucionDTO buscarPorId(Integer id) {
        return InstitucionMapper.toDTO(institucionDAO.buscarPorId(id));
    }

    @Override
    public List<InstitucionDTO> obtenerTodasLasInstituciones() {
        return InstitucionMapper.toDTOList(institucionDAO.obtenerTodasLasInstituciones());
    }

    @Override
    public List<InstitucionDTO> obtenerTodasLasInstitucionesActivas() {
        return InstitucionMapper.toDTOList(institucionDAO.obtenerTodasLasInstitucionesActivas());
    }

    @Override
    public List<InstitucionDTO> obtenerTodasLasInstitucionesEliminadas() {
        return InstitucionMapper.toDTOList(institucionDAO.obtenerTodasLasInstitucionesEliminadas());
    }

    @Override
    public InstitucionDTO buscarPorNombre(String nombre) {
        return InstitucionMapper.toDTO(institucionDAO.buscarPorNombre(nombre));
    }
}
