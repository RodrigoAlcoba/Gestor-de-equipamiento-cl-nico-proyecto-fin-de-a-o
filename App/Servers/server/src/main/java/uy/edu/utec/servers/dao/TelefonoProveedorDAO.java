package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.TelefonoProveedor;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class TelefonoProveedorDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    public void agregarTelefono(TelefonoProveedor telefonoProveedor) throws ServiciosException {
        try{
            em.persist(telefonoProveedor);
            em.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar el Telefono");
        }
    }

    public List<TelefonoProveedor> obtenerTodosLosTelefonos(Integer idProveedor) {
        TypedQuery<TelefonoProveedor> query = em.createQuery(
                "SELECT t FROM TelefonoProveedor t WHERE t.PK_TEL_PROVE.proveedor.id = :idProveedor",
                TelefonoProveedor.class
        );
        query.setParameter("idProveedor", idProveedor);
        return query.getResultList();
    }


}
