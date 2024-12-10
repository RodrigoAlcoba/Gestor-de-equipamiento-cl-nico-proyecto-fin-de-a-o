package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IInstitucionBean;
import uy.edu.utec.servers.beans.remote.interfaces.InstitucionRemote;
import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearInstitucionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class InstitucionBeanImpl implements InstitucionRemote {

    @EJB
    private IInstitucionBean institucionBean;

    @Override
    public void agregarInstitucion(CrearInstitucionDTO institucion) throws ServiciosException {
        institucionBean.agregarInstitucion(institucion);
    }

    @Override
    public void bajaInstitucion(Integer id) throws ServiciosException {
        institucionBean.bajaInstitucion(id);
    }

    @Override
    public void activarInstitucion(Integer id) throws ServiciosException {
        institucionBean.activarInstitucion(id);
    }

    @Override
    public InstitucionDTO buscarPorId(Integer id) {
        return institucionBean.buscarPorId(id);
    }

    @Override
    public List<InstitucionDTO> obtenerTodasLasInstituciones() {
        return institucionBean.obtenerTodasLasInstituciones();
    }

    @Override
    public List<InstitucionDTO> obtenerTodasLasInstitucionesActivas() {
        return institucionBean.obtenerTodasLasInstitucionesActivas();
    }

    @Override
    public List<InstitucionDTO> obtenerTodasLasInstitucionesEliminadas() {
        return institucionBean.obtenerTodasLasInstitucionesEliminadas();
    }

    @Override
    public InstitucionDTO buscarPorNombre(String nombre) {
        return institucionBean.buscarPorNombre(nombre);
    }
}
