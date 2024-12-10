package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.UbicacionDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearUbicacionDTO;
import uy.edu.utec.servers.entidades.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class UbicacionMapper {
    public static Ubicacion toEntity(CrearUbicacionDTO newUbicacion) {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCama(newUbicacion.getCama());
        ubicacion.setNombre(newUbicacion.getNombre());
        ubicacion.setPiso(newUbicacion.getPiso());
        ubicacion.setNumero(newUbicacion.getNumero());
//        ubicacion.setInstitucion(); Se setea desde el bean local
//        ubicacion.setSector(); Se setea desde el bean local
        return ubicacion;
    };

    public static UbicacionDTO toDTO(Ubicacion ubicacion) {
        UbicacionDTO ubicacionDTO = new UbicacionDTO();
        ubicacionDTO.setIdUbicacion(ubicacion.getIdUbicacion());
        ubicacionDTO.setCama(ubicacion.getCama());
        ubicacionDTO.setPiso(ubicacion.getPiso());
        ubicacionDTO.setNombre(ubicacion.getNombre());
        ubicacionDTO.setNumero(ubicacion.getNumero());
        ubicacionDTO.setInstitucionId(ubicacion.getInstitucion().getIdInstitucion());
        ubicacionDTO.setSectorId(ubicacion.getSector().getIdSector());
        return ubicacionDTO;
    };

    public static List<UbicacionDTO> toDTO(List<Ubicacion> ubicaciones) {
        List<UbicacionDTO> ubicacionDTOS = new ArrayList<>();
        for (Ubicacion ubicacion : ubicaciones) {
            ubicacionDTOS.add(new UbicacionDTO(
                    ubicacion.getIdUbicacion(),
                    ubicacion.getNombre(),
                    ubicacion.getNumero(),
                    ubicacion.getPiso(),
                    ubicacion.getCama(),
                    ubicacion.getIdUbicacion(),
                    ubicacion.getIdUbicacion()
            ));
        }
        return ubicacionDTOS;
    }
}
