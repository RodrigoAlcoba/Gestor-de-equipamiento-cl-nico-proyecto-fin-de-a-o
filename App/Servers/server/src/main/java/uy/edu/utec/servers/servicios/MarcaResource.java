package uy.edu.utec.servers.servicios;


import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.dtos.MarcaDTO;
import uy.edu.utec.servers.entidades.Marca;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.beans.local.interfaces.IMarcaBean;

import java.util.List;

@Path("/marcas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarcaResource {

    @EJB
    IMarcaBean iMarcaBean;

    // CREAR
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearMarca(MarcaDTO marca) throws ServiciosException {
        iMarcaBean.agregarMarca(marca);
    }

    // BAJA LÓGICA
    @PUT
    @Path("/baja/{id}")
    public void eliminarMarca(@PathParam("id") Integer id) throws ServiciosException{
        iMarcaBean.eliminarMarca(id);
    }

    // MODIFICAR
    @PUT
    @Path("/modificar/{id}")
    public void activarMarca(@PathParam("id") Integer id) throws ServiciosException{
        iMarcaBean.activarMarca(id);
    }

    // LISTAR
    @GET
    public List<MarcaDTO> obtenerTodasLasMarcas() {
        return iMarcaBean.obtenerTodasLasMarcas();
    }

    @GET
    @Path("/activas")
    public List<MarcaDTO> obtenerMarcasActivas() {
        return iMarcaBean.obtenerTodasLasMarcasActivas();
    }

    @GET
    @Path("/eliminadas")
    public List<MarcaDTO> obtenerMarcasEliminadas() {
        return iMarcaBean.obtenerTodasLasMarcasEliminadas();
    }

    @GET
    @Path("/{id}")
    public MarcaDTO obtenerMarcaPorId(@PathParam("id") Integer id) {
        return iMarcaBean.buscarPorId(id);
    }

}
