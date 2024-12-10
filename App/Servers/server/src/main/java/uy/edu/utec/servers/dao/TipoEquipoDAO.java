package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.TipoEquipo;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class TipoEquipoDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;
    //-------------------MÉTODOS AGREGAR-------------------
    public void agregarTipoEquipo(TipoEquipo tipoEquipo) throws ServiciosException{
        try {
            em.persist(tipoEquipo);
            em.flush();
        }catch (PersistenceException e){
            System.out.println(e.getMessage());
            throw new ServiciosException("No se pudo agregar el Tipo de Equipo.");
        }
    }
    //-------------------MÉTODOS LISTAR-------------------
    public TipoEquipo buscarPorNombre(String nombre){
        TypedQuery<TipoEquipo> query= em.createQuery("SELECT ti FROM TipoEquipo ti WHERE ti.nombre = :nombre",
                        TipoEquipo.class)
                .setParameter("nombre", nombre);
        return query.getSingleResult();
    }

    public TipoEquipo buscarPorId(Integer id){
        return em.find(TipoEquipo.class, id);
    }
    public List<TipoEquipo> obtenerTodosLosTiposEquipo() {
        TypedQuery<TipoEquipo> query = em.createQuery("SELECT ti FROM TipoEquipo ti", TipoEquipo.class);
        return query.getResultList();
    }
    public List<TipoEquipo> obtenerTodosLosTipoEquipoActivos() {
        TypedQuery<TipoEquipo> query = em.createQuery(
                "SELECT ti FROM TipoEquipo ti WHERE ti.estado = :estado",
                TipoEquipo.class
        );
        query.setParameter("estado", EstadoGeneral.ACTIVO);
        return query.getResultList();
    }
    public List<TipoEquipo> obtenerTodosLoTipoEquipoEliminados() {
        TypedQuery<TipoEquipo> query = em.createQuery(
                "SELECT ti FROM TipoEquipo ti WHERE ti.estado = :estado",
                TipoEquipo.class
        );
        query.setParameter("estado", EstadoGeneral.ELIMINADO);
        return query.getResultList();
    }
    public List<TipoEquipo> obtenerTodosLosTipoEquipoPorTipo(String inputTipo) {
        TypedQuery<TipoEquipo> query = em.createQuery(
                "SELECT ti FROM TipoEquipo ti WHERE UPPER(ti.nombre) LIKE UPPER(:nombre)",
                TipoEquipo.class
        );
        query.setParameter("nombre", "%" + inputTipo + "%");
        return query.getResultList();
    }
    //-------------------MÉTODO ELIMINAR-------------------
    public void eliminarTipoIntervencion(Integer id) throws ServiciosException {
        try{
            TipoEquipo tipoEquipo = em.find(TipoEquipo.class, id);
            tipoEquipo.setEstado(EstadoGeneral.ELIMINADO);
            em.merge(tipoEquipo);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo eliminar el Tipo de Equipo.");
        }
    }
    //-------------------MÉTODO ACTIVAR-------------------
    public void activarTipoEquipo(Integer id) throws ServiciosException {
        try{
            TipoEquipo tipoEquipo = em.find(TipoEquipo.class, id);
            tipoEquipo.setEstado(EstadoGeneral.ACTIVO);
            em.merge(tipoEquipo);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo activar el Tipo de Equipo.");
        }
    }
}
