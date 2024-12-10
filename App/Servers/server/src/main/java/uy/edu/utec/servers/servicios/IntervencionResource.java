package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.IIntervencionBean;
import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.time.LocalDate;
import java.util.List;

@Path("/intervenciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IntervencionResource {
    @EJB
    IIntervencionBean intervencionBean;

    //-------------------POST INTERVENCION-------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearIntervencion(IntervencionDTO intervencionDTO) throws ServiciosException {
        intervencionBean.agregarIntervencion(intervencionDTO);
    }
    //-------------------GET INTERVENCION-------------------
    @GET
    @Path("/{id}")
    public IntervencionDTO buscarPorId(@PathParam("id") Integer id){
        return intervencionBean.buscarPorId(id);
    }
    @GET
    public List<IntervencionDTO> obtenerTodasLasIntervenciones(){
        return intervencionBean.obtenerTodasLasIntervenciones();
    }
    @GET
    @Path("/tipo-fecha")
    public List<IntervencionDTO> obtenerIntervencionesPorTipoYFechas(
            @QueryParam("tipoIntervencionID") Integer tipoIntervencionID,
            @QueryParam("fechaDesde") String fechaDesde,
            @QueryParam("fechaHasta") String fechaHasta){

        LocalDate fechaDesdeLocalDate = LocalDate.parse(fechaDesde);//"2007-12-03"
        LocalDate fechaHastaLocalDate = LocalDate.parse(fechaHasta);//"2007-12-03"

        return intervencionBean.obtenerIntervencionesPorTipoYFechas(
                tipoIntervencionID,
                fechaDesdeLocalDate,
                fechaHastaLocalDate);
    }
    @GET
    @Path("/idEquipo-fecha")
    public List<IntervencionDTO> obtenerIntervencionesPorEquipoID(
            @QueryParam("equipoid") Integer equipoId,
            @QueryParam("fechaDesde") String fechaDesde,
            @QueryParam("fechaHasta") String fechaHasta) {
        LocalDate fechaDesdeLocalDate = LocalDate.parse(fechaDesde);
        LocalDate fechaHastaLocalDate = LocalDate.parse(fechaHasta);

        System.out.println("resourse equipo id "+equipoId);

        return intervencionBean.obtenerIntervencionesPorEquipoID(
                equipoId,
                fechaDesdeLocalDate,
                fechaHastaLocalDate);
    }
}
