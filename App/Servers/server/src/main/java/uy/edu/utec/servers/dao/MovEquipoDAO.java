package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.MovEquipo;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Stateless
public class MovEquipoDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    //-------------------MÉTODO CREAR-------------------
    public void crearMovEquipo(MovEquipo movEquipo) throws ServiciosException {
        try{
            em.persist(movEquipo);
            em.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo crear el Movimiento del Equipo");
        }
    }

    //-------------------MÉTODO ACTUALIZAR-------------------
    public void actualizarMovEquipo(MovEquipo movEquipo) throws ServiciosException {
        try{
            em.merge(movEquipo);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo actualizar el Movimiento del Equipo");
        }
    }

    //-------------------MÉTODO BUSCAR-------------------
    public MovEquipo buscarPorId(Integer id) {
        return em.find(MovEquipo.class, id);
    }

    //-------------------MÉTODO LISTAR-------------------
    public List<MovEquipo> obtenerTodosMovDeEquipo() {
        TypedQuery<MovEquipo> query = em.createQuery(
                "SELECT m FROM MovEquipo m",
                MovEquipo.class
        );
        return query.getResultList();
    }
    public MovEquipo buscarUbicacionActualByEquipoId(Integer equipoId) {
        TypedQuery<MovEquipo> query = em.createQuery(
                "SELECT m FROM MovEquipo m WHERE equipoMovEquipo.id = :id ORDER BY m.fecEntrada DESC",
                MovEquipo.class
        );
        query.setMaxResults(1);
        query.setParameter("id", equipoId);
        List<MovEquipo> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }
    public List<MovEquipo> obtenerTodosMovDeEquipoByEuipoId(Integer equipoId) {
        String queryString = "SELECT m FROM MovEquipo m " +
                "WHERE m.equipoMovEquipo.id = :id ";
        TypedQuery<MovEquipo> query = em.createQuery(queryString, MovEquipo.class);
        query.setParameter("id", equipoId);
        return query.getResultList();
    }

    public List<MovEquipo> obtenerTodosMovDeEquipoPorInstitucion(Integer InstitucionId) {
        TypedQuery<MovEquipo> query = em.createQuery(
                "SELECT m FROM MovEquipo m WHERE m.ubicacionMovEquipo.institucion.idInstitucion =:InstitucionId",
                MovEquipo.class
        ).setParameter("InstitucionId", InstitucionId);
        return query.getResultList();
    }

    public List<MovEquipo> obtenerTodosMovDeEquipoPorSector(Integer idSector) {
        TypedQuery<MovEquipo> query = em.createQuery(
                "SELECT m FROM MovEquipo m WHERE m.ubicacionMovEquipo.sector.idSector=:idSector",
                MovEquipo.class
        ).setParameter("idSector", idSector);
        return query.getResultList();
    }

    public List<MovEquipo> obtenerTodosMovDeEquipoPorUsuario(Integer idUsuario) {
        TypedQuery<MovEquipo> query = em.createQuery(
                "SELECT m FROM MovEquipo m WHERE m.usuarioMovEquipo.idUsuario=:idUsuario",
                MovEquipo.class
        ).setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    public List<MovEquipo> obtenerTodosMovDeEquipoPorFecDesde(LocalDate fecEntrada) {
        TypedQuery<MovEquipo> query = em.createQuery(
                "SELECT m FROM MovEquipo m WHERE m.fecEntrada=:fecEntrada",
                MovEquipo.class
        ).setParameter("fecEntrada", fecEntrada);
        return query.getResultList();
    }

    public List<MovEquipo> obtenerTodosMovDeEquipoPorFechaHasta(LocalDate fecHasta) {
        TypedQuery<MovEquipo> query = em.createQuery(
                "SELECT m FROM MovEquipo m WHERE m.fecSalida=:fecHasta",
                MovEquipo.class
        ).setParameter("fecHasta", fecHasta);
        return query.getResultList();
    }

}
