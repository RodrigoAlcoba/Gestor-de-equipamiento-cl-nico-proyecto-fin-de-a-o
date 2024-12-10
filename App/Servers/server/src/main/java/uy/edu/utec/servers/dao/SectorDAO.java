package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.Sector;

import java.util.List;

@Stateless
public class SectorDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    public Sector buscarPorId(Integer id) {
        return em.find(Sector.class, id);
    }

    public List<Sector> obtenerTodosLosSectores() {
        TypedQuery<Sector> query = em.createQuery(
                "SELECT s FROM Sector s",
                Sector.class
        );
        return query.getResultList();
    }

    public Sector buscarPorNombre(String nombre) {
        TypedQuery<Sector> query = em.createQuery(
                "SELECT s FROM Sector s WHERE UPPER(s.nombreSector) = UPPER(:nombreSec)",
                Sector.class
        );
        query.setParameter("nombreSec",  nombre );
        return query.getSingleResult();
    }
}
