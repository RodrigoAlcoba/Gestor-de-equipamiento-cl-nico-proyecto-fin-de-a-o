package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.IMovEquipoBean;
import uy.edu.utec.servers.dtos.MovEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearMovEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Path("/movequipo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovEquipoResourse {
    @EJB
    IMovEquipoBean bean;

    @GET
    public List<MovEquipoDTO> obtenerTodosLosMovimientosDeEquipo() {
        return bean.obtenerTodosMovDeEquipo();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearMovEquipo(CrearMovEquipoDTO movEquipo) throws ServiciosException {
        bean.crearMovEquipo(movEquipo);
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarMovEquipo(MovEquipoDTO movEquipo) throws ServiciosException {
        bean.actualizarMovEquipo(movEquipo);
    }
    @GET
    @Path("/{id}")
    public MovEquipoDTO buscarMovEquipoPorId(@PathParam("id") Integer id) {
        return bean.buscarPorId(id);
    }
    @GET
    @Path("/movactual/{equipoid}")
    public MovEquipoDTO buscarUbicacionActualByEquipoId(@PathParam("equipoid") Integer equipoId){
        return bean.buscarUbicacionActualByEquipoId(equipoId);
    }
    @GET
    @Path("/equipoid/{equipoid}")
    public List<MovEquipoDTO> obtenerTodosMovDeEquipoByEuipoId(@PathParam("equipoid") Integer equipoIdInterna) {
        return bean.obtenerTodosMovDeEquipoByEuipoId(equipoIdInterna);
    }
}
