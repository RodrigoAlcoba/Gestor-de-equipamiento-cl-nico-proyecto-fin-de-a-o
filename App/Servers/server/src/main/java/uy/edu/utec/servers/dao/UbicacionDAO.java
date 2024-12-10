package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.entidades.Ubicacion;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class UbicacionDAO {

    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    public void agregarUbicacion(Ubicacion ubicacion) throws ServiciosException {
        try {
            em.persist(ubicacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar la ubicación");
        }
    }

    public void actualizarUbicacion(Ubicacion ubicacion) throws ServiciosException {
        try {
            em.merge(ubicacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServiciosException("No se pudo actualizar la ubicación");
        }
    }

    public void eliminarUbicacion(Integer id) {
        Ubicacion ubicacion = em.find(Ubicacion.class, id);
        if (ubicacion != null) {
            em.remove(ubicacion);
        }
    }

    public Ubicacion buscarPorId(Integer id) {
        return em.find(Ubicacion.class, id);
    }

    public List<Ubicacion> obtenerTodasLasUbicaciones() {
        TypedQuery<Ubicacion> query = em.createQuery("SELECT u FROM Ubicacion u", Ubicacion.class);
        return query.getResultList();
    }

    public Ubicacion buscarUbicacionPorNombre(String inputNombre) {
        TypedQuery<Ubicacion> query = em.createQuery(
                "SELECT u FROM Ubicacion u WHERE upper(u.nombre) like upper(:nombre)",
                Ubicacion.class
        );
        query.setParameter("nombre", inputNombre);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Ubicacion> obtenerTodasLasUbicacionesActivas() {
            TypedQuery<Ubicacion> query = em.createQuery("SELECT u FROM Ubicacion u WHERE NOT EXISTS " +
                            "(SELECT b FROM BajaUsuarioUbicacion b WHERE b.ubicacionBajaUbicacion.idUbicacion = u.idUbicacion)",
                    Ubicacion.class);
            return query.getResultList();
    }
}