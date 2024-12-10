package uy.edu.utec.servers.dao;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.dao.interfaces.ISeguimientoIntervencionDAO;
import uy.edu.utec.servers.entidades.SeguimientoIntervencion;
import uy.edu.utec.servers.excepciones.ServiciosException;
import java.util.List;

@Stateless
public class SeguimientoIntervencionDAO implements ISeguimientoIntervencionDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    public void altaSeguimiento(SeguimientoIntervencion seguimientoIntervencion) throws ServiciosException {
        try {
            em.persist(seguimientoIntervencion);
            em.flush();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw new ServiciosException("No se pudo agregar el seguimiento." );
        }
    }
    public SeguimientoIntervencion buscarPorId(Integer id) {
        return em.find(SeguimientoIntervencion.class, id);
    }

    @Override
    public List<SeguimientoIntervencion> obtenerTodosLosSeguimientosDeUnaIntervencionPorID(Integer intervencionId) {
        String queryString = "SELECT s FROM SeguimientoIntervencion s " +
                "WHERE s.intervencion.id = :id " +
                "ORDER BY s.fechaHora";
        TypedQuery<SeguimientoIntervencion> query = em.createQuery(queryString, SeguimientoIntervencion.class);
        query.setParameter("id", intervencionId);
        return query.getResultList();
    }

}
