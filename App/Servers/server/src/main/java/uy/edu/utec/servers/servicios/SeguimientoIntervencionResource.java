package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.ISeguimientoIntervencionBean;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Path("/seguimiento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeguimientoIntervencionResource {
    @EJB
    ISeguimientoIntervencionBean seguimientoIntervencionBean;

    //-------------------POST SEGUIMIENTO -------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void altaSeguimiento(SeguimientoIntervencionDTO seguimientoIntervencion) throws ServiciosException {
        seguimientoIntervencionBean.altaSeguimiento(seguimientoIntervencion);
    }
    //-------------------GET SEGUIMIENTO------------------
    @GET
    @Path("/{id}")
    public SeguimientoIntervencionDTO buscarPorId(@PathParam("id") Integer id){
        return seguimientoIntervencionBean.buscarPorId(id);
    }

    @GET
    @Path("/intervencion/{intervencionId}")
    public List<SeguimientoIntervencionDTO> obtenerTodosLosSeguimientosDeUnaIntervencionPorID(@PathParam("intervencionId") Integer intervencionId){
        return seguimientoIntervencionBean.obtenerTodosLosSeguimientosDeUnaIntervencionPorID(intervencionId);
    }
}
