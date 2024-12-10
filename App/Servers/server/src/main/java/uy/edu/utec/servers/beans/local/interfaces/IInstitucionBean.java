package uy.edu.utec.servers.beans.local.interfaces;


import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearInstitucionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@LocalBean
public interface IInstitucionBean {
    void agregarInstitucion(CrearInstitucionDTO institucion) throws ServiciosException;

    void bajaInstitucion(Integer id) throws ServiciosException;
    void activarInstitucion(Integer id) throws ServiciosException;

    InstitucionDTO buscarPorId(Integer id);

    List<InstitucionDTO> obtenerTodasLasInstituciones();

    List<InstitucionDTO> obtenerTodasLasInstitucionesActivas();
    List<InstitucionDTO> obtenerTodasLasInstitucionesEliminadas();
    InstitucionDTO buscarPorNombre(String nombre);
}
