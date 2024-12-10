package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import uy.edu.utec.servers.beans.local.interfaces.IUsuarioBeanNew;
import uy.edu.utec.servers.dao.InstitucionDAO;
import uy.edu.utec.servers.dao.PerfilDAO;
import uy.edu.utec.servers.dao.TelefonoUsuarioDAO;
import uy.edu.utec.servers.dao.UsuarioDAOnew;
import uy.edu.utec.servers.dtos.LoginDTO;
import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.entidades.PK_Compuestas.TelefonoUsuario_PK;
import uy.edu.utec.servers.entidades.TelefonoUsuario;
import uy.edu.utec.servers.entidades.Usuario;
import uy.edu.utec.servers.enums.EstadoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.UsuarioMapper;

import java.util.List;
import java.util.Objects;
@Stateful
public class UsuarioBeanNew implements IUsuarioBeanNew {
    private static Usuario userLogged;
    @EJB
    private UsuarioDAOnew usuarioDao;
    @EJB
    private TelefonoUsuarioDAO telefonoDAO;
    @EJB
    private InstitucionDAO institucionDAO;
    @EJB
    private PerfilDAO perfilDAO;

    public static Usuario getUserLogged() {
        return userLogged;
    }

    public static void setUserLogged(Usuario userLogged) {
        UsuarioBeanNew.userLogged = userLogged;
    }

    public void agregarUsuario(UsuarioDTO usuario) throws ServiciosException {

        Usuario newUsuario = UsuarioMapper.toEntityCreate(usuario);

        // POR MEDIO DEL ID SE BUSCA PERFIL Y INSTITUCION
        newUsuario.setInstitucion(institucionDAO.buscarPorId(usuario.getInstitucionId()));
        newUsuario.setPerfilUsuario(perfilDAO.buscarPorId(usuario.getPerfilId()));

        // SE PERSISTE EL USUARIO
        newUsuario.setEstado(EstadoUsuario.SINVALIDAR);
        newUsuario.setContrasenia(usuario.getContrasenia());
        usuarioDao.agregarUsuario(newUsuario);
        System.out.println(usuario.getContrasenia());


        //Se crean las entidades telefono para proveedor
            Usuario newUsuarioEntity = usuarioDao.buscarPorEmail(usuario.getEmail());
            TelefonoUsuario telefonoUsuario = new TelefonoUsuario();
            TelefonoUsuario_PK telefonoUsuarioPK = new TelefonoUsuario_PK();
            telefonoUsuarioPK.setUsuario(newUsuarioEntity);
            telefonoUsuarioPK.setTelefono(usuario.getTelefonos());
            telefonoUsuario.setPK_TEL(telefonoUsuarioPK);
            telefonoDAO.agregarTelefono(telefonoUsuario);

    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuario) throws ServiciosException {
        Usuario usuario1 = usuarioDao.buscarPorId(usuario.getID_Usuario());
        usuario1.setNombre(usuario.getNombre());
        usuario1.setNombre2(usuario.getNombre2());
        usuario1.setApellido(usuario.getApellido());
        usuario1.setApellido2(usuario.getApellido2());
        usuario1.setCedula(usuario.getCedula());
        usuario1.setFec_nac(usuario.getFec_nac());
//        usuario1.setTelefono(usuario.getTelefono());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setEstado(usuario.getEstado());
        usuario1.setContrasenia(usuario.getContrasenia());
        usuarioDao.actualizarUsuario(usuario1);

        gestionarTelefonosUsuario(usuario, usuario1);
    }

    @Override
    public UsuarioDTO buscarPorId(Integer id) {
        return UsuarioMapper.toDTO(usuarioDao.buscarPorId(id));
    }

    @Override
    public UsuarioDTO buscarPorEmail(String email) {
        return UsuarioMapper.toDTO(usuarioDao.buscarPorEmail(email));
    }

    @Override
    public List<UsuarioDTO> buscarPorEmails(String email){
        return UsuarioMapper.toDTOList(usuarioDao.buscarPorEmails(email));
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return UsuarioMapper.toDTOList(usuarioDao.obtenerTodosLosUsuarios());
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosActivos() {
        return UsuarioMapper.toDTOList(usuarioDao.obtenerTodosLosUsuariosActivos());
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosEliminados() {
        return UsuarioMapper.toDTOList(usuarioDao.obtenerTodosLosUsuariosEliminados());
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosSinValidar() {
        return UsuarioMapper.toDTOList(usuarioDao.obtenerTodosLosUsuariosSinValidar());
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorNombre1(String inputNombre1) {
        return UsuarioMapper.toDTOList(usuarioDao.obtenerTodosLosUsuariosPorNombre1(inputNombre1));
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorNombre2(String inputNombre2) {
        return UsuarioMapper.toDTOList(usuarioDao.obtenerTodosLosUsuariosPorNombre2(inputNombre2));
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorApellido1(String inputApellido1) {
        return UsuarioMapper.toDTOList(usuarioDao.obtenerTodosLosUsuariosPorApellido1(inputApellido1));
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorApellido2(String inputApellido2) {
        return UsuarioMapper.toDTOList(usuarioDao.obtenerTodosLosUsuariosPorApellido2(inputApellido2));
    }

    @Override
    public List<UsuarioDTO> obtenerPorPerfil(Integer idPerfil) {
        return UsuarioMapper.toDTOList(usuarioDao.buscarPorPerfil(idPerfil));
    }

//    @Override
//    public List<UsuarioDTO> obtenerPorTipoUsuario(Integer usuarioID) {
//        return UsuarioMapper.toDTOList(usuarioDao.obtenerPorTipoUsuario(usuarioID));
//    }

    @Override
    public List<UsuarioDTO> getUsuariosSinValidarYEliminados() {
        return UsuarioMapper.toDTOList(
                usuarioDao.getUsuariosSinValidarYEliminados()
        );
    }

    @Override
    public void bajaUsuarioLogica(Integer id) throws ServiciosException {
        usuarioDao.bajaUsuarioLogica(id);
    }

    @Override
    public void activarUsuario(Integer id) throws ServiciosException {
        usuarioDao.activarUsuario(id);
    }

    @Override
    public UsuarioDTO login(LoginDTO login) {
        Usuario usuario = usuarioDao.buscarPorEmail(login.getEmail());
        if (usuario != null){
            boolean isEmail = Objects.equals(usuario.getEmail(), login.getEmail());
            boolean isPassword = Objects.equals(usuario.getContrasenia(), login.getPassword());

            if (isEmail && isPassword) {
                //Guardamos el usuario que inició sesión en la variable statica
                userLogged = usuario;
                return UsuarioMapper.toDTO(usuario);
            }
            return null;
        }
        return null;
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuariosPorNombreUsuario(String inputNombreUsuario) {
        return UsuarioMapper.toDTOList(usuarioDao.obtenerTodosLosUsuariosPorNombreUsuario(inputNombreUsuario));
    }

    @Override
    public UsuarioDTO buscarPorNombreUsuario(String inputnomUsuario) {
        return UsuarioMapper.toDTO(usuarioDao.buscarPorNombreUsuario(inputnomUsuario));
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorCedula(String cedula) {
        return UsuarioMapper.toDTO(usuarioDao.obtenerUsuarioPorCedula(cedula));
    }

    private void gestionarTelefonosUsuario(UsuarioDTO usuarioDTO, Usuario usuario) throws ServiciosException {
        String telefonosFront = usuarioDTO.getTelefonos();

        TelefonoUsuario telefonosBD = usuario.getTelefonos().get(0);
       // telefonoDAO.eliminarTelefono(telefonosBD.getPK_TEL().getTelefono(), usuario.getIdUsuario());
        // Eliminar teléfonos que están en la BD pero no en la lista del front

           /* if (!telefonosFront.contains(telefonosBD.getPK_TEL().getTelefono())) {

            }*/

        TelefonoUsuario telefonoUsuario = new TelefonoUsuario();
        TelefonoUsuario_PK telefonoUsuarioPK = new TelefonoUsuario_PK();
        telefonoUsuarioPK.setUsuario(usuario);
        telefonoUsuarioPK.setTelefono(usuario.getTelefono());
        telefonoUsuario.setPK_TEL(telefonoUsuarioPK);
        //telefonoDAO.agregarTelefono(telefonoUsuario);

        // Crear y agregar nuevos teléfonos a la entidad de usuario
        /*for (String telefonoFront : telefonosFront) {
            boolean telefonoExistente = telefonosBD.stream().anyMatch(telefono ->
                    telefono.getPK_TEL().getTelefono()
                            .equals(telefonoFront));
            if (!telefonoExistente) {
                TelefonoUsuario telefonoUsuario = new TelefonoUsuario();
                TelefonoUsuario_PK telefonoUsuarioPK = new TelefonoUsuario_PK();
                telefonoUsuarioPK.setUsuario(usuario);
                telefonoUsuarioPK.setTelefono(telefonoFront);
                telefonoUsuario.setPK_TEL(telefonoUsuarioPK);
                telefonoDAO.agregarTelefono(telefonoUsuario);
            }
        }*/
    }
}
