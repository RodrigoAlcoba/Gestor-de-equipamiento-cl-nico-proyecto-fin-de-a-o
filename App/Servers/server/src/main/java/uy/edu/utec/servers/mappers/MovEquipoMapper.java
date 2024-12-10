package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.MovEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearMovEquipoDTO;
import uy.edu.utec.servers.entidades.MovEquipo;

import java.util.ArrayList;
import java.util.List;

public class MovEquipoMapper {
    public static MovEquipo toEntity(CrearMovEquipoDTO newMovEquipo) {
        MovEquipo movEquipo = new MovEquipo();

        movEquipo.setFecEntrada(newMovEquipo.getFecEntrada());
        movEquipo.setObservaciones(newMovEquipo.getObservaciones());

        //Seteados desde el bean local, se necesita la entidad
        //USUARIO
        //EQUIPO
        //UBICACION

        return movEquipo;
    }
    public static MovEquipoDTO toDTO(MovEquipo movEquipo) {
        return new MovEquipoDTO(
                movEquipo.getIdMovEquipo(),
                movEquipo.getFecEntrada(),
                movEquipo.getFecSalida(),
                movEquipo.getObservaciones(),
                movEquipo.getEquipoMovEquipo().getIdEquipo(),
                movEquipo.getUbicacionMovEquipo().getIdUbicacion(),
                movEquipo.getUsuarioMovEquipo().getIdUsuario()
        );
    }
    public static List<MovEquipoDTO> toDTO(List<MovEquipo> movEquipoList) {
        List<MovEquipoDTO> movEquipoDTOs = new ArrayList<>();
        for (MovEquipo movEquipo : movEquipoList) {
            movEquipoDTOs.add(new MovEquipoDTO(
                    movEquipo.getIdMovEquipo(),
                    movEquipo.getFecEntrada(),
                    movEquipo.getFecSalida(),
                    movEquipo.getObservaciones(),
                    movEquipo.getEquipoMovEquipo().getIdEquipo(),
                    movEquipo.getUbicacionMovEquipo().getIdUbicacion(),
                    movEquipo.getUsuarioMovEquipo().getIdUsuario()
            ));
        }
        return movEquipoDTOs;
    }
}
