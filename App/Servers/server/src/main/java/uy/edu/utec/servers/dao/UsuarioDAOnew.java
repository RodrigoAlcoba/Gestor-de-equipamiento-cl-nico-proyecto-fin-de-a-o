package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import uy.edu.utec.servers.entidades.Intervencion;
import uy.edu.utec.servers.entidades.Usuario;
import uy.edu.utec.servers.enums.EstadoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.time.LocalDate;
import java.util.List;
@Stateless
public class UsuarioDAOnew {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    //-------------------MÉTODO CREAR-------------------
    public void agregarUsuario(Usuario usuario) throws ServiciosException {
        try{
            em.persist(usuario);
            em.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar al Usuario");
        }
    }

    //-------------------MÉTODO ACTUALIZAR-------------------
    public void actualizarUsuario(Usuario usuario) throws ServiciosException {
        try{
            em.merge(usuario);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo actualizar al Usuario");
        }
    }

    //-------------------MÉTODO BUSCAR-------------------
    public Usuario buscarPorId(Integer id) {
        return em.find(Usuario.class, id);
    }

    public Usuario buscarPorEmail(String inputEmail) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.email like :email",
                Usuario.class
        );
        query.setParameter("email", inputEmail);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> buscarPorEmails(String inputEmail) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.email like :email",
                Usuario.class
        );
        query.setParameter("email", "%" + inputEmail + "%");
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario buscarPorNombreUsuario(String inputnomUsuario) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nom_usuario like :nom_usuario",
                Usuario.class
        );
        query.setParameter("nom_usuario", inputnomUsuario);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    //-------------------MÉTODO LISTAR-------------------
    public List<Usuario> obtenerTodosLosUsuarios() {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u JOIN u.telefonos",
                Usuario.class
        );
        return query.getResultList();
    }
    public List<Usuario> obtenerTodosLosUsuariosActivos() {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.estado = :estado",
                Usuario.class
        );
        query.setParameter("estado", EstadoUsuario.ACTIVO);
        return query.getResultList();
    }

    public List<Usuario> obtenerTodosLosUsuariosEliminados() {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.estado = :estado",
                Usuario.class
        );
        query.setParameter("estado", EstadoUsuario.ELIMINADO);
        return query.getResultList();
    }
    public List<Usuario> obtenerTodosLosUsuariosSinValidar() {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.estado = :estado",
                Usuario.class
        );
        query.setParameter("estado", EstadoUsuario.SINVALIDAR);
        return query.getResultList();
    }

    public List<Usuario> obtenerTodosLosUsuariosPorNombre1(String inputNombre1) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE UPPER(u.nombre) LIKE UPPER(:nombre1)",
                Usuario.class
        );
        query.setParameter("nombre1", "%" + inputNombre1 + "%");
        return query.getResultList();
    }

    public List<Usuario> obtenerTodosLosUsuariosPorNombre2(String inputNombre2) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE UPPER(u.nombre2) LIKE UPPER(:nombre2)",
                Usuario.class
        );
        query.setParameter("nombre2", "%" + inputNombre2 + "%");
        return query.getResultList();
    }

    public List<Usuario> obtenerTodosLosUsuariosPorApellido1(String inputApellido1) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE UPPER(u.apellido) LIKE UPPER(:apellido1)",
                Usuario.class
        );
        query.setParameter("apellido1", "%" + inputApellido1 + "%");
        return query.getResultList();
    }

    public List<Usuario> obtenerTodosLosUsuariosPorApellido2(String inputApellido2) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE UPPER(u.apellido2) LIKE UPPER(:apellido2)",
                Usuario.class
        );
        query.setParameter("apellido2", "%" + inputApellido2 + "%");
        return query.getResultList();
    }
//    public List<Usuario> obtenerPorTipoUsuario(Integer usuarioID){
//
//        TypedQuery<Usuario> query = em.createQuery(
//                "SELECT u FROM Usuario u JOIN u.perfilUsuario",
//                Usuario.class
//        );
//        return query.getResultList();
//    }
    public List<Usuario> getUsuariosSinValidarYEliminados() {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.estado = :estado1 OR u.estado = :estado2",
                Usuario.class
        );
        query.setParameter("estado1", EstadoUsuario.SINVALIDAR);
        query.setParameter("estado2", EstadoUsuario.ELIMINADO);
        return query.getResultList();
    }

    public List<Usuario> obtenerTodosLosUsuariosPorNombreUsuario(String inputNombreUsuario){
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nom_usuario LIKE :nombreUsuario",
                Usuario.class
        );
        query.setParameter("nombreUsuario", "%" + inputNombreUsuario + "%");
        return query.getResultList();
    }

    //-------------------MÉTODO BAJA-------------------
    public void bajaUsuarioLogica(Integer id) throws ServiciosException {
        try{
            Usuario usuario = em.find(Usuario.class, id);
            usuario.setEstado(EstadoUsuario.ELIMINADO);
            em.merge(usuario);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo eliminar el Usuario.");
        }
    }
    //-------------------MÉTODO RE-ACTIVAR-------------------
    public void activarUsuario(Integer id) throws ServiciosException {
        try{
            Usuario usuario = em.find(Usuario.class, id);
            usuario.setEstado(EstadoUsuario.ACTIVO);
            em.merge(usuario);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo activar el Usuario.");
        }
    }

    public List<Usuario> buscarPorPerfil(Integer idPerfil) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE FUNCTION('TO_CHAR',u.perfilUsuario.id ) like :idPerfil",
                Usuario.class
        );
        query.setParameter("idPerfil", "%" + idPerfil + "%");
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario obtenerUsuarioPorCedula(String cedula){
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.cedula LIKE :cedula",
                Usuario.class
        );
        query.setParameter("cedula", cedula );
        return query.getSingleResult();
    }


}
