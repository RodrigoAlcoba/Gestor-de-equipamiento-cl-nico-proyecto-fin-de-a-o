package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.InstitucionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearInstitucionDTO;
import uy.edu.utec.servers.entidades.Institucion;

import java.util.ArrayList;
import java.util.List;

public class InstitucionMapper {
    public static Institucion toEntity(CrearInstitucionDTO newInstitucion) {
        Institucion institucion = new Institucion();
        institucion.setNom_Institucion(newInstitucion.getNombre());
        return institucion;
    }
    public static InstitucionDTO toDTO(Institucion institucion) {
        InstitucionDTO institucionDTO = new InstitucionDTO();
        institucionDTO.setId(institucion.getIdInstitucion());
        institucionDTO.setNom_Institucion(institucion.getNom_Institucion());
        return institucionDTO;
    }
    public static Institucion toEntity(InstitucionDTO institucionDTO) {
        Institucion institucion = new Institucion();
        institucion.setIdInstitucion(institucionDTO.getId());
        institucion.setNom_Institucion(institucionDTO.getNom_Institucion());
        return institucion;
    }
    public static List<InstitucionDTO> toDTOList(List<Institucion> instituciones) {
        List<InstitucionDTO> institucionDTOS = new ArrayList<>();
        for (Institucion institucion : instituciones) {
            InstitucionDTO institucionDTO = new InstitucionDTO();
            institucionDTO.setId(institucion.getIdInstitucion());
            institucionDTO.setNom_Institucion(institucion.getNom_Institucion());
            institucionDTOS.add(institucionDTO);
        }
        return institucionDTOS;
    }
}
