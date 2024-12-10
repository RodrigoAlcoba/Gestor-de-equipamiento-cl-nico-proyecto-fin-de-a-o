package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.TipoIntervencionDTO;
import uy.edu.utec.servers.entidades.TipoIntervencion;

import java.util.ArrayList;
import java.util.List;

public class TipoIntervencionMapper {
    public static TipoIntervencion toEntity(TipoIntervencionDTO tipoIntervencionDTO) {
       TipoIntervencion tipoIntervencion = new TipoIntervencion();
       tipoIntervencion.setIdTipoIntervencion(tipoIntervencionDTO.getIdTipointervencion());
       tipoIntervencion.setNomTipo(tipoIntervencionDTO.getNomTipoIntervencion().toUpperCase());
       tipoIntervencion.setEstado(tipoIntervencionDTO.getEstado());
       return tipoIntervencion;
    }
    public static TipoIntervencionDTO toDTO(TipoIntervencion tipoIntervencion) {
       TipoIntervencionDTO tipoIntervencionDTO = new TipoIntervencionDTO();
       tipoIntervencionDTO.setIdTipointervencion(tipoIntervencion.getIdTipoIntervencion());
       tipoIntervencionDTO.setNomTipoIntervencion(tipoIntervencion.getNomTipo().toUpperCase());
       tipoIntervencionDTO.setEstado(tipoIntervencion.getEstado());
       return tipoIntervencionDTO;
    }

    public static List<TipoIntervencion> toEntityList(List<TipoIntervencionDTO> tipoIntervencionDTOs) {
        List<TipoIntervencion> tipoIntervenciones = new ArrayList<>();
        for (TipoIntervencionDTO tipoIntervencionDTO : tipoIntervencionDTOs) {
            TipoIntervencion tipoIntervencion = new TipoIntervencion();
            tipoIntervencion.setIdTipoIntervencion(tipoIntervencionDTO.getIdTipointervencion());
            tipoIntervencion.setNomTipo(tipoIntervencionDTO.getNomTipoIntervencion().toUpperCase());
            tipoIntervencion.setEstado(tipoIntervencionDTO.getEstado());
            tipoIntervenciones.add(tipoIntervencion);
        }
        return tipoIntervenciones;
    }
    public static List<TipoIntervencionDTO> toDTOList(List<TipoIntervencion> tipoIntervenciones) {
       List<TipoIntervencionDTO> tipoIntervencionDTOs = new ArrayList<>();
        for (TipoIntervencion tipoIntervencion: tipoIntervenciones) {
            TipoIntervencionDTO tipoIntervencionDTO = new TipoIntervencionDTO();
            tipoIntervencionDTO.setIdTipointervencion(tipoIntervencion.getIdTipoIntervencion());
            tipoIntervencionDTO.setNomTipoIntervencion(tipoIntervencion.getNomTipo().toUpperCase());
            tipoIntervencionDTO.setEstado(tipoIntervencion.getEstado());
            tipoIntervencionDTOs.add(tipoIntervencionDTO);
        }
        return tipoIntervencionDTOs;
    }

}
