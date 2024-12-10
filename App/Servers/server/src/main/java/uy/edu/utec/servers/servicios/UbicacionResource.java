package uy.edu.utec.servers.servicios;


import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.IUbicacionBean;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;
import java.util.Set;

@Path("/ubicacion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UbicacionResource {

    @Inject
    private Validator validator;

    @EJB
    IUbicacionBean ubicacionBean;
    @GET
    public List<UbicacionDTO> obtenerTodasLasUbicaciones() {
        return ubicacionBean.obtenerTodasLasUbicaciones();
    }

    @GET
    @Path("/{id}")
    public UbicacionDTO obtenerUbicacionPorId(@PathParam("id") Integer id) {
        return ubicacionBean.buscarPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearUbicacion(@Valid CrearUbicacionDTO ubicacion) throws ServiciosException {

        /*Set<ConstraintViolation<CrearUbicacionDTO>> violations = validator.validate(ubicacion);
        String errores = "";
        for(ConstraintViolation<CrearUbicacionDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);*/

        ubicacionBean.agregarUbicacion(ubicacion);
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarUbicacion(@Valid UbicacionDTO ubicacion) throws ServiciosException {
        ubicacionBean.actualizarUbicacion(ubicacion);
    }
    @GET
    @Path("/nombre/{nombre}")
    public UbicacionDTO buscarUbicacionPorNombre(@PathParam("nombre") String nombre) {
        return ubicacionBean.buscarUbicacionPorNombre(nombre);
    }
}
