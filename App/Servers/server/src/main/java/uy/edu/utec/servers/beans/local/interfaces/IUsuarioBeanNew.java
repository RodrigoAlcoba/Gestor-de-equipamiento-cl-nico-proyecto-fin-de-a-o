package uy.edu.utec.servers.beans.local.interfaces;

import jakarta.ejb.LocalBean;
import uy.edu.utec.servers.dtos.LoginDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.dtos.creacionales.CreateUsuarioDTO;
import uy.edu.utec.servers.entidades.Usuario;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;
@LocalBean
public interface IUsuarioBeanNew {
    void agregarUsuario(UsuarioDTO usuario) throws ServiciosException;
    void actualizarUsuario(UsuarioDTO usuario) throws ServiciosException;
    UsuarioDTO buscarPorId(Integer id);
    UsuarioDTO buscarPorEmail(String email);
    List<UsuarioDTO> buscarPorEmails(String email);
    List<UsuarioDTO> obtenerTodosLosUsuarios();
    List<UsuarioDTO> obtenerTodosLosUsuariosActivos();
    List<UsuarioDTO> obtenerTodosLosUsuariosEliminados();
    List<UsuarioDTO> obtenerTodosLosUsuariosSinValidar();
    List<UsuarioDTO> obtenerTodosLosUsuariosPorNombre1(String inputNombre1);
    List<UsuarioDTO> obtenerTodosLosUsuariosPorNombre2(String inputNombre2);
    List<UsuarioDTO> obtenerTodosLosUsuariosPorApellido1(String inputApellido1);
    List<UsuarioDTO> obtenerTodosLosUsuariosPorApellido2(String inputApellido2);
    List<UsuarioDTO> obtenerPorPerfil(Integer idPerfil);
    List<UsuarioDTO> getUsuariosSinValidarYEliminados();
    void bajaUsuarioLogica(Integer id) throws ServiciosException;
    void activarUsuario(Integer id) throws ServiciosException;
    UsuarioDTO login(LoginDTO login);

    List<UsuarioDTO> obtenerTodosLosUsuariosPorNombreUsuario(String inputNombreUsuario);
    UsuarioDTO buscarPorNombreUsuario(String inputnomUsuario);
    UsuarioDTO obtenerUsuarioPorCedula(String cedula);
}
