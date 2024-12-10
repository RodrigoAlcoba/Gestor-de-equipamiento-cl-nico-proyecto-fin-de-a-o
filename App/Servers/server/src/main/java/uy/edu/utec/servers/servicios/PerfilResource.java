package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.IPerfilBean;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearPerfilDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Path("/perfiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PerfilResource {
    @EJB
    IPerfilBean perfilBean;

    //-------------------POST PERFIL-------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearPerfil(@Valid CrearPerfilDTO perfil) throws ServiciosException {
        System.out.println(perfil.toString());
        perfilBean.agregarPerfil(perfil);
    }

    //-------------------GET PERFIL-------------------
    @GET
    public List<PerfilDTO> obtenerTodosLosPerfiles() {
        return perfilBean.obtenerTodosLosPerfiles();
    }

    @GET
    @Path("/nomPerfil/{nombre}")
    public List<PerfilDTO> obtenerTodosLosPerfilesPorNombre(@PathParam("nombre") String nombre) {
        return perfilBean.obtenerTodosLosPerfilesPorNombre(nombre);
    }

    @GET
    @Path("/activos")
    public List<PerfilDTO> obtenerTodosLosPerfilesActivo() {
        return perfilBean.obtenerTodosLosPerfilesActivos();
    }

    @GET
    @Path("/eliminados")
    public List<PerfilDTO> obtenerTodosLosPerfilesEliminados() {
        return perfilBean.obtenerTodosLosPerfilesEliminados();
    }

    @GET
    @Path("/{id}")
    public PerfilDTO obtenerPerfilPorId(@PathParam("id") Integer id) {
        return perfilBean.buscarPorId(id);
    }

    //-------------------PUT PERFIL-------------------
    @PUT
    @Path("/activar/{id}")
    //@Consumes(MediaType.APPLICATION_JSON)
    public void activarPerfil(@PathParam("id") Integer id) throws ServiciosException {
        perfilBean.activarPerfil(id);
    }
    @PUT
    @Path("/baja/{id}")
    //@Consumes(MediaType.APPLICATION_JSON)
    public void bajaLogicaPerfil(@PathParam("id") Integer id) throws ServiciosException {
        perfilBean.eliminarPerfilLogico(id);
    }
}
