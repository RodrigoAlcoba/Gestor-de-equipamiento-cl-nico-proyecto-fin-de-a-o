package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.ITipoIntervencionBean;
import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Path("/tipointervencion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoIntervencionResource {
    @EJB
    ITipoIntervencionBean tipoIntervencionBean;

    //-------------------POST TIPO INTERVENCION-------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearTipoIntervencion(TipoIntervencionDTO tipoIntervencionDTO) throws ServiciosException {
        tipoIntervencionBean.agregarTipoIntervencion(tipoIntervencionDTO);
    }
    //-------------------GET TIPO INTERVENCION-------------------
    @GET
    @Path("/{id}")
    public TipoIntervencionDTO buscarPorId(@PathParam("id") Integer id){
        return tipoIntervencionBean.buscarPorId(id);
    }
    @GET
    public List<TipoIntervencionDTO> obtenerTodosLosTiposIntervencion(){
        return tipoIntervencionBean.obtenerTodosLosTiposIntervencion();
    }
    @GET
    @Path("/activos")
    public List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionActivos(){
        return tipoIntervencionBean.obtenerTodosLosTipoIntervencionActivos();
    }
    @GET
    @Path("/eliminados")
    public List<TipoIntervencionDTO> obtenerTodosLoTipoIntervencionEliminados(){
        return tipoIntervencionBean.obtenerTodosLoTipoIntervencionEliminados();
    }
    @GET
    @Path("/tipo/{nombre}")
    public List<TipoIntervencionDTO> obtenerTodosLosTipoIntervencionPorTipo(@PathParam("nombre") String inputTipo){
        return tipoIntervencionBean.obtenerTodosLosTipoIntervencionPorTipo(inputTipo);
    }
    @PUT
    @Path("/baja/{id}")
    public void eliminarTipoIntervencion(@PathParam("id") Integer id) throws ServiciosException{
        tipoIntervencionBean.eliminarTipoIntervencion(id);
    }
    @PUT
    @Path("/activar/{id}")
    public void activarTipoIntervencion(@PathParam("id") Integer id) throws ServiciosException{
        tipoIntervencionBean.activarTipoIntervencion(id);
    }
}
