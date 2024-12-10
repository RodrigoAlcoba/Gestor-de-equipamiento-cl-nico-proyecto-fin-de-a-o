package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.Modelo;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class ModeloDAO {
    @PersistenceContext
    EntityManager em;

    //-------------------MÉTODO CREAR-------------------
    public void agregarModelo(Modelo modelo) throws ServiciosException {
        try{
            em.persist(modelo);
            em.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar al Modelo");
        }
    }
    //-------------------MÉTODO ACTIVAR-------------------
    public void activarModelo(Integer id) throws ServiciosException {
        try{
            Modelo modelo = em.find(Modelo.class, id);
            modelo.setEstado(EstadoGeneral.ACTIVO);
            em.merge(modelo);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo activar el Modelo");
        }
    }
    //-------------------MÉTODO ELIMINAR-------------------
    public void bajaModelo(Integer id) throws ServiciosException {
        try{
            Modelo modelo = em.find(Modelo.class, id);
            modelo.setEstado(EstadoGeneral.ELIMINADO);
            em.merge(modelo);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo eliminar el Modelo");
        }
    }
    //-------------------MÉTODO BUSCAR-------------------
    public Modelo buscarPorId(Integer id) {
        System.out.println("Id: dao" + id);
        return em.find(Modelo.class, id);
    }

    //-------------------MÉTODOS LISTAR-------------------
    public List<Modelo> obtenerTodosLosModelos() {
        TypedQuery<Modelo> query = em.createQuery("SELECT m FROM Modelo m", Modelo.class);
        return query.getResultList();
    }
    public List<Modelo> obtenerTodosLosModelosPorNombre(String inputNombre) {
        TypedQuery<Modelo> query = em.createQuery(
                "SELECT m FROM Modelo m WHERE UPPER(m.nombreModelo) LIKE UPPER(:nombre)",
                Modelo.class
        );
        query.setParameter("nombre", "%" + inputNombre + "%");
        return query.getResultList();
    }
    public Modelo obtenerModeloPorNombre(String inputNombre) {
        TypedQuery<Modelo> query = em.createQuery(
                "SELECT m FROM Modelo m WHERE UPPER(m.nombreModelo) LIKE UPPER(:nombre)",
                Modelo.class
        );
        query.setParameter("nombre",inputNombre);
        return query.getSingleResult();
    }
    public List<Modelo> obtenerTodosLosModelosActivos() {
        TypedQuery<Modelo> query = em.createQuery(
                "SELECT m FROM Modelo m WHERE m.estado = :estado",
                Modelo.class
        );
        query.setParameter("estado", EstadoGeneral.ACTIVO);

        return query.getResultList();
    }

    public List<Modelo> obtenerTodosLosModelosEliminados() {
        TypedQuery<Modelo> query = em.createQuery(
                "SELECT m FROM Modelo m WHERE m.estado = :estado",
                Modelo.class
        );
        query.setParameter("estado", EstadoGeneral.ELIMINADO);
        return query.getResultList();
    }
}
