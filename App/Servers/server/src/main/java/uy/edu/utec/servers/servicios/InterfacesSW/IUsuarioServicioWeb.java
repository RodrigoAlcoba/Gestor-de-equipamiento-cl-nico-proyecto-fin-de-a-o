package uy.edu.utec.servers.servicios.InterfacesSW;

import jakarta.ws.rs.PathParam;
import uy.edu.utec.servers.entidades.Usuario;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

public interface IUsuarioServicioWeb {
    String test();
    List<Usuario> obtenerTodosLosUsuarios();
    Usuario obtenerUsuarioPorId(@PathParam("id") Integer id);
    void crearUsuario(Usuario usuario) throws ServiciosException;
    void actualizarUsuario(@PathParam("id") Integer id, Usuario usuarioInput) throws ServiciosException;
    void eliminarUsuarioFisic(@PathParam("id") Integer id);
}
