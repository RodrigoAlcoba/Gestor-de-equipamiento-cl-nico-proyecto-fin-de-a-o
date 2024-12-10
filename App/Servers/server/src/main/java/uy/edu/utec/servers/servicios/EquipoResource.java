package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.IEquipoBean;
import uy.edu.utec.servers.dtos.EquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.sql.SQLException;
import java.util.List;
@Path("/equipos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EquipoResource {
    @EJB
    IEquipoBean equipoBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarEquipo(@Valid CrearEquipoDTO equipo) throws ServiciosException, SQLException {
        equipoBean.agregarEquipo(equipo);
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarEquipo(EquipoDTO equipo) throws ServiciosException, SQLException {
        equipoBean.actualizarEquipo(equipo);
    }
    @GET
    @Path("/{id}")
    public EquipoDTO obtenerEquipoPorId(@PathParam("id") Integer id) throws SQLException {
        return equipoBean.buscarPorId(id);
    }
    @GET
    public List<EquipoDTO> obtenerTodasLosEquipos() throws SQLException {
        return equipoBean.obtenerTodosLosEquipos();
    }
    @GET
    @Path("/activos")
    public List<EquipoDTO> obtenerTodasLosEquiposActivoSinValidar() throws SQLException {
        return equipoBean.buscarTodosLosEquiposActivosSinValidar();
    }
    @PUT()
    @Path("/activar/{id}")
    public void activarEquipo(@PathParam("id") Integer id) throws ServiciosException {
        equipoBean.activarEquipo(id);
    }
    @PUT()
    @Path("/baja/{id}")
    public void bajaEquipo(@PathParam("id") Integer id) throws ServiciosException, SQLException {
        equipoBean.bajaEquipo(id);
    }
    @GET
    @Path("/idinterna/{idinterna}")
    public EquipoDTO buscarEquipoPoridInterna(@PathParam("idinterna") Integer idInterna) throws SQLException {
        return equipoBean.buscarEquipoPoridInterna(idInterna);
    }
}

