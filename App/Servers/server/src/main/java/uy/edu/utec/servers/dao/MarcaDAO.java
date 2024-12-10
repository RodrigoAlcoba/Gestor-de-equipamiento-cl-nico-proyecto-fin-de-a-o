package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import uy.edu.utec.servers.entidades.Marca;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;

import java.util.List;


@Stateless
public class MarcaDAO {

    @PersistenceContext
    EntityManager entityManager;

    // CREAR

    public void agregarMarca(Marca marca) throws ServiciosException {
        try{
            entityManager.persist(marca);
            entityManager.flush();
        }catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw new ServiciosException("No se pudo agregar la marca");
        }
    }

    // BAJA LÓGICA

    public void eliminarMarca(Integer id) throws ServiciosException { //Se elimina de forma lógica.
        try {
            Marca marca = entityManager.find(Marca.class, id);
            marca.setEstado(EstadoGeneral.ELIMINADO);
            entityManager.merge(marca);
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo eliminar la marca");
        }
    }

    // MODIFICAR

    public void activarMarca(Integer id) throws ServiciosException {
        try{
            Marca marca = entityManager.find(Marca.class, id);
            marca.setEstado(EstadoGeneral.ACTIVO);
            entityManager.merge(marca);
        }catch (PersistenceException e){
            throw new ServiciosException("No se pudo actualizar la marca");
        }
    }

    // LISTADOS (FILTROS)

    public List<Marca> obtenerTodasLasMarcas() {
        TypedQuery<Marca> query = entityManager.createQuery("SELECT e FROM Marca e", Marca.class);
        return query.getResultList();
    }

    public List<Marca> obtenerTodasLasMarcasActivas() {
        TypedQuery<Marca> query = entityManager.createQuery(
                "SELECT m FROM Marca m WHERE m.estado = :estado",
                Marca.class
        );
        query.setParameter("estado", EstadoGeneral.ACTIVO);
        return query.getResultList();
    }

    public List<Marca> obtenerTodasLasMarcasEliminadas() {
        TypedQuery<Marca> query = entityManager.createQuery(
                "SELECT m FROM Marca m WHERE m.estado = :estado",
                Marca.class
        );
        query.setParameter("estado", EstadoGeneral.ELIMINADO);
        return query.getResultList();
    }

    public List<Marca> obtenerTodosLasMarcasPorNombre(String inputNombre) {
        TypedQuery<Marca> query = entityManager.createQuery(
                "SELECT m FROM Marca m WHERE m.nombreMarca LIKE :nombre",
                Marca.class
        );
        query.setParameter("nombre", "%" + inputNombre + "%");
        return query.getResultList();
    }

    public Marca buscarPorId(Integer id) {
        return entityManager.find(Marca.class, id);
    }

    public List<Marca> obtenerTodosLasMarcas() {
        TypedQuery<Marca> query = entityManager.createQuery("SELECT e FROM Marca e", Marca.class);
        return query.getResultList();
    }

    public Marca obtenerMarcaPorNombre(String nombre) {
        TypedQuery<Marca> query = entityManager.createQuery("SELECT e FROM Marca e WHERE e.nombreMarca = :nombre", Marca.class)
                .setParameter("nombre", nombre);
        return query.getSingleResult();
    }

}
