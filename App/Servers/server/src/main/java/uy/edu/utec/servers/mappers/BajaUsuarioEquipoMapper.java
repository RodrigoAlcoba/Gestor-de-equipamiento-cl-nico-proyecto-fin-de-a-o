package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.BajaUsuarioEquipoDTO;
import uy.edu.utec.servers.entidades.BajaUsuarioEquipo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BajaUsuarioEquipoMapper {

    public static BajaUsuarioEquipoDTO toDTO(BajaUsuarioEquipo bajaUsuarioEquipo) throws SQLException {
        BajaUsuarioEquipoDTO bajaUsuarioEquipoDTO = new BajaUsuarioEquipoDTO();
        bajaUsuarioEquipoDTO.setEquipoId(bajaUsuarioEquipo.getEquipo().getIdEquipo());
        bajaUsuarioEquipoDTO.setFechaBaja(bajaUsuarioEquipo.getFecBaja());
        bajaUsuarioEquipoDTO.setUsuarioId(bajaUsuarioEquipo.getUsuario().getIdUsuario());
        bajaUsuarioEquipoDTO.setRazon(bajaUsuarioEquipo.getRazon());
        bajaUsuarioEquipoDTO.setComentario(bajaUsuarioEquipo.getComentario());
        return bajaUsuarioEquipoDTO;
    }

    public static List<BajaUsuarioEquipoDTO> toDTOList(List<BajaUsuarioEquipo> bajaUsuarioEquipos) {
        List<BajaUsuarioEquipoDTO> bajaUsuarioEquipoDTOS = new ArrayList<>();
        for (BajaUsuarioEquipo bajaUsuario : bajaUsuarioEquipos) {
            BajaUsuarioEquipoDTO bajaUsuarioEquipoDTO = new BajaUsuarioEquipoDTO();
            bajaUsuarioEquipoDTO.setEquipoId(bajaUsuario.getEquipo().getIdEquipo());
            bajaUsuarioEquipoDTO.setFechaBaja(bajaUsuario.getFecBaja());
            bajaUsuarioEquipoDTO.setUsuarioId(bajaUsuario.getUsuario().getIdUsuario());
            bajaUsuarioEquipoDTO.setRazon(bajaUsuario.getRazon());
            bajaUsuarioEquipoDTO.setComentario(bajaUsuario.getComentario());
            bajaUsuarioEquipoDTOS.add(bajaUsuarioEquipoDTO);
        }
        return bajaUsuarioEquipoDTOS;
    }
}
