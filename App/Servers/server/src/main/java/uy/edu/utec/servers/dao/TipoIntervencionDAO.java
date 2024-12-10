package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.TipoIntervencion;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class TipoIntervencionDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    public void agregarTipoIntervencion(TipoIntervencion tipoIntervencion) throws ServiciosException {
        try {
            em.persist(tipoIntervencion);
            em.flush();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw new ServiciosException("No se pudo agregar el tipo de intervención");
        }
    }
    //-------------------MÉTODOS LISTAR-------------------

    public TipoIntervencion buscarPorId(Integer id) {
        return em.find(TipoIntervencion.class, id);
    }
    public List<TipoIntervencion> obtenerTodosLosTiposIntervencion() {
        TypedQuery<TipoIntervencion> query = em.createQuery("SELECT ti FROM TipoIntervencion ti", TipoIntervencion.class);
        return query.getResultList();
    }


    public List<TipoIntervencion> obtenerTodosLosTipoIntervencionActivos() {
        TypedQuery<TipoIntervencion> query = em.createQuery(
                "SELECT ti FROM TipoIntervencion ti WHERE ti.estado = :estado",
                TipoIntervencion.class
        );
        query.setParameter("estado", EstadoGeneral.ACTIVO);
        return query.getResultList();
    }
    public List<TipoIntervencion> obtenerTodosLoTipoIntervencionEliminados() {
        TypedQuery<TipoIntervencion> query = em.createQuery(
                "SELECT ti FROM TipoIntervencion ti WHERE ti.estado = :estado",
                TipoIntervencion.class
        );
        query.setParameter("estado", EstadoGeneral.ELIMINADO);
        return query.getResultList();
    }

    public List<TipoIntervencion> obtenerTodosLosTipoIntervencionPorTipo(String inputTipo) {
        TypedQuery<TipoIntervencion> query = em.createQuery(
                "SELECT ti FROM TipoIntervencion ti WHERE UPPER(ti.nomTipo) LIKE UPPER(:nombre)",
                TipoIntervencion.class
        );
        query.setParameter("nombre", "%" + inputTipo + "%");
        return query.getResultList();
    }
    public TipoIntervencion obtenerTodosLosTipoIntervencionPorNombre(String inputTipo) {
        TypedQuery<TipoIntervencion> query = em.createQuery(
                "SELECT ti FROM TipoIntervencion ti WHERE UPPER(ti.nomTipo) LIKE UPPER(:nombre)",
                TipoIntervencion.class
        );
        query.setParameter("nombre", inputTipo);
        return query.getSingleResult();
    }

    //-------------------MÉTODO ELIMINAR-------------------
    public void eliminarTipoIntervencion(Integer id) throws ServiciosException {
        try{
            TipoIntervencion tipoIntervencion = em.find(TipoIntervencion.class, id);
            tipoIntervencion.setEstado(EstadoGeneral.ELIMINADO);
            em.merge(tipoIntervencion);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo eliminar el Tipo de intervención.");
        }
    }
    //-------------------MÉTODO ACTIVAR-------------------
    public void activarTipoIntervencion(Integer id) throws ServiciosException {
        try{
            TipoIntervencion tipoIntervencion = em.find(TipoIntervencion.class, id);
            tipoIntervencion.setEstado(EstadoGeneral.ACTIVO);
            em.merge(tipoIntervencion);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo activar el Tipo de Intervención.");
        }
    }
}




