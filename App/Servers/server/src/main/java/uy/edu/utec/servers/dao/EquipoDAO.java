package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.dtos.EquipoDTO;
import jakarta.persistence.*;
import uy.edu.utec.servers.entidades.Equipo;
import uy.edu.utec.servers.entidades.Ubicacion;
import uy.edu.utec.servers.entidades.Usuario;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.Base64Utils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Stateless
public class EquipoDAO {

    @PersistenceContext(unitName = "oracle")
    EntityManager entityManager;

    public void agregarEquipo(Equipo equipo) throws ServiciosException{
        try{
            entityManager.persist(equipo);
            entityManager.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar el equipo");
        }
    }

    public void actualizarEquipo(Equipo equipo) throws ServiciosException {
        try{
            //experimento
            String imgBase64 = Base64Utils.blobToBase64(equipo.getImagen());
            equipo.setImagen(Base64Utils.base64ToBlob(imgBase64));
            entityManager.merge(equipo);
            entityManager.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo actualizar al equipo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Equipo buscarPorId(Integer id) {
        return entityManager.find(Equipo.class, id);
    }

    public List<Equipo> obtenerTodosLosEquipos() {
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e",
                Equipo.class
        );
        return query.getResultList();
    }

    public List<Equipo> obtenerTodosLosEquiposActivosSinValidar() {
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e where e.estado = 'ACTIVO' OR e.estado='SINVALIDAR'",
                Equipo.class
        );
        return query.getResultList();
    }


    public Equipo buscarEquipoPoridInternaI(Integer inputIdInterna) {
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT u FROM Equipo u WHERE u.idInterna = :idInterna",
                Equipo.class
        );
        query.setParameter("idInterna", inputIdInterna);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Equipo buscarEquipoPorNombre(String nombre){
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e WHERE e.nomEquipo = :nombre",
                Equipo.class
        ).setParameter("nombre", nombre);
        return query.getSingleResult();
    }

    public List<Equipo> buscarTodosLosEquiposPorNombre(String inputNombre) {
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e WHERE e.nomEquipo like :nom_equipo",
                Equipo.class
        );
        query.setParameter("nom_equipo", "%" +  inputNombre +  "%");
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    };

    public List<Equipo> obtenerEquiposPorTipoEquipo(Integer idTipoEquipo){
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e WHERE e.tipoEquipo.id = :idTipoEquipo1",
                Equipo.class
        ).setParameter("idTipoEquipo1", idTipoEquipo );
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Equipo> obtenerEquiposPorMarca(Integer idMarca){
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e WHERE e.modelo.marca.id = :idMarca",
                Equipo.class
        ).setParameter("idMarca", idMarca );
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Equipo> obtenerEquiposPorModelo(Integer idModelo){
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e WHERE e.modelo.id = :idModelo",
                Equipo.class
        ).setParameter("idModelo", idModelo );
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Equipo> obtenerEquiposPorNumeroSerie(Integer numSerie){
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e WHERE FUNCTION('TO_CHAR', e.numSerie) LIKE :numSerie",
                Equipo.class
        ).setParameter("numSerie", "%"+numSerie+ "%");
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Equipo> obtenerEquiposPorPais(Integer idPais){
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e WHERE e.pais.id = :idPais",
                Equipo.class
        ).setParameter("idPais", idPais);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Equipo> obtenerEquiposPorProveedor(Integer idProveedor){
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e WHERE e.proveedor.id= :idProveedor",
                Equipo.class
        ).setParameter("idProveedor", idProveedor);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Equipo> obtenerEquiposPorFecAdquisicion(LocalDate fecAdquisicion){
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e FROM Equipo e WHERE e.fecAdquisicion= :fecAdquisicion",
                Equipo.class
        ).setParameter("fecAdquisicion", fecAdquisicion);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Equipo> obtenerEquiposPorUbicacion(Integer idUbicacion){
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT e.equipoMovEquipo FROM MovEquipo e WHERE e.ubicacionMovEquipo.id= :idUbicacion AND e.fecSalida is null",
                Equipo.class
        ).setParameter("idUbicacion", idUbicacion);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Equipo> buscarEquiposPoridInterna(Integer inputIdInterna) {
        TypedQuery<Equipo> query = entityManager.createQuery(
                "SELECT u FROM Equipo u WHERE FUNCTION('TO_CHAR',u.idInterna) LIKE :idInterna",
                Equipo.class
        );
        query.setParameter("idInterna", "%"+ inputIdInterna + "%");
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
