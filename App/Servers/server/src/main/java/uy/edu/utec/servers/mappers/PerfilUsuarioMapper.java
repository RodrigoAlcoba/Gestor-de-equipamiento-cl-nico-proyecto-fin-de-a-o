package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.PerfilUsuarioDTO;
import uy.edu.utec.servers.entidades.Perfil;

import java.util.ArrayList;
import java.util.List;

public class PerfilUsuarioMapper {
    //-------------------MAPPER LISTADOS-------------------
    public static List<PerfilUsuarioDTO> toDTOList(List<Perfil> perfiles) {
        List<PerfilUsuarioDTO> perfilDTOs = new ArrayList<>();
        for (Perfil perfil : perfiles) {
            PerfilUsuarioDTO perfilDTO = new PerfilUsuarioDTO();
            perfilDTO.setIdPerfil(perfil.getIdPerfil());
            perfilDTO.setNomPerfil(perfil.getNom_Perfil().toUpperCase());
            perfilDTOs.add(perfilDTO);
        }
        return perfilDTOs;
    }
    public static List<Perfil> toEntitylList(List<PerfilUsuarioDTO> perfilDTOs) {
        List<Perfil> perfiles = new ArrayList<>();
        for (PerfilUsuarioDTO perfilDTO : perfilDTOs) {
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(perfilDTO.getIdPerfil());
            perfil.setNom_Perfil(perfilDTO.getNomPerfil().toUpperCase());
            perfiles.add(perfil);
        }
        return perfiles;
    }

    //-------------------MAPPER PERFIL-------------------
    public static PerfilUsuarioDTO toDTO(Perfil perfil) {
        PerfilUsuarioDTO perfilDTO = new PerfilUsuarioDTO();

        perfilDTO.setIdPerfil(perfil.getIdPerfil());
        perfilDTO.setNomPerfil(perfil.getNom_Perfil().toUpperCase());

        return perfilDTO;
    }
    public static Perfil toEntity(PerfilUsuarioDTO perfilDTO) {
        Perfil perfil = new Perfil();

        perfil.setIdPerfil(perfilDTO.getIdPerfil());
        perfil.setNom_Perfil(perfilDTO.getNomPerfil().toUpperCase());

        return perfil;
    }
}
