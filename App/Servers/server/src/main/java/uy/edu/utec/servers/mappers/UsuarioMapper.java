package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.UsuarioDTO;
import uy.edu.utec.servers.entidades.PK_Compuestas.TelefonoUsuario_PK;
import uy.edu.utec.servers.entidades.TelefonoUsuario;
import uy.edu.utec.servers.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {
    public static UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setID_Usuario(usuario.getIdUsuario());
        usuarioDTO.setCedula(usuario.getCedula());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setNombre2(usuario.getNombre2());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setApellido2(usuario.getApellido2());
        usuarioDTO.setFec_nac(usuario.getFec_nac());
        usuarioDTO.setNom_usuario(usuario.getNom_usuario());
        usuarioDTO.setEstado(usuario.getEstado());
        usuarioDTO.setPerfilId(usuario.getPerfilUsuario().getIdPerfil());
        usuarioDTO.setInstitucionId(usuario.getInstitucion().getIdInstitucion());
        usuarioDTO.setContrasenia(usuario.getContrasenia());
       // usuarioDTO.setTelefonos(usuario.getTelefonos().toString());

        usuarioDTO.setTelefonos(telefonosToSting(usuario.getTelefonos().get(0)));

        return usuarioDTO;
    }
    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioDTO.getID_Usuario());
        usuario.setCedula(usuarioDTO.getCedula());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setNombre2(usuarioDTO.getNombre2());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setApellido2(usuarioDTO.getApellido2());
        usuario.setFec_nac(usuarioDTO.getFec_nac());
        usuario.setNom_usuario(usuarioDTO.getNom_usuario());
        usuario.setEstado(usuarioDTO.getEstado());
        usuario.setContrasenia(usuarioDTO.getContrasenia());
        List<TelefonoUsuario> arrayTelefonos = new ArrayList<>();
        arrayTelefonos.add(stringToTelefonos(usuarioDTO.getTelefonos()));
        usuario.setTelefonos(arrayTelefonos);
        //Luego de esta transformación hay que agregarle Institución y Perfil
        //En el DTO viene los ID de ambos
        return usuario;
    }
    public static Usuario toEntityCreate(UsuarioDTO newUsuario) {
        Usuario usuario = new Usuario();
        usuario.setCedula(newUsuario.getCedula());
        usuario.setEmail(newUsuario.getEmail());
        usuario.setNombre(newUsuario.getNombre());
        usuario.setNombre2(newUsuario.getNombre2());
        usuario.setApellido(newUsuario.getApellido());
        usuario.setApellido2(newUsuario.getApellido2());
        usuario.setFec_nac(newUsuario.getFec_nac());
        usuario.setNom_usuario(newUsuario.getNom_usuario());
        usuario.setEstado(newUsuario.getEstado());
        usuario.setContrasenia(newUsuario.getContrasenia());
        //Luego de esta transformación hay que agregarle Institución y Perfil
        //En el DTO viene los ID de ambos
        return usuario;
    }

    public static List<Usuario> toEntityList(List<UsuarioDTO> usuarioDTOs) {
        List<Usuario> usuarios = new ArrayList<>();
        for (UsuarioDTO usuarioDTO : usuarioDTOs) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(usuarioDTO.getID_Usuario());
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setNombre2(usuarioDTO.getNombre2());
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setApellido2(usuarioDTO.getApellido2());
            usuario.setApellido2(usuarioDTO.getApellido2());
            usuario.setFec_nac(usuarioDTO.getFec_nac());
            usuario.setNom_usuario(usuarioDTO.getNom_usuario());
            usuario.setEstado(usuarioDTO.getEstado());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setContrasenia(usuarioDTO.getContrasenia());
            List<TelefonoUsuario> arrayTelefonos = new ArrayList<>();
            arrayTelefonos.add(stringToTelefonos(usuarioDTO.getTelefonos()));
            usuario.setTelefonos(arrayTelefonos);
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public static List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setID_Usuario(usuario.getIdUsuario());
            usuarioDTO.setCedula(usuario.getCedula());
            usuarioDTO.setEmail(usuario.getEmail());
            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setNombre2(usuario.getNombre2());
            usuarioDTO.setApellido(usuario.getApellido());
            usuarioDTO.setApellido2(usuario.getApellido2());
            usuarioDTO.setFec_nac(usuario.getFec_nac());
            usuarioDTO.setNom_usuario(usuario.getNom_usuario());
            usuarioDTO.setEstado(usuario.getEstado());
            usuarioDTO.setPerfilId(usuario.getPerfilUsuario().getIdPerfil());
            usuarioDTO.setInstitucionId(usuario.getInstitucion().getIdInstitucion());
            usuarioDTO.setContrasenia(usuario.getContrasenia());

            //usuarioDTO.setTelefono(usuario.getTelefono());

            List<TelefonoUsuario> arrayTelefonos = new ArrayList<>();
            arrayTelefonos.add(stringToTelefonos(usuarioDTO.getTelefonos()));
            usuario.setTelefonos(arrayTelefonos);

            usuarioDTOs.add(usuarioDTO);
        }
        return usuarioDTOs;
    }
    private static List<String> telefonosToStingList(List<TelefonoUsuario> telefonos) {
        List<String> telefonosList = new ArrayList<>();
        for (TelefonoUsuario telefono : telefonos) {
            telefonosList.add(telefono.getPK_TEL().getTelefono());
        }
        return telefonosList;
    }

    private static List<TelefonoUsuario> stringListToTelefonos(List<String> telefonosList) {
        List<TelefonoUsuario> telefonos = new ArrayList<>();
        for (String telefono : telefonosList) {
            TelefonoUsuario telefonoUsuario = new TelefonoUsuario();
            TelefonoUsuario_PK telefonoUsuarioPk = new TelefonoUsuario_PK();
            telefonoUsuario.setPK_TEL(telefonoUsuarioPk);
            telefonos.add(telefonoUsuario);
        }
        return telefonos;
    }

    private static TelefonoUsuario stringToTelefonos(String telefonosList) {
            TelefonoUsuario telefonoUsuario = new TelefonoUsuario();
            TelefonoUsuario_PK telefonoUsuarioPk = new TelefonoUsuario_PK();
            telefonoUsuario.setPK_TEL(telefonoUsuarioPk);
        return telefonoUsuario;
    }


    private static String telefonosToSting(TelefonoUsuario telefonos) {

        return   (telefonos.getPK_TEL().getTelefono());

    }
}
