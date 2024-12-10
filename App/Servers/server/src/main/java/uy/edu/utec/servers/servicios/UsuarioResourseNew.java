package uy.edu.utec.servers.servicios;

import jakarta.ejb.EJB;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import uy.edu.utec.servers.beans.local.UsuarioBeanNew;
import uy.edu.utec.servers.beans.local.interfaces.IUsuarioBeanNew;
import uy.edu.utec.servers.dtos.LoginDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.dtos.creacionales.CreateUsuarioDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.UsuarioMapper;

import java.util.List;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResourseNew {
    @EJB
    IUsuarioBeanNew usuarioBean;
    //-------------------POST USUARIO-------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void crearUsuario(@Valid UsuarioDTO usuario) throws ServiciosException {
        System.out.println(usuario);
        usuarioBean.agregarUsuario(usuario);

    }

    //-------------------GET USUARIO-------------------
    @GET
    @Path("/{id}")
    public UsuarioDTO buscarUsuarioPorId(@PathParam("id") Integer id) {
        return usuarioBean.buscarPorId(id);
    }
    @GET
    @Path("/email/{email}")
    public UsuarioDTO buscarPorEmail(@PathParam("email") String email){
        return usuarioBean.buscarPorEmail(email);
    }

    @GET
    @Path("/emails/{email}")
    public List<UsuarioDTO> buscarPorEmails(@PathParam("email") String email){
        return usuarioBean.buscarPorEmails(email);
    }

    @GET
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioBean.obtenerTodosLosUsuarios();
    }

    @GET
    @Path("/nomUsuarios/{nombreUsuario}")
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorNombreUsuario( @PathParam("nombreUsuario")String inputNombreUsuario) {
        return usuarioBean.obtenerTodosLosUsuariosPorNombreUsuario(inputNombreUsuario);
    }
    @GET
    @Path("/activos")
    public List<UsuarioDTO> obtenerTodosLosUsuariosActivos(){
        return usuarioBean.obtenerTodosLosUsuariosActivos();
    }
    @GET
    @Path("/eliminados")
    public List<UsuarioDTO> obtenerTodosLosUsuariosEliminados(){
        return usuarioBean.obtenerTodosLosUsuariosEliminados();
    }
    @GET
    @Path("/sin-validar")
    public  List<UsuarioDTO> obtenerTodosLosUsuariosSinValidar(){
        return usuarioBean.obtenerTodosLosUsuariosSinValidar();
    }
    @GET
    @Path("/nomUsuario1/{nombre}")
    public  List<UsuarioDTO> obtenerTodosLosUsuariosPorNombre1(@PathParam("nombre") String inputNombre1){
        return usuarioBean.obtenerTodosLosUsuariosPorNombre1(inputNombre1);
    }

    @GET
    @Path("/nomUsuario/{nombre}")
    public  UsuarioDTO obtenerUsuarioPorNombre(@PathParam("nombre") String inputNombre){
        return usuarioBean.buscarPorNombreUsuario(inputNombre);
    }
    @GET
    @Path("/nomUsuario2/{nombre}")
    public  List<UsuarioDTO> obtenerTodosLosUsuariosPorNombre2(@PathParam("nombre")String inputNombre2){
        return usuarioBean.obtenerTodosLosUsuariosPorNombre2(inputNombre2);
    }
    @GET
    @Path("/apeUsuario1/{apellido}")
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorApellido1(@PathParam("apellido")String inputApellido1){
        return usuarioBean.obtenerTodosLosUsuariosPorApellido1(inputApellido1);
    }
    @GET
    @Path("/apeUsuario2/{apellido}")
    public  List<UsuarioDTO> obtenerTodosLosUsuariosPorApellido2(@PathParam("apellido")String inputApellido2){
        return usuarioBean.obtenerTodosLosUsuariosPorApellido2(inputApellido2);
    }
//    @GET
//    @Path("/tipoUsuario/{tipo}")
//    public List<UsuarioDTO> obtenerPorTipoUsuario(@PathParam("tipo") String nomTipo){
//        return usuarioBean.obtenerPorTipoUsuario(nomTipo);
//    }

    @GET
    @Path("/tipoUsuario/{idPerfil}")
    public List<UsuarioDTO> obtenerPorTipoUsuario(@PathParam("idPerfil") Integer idPerfil){
        return usuarioBean.obtenerPorPerfil(idPerfil);
    }
    @GET
    @Path("/sinvalidaryeliminados")
    public List<UsuarioDTO> getUsuariosSinValidarYEliminados() {
        return usuarioBean.getUsuariosSinValidarYEliminados();
    }
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public UsuarioDTO login(LoginDTO login) {
        System.out.println(login.getEmail());
        System.out.println(login.getPassword());
        return usuarioBean.login(login);
    }

    //-------------------PUT USUARIO-------------------
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarUsuario(@Valid UsuarioDTO usuario) throws ServiciosException {
        usuarioBean.actualizarUsuario(usuario);
    }

    @PUT
    @Path("/activar/{id}")
    public void activarUsuario(@PathParam("id") Integer id) throws ServiciosException {
        usuarioBean.activarUsuario(id);
    }
    @PUT
    @Path("/baja/{id}")
    public void bajaLogicaUsuario(@PathParam("id") Integer id) throws ServiciosException {
        usuarioBean.bajaUsuarioLogica(id);
    }

}
