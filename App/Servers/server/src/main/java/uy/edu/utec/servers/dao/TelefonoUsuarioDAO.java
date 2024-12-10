package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.TelefonoUsuario;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;

@Stateless
public class TelefonoUsuarioDAO {
    @PersistenceContext(unitName = "oracle")
    private EntityManager em;

    public void agregarTelefono(TelefonoUsuario telefonoUsuario) throws ServiciosException {
        try{
            em.persist(telefonoUsuario);
            em.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar al Telefono");
        }
    }

    public List<TelefonoUsuario> obtenerTodosLosTelefonos(Integer idUsuario) {
        TypedQuery<TelefonoUsuario> query = em.createQuery(
                "SELECT t FROM TelefonoUsuario t WHERE t.PK_TEL.usuario.id = :idUsuario",
                TelefonoUsuario.class
        );
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }
    public void eliminarTelefono(String telefono, Integer idusuario) {
        TypedQuery<TelefonoUsuario> query = em.createQuery(
                "SELECT t FROM TelefonoUsuario t WHERE t.PK_TEL.telefono = :telefono AND t.usuario.id = :id",
                TelefonoUsuario.class
        );
        query.setParameter("telefono", telefono);
        query.setParameter("id", idusuario);

        TelefonoUsuario telefonoUsuario = query.getSingleResult();

        // Elimina el objeto de la base de datos
        if (telefonoUsuario != null) {
            em.remove(telefonoUsuario);
        }
    }

    public List<String> obtenerTodosLosTelefonosPorUsuarioID(Integer idUsuario) {
        TypedQuery<String> query = em.createQuery(
                "SELECT t.PK_TEL.telefono FROM TelefonoUsuario t WHERE t.PK_TEL.usuario = :idUsuario",
                String.class
        );
        query.setParameter("idUsuario", idUsuario);

        List<String> telefonos = query.getResultList();
        return telefonos;
    }
}
