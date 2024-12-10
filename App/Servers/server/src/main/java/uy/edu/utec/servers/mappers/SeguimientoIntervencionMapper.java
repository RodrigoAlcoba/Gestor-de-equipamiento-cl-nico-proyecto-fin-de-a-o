package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.dtos.SeguimientoIntervencionDTO;
import uy.edu.utec.servers.entidades.Intervencion;
import uy.edu.utec.servers.entidades.SeguimientoIntervencion;
import uy.edu.utec.servers.entidades.TipoIntervencion;

import java.util.ArrayList;
import java.util.List;

public class SeguimientoIntervencionMapper {

    public static List<SeguimientoIntervencionDTO> toDTOList(List<SeguimientoIntervencion> seguimientos) {
        List<SeguimientoIntervencionDTO> seguimientoIntervencionDTOS = new ArrayList<>();
        for (SeguimientoIntervencion seguimiento : seguimientos) {
            SeguimientoIntervencionDTO seguimientoIntervencionDTO = new SeguimientoIntervencionDTO();
            seguimientoIntervencionDTO.setIntervencionId(seguimiento.getIntervencion().getIdIntervencion());
            seguimientoIntervencionDTO.setTipoIntervencion(seguimiento.getTipoIntervencion().getNomTipo());
            seguimientoIntervencionDTO.setMotivo(seguimiento.getMotivo());
            seguimientoIntervencionDTO.setObservaciones(seguimiento.getObservaciones());
            seguimientoIntervencionDTOS.add(toDTO(seguimiento));
        }
        return seguimientoIntervencionDTOS;
    }

    public static List<SeguimientoIntervencion> toEntityList(List<SeguimientoIntervencionDTO> dtoList) {
        List<SeguimientoIntervencion> seguimientoIntervencionList = new ArrayList<>();
        for (SeguimientoIntervencionDTO dto : dtoList) {
            SeguimientoIntervencion seguimientoIntervencion = new SeguimientoIntervencion();

//            seguimientoIntervencionList.add(toEntity(dto));
        }
        return seguimientoIntervencionList;
    }

    public static SeguimientoIntervencionDTO toDTO(SeguimientoIntervencion seguimiento) {
        SeguimientoIntervencionDTO seguimientoIntervencionDTO = new SeguimientoIntervencionDTO();
        seguimientoIntervencionDTO.setSeguimientoIntervencionId(seguimiento.getIdSeguimientoIntervencion());
        seguimientoIntervencionDTO.setIntervencionId(seguimiento.getIntervencion().getIdIntervencion());
        seguimientoIntervencionDTO.setTipoIntervencion(seguimiento.getTipoIntervencion().getNomTipo());
        seguimientoIntervencionDTO.setMotivo(seguimiento.getMotivo());
        seguimientoIntervencionDTO.setObservaciones(seguimiento.getObservaciones());
        return seguimientoIntervencionDTO;
    }

    public static SeguimientoIntervencion toEntity(SeguimientoIntervencionDTO seguimientoIntervencionDTO,
                                                   TipoIntervencion tipoIntervencion,
                                                   Intervencion intervencion) {
        SeguimientoIntervencion seguimiento = new SeguimientoIntervencion();
        seguimiento.setIntervencion(intervencion);
        seguimiento.setTipoIntervencion(tipoIntervencion);
        seguimiento.setMotivo(seguimientoIntervencionDTO.getMotivo());
        seguimiento.setObservaciones(seguimientoIntervencionDTO.getObservaciones());
        seguimiento.setFechaHora(seguimientoIntervencionDTO.getFechaHora());
        return seguimiento;
    }
}

