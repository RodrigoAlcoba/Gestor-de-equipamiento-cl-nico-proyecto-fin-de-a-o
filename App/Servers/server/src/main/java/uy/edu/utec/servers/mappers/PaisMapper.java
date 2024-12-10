package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.PaisDTO;
import uy.edu.utec.servers.entidades.Pais;

import java.util.ArrayList;
import java.util.List;

public class PaisMapper {

    //-------------------MAPPER LISTADOS-------------------
    public static List<PaisDTO> toDTOList(List<Pais> paises) {
        List<PaisDTO> paisDTOs = new ArrayList<>();
        for (Pais pais : paises) {
            PaisDTO paisDTO = new PaisDTO();
            paisDTO.setID_Pais(pais.getIdPais());
            paisDTO.setNombre(pais.getNombre());
            paisDTO.setEstado(pais.getEstado());
            paisDTOs.add(paisDTO);
        }
        return paisDTOs;
    }

    public static List<Pais> toPaisList(List<PaisDTO> paisesDTOs) {
        List<Pais> paises = new ArrayList<>();
        for (PaisDTO paisDTO : paisesDTOs) {
            Pais pais = new Pais();
            pais.setIdPais(paisDTO.getID_Pais());
            pais.setNombre(paisDTO.getNombre());
            pais.setEstado(paisDTO.getEstado());
            paises.add(pais);
        }
        return paises;
    }

    //-------------------MAPPER PAIS-------------------

    public static PaisDTO toDTO(Pais pais) {
        PaisDTO paisDTO = new PaisDTO();
        paisDTO.setID_Pais(pais.getIdPais());
        paisDTO.setNombre(pais.getNombre());
        paisDTO.setEstado(pais.getEstado());
        return paisDTO;
    }

    public static Pais toPais(PaisDTO paisDTO) {
        Pais pais = new Pais();
        pais.setIdPais(paisDTO.getID_Pais());
        pais.setNombre(paisDTO.getNombre());
        pais.setEstado(paisDTO.getEstado());
        return pais;
    }
}
