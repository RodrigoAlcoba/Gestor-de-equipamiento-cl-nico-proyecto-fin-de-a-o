package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.IntervencionDTO;
import uy.edu.utec.servers.entidades.Intervencion;

import java.util.ArrayList;
import java.util.List;


public class IntervencionMapper {
    public static Intervencion toEntity(IntervencionDTO intervencionDTO) {
        Intervencion intervencion = new Intervencion();
        intervencion.setMotivo(intervencionDTO.getMotivo());
        intervencion.setComentario(intervencionDTO.getComentario());
        intervencion.setFechaHora(intervencionDTO.getFechaHora());
        return intervencion;
    }
    public static IntervencionDTO toDTO(Intervencion intervencion) {
        IntervencionDTO intervencionDTO = new IntervencionDTO();
        intervencionDTO.setIdIntervencion(intervencion.getIdIntervencion());
        intervencionDTO.setMotivo(intervencion.getMotivo());
        intervencionDTO.setComentario(intervencion.getComentario());
        intervencionDTO.setEquipoId(intervencion.getEquipo().getIdEquipo());
        intervencionDTO.setUsuarioId(intervencion.getUsuario().getIdUsuario());
        intervencionDTO.setFechaHora(intervencion.getFechaHora());
        intervencionDTO.setTipoIntervencion(TipoIntervencionMapper.toDTO(intervencion.getTipoIntervencion()));
        intervencionDTO.setEquipoId(intervencion.getEquipo().getIdEquipo());
        return intervencionDTO;
    }
    public static List<IntervencionDTO> toDTOList(List<Intervencion> intervenciones) {
        List<IntervencionDTO> intervencionDTOs = new ArrayList<>();
        for (Intervencion intervencion : intervenciones) {
            IntervencionDTO intervencionDTO = new IntervencionDTO();
            intervencionDTO.setIdIntervencion(intervencion.getIdIntervencion());
            intervencionDTO.setMotivo(intervencion.getMotivo());
            intervencionDTO.setComentario(intervencion.getComentario());
            intervencionDTO.setEquipoId(intervencion.getEquipo().getIdEquipo());
            intervencionDTO.setUsuarioId(intervencion.getUsuario().getIdUsuario());
            intervencionDTO.setFechaHora(intervencion.getFechaHora());
            intervencionDTO.setTipoIntervencion(TipoIntervencionMapper.toDTO(intervencion.getTipoIntervencion()));
            intervencionDTOs.add(intervencionDTO);
        }
        return intervencionDTOs;
    }

}
