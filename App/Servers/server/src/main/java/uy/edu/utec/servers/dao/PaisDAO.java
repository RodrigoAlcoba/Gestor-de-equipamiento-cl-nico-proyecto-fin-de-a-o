package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.Pais;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class PaisDAO {
    @PersistenceContext
    EntityManager em;

    //-------------------MÉTODO CREAR-------------------
    public void agregarPais(Pais pais) throws ServiciosException {
        try{
            em.persist(pais);
            em.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar el pais");
        }
    }
    //-------------------MÉTODO ACTIVAR-------------------
    public void activarPais(Integer id) throws ServiciosException {
        try{
            Pais pais = em.find(Pais.class, id);
            pais.setEstado(EstadoGeneral.ACTIVO);
            em.merge(pais);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo activar el Pais");
        }
    }
    //-------------------MÉTODO ELIMINAR-------------------
    public void bajaPais(Integer id) throws ServiciosException {
        try{
            Pais pais = em.find(Pais.class, id);
            pais.setEstado(EstadoGeneral.ELIMINADO);
            em.merge(pais);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo eliminar el Pais");
        }
    }
    //-------------------MÉTODO BUSCAR-------------------
    public Pais buscarPorId(Integer id) {
        return em.find(Pais.class, id);
    }

    //-------------------MÉTODOS LISTAR-------------------
    public List<Pais> obtenerTodosLosPaises() {
        TypedQuery<Pais> query = em.createQuery("SELECT p FROM Pais p", Pais.class);
        return query.getResultList();
    }
    public List<Pais> obtenerTodosLosPaisActivos() {
        TypedQuery<Pais> query = em.createQuery(
                "SELECT p FROM Pais p WHERE p.estado = :estado",
                Pais.class
        );
        query.setParameter("estado", EstadoGeneral.ACTIVO);
        return query.getResultList();
    }

    public List<Pais> obtenerTodosLosPaisesEliminados() {
        TypedQuery<Pais> query = em.createQuery(
                "SELECT p FROM Pais p WHERE p.estado = :estado",
                Pais.class
        );
        query.setParameter("estado", EstadoGeneral.ELIMINADO);
        return query.getResultList();
    }

    public List<Pais> obtenerTodosLosPaisesPorNombre(String inputNombre) {
        TypedQuery<Pais> query = em.createQuery(
                "SELECT p FROM Pais p WHERE UPPER(p.nombre) LIKE UPPER(:nombre)",
                Pais.class
        );
        query.setParameter("nombre", "%" + inputNombre + "%");
        return query.getResultList();
    }
    public Pais obtenerPaisPorNombre(String inputNombre) {
        TypedQuery<Pais> query = em.createQuery(
                "SELECT p FROM Pais p WHERE UPPER(p.nombre) LIKE UPPER(:nombre)",
                Pais.class
        );
        query.setParameter("nombre", inputNombre);
        return query.getSingleResult();
    }

}
