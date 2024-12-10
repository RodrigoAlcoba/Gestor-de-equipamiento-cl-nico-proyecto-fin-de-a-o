package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.ISectorBean;
import uy.edu.utec.servers.dtos.SectorDTO;

import java.util.List;

@Path("/sectores")
@Produces(MediaType.APPLICATION_JSON)
public class SectorResourse {
    @EJB
    ISectorBean bean;

    @GET
    @Path("/{id}")
    public SectorDTO buscarPorId(@PathParam("id") Integer id) {
        return bean.buscarPorId(id);
    }
    @GET
    @Path("/todos")
    public List<SectorDTO> obtenerTodosLosSectores() {
        return bean.obtenerTodosLosSectores();
    }
    @GET
    @Path("/buscarpornombre/{nombre}")
    public SectorDTO buscarPorNombre(@PathParam("nombre") String nombre) {
        return bean.buscarPorNombre(nombre);
    }
}
