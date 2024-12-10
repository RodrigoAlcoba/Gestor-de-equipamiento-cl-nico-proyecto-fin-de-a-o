package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.IProveedorBean;
import uy.edu.utec.servers.dtos.ProveedorDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearProveedorDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Path("/proveedor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProveedorResource {
    @EJB
    IProveedorBean proveedorBean;
    @GET
    public List<ProveedorDTO> obtenerTodosLosProveedores() {
        return proveedorBean.obtenerTodosLosProveedores();
    }
    @GET
    @Path("/activos")
    public List<ProveedorDTO> obtenerTodosLosProveedoresActivos() {
        return proveedorBean.obtenerTodosLosProveedoresActivos();
    }
    @GET
    @Path("/eliminados")
    public List<ProveedorDTO> obtenerTodosLosProveedoresEliminados() {
        return proveedorBean.obtenerTodosLosProveedoresEliminados();
    }

    @GET
    @Path("/{id}")
    public ProveedorDTO obtenerProveedorPorId(@PathParam("id") Integer id) {
        return proveedorBean.buscarPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearProveedor(CrearProveedorDTO proveedor) throws ServiciosException {
        proveedorBean.agregarProveedor(proveedor);
    }

    @PUT
    @Path("/baja/{id}")
    public void bajaProveedor(@PathParam("id") Integer id) throws ServiciosException {
        proveedorBean.bajaProveedor(id);
    }

    @PUT
    @Path("/activar/{id}")
    public void activarProveedor(@PathParam("id") Integer id) throws ServiciosException {
        proveedorBean.activarProveedor(id);
    }

}
