package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dao.UbicacionDAO;
import uy.edu.utec.servers.dao.UsuarioDAOnew;
import uy.edu.utec.servers.dtos.BajaUsuarioUbicacionDTO;
import uy.edu.utec.servers.entidades.BajaUsuarioUbicacion;
import uy.edu.utec.servers.entidades.PK_Compuestas.BajaUsuarioUbicacion_PK;
import uy.edu.utec.servers.entidades.Usuario;
import uy.edu.utec.servers.utils.DateMapper;

import java.util.Date;

public class BajaUsuarioUbicacionMapper {

    public static BajaUsuarioUbicacionDTO toDTO(BajaUsuarioUbicacion bajaUsuarioUbicacion){
        BajaUsuarioUbicacionDTO bajaUsuarioUbicacionDTO = new BajaUsuarioUbicacionDTO();
        bajaUsuarioUbicacionDTO.setUsuarioId(bajaUsuarioUbicacion.getUsuarioBajaUbicacion().getIdUsuario());
        bajaUsuarioUbicacionDTO.setFec_baja(DateMapper.convertDateToLocalDate(bajaUsuarioUbicacion.getBajaUsuarioUbicacionPk().getFec_baja()));
        bajaUsuarioUbicacionDTO.setUbicacionId(bajaUsuarioUbicacion.getUbicacionBajaUbicacion().getIdUbicacion());
        bajaUsuarioUbicacionDTO.setRazon(bajaUsuarioUbicacion.getRazon());
        bajaUsuarioUbicacionDTO.setComentario(bajaUsuarioUbicacion.getComentario());
        return bajaUsuarioUbicacionDTO;
    }


    public static BajaUsuarioUbicacion toEntity(BajaUsuarioUbicacionDTO bajaUsuarioUbicacionDTO){
        UsuarioDAOnew usuarioDAOnew = new UsuarioDAOnew();
        UbicacionDAO ubicacionDAO = new UbicacionDAO();
        BajaUsuarioUbicacion bajaUsuarioUbicacion = new BajaUsuarioUbicacion();
        BajaUsuarioUbicacion_PK bajaUsuarioUbicacionPk =
                new BajaUsuarioUbicacion_PK(usuarioDAOnew.buscarPorId(bajaUsuarioUbicacionDTO.getUsuarioId()),
                ubicacionDAO.buscarPorId(bajaUsuarioUbicacionDTO.getUbicacionId()),
                DateMapper.convertLocalDateToDate(bajaUsuarioUbicacionDTO.getFec_baja()));
        bajaUsuarioUbicacion.setBajaUsuarioUbicacionPk(bajaUsuarioUbicacionPk);
        bajaUsuarioUbicacion.setRazon(bajaUsuarioUbicacionDTO.getRazon());
        bajaUsuarioUbicacion.setComentario(bajaUsuarioUbicacionDTO.getComentario());
        bajaUsuarioUbicacion.setUsuarioBajaUbicacion(usuarioDAOnew.buscarPorId(bajaUsuarioUbicacionDTO.getUsuarioId()));
        bajaUsuarioUbicacion.setUbicacionBajaUbicacion(ubicacionDAO.buscarPorId(bajaUsuarioUbicacionDTO.getUbicacionId()));
        return bajaUsuarioUbicacion;
    }
}
