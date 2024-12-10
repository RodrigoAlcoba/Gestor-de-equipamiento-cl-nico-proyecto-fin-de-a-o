package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.Institucion;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class InstitucionDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;


    public void agregarInstitucion(Institucion institucion) throws ServiciosException {
        try{
            em.persist(institucion);
            em.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar la institucion");
        }
    }



    public void actualizarInstitucion(Institucion institucion) throws ServiciosException {
        try{
            em.merge(institucion);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo actualizar la institución");
        }
    }

    public Institucion buscarPorId(Integer id) {
        return em.find(Institucion.class, id);
    }

    public List<Institucion> obtenerTodasLasInstituciones() {
        TypedQuery<Institucion> query = em.createQuery(
                "SELECT i FROM Institucion i",
                Institucion.class
        );
        return query.getResultList();
    }
    public List<Institucion> obtenerTodasLasInstitucionesActivas() {
        TypedQuery<Institucion> query = em.createQuery(
                "SELECT i FROM Institucion i WHERE i.estado = 'ACTIVO'",
                Institucion.class
        );
        return query.getResultList();
    }
    public List<Institucion> obtenerTodasLasInstitucionesEliminadas() {
        TypedQuery<Institucion> query = em.createQuery(
                "SELECT i FROM Institucion i WHERE i.estado = 'ELIMINADO'",
                Institucion.class
        );
        return query.getResultList();
    }
    public Institucion buscarPorNombre(String nombre) {
        TypedQuery<Institucion> query = em.createQuery(
                "SELECT i FROM Institucion i WHERE UPPER(i.nom_Institucion) = UPPER(:nombreIns)",
                Institucion.class
        );
        query.setParameter("nombreIns",  nombre );
        return query.getSingleResult();
    }
}
