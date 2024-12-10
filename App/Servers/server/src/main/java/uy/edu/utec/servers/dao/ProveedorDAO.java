package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.Proveedor;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class ProveedorDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    public void agregarProveedor(Proveedor proveedor) throws ServiciosException {
        try{
            em.persist(proveedor);
            em.flush();
        }catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw new ServiciosException("No se pudo agregar el proveedor.");
        }
    }

    public void actualizarProveedor(Proveedor proveedor) throws ServiciosException {
        try{
            em.merge(proveedor);
            em.flush();
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo actualizar al Perfil");
        }
    }

    public Proveedor buscarPorId(Integer id) {
        return em.find(Proveedor.class, id);
    }

    public List<Proveedor> obtenerTodosLosProveedores() {
        TypedQuery<Proveedor> query = em.createQuery("SELECT p FROM Proveedor p", Proveedor.class);
        return query.getResultList();
    }
    public List<Proveedor> obtenerTodosLosProveedoresActivos() {
        TypedQuery<Proveedor> query = em.createQuery(
                "SELECT p FROM Proveedor p WHERE p.estado = :estado",
                Proveedor.class
        );
        query.setParameter("estado", EstadoGeneral.ACTIVO);
        return query.getResultList();
    }
    public List<Proveedor> obtenerTodosLosProveedoresEliminados() {
        TypedQuery<Proveedor> query = em.createQuery(
                "SELECT p FROM Proveedor p WHERE p.estado = :estado",
                Proveedor.class
        );
        query.setParameter("estado", EstadoGeneral.ELIMINADO);
        return query.getResultList();
    }

    public Proveedor buscarPorNombre(String nomProveedor) {
        TypedQuery<Proveedor> query = em.createQuery(
                "SELECT p FROM Proveedor p WHERE p.nomProveedor = :nombre",
                Proveedor.class
        );
        query.setParameter("nombre", nomProveedor);
        return query.getSingleResult();
    }
}
