package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.Perfil;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class PerfilDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    //-------------------MÉTODO CREAR-------------------
    public void agregarPerfil(Perfil perfil) throws ServiciosException {
        try{
            em.persist(perfil);
            em.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar al perfil");
        }
    }
    //-------------------MÉTODO ACTIVAR-------------------
    public void activarPerfil(Integer id) throws ServiciosException {
        try{
            Perfil perfil = em.find(Perfil.class, id);
            perfil.setEstado(EstadoGeneral.ACTIVO);
            em.merge(perfil);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo activar el Perfil");
        }
    }
    //-------------------MÉTODO BUSCAR-------------------
    public Perfil buscarPorId(Integer id) {
        return em.find(Perfil.class, id);
    }

    public Perfil obtenerPerfilPorNombre(String inputNombre) {
        TypedQuery<Perfil> query = em.createQuery(
                "SELECT p FROM Perfil p WHERE p.Nom_Perfil LIKE :nombre",
                Perfil.class
        );
        query.setParameter("nombre", "%" + inputNombre + "%");
        return query.getSingleResult();
    }


        //-------------------MÉTODOS LISTAR-------------------
    public List<Perfil> obtenerTodosLosPerfiles() {
        TypedQuery<Perfil> query = em.createQuery("SELECT p FROM Perfil p", Perfil.class);
        return query.getResultList();
    }
    public List<Perfil> obtenerTodosLosPerfilesActivos() {
        TypedQuery<Perfil> query = em.createQuery(
                "SELECT p FROM Perfil p WHERE p.estado = :estado",
                Perfil.class
        );
        query.setParameter("estado", EstadoGeneral.ACTIVO);
        return query.getResultList();
    }

    public List<Perfil> obtenerTodosLosPerfilesEliminados() {
        TypedQuery<Perfil> query = em.createQuery(
                "SELECT p FROM Perfil p WHERE p.estado = :estado",
                Perfil.class
        );
        query.setParameter("estado", EstadoGeneral.ELIMINADO);
        return query.getResultList();
    }

    public List<Perfil> obtenerTodosLosPerfilesPorNombre(String inputNombre) {
        TypedQuery<Perfil> query = em.createQuery(
                "SELECT p FROM Perfil p WHERE UPPER(p.Nom_Perfil) LIKE UPPER(:nombre)",
                Perfil.class
        );
        query.setParameter("nombre", "%" + inputNombre + "%");
        return query.getResultList();
    }

    //-------------------MÉTODO ELIMINAR-------------------
    public void eliminarPerfilLogico(Integer id) throws ServiciosException {
        try{
            Perfil perfil = em.find(Perfil.class, id);
            perfil.setEstado(EstadoGeneral.ELIMINADO);
            em.merge(perfil);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo eliminar el Perfil");
        }
    }


}
