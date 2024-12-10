package uy.edu.utec.servers.beans.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import uy.edu.utec.servers.beans.local.interfaces.IUsuarioBeanNew;
import uy.edu.utec.servers.beans.remote.interfaces.IUsuarioRemoteNew;
import uy.edu.utec.servers.dtos.LoginDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;
import java.util.Set;
@Stateless
public class UsuarioRemoteNew implements IUsuarioRemoteNew {

    @Inject
    private Validator validator;
    @EJB
    private IUsuarioBeanNew usuarioBean;

    @Override
    public void agregarUsuario(UsuarioDTO usuario) throws ServiciosException {
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuario);
        String errores = "";
        for(ConstraintViolation<UsuarioDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);
        usuarioBean.agregarUsuario(usuario);
    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuario) throws ServiciosException {
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuario);
        String errores = "";
        for(ConstraintViolation<UsuarioDTO> violation : violations){
            System.out.println(violation.getMessage());
            errores+=violation.getMessage() + "\n";
        }
        if(!errores.isEmpty())
            throw new ServiciosException(errores);
        usuarioBean.actualizarUsuario(usuario);
    }

    @Override
    public UsuarioDTO buscarPorId(Integer id) {
        return usuarioBean.buscarPorId(id);
    }

    @Override
    public UsuarioDTO buscarPorEmail(String email) {
        return usuarioBean.buscarPorEmail(email);
    }

    @Override
    public List<UsuarioDTO> buscarPorEmails(String email){
        return usuarioBean.buscarPorEmails(email);
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioBean.obtenerTodosLosUsuarios();
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosActivos() {
        return usuarioBean.obtenerTodosLosUsuariosActivos();
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosEliminados() {
        return usuarioBean.obtenerTodosLosUsuariosEliminados();
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosSinValidar() {
        return usuarioBean.obtenerTodosLosUsuariosSinValidar();
    }

    @Override
    public List<UsuarioDTO> getUsuariosSinValidarYEliminados() {
        return usuarioBean.getUsuariosSinValidarYEliminados();
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorNombre1(String inputNombre1) {
        return usuarioBean.obtenerTodosLosUsuariosPorNombre1(inputNombre1);
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorNombre2(String inputNombre2) {
        return usuarioBean.obtenerTodosLosUsuariosPorNombre2(inputNombre2);
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorApellido1(String inputApellido1) {
        return usuarioBean.obtenerTodosLosUsuariosPorApellido1(inputApellido1);
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorApellido2(String inputApellido2) {
        return usuarioBean.obtenerTodosLosUsuariosPorApellido2(inputApellido2);
    }

    @Override
    public List<UsuarioDTO> obtenerPorPerfil(Integer idPerfil) {
        return usuarioBean.obtenerPorPerfil(idPerfil);
    }

//    @Override
//    public List<UsuarioDTO> obtenerPorTipoUsuario(Integer usuarioID) {
//        return usuarioBean.obtenerPorTipoUsuario(usuarioID);
//    }

    @Override
    public void bajaUsuarioLogica(Integer id) throws ServiciosException {
        usuarioBean.bajaUsuarioLogica(id);
    }

    @Override
    public void activarUsuario(Integer id) throws ServiciosException {
        usuarioBean.activarUsuario(id);
    }

    @Override
    public UsuarioDTO login(LoginDTO login) {
        return usuarioBean.login(login);
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorNombreUsuario(String inputNombreUsuario) {
        return usuarioBean.obtenerTodosLosUsuariosPorNombreUsuario(inputNombreUsuario);
    }

    @Override
    public UsuarioDTO buscarPorNombreUsuario(String inputnomUsuario) {
        return usuarioBean.buscarPorNombreUsuario(inputnomUsuario);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorCedula(String cedula) {
        return usuarioBean.obtenerUsuarioPorCedula(cedula);
    }

}
