package uy.edu.utec.servers.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import uy.edu.utec.servers.entidades.BajaUsuarioEquipo;
import uy.edu.utec.servers.entidades.BajaUsuarioUbicacion;
import uy.edu.utec.servers.excepciones.ServiciosException;

@Stateless
public class BajaUsuarioUbicacionDAO {
    @PersistenceContext(unitName = "oracle")
    EntityManager entityManager;
    public void crearBajaUbicacion(BajaUsuarioUbicacion bajaUbicacion) throws ServiciosException {
        System.out.println(bajaUbicacion.getUsuarioBajaUbicacion());
        System.out.println(bajaUbicacion.getUbicacionBajaUbicacion());
        System.out.println(bajaUbicacion.getComentario());
        System.out.println(bajaUbicacion.getRazon());
        System.out.println(bajaUbicacion.getBajaUsuarioUbicacionPk().getFec_baja());
        System.out.println(bajaUbicacion.getBajaUsuarioUbicacionPk().getUsuarioBajaUbicacion_id());
        System.out.println(bajaUbicacion.getBajaUsuarioUbicacionPk().getUbicacionBajaUbicacion_id());
        try{
            entityManager.persist(bajaUbicacion);
            entityManager.flush();
        }catch (PersistenceException e) {
            throw new ServiciosException("No se pudo agregar la baja del de la ubicacion");
        }
    }
}
