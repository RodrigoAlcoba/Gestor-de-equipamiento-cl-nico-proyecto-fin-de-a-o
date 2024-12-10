package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.ModeloDTO;
import uy.edu.utec.servers.entidades.Modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloMapper {
    //-------------------MAPPER LISTADOS-------------------
    public static List<ModeloDTO> toDTOList(List<Modelo> modelos) {
        List<ModeloDTO> modeloDTOs = new ArrayList<>();
        for (Modelo modelo : modelos) {
            ModeloDTO modeloDTO = new ModeloDTO();
            modeloDTO.setIdModelo(modelo.getIdModelo());
            modeloDTO.setNombreModelo(modelo.getNombreModelo());
            modeloDTO.setEstado(modelo.getEstado());
            modeloDTOs.add(modeloDTO);
        }
        return modeloDTOs;
    }

    public static List<Modelo> toEntityList(List<ModeloDTO> modeloDTOs) {
        List<Modelo> modelos = new ArrayList<>();
        for (ModeloDTO modeloDTO : modeloDTOs) {
            Modelo modelo = new Modelo();
            modelo.setIdModelo(modeloDTO.getIdModelo());
            modelo.setNombreModelo(modeloDTO.getNombreModelo());
            modelo.setEstado(modeloDTO.getEstado());
            modelos.add(modelo);
        }
        return modelos;
    }

    //-------------------MAPPER PAIS-------------------

    public static ModeloDTO toDTO(Modelo modelo) {
        ModeloDTO modeloDTO = new ModeloDTO();
        modeloDTO.setIdModelo(modelo.getIdModelo());
        modeloDTO.setNombreModelo(modelo.getNombreModelo());
        modeloDTO.setEstado(modelo.getEstado());
        return modeloDTO;
    }

    public static Modelo toEntity(ModeloDTO modeloDTO) {
        Modelo modelo = new Modelo();
        modelo.setIdModelo(modeloDTO.getIdModelo());
        modelo.setNombreModelo(modeloDTO.getNombreModelo());
        modelo.setEstado(modeloDTO.getEstado());
        return modelo;
    }
}
