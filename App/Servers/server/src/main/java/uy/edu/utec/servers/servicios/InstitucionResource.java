package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.IInstitucionBean;
import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearInstitucionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Path("/instituciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InstitucionResource {
    @EJB
    IInstitucionBean intervencionBean;
    @GET
    public List<InstitucionDTO> obtenerTodasLasInstituciones() {
        return intervencionBean.obtenerTodasLasInstituciones();
    }
    @GET
    @Path("/activas")
    public List<InstitucionDTO> obtenerTodasLasInstitucionesActivas() {
        return intervencionBean.obtenerTodasLasInstitucionesActivas();
    }
    @GET
    @Path("/eliminadas")
    public List<InstitucionDTO> obtenerTodasLasInstitucionesEliminadas() {
        return intervencionBean.obtenerTodasLasInstitucionesEliminadas();
    }

    @GET
    @Path("/{id}")
    public InstitucionDTO obtenerInstitucionPorId(@PathParam("id") Integer id) {
        return intervencionBean.buscarPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearInstitucion(CrearInstitucionDTO institucion) throws ServiciosException {
        intervencionBean.agregarInstitucion(institucion);
    }

    @PUT
    @Path("/baja/{id}")
    public void bajaInstitucion(@PathParam("id") Integer id) throws ServiciosException {
        intervencionBean.bajaInstitucion(id);
    }

    @PUT
    @Path("/activar/{id}")
    public void activarInstitucion(@PathParam("id") Integer id) throws ServiciosException {
        intervencionBean.activarInstitucion(id);
    }
    @GET
    @Path("/buscarpornombre/{nombre}")
    public InstitucionDTO buscarPorNombre(@PathParam("nombre") String nombre) {
        return intervencionBean.buscarPorNombre(nombre);
    }
}
