package uy.edu.utec.servers.mappers;
import uy.edu.utec.servers.dtos.PerfilDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearPerfilDTO;
import uy.edu.utec.servers.entidades.Perfil;

import java.util.ArrayList;
import java.util.List;

public class PerfilMapper {

    //-------------------MAPPER LISTADOS-------------------
    public static List<PerfilDTO> toDTOList(List<Perfil> perfiles) {
        List<PerfilDTO> perfilDTOs = new ArrayList<>();
        for (Perfil perfil : perfiles) {
            PerfilDTO perfilDTO = new PerfilDTO();
            perfilDTO.setIdPerfil(perfil.getIdPerfil());
            perfilDTO.setNomPerfil(perfil.getNom_Perfil().toUpperCase());
            perfilDTO.setEstado(perfil.getEstado());
            perfilDTOs.add(perfilDTO);
        }
        return perfilDTOs;
    }
    public static List<Perfil> toEntitylList(List<PerfilDTO> perfilDTOs) {
        List<Perfil> perfiles = new ArrayList<>();
        for (PerfilDTO perfilDTO : perfilDTOs) {
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(perfilDTO.getIdPerfil());
            perfil.setNom_Perfil(perfilDTO.getNomPerfil().toUpperCase());
            perfil.setEstado(perfilDTO.getEstado());
            perfiles.add(perfil);
        }
        return perfiles;
    }

    //-------------------MAPPER PERFIL-------------------
    public static PerfilDTO toDTO(Perfil perfil) {
        PerfilDTO perfilDTO = new PerfilDTO();

        perfilDTO.setIdPerfil(perfil.getIdPerfil());
        perfilDTO.setNomPerfil(perfil.getNom_Perfil().toUpperCase());
        perfilDTO.setEstado(perfil.getEstado());

        return perfilDTO;
    }

    public static Perfil toEntity(PerfilDTO perfilDTO) {
        Perfil perfil = new Perfil();

        perfil.setIdPerfil(perfilDTO.getIdPerfil());
        perfil.setNom_Perfil(perfilDTO.getNomPerfil().toUpperCase());
        perfil.setEstado(perfilDTO.getEstado());

        return perfil;
    }

    public static Perfil toEntityCreate(CrearPerfilDTO perfilDTO) {
        Perfil perfil = new Perfil();

        perfil.setNom_Perfil(perfilDTO.getNomPerfil().toUpperCase());

        return perfil;
    }
}
