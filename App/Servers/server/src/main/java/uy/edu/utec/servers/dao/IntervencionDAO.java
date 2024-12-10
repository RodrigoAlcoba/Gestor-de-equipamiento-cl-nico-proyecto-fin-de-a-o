package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.Intervencion;
import uy.edu.utec.servers.entidades.TipoIntervencion;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class IntervencionDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;
    private TipoIntervencionDAO tipoIntervencionDAO;

    public void agregarIntervencion(Intervencion intervencion) throws ServiciosException {
        try{
            em.persist(intervencion);
            em.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar la intervención");
        }
    }
    public Intervencion buscarPorId(Integer id) {
        return em.find(Intervencion.class, id);
    }

    public List<Intervencion> obtenerTodasLasIntervenciones() {
        TypedQuery<Intervencion> query = em.createQuery(
                "SELECT i FROM Intervencion i",
                Intervencion.class);
        return query.getResultList();
    }

    public List<Intervencion> obtenerIntervencionesPorTipoYFechas(
            Integer tipoIntervencionID,
            LocalDate fechaDesde,
            LocalDate fechaHasta) {

        String queryString = "SELECT i FROM Intervencion i " +
                "WHERE i.tipoIntervencion.id = :id " +
                "AND i.fechaHora BETWEEN :fechaDesde AND :fechaHasta";

        TypedQuery<Intervencion> query = em.createQuery(queryString, Intervencion.class);
        query.setParameter("id", tipoIntervencionID);
        query.setParameter("fechaDesde", fechaDesde);
        query.setParameter("fechaHasta", fechaHasta);
        return query.getResultList();
    }
    public List<Intervencion> obtenerIntervencionesPorEquipoID(
            Integer equipoId,
            LocalDate fechaDesde,
            LocalDate fechaHasta) {

        String queryString = "SELECT i FROM Intervencion i " +
                "WHERE i.equipo.id = :id " +
                "AND i.fechaHora BETWEEN :fechaDesde AND :fechaHasta";

        TypedQuery<Intervencion> query = em.createQuery(queryString, Intervencion.class);
        query.setParameter("id", equipoId);
        query.setParameter("fechaDesde", fechaDesde);
        query.setParameter("fechaHasta", fechaHasta);
        return query.getResultList();
    }

    public List<Intervencion> obtenerIntervencionesPorTipo(String tipoIntervencion){
        TypedQuery<Intervencion> query = em.createQuery("SELECT i FROM Intervencion i where i.tipoIntervencion.nomTipo = " +
                        ":tipoIntervencion" ,
                Intervencion.class).setParameter("tipoIntervencion", tipoIntervencion);
        return query.getResultList();
    }
}
