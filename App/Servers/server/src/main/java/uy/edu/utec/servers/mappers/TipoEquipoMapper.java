package uy.edu.utec.servers.mappers;


import uy.edu.utec.servers.dtos.TipoEquipoDTO;
import uy.edu.utec.servers.entidades.TipoEquipo;

import java.util.ArrayList;
import java.util.List;

public class TipoEquipoMapper {
    public static List<TipoEquipoDTO> toDTOList(List<TipoEquipo> tipoEquipos) {
        List<TipoEquipoDTO> tipoEquipoDTOs = new ArrayList<>();
        for (TipoEquipo tipoEquipo : tipoEquipos) {
            TipoEquipoDTO tipoEquipoDTO = new TipoEquipoDTO();
            tipoEquipoDTO.setTipoEquipo(tipoEquipo.getIdTipoEquipo());
            tipoEquipoDTO.setNomTipo(tipoEquipo.getNombre());
            tipoEquipoDTO.setEstado(tipoEquipo.getEstado());
            tipoEquipoDTOs.add(tipoEquipoDTO);
        }
        return tipoEquipoDTOs;
    }

    public static List<TipoEquipo> toEntityList(List<TipoEquipoDTO> tipoEquipoDTOs) {
        List<TipoEquipo> tipoEquipos = new ArrayList<>();
        for (TipoEquipoDTO tipoEquipoDTO : tipoEquipoDTOs) {
            TipoEquipo tipoEquipo = new TipoEquipo();
            tipoEquipo.setIdTipoEquipo(tipoEquipoDTO.getTipoEquipo());
            tipoEquipo.setNombre(tipoEquipoDTO.getNomTipo());
            tipoEquipo.setEstado(tipoEquipoDTO.getEstado());
            tipoEquipos.add(tipoEquipo);
        }
        return tipoEquipos;
    }

    //-------------------MAPPER TIPO EQUIPO-------------------

    public static TipoEquipoDTO toDTO(TipoEquipo tipoEquipo) {
        TipoEquipoDTO tipoEquipoDTO = new TipoEquipoDTO();
        tipoEquipoDTO.setId(tipoEquipo.getIdTipoEquipo());
        tipoEquipoDTO.setNomTipo(tipoEquipo.getNombre());
        tipoEquipoDTO.setEstado(tipoEquipo.getEstado());
        return tipoEquipoDTO;
    }

    public static TipoEquipo toEntity(TipoEquipoDTO tipoEquipoDTO) {
       TipoEquipo tipoEquipo = new TipoEquipo();
       tipoEquipo.setIdTipoEquipo(tipoEquipoDTO.getTipoEquipo());
       tipoEquipo.setNombre(tipoEquipoDTO.getNomTipo());
       tipoEquipo.setEstado(tipoEquipoDTO.getEstado());
       return tipoEquipo;
    }
}
