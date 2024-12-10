package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.IBajaUsuarioEquipoBean;
import uy.edu.utec.servers.dtos.BajaUsuarioEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Path("/bajaequipo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BajaUsuarioEquipoResourse {
    @EJB
    IBajaUsuarioEquipoBean bean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearBajaEquipo(BajaUsuarioEquipoDTO bajaEquipo) throws ServiciosException, SQLException {
        bean.crearBajaEquipo(bajaEquipo);
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarBajaEquipo(BajaUsuarioEquipoDTO bajaEquipo) throws ServiciosException {
        bean.actualizarBajaEquipo(bajaEquipo);
    }
    @GET
    @Path("/clavepk")
    public BajaUsuarioEquipoDTO buscarPorClaveCompuesta(
            @QueryParam("usuarioid") Integer usuarioid,
            @QueryParam("equipoid") Integer equipoid,
            @QueryParam("fechabaja") String fechabaja
    ) throws SQLException {
        LocalDate fechabajaLocalDate = LocalDate.parse(fechabaja);
        return bean.buscarPorClaveCompuesta(usuarioid, equipoid, fechabajaLocalDate);
    }

    @GET
    public List<BajaUsuarioEquipoDTO> obtenertodasLasBajasEquipo() {
        return bean.obtenertodasLasBajasEquipo();
    }
    @GET
    @Path("/porequipoid/{id}")
    public BajaUsuarioEquipoDTO buscarBajaPorEquipoId(@PathParam("id")Integer id) throws SQLException {
        return bean.buscarBajaPorEquipoId(id);
    }
    @GET
    @Path("/porusuarioid/{id}")
    public List<BajaUsuarioEquipoDTO> buscarBajasPorUsuarioId(@PathParam("id")Integer id) {
        return bean.buscarBajasPorUsuarioId(id);
    }
}
