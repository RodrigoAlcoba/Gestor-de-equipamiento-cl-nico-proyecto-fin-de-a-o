package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.BajaUsuarioEquipo;
import uy.edu.utec.servers.entidades.PK_Compuestas.BajaUsuarioEquipoPK;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class BajaUsuarioEquipoDAO {
    @PersistenceContext(unitName = "oracle")
    EntityManager entityManager;
    public void crearBajaEquipo(BajaUsuarioEquipo bajaEquipo) throws ServiciosException {
        try{
            entityManager.persist(bajaEquipo);
            entityManager.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar la baja del equipo");
        }
    }

    public void actualizarBajaEquipo(BajaUsuarioEquipo bajaEquipo) throws ServiciosException {
        try{
            entityManager.merge(bajaEquipo);
            entityManager.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo actualizar la baja del equipo");
        }
    }

    public BajaUsuarioEquipo buscarPorClaveCompuesta(BajaUsuarioEquipoPK bajaUsuarioEquipoPK) {
        return entityManager.find(BajaUsuarioEquipo.class, bajaUsuarioEquipoPK);
    }

    public List<BajaUsuarioEquipo> obtenertodasLasBajasEquipo() {
        TypedQuery<BajaUsuarioEquipo> query = entityManager.createQuery(
                "SELECT b FROM BajaUsuarioEquipo b",
                BajaUsuarioEquipo.class
        );
        return query.getResultList();
    }

    public BajaUsuarioEquipo buscarBajaPorEquipoId(Integer id) {
        TypedQuery<BajaUsuarioEquipo> query = entityManager.createQuery(
                "SELECT b FROM BajaUsuarioEquipo b WHERE b.usuarioEquipoPK.equipo.id = :id",
                BajaUsuarioEquipo.class
        );
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    public List<BajaUsuarioEquipo> buscarBajasPorUsuarioId(Integer id) {
        TypedQuery<BajaUsuarioEquipo> query = entityManager.createQuery(
                "SELECT b FROM BajaUsuarioEquipo b WHERE b.usuarioEquipoPK.usuario.id = :id",
                BajaUsuarioEquipo.class
        );
        query.setParameter("id", id);
        return query.getResultList();
    }
}
