package uy.edu.utec.servers.beans.local;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import uy.edu.utec.servers.beans.local.interfaces.IBajaUsuarioUbicacion;
import uy.edu.utec.servers.dao.BajaUsuarioUbicacionDAO;
import uy.edu.utec.servers.dao.UbicacionDAO;
import uy.edu.utec.servers.dao.UsuarioDAOnew;
import uy.edu.utec.servers.dtos.BajaUsuarioUbicacionDTO;
import uy.edu.utec.servers.entidades.*;
import uy.edu.utec.servers.entidades.PK_Compuestas.BajaUsuarioUbicacion_PK;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.mappers.BajaUsuarioUbicacionMapper;
import uy.edu.utec.servers.utils.DateMapper;

@Stateless
public class BajaUsuarioUbicacionBean implements IBajaUsuarioUbicacion {

    @EJB
    BajaUsuarioUbicacionDAO bajaUsuarioUbicacionDAO;

    @EJB
    UsuarioDAOnew usuarioDAO;
    @EJB
    UbicacionDAO ubicacionDAO;

    @Override
    public void usuarioBajaUbicacion(BajaUsuarioUbicacionDTO bajaUbicacionDTO) throws ServiciosException {
        Usuario usuario = usuarioDAO.buscarPorId(bajaUbicacionDTO.getUsuarioId());
        Ubicacion ubicacion = ubicacionDAO.buscarPorId(bajaUbicacionDTO.getUbicacionId());
        BajaUsuarioUbicacion_PK bajaUsuarioUbicacion_pk = new BajaUsuarioUbicacion_PK(usuario, ubicacion,
                DateMapper.convertLocalDateToDate(bajaUbicacionDTO.getFec_baja()));
        BajaUsuarioUbicacion bajaUsuarioUbicacion = new BajaUsuarioUbicacion();
        bajaUsuarioUbicacion.setBajaUsuarioUbicacionPk(bajaUsuarioUbicacion_pk);
        bajaUsuarioUbicacion.setRazon(bajaUbicacionDTO.getRazon());
        bajaUsuarioUbicacion.setComentario(bajaUbicacionDTO.getComentario());
        bajaUsuarioUbicacion.setUbicacionBajaUbicacion(ubicacion);
        bajaUsuarioUbicacion.setUsuarioBajaUbicacion(usuario);
        bajaUsuarioUbicacionDAO.crearBajaUbicacion(bajaUsuarioUbicacion);
    }


}
