package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.ITipoEquipoBean;
import uy.edu.utec.servers.dtos.TipoEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Path("/tipoequipo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoEquipoResource {
    @EJB
    ITipoEquipoBean tipoEquipoBean;

    //-------------------POST TIPO EQUIPO-------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarTipoEquipo(TipoEquipoDTO tipoEquipoDTO) throws ServiciosException {
        tipoEquipoBean.agregarTipoEquipo(tipoEquipoDTO);
    }
    //-------------------GET TIPO EQUIPO-------------------
    @GET
    @Path("/{id}")
    public TipoEquipoDTO buscarPorId(@PathParam("id") Integer id){
        return tipoEquipoBean.buscarPorId(id);
    }
    @GET
    public List<TipoEquipoDTO> obtenerTodosLosTiposEquipo(){
        return tipoEquipoBean.obtenerTodosLosTiposEquipo();
    }
    @GET
    @Path("/activos")
    public List<TipoEquipoDTO> obtenerTodosLosTipoEquiponActivos(){
        return tipoEquipoBean.obtenerTodosLosTipoEquipoActivos();
    }
    @GET
    @Path("/eliminados")
    public List<TipoEquipoDTO> obtenerTodosLoTipoEquipoEliminados(){
        return tipoEquipoBean.obtenerTodosLoTipoEquipoEliminados();
    }
    @GET
    @Path("/tipo/{nombre}")
    public List<TipoEquipoDTO> obtenerTodosLosTipoEquipoPorTipo(@PathParam("nombre") String inputTipo){
        return tipoEquipoBean.obtenerTodosLosTipoEquipoPorTipo(inputTipo);
    }
    @PUT
    @Path("/baja/{id}")
    public void eliminarTipoEquipo(@PathParam("id") Integer id) throws ServiciosException{
        tipoEquipoBean.eliminarTipoIntervencion(id);
    }
    @PUT
    @Path("/activar/{id}")
    public void activarTipoEquipo(@PathParam("id") Integer id) throws ServiciosException{
        tipoEquipoBean.activarTipoEquipo(id);
    }
}
