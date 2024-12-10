package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.interfaces.IModeloBean;
import uy.edu.utec.servers.dtos.ModeloDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Path("/modelos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModeloResource {
    @EJB
    IModeloBean modeloBean;

    //-------------------POST MODELO-------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarModelo(ModeloDTO modelo) throws ServiciosException {
        modeloBean.agregarModelo(modelo);
    }
    //-------------------GET MODELO-------------------
    @GET
    @Path("/{id}")
    public ModeloDTO buscarPorId(@PathParam("id") Integer id){
        System.out.println("Id: rs" + id);
        return modeloBean.buscarPorId(id);
    }

    @GET
    public List<ModeloDTO> obtenerTodosLosModelos(){
        return modeloBean.obtenerTodosLosModelos();
    }
    @GET
    @Path("/activos")
    public List<ModeloDTO> obtenerTodosLosModelosActivos(){
        return modeloBean.obtenerTodosLosModelosActivos();
    }
    @GET
    @Path("/eliminados")
    public List<ModeloDTO> obtenerTodosLosModelosEliminados(){
        return modeloBean.obtenerTodosLosModelosEliminados();
    }
    @GET
    @Path("/nombre/{nombre}")
    public List<ModeloDTO> obtenerTodosLosModelosPorNombre(@PathParam("nombre") String inputNombre){
        return modeloBean.obtenerTodosLosModelosPorNombre(inputNombre);
    }

    //-------------------PUT MODELO-------------------
    @PUT
    @Path("/activar/{id}")
    public void activarModelo(@PathParam("id") Integer id) throws ServiciosException {
        modeloBean.activarModelo(id);
    }
    @PUT
    @Path("/baja/{id}")
    public void bajaLogicaModelo(@PathParam("id") Integer id) throws ServiciosException {
        modeloBean.bajaModelo(id);
    }
}
