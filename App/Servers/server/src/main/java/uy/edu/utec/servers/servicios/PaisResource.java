package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.IPaisBean;
import uy.edu.utec.servers.dtos.PaisDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Path("/paises")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaisResource {
    @EJB
    IPaisBean paisBean;

    //-------------------POST PAIS-------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarPais(PaisDTO pais) throws ServiciosException {
        paisBean.agregarPais(pais);
    }

    //-------------------GET PAIS-------------------
    @GET
    @Path("/{id}")
    public PaisDTO buscarPorId(@PathParam("id") Integer id){
        return paisBean.buscarPorId(id);
    }

    @GET
    public List<PaisDTO> obtenerTodosLosPaises(){
        return paisBean.obtenerTodosLosPaises();
    }
    @GET
    @Path("/activos")
    public List<PaisDTO> obtenerTodosLosPaisActivos(){
        return paisBean.obtenerTodosLosPaisActivos();
    }
    @GET
    @Path("/eliminados")
    public List<PaisDTO> obtenerTodosLosPaisesEliminados(){
        return paisBean.obtenerTodosLosPaisesEliminados();
    }
    @GET
    @Path("/nombre/{nombre}")
    public List<PaisDTO> obtenerTodosLosPaisesPorNombre(@PathParam("nombre") String inputNombre){
        return paisBean.obtenerTodosLosPaisesPorNombre(inputNombre);
    }

    //-------------------PUT PAIS-------------------
    @PUT
    @Path("/activar/{id}")
    public void activarPais(@PathParam("id") Integer id) throws ServiciosException{
        paisBean.activarPais(id);
    }
    @PUT
    @Path("/baja/{id}")
    public void bajaPais(@PathParam("id") Integer id) throws ServiciosException{
        paisBean.bajaPais(id);
    }
}
