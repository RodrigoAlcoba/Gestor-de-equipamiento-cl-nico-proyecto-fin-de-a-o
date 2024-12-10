package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.MarcaDTO;
import uy.edu.utec.servers.entidades.Marca;

import java.util.ArrayList;
import java.util.List;

public class MarcaMapper {

    //-------------------MAPPER LISTADOS-------------------
    public static List<MarcaDTO> toDTOList(List<Marca> marcas) {
        List<MarcaDTO> marcaDTOs = new ArrayList<>();
        for (Marca marca : marcas) {
            MarcaDTO marcaDTO = new MarcaDTO();
            marcaDTO.setIdMarca(marca.getIdMarca());
            marcaDTO.setNombreMarca(marca.getNombreMarca());
            marcaDTO.setEstado(marca.getEstado());
            marcaDTOs.add(marcaDTO);
        }
        return marcaDTOs;
    }
    public static List<Marca> toMarcaList(List<MarcaDTO> marcaDTOs) {
        List<Marca> marcas = new ArrayList<>();
        for (MarcaDTO marcaDTO : marcaDTOs) {
            Marca marca = new Marca();
            marca.setIdMarca(marcaDTO.getIdMarca());
            marca.setNombreMarca(marcaDTO.getNombreMarca());
            marca.setEstado(marcaDTO.getEstado());
            marcas.add(marca);
        }
        return marcas;
    }

    //-------------------MAPPER MARCA-------------------
    public static MarcaDTO toDTO(Marca marca) {
        MarcaDTO marcaDTO = new MarcaDTO();

        marcaDTO.setIdMarca(marca.getIdMarca());
        marcaDTO.setNombreMarca(marca.getNombreMarca());
        marcaDTO.setEstado(marca.getEstado());

        return marcaDTO;
    }
    public static Marca toMarca(MarcaDTO marcaDTO) {
        Marca marca = new Marca();

        marca.setIdMarca(marcaDTO.getIdMarca());
        marca.setNombreMarca(marcaDTO.getNombreMarca());
        marca.setEstado(marcaDTO.getEstado());

        return marca;
    }
}
